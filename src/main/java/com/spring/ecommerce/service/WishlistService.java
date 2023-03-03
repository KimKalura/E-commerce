package com.spring.ecommerce.service;

import com.spring.ecommerce.dto.AddToWishlistDTO;
import com.spring.ecommerce.model.*;
import com.spring.ecommerce.repository.ProductRepository;
import com.spring.ecommerce.repository.UserRepository;
import com.spring.ecommerce.repository.WishlistItemRepository;
import com.spring.ecommerce.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class WishlistService {
    private ProductRepository productRepository;
    private UserRepository userRepository;
    private WishlistRepository wishlistRepository;
    private WishlistItemRepository wishlistItemRepository;


    @Autowired
    public WishlistService(ProductRepository productRepository, UserRepository userRepository, WishlistRepository wishlistRepository, WishlistItemRepository wishlistItemRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.wishlistRepository = wishlistRepository;
        this.wishlistItemRepository = wishlistItemRepository;
    }


    public Wishlist addItemToWishlist(AddToWishlistDTO addToWishlistDTO) {
        Product foundProduct = productRepository.findById(addToWishlistDTO.getProductId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found"));
        User foundUser = userRepository.findById(addToWishlistDTO.getUserId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
        Wishlist foundWishlist = foundUser.getWishlist();
        foundWishlist.setUser(foundUser);

        WishlistItem wishlistItem = new WishlistItem();
        wishlistItem.setProduct(foundProduct);

        wishlistItem.setWishlist(foundWishlist);
        foundWishlist.getWishlistItems().add(wishlistItem);
        return wishlistRepository.save(foundWishlist);
    }

    public List<WishlistItem> getAllItemsByUser(Long userId) {
        User foundUser = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "the user doesn't exist"));
        return wishlistItemRepository.findAllByWishlist_User(foundUser);
    }

    public void deleteProductFromUserWishlist(AddToWishlistDTO addToWishlistDTO) {
        Product foundProduct = productRepository.findById(addToWishlistDTO.getProductId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found"));
        User foundUser = userRepository.findById(addToWishlistDTO.getUserId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
        WishlistItem wishlistItemToDelete = wishlistItemRepository.findWishlistItemByWishlist_UserAndProduct(foundUser, foundProduct).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "whishlist item not found"));
        wishlistItemRepository.delete(wishlistItemToDelete);

        //foundUser.getWishlist().getWishlistItems().remove(wishlistItemToDelete);
        //  wishlistRepository.save(foundUser.getWishlist());
    }

}
