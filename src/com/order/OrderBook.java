package com.order;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class OrderBook {
    private Map<Character, List<Order>> ordersBySide;
    private Map<Long, Order> ordersById;

    public OrderBook() {
        ordersBySide = new HashMap<>();
        ordersById = new HashMap<>();
    }

    // Add Order to OrderBook
    public void addOrder(Order order) {
        char side = Character.toUpperCase(order.getSide());
        long id = order.getId();
        double price = order.getPrice();
        long size = order.getSize();
        int i = 0;

        List<Order> orders = ordersBySide.get(side);
        if (side == 'B' || side == 'O') {
            if (orders == null) {
                orders = new LinkedList<>();
                ordersBySide.put(side, orders);
            }

            if (side == 'B') {
                for (; i < orders.size(); i++) {
                    Order o = orders.get(i);
                    if (o.getPrice() < price || (o.getPrice() == price && o.getId() > id)) {
                        break;
                    }
                }
                orders.add(i, order);
                ordersById.put(id, order);
            } else {
                for (; i < orders.size(); i++) {
                    Order o = orders.get(i);
                    if (o.getPrice() > price || (o.getPrice() == price && o.getId() > id)) {
                        break;
                    }
                }
                orders.add(i, order);
                ordersById.put(id, order);
            }
        }
    }

    // Remove Order from OrderBook
    public void removeOrder(long id) {
        Order order = ordersById.get(id);
        if (order == null) {
            return;
        }

        char side = order.getSide();
        List<Order> orders = ordersBySide.get(side);
        if (orders == null) {
            return;
        }

        orders.remove(order);
        ordersById.remove(id);
    }

    // Modify Order in OrderBook
   public void modifyOrder(long id, long newSize) {
        Order order = ordersById.get(id);
        if (order == null) {
            return;
        }

       char side = Character.toUpperCase(order.getSide());
       if (side == 'B' || side == 'O') {
           List<Order> orders = ordersBySide.get(side);
           if (orders == null) {
               return;
           }

           char modSide = order.getSide();
           long modId = order.getId();
           double modPrice = order.getPrice();
           long modSize = newSize;

           orders.remove(order);

           Order order1 = new Order(modId, modPrice, modSide, modSize);

           orders.add((int) id - 1, order1);
           ordersById.put(id, order1);
           ordersBySide.put(modSide, orders);
       } else {
           return;
       }

    }

    // Return Price based on Side ('B' bid or 'O' offer and Level) from OrderBook
    public double getPrice(char side, int level) {
        if (Character.toUpperCase(side) == 'B' || Character.toUpperCase(side) == 'O') {
        List<Order> orders = ordersBySide.get(Character.toUpperCase(side));
            if (orders == null || orders.size() < level) {
                return Double.NaN;
            }
            return orders.get(level - 1).getPrice();
        } else {
            return Double.NaN;
        }
    }

    //Given a side and a level return the size for that level
    public long getSize(char side, int level) {
        if (Character.toUpperCase(side) == 'B' || Character.toUpperCase(side) == 'O') {
        List<Order> orders = ordersBySide.get(Character.toUpperCase(side));
            if (orders == null || orders.size() < level) {
                return 0;
            }
            return orders.get(level - 1).getSize();
        } else {
            return 0;
        }
    }

    // Return OrderBook Size based on Side ('B' bid or 'O' offer)
    public long getTotalSize(char side) {
        if (Character.toUpperCase(side) == 'B' || Character.toUpperCase(side) == 'O') {
            List<Order> orders = ordersBySide.get(Character.toUpperCase(side));
            if (orders == null) {
                return 0;
            }

            return orders.size();
        } else {
            return 0;
        }
    }

    // Return all the orders from that side ('B' bid or 'O' offer and Level)  of the book, in level- and time-order based on Side
    public List<Order> getOrders(char side) {
        if (Character.toUpperCase(side) == 'B' || Character.toUpperCase(side) == 'O') {
            List<Order> orders = ordersBySide.get(Character.toUpperCase(side));
            if (orders == null) {
                return new LinkedList<>();
            }
            return new LinkedList<>(orders);
        } else {
            return new LinkedList<>();
        }
    }
}

