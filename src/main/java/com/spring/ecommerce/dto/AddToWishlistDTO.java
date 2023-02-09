package com.spring.ecommerce.dto;

public class AddToWishlistDTO {

    private Long productId;
    private Long userId;


    public AddToWishlistDTO(Long productId, Long userId) {
        this.productId = productId;
        this.userId = userId;

    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
