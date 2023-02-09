package com.spring.ecommerce.service;

import com.spring.ecommerce.model.CartItem;
import com.spring.ecommerce.model.Order;
import com.spring.ecommerce.model.OrderItem;
import com.spring.ecommerce.model.User;
import com.spring.ecommerce.repository.CartItemRepository;
import com.spring.ecommerce.repository.OrderRepository;
import com.spring.ecommerce.repository.UserRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderService {

    private OrderRepository orderRepository;
    private UserRepository userRepository;

    private CartItemService cartItemService;
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserRepository userRepository, CartItemService cartItemService) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.cartItemService = cartItemService;
    }


    public Order placeOrder() {//(Long userId)
        //1.cautam utilizatorul dupa Id
        //2.luam toate cartItem-urile de la utilizator
        //4.cream cate un OrderItem pt fiecare CartItem si il salvam in DB
        //5.salvam order-ul in DB
        //6.stergem toate cartItem-rile  utilizatorului din DB

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User foundUser = userRepository.findUserByUsername(userDetails.getUsername()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
        List<CartItem> userCartItems = cartItemRepository.findAllByUser(foundUser);
        Order newOrder = new Order();
        newOrder.setCreatedDate(new Date());
        newOrder.setUser(foundUser);
        newOrder.setTotalPrice(cartItemService.computeTotalPrice(userCartItems));

        for (CartItem cartItem : userCartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setOrder(newOrder);
            newOrder.getOrderItems().add(orderItem);
        }
        Order savedOrder = orderRepository.save(newOrder);
        /*List<CartItem> userCartItems2 = cartItemRepository.findAllByUser(foundUser);
        userCartItems2.forEach(uci -> cartItemRepository.delete(uci));*/

        cartItemService.deleteAllUserCartItems(foundUser.getId()); // se inlocuieste cu (userId) daca se apeleaza met deleteAllUserCartItems - test
        return savedOrder;
    }

    public List<Order> getAllOrdersByUser(Long userId) {
        User foundUser = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
        return orderRepository.findAllByUserOrderByCreatedDateDesc(foundUser);
    }

    public Order getOrderDetails(Long orderId) {
        Order foundOrder = orderRepository.findById(orderId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "order not found"));
        return foundOrder;
    }
}
