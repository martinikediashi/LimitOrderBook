package com.order;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        OrderBook orderBook = new OrderBook();

        Order order1 = new Order(1, 100.0, 'B', 10);
        Order order2 = new Order(2, 99.0, 'B', 20);
        Order order3 = new Order(3, 100.0, 'O', 30);
        Order order4 = new Order(4, 101.0, 'O', 40);
        Order order5 = new Order(5, 97.0, 'B', 50);
        Order order6 = new Order(6, 14.0, 'O', 60);

        orderBook.addOrder(order1);
        orderBook.addOrder(order2);
        orderBook.addOrder(order3);
        orderBook.addOrder(order4);
        orderBook.addOrder(order5);
        orderBook.addOrder(order6);

        List<Order> bidList = orderBook.getOrders('B');
        bidList.stream().map(e -> " "+e.getId()+" "+e.getPrice()+" "+e.getSide()+" "+e.getSize()).forEach(System.out::println);

        List<Order> offerList = orderBook.getOrders('O');
        offerList.stream().map(e -> " "+e.getId()+" "+e.getPrice()+" "+e.getSide()+" "+e.getSize()).forEach(System.out::println);

    }
}