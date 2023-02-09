package com.spring.ecommerce.controller;

import com.spring.ecommerce.dto.AddToCartDTO;
import com.spring.ecommerce.dto.UserCartDTO;
import com.spring.ecommerce.model.CartItem;
import com.spring.ecommerce.model.Product;
import com.spring.ecommerce.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartItemController {

    private CartItemService cartItemService;

    @Autowired
    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }


    @PostMapping("/add")
    public CartItem addToCart(@RequestBody AddToCartDTO addToCartDTO) {
        return cartItemService.addToCart(addToCartDTO);
    }


    @GetMapping("/{userId}")
//    public List<CartItem> viewAllProductsFromUserCart(@PathVariable Long userId) {
//        return cartItemService.viewAllProductsFromUserCart(userId);
//    }
    public UserCartDTO viewAllProductsFromUserCart(@PathVariable Long userId) {
        return cartItemService.viewAllProductsFromUserCart(userId);
    }


    @DeleteMapping("/delete/{cartItemId}")
    public void deleteCartItemFromUserCart(@PathVariable Long cartItemId) {
        cartItemService.deleteCartItemFromUserCart(cartItemId);
    }

    //*test delete
    @DeleteMapping("/delete/user/{userId}")
    public void deleteAllUserCartItems(@PathVariable Long userId) {
        cartItemService.deleteAllUserCartItems(userId);
    }

}
