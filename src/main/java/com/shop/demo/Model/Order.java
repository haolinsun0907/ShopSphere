package com.shop.demo.Model;

public class Order {
//    - OrderId (Primary Key, Auto-increment)
//- UserId (Foreign Key)
//- TotalPrice
//- Status (e.g., Pending, Completed, Cancelled)
//- OrderDate
//- DeliveryAddress
private String orderId;

private String userId;
private Float orderTotalPrice;
private String orderType;
private  String orderDeliveryAddress;
private  String orderCreateAt;
private  String orderUpdateAt;

    public Order(String orderId, String userId, Float orderTotalPrice, String orderType, String orderDeliveryAddress, String orderCreateAt, String orderUpdateAt) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderTotalPrice = orderTotalPrice;
        this.orderType = orderType;
        this.orderDeliveryAddress = orderDeliveryAddress;
        this.orderCreateAt = orderCreateAt;
        this.orderUpdateAt = orderUpdateAt;
    }

    public Order() {
    }
}
