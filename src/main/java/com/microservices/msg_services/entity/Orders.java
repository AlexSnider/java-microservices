package com.microservices.msg_services.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Document(collection = "orders")
public class Orders {

    @Id
    private UUID id;
    private String customer;
    private List<Item> items;
    private double total_price;
    private Date order_date;

    public Orders(UUID id, String customer, List<Item> items, double total_price, Date order_date) {
        this.id = id;
        this.customer = customer;
        this.items = items;
        this.total_price = total_price;
        this.order_date = order_date;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public static class Item {
        private String type;
        private String size;
        private int quantity;

        public Item(String type, String size, int quantity) {
            this.type = type;
            this.size = size;
            this.quantity = quantity;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}
