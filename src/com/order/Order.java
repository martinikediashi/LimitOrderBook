package com.order;

public class Order {

    private long id; // id of order
    private double price ;
    private char side; // B "Bid " or O " Offer"
    private long size ;

    public Order (long id, double price, char side, long size) {
        this.id=id;
        this.price=price;
        this.size=size;
        this.side=side;
    }

    public long getId() { return id; }
    public double getPrice() { return price; }
    public long getSize() { return size; }
    public char getSide ( ) { return side; }

    //Suggestion, having setters would make it easier to modify existing modifications
    /*public void setId(long id) {
        this.id = id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSide(char side) {
        this.side = side;
    }

    public void setSize(long size) {
        this.size = size;
    }*/

}