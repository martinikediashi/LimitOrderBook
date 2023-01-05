package com.testing;

import com.order.Order;
import com.order.OrderBook;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class OrderBookTest {

    @Test
    public void testAddOrder() {
        OrderBook orderBook = new OrderBook();

        Order order1 = new Order(1, 100.0, 'B', 10);
        Order order2 = new Order(2, 99.5, 'B', 20);
        Order order3 = new Order(3, 100.0, 'O', 30);
        Order order4 = new Order(4, 101.0, 'O', 40);
        Order order5 = new Order(5, 97.0, 'B', 50);
        Order order6 = new Order(6, 14.0, 'O', 60);
        Order order7 = new Order(1, 12.0, 'B', 60);

        orderBook.addOrder(order1);
        orderBook.addOrder(order2);
        orderBook.addOrder(order3);
        orderBook.addOrder(order4);
        orderBook.addOrder(order5);
        orderBook.addOrder(order6);
        orderBook.addOrder(order7);

        assertEquals(100.0, orderBook.getPrice('B', 1), 0.01);
        assertEquals(99.5, orderBook.getPrice('B', 2), 0.01);
        assertEquals(97.0, orderBook.getPrice('B', 3), 0.01);
        assertEquals(12.0, orderBook.getPrice('B', 4), 0.01);
        assertEquals(14.0, orderBook.getPrice('O', 1), 0.01);
        assertEquals(100.0, orderBook.getPrice('O', 2), 0.01);
        assertEquals(101.0, orderBook.getPrice('O', 3), 0.01);
    }

    @Test
    public void testRemoveOrder() {
        OrderBook orderBook = new OrderBook();

        Order order1 = new Order(1, 100.0, 'B', 10);
        Order order2 = new Order(2, 99.0, 'B', 20);
        Order order3 = new Order(3, 100.0, 'O', 30);
        Order order4 = new Order(4, 101.0, 'O', 40);

        orderBook.addOrder(order1);
        orderBook.addOrder(order2);
        orderBook.addOrder(order3);
        orderBook.addOrder(order4);

        orderBook.removeOrder(2);

        assertEquals(100.0, orderBook.getPrice('B', 1), 0.01);
        assertEquals(10.0, orderBook.getSize('B', 1), 0.01);
        assertEquals(Double.NaN, orderBook.getPrice('B', 2), 0.01);
        assertEquals(30.0, orderBook.getSize('O', 1), 0.01);
        assertEquals(40.0, orderBook.getSize('O', 2), 0.01);
    }

    @Test
    public void testModifyOrder() {
        OrderBook orderBook = new OrderBook();

        Order order1 = new Order(1, 100.0, 'B', 10);
        Order order2 = new Order(2, 99.0, 'B', 20);
        Order order3 = new Order(3, 100.0, 'O', 30);
        Order order4 = new Order(4, 101.0, 'O', 40);

        orderBook.addOrder(order1);
        orderBook.addOrder(order2);
        orderBook.addOrder(order3);
        orderBook.addOrder(order4);

        orderBook.modifyOrder(1, 25);

        assertEquals(100.0, orderBook.getPrice('B', 1), 0.01);
        assertEquals(25.0, orderBook.getSize('B', 1), 0.01);

    }


    @Test
    public void testPrice() {
        OrderBook orderBook = new OrderBook();

        Order order1 = new Order(1, 100.0, 'B', 10);
        Order order2 = new Order(2, 99.0, 'B', 20);
        Order order3 = new Order(3, 100.0, 'O', 30);
        Order order4 = new Order(4, 101.0, 'O', 40);

        orderBook.addOrder(order1);
        orderBook.addOrder(order2);
        orderBook.addOrder(order3);
        orderBook.addOrder(order4);

        assertEquals(100.0, orderBook.getPrice('B', 1), 0.01);
        assertEquals(99.0, orderBook.getPrice('B', 2), 0.01);
        assertEquals(100.0, orderBook.getPrice('O', 1), 0.01);
        assertEquals(101.0, orderBook.getPrice('O', 2), 0.01);

    }

    @Test
    public void testSize() {
        OrderBook orderBook = new OrderBook();

        Order order1 = new Order(1, 100.0, 'B', 10);
        Order order2 = new Order(2, 99.0, 'B', 20);
        Order order3 = new Order(3, 100.0, 'O', 30);
        Order order4 = new Order(4, 101.0, 'O', 40);

        orderBook.addOrder(order1);
        orderBook.addOrder(order2);
        orderBook.addOrder(order3);
        orderBook.addOrder(order4);

        assertEquals(2, orderBook.getTotalSize('B'), 0.01);
        assertEquals(2, orderBook.getTotalSize('O'), 0.01);

    }

    @Test
    public void testAllOrders() {
        OrderBook orderBook = new OrderBook();

        Order order1 = new Order(1, 100.0, 'B', 10);
        Order order2 = new Order(2, 99.0, 'B', 20);
        Order order3 = new Order(3, 100.0, 'O', 30);
        Order order4 = new Order(4, 101.0, 'O', 40);

        orderBook.addOrder(order1);
        orderBook.addOrder(order2);
        orderBook.addOrder(order3);
        orderBook.addOrder(order4);

        assertEquals(2, orderBook.getOrders('B').size(), 0.01);

        List<Order> bidList = orderBook.getOrders('B');
        bidList.stream().map(e -> " "+e.getId()+" "+e.getPrice()+" "+e.getSide()+" "+e.getSize()).forEach(System.out::println);

        assertEquals(2, orderBook.getOrders('O').size(), 0.01);

        List<Order> offerList = orderBook.getOrders('O');
        offerList.stream().map(e -> " "+e.getId()+" "+e.getPrice()+" "+e.getSide()+" "+e.getSize()).forEach(System.out::println);

    }

    @Test
    public void testErrorHandling() {
        OrderBook orderBook = new OrderBook();

        Order order1 = new Order(1, 100.0, 'B', 10);
        Order order2 = new Order(2, 99.0, 'B', 20);
        Order order3 = new Order(3, 100.0, 'O', 30);
        Order order4 = new Order(4, 101.0, 'O', 40);
        Order order5 = new Order(415, 103.0, 'O', 50);

        orderBook.addOrder(order1);
        orderBook.addOrder(order2);
        orderBook.addOrder(order3);
        orderBook.addOrder(order4);
        orderBook.addOrder(order5);

        // test lowercase side characters being submitted
        assertEquals(100.0, orderBook.getPrice('b', 1), 0.01);
        // test incorrect level number being submitted
        assertEquals(Double.NaN, orderBook.getPrice('B', 3), 0.01);
        // test lowercase side characters being submitted
        assertEquals(10.0, orderBook.getSize('b', 1), 0.01);
        // test incorrect level number being submitted
        assertEquals(0.0, orderBook.getSize('O', 4), 0.01);
        // test lowercase side characters being submitted
        assertEquals(2, orderBook.getTotalSize('b'), 0.01);
        // test lowercase side characters being submitted
        assertEquals(3, orderBook.getTotalSize('o'), 0.01);
        // test incorrect side characters being submitted
        assertEquals(0.0, orderBook.getTotalSize('d'), 0.01);
        // test incorrect side characters being submitted
        assertEquals(0.0, orderBook.getTotalSize('e'), 0.01);
        // test random id
        assertEquals(103.0, orderBook.getPrice('O', 3), 0.01);


    }
}
