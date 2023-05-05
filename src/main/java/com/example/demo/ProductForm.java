package com.example.demo;

public class ProductForm {
    private String name;
    private Integer idOfShoppingList;
    private Integer amount;

    public String getProductname() {
        return name;
    }

    public Integer getProductListId() {
        return idOfShoppingList;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIdOfShoppingList(Integer idOfShoppingList) {
        this.idOfShoppingList = idOfShoppingList;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
