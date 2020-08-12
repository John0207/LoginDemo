package com.zybooks.logindemo;

public class Item {
    public String itemName;
    public String itemQuantity;

    public Item(){
        this("Unknown", "0");

    }

    public Item(String itemName, String itemQuantity){
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(String itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

//    public void incrementItemQuantity(){
//        itemQuantity++;
//    }
//
//    public void decrementItemQuantity(){
//        itemQuantity--;
//    }
}
