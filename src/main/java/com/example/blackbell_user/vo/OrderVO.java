package com.example.blackbell_user.vo;

import lombok.Getter;

import java.util.Date;

public class OrderVO {

    @Getter
    public static class GetResponseVO {
        private String productId;

        private Integer quantity;

        private Integer unitPrice;

        private Integer totalPrice;

        private Date createdAt;

        private String orderId;
    }
}
