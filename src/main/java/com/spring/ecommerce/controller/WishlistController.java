package com.spring.ecommerce.controller;


import com.spring.ecommerce.dto.AddToWishlistDTO;
import com.spring.ecommerce.model.Wishlist;
import com.spring.ecommerce.model.WishlistItem;
import com.spring.ecommerce.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    private WishlistService wishlistService;

    @Autowired
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }



    @PostMapping("/add")
    public Wishlist addItemToWishlist(@RequestBody AddToWishlistDTO addToWishlistDTO){
        return wishlistService.addItemToWishlist(addToWishlistDTO);
    }

    @GetMapping("/{userId}")
    public List<WishlistItem> getAllItemsByUser(@PathVariable Long userId) {
        return wishlistService.getAllItemsByUser(userId);
    }

    @DeleteMapping("/delete")
    public void deleteProductFromUserWishlist(@PathVariable AddToWishlistDTO addToWishlistDTO) {
        wishlistService.deleteProductFromUserWishlist(addToWishlistDTO);
    }
}
