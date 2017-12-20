package com.easybuy.utils;

import com.easybuy.entity.Product;

public class ShoppingCart {
    private Product product;
    //商品的数量
    private  Integer quantity;
    //商品的价格
    private float cost;

    public ShoppingCart(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
}
