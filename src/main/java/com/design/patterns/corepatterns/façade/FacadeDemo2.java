package com.design.patterns.corepatterns.façade;

import org.springframework.core.annotation.Order;

public class FacadeDemo2 {
    public static void main(String[] args) {
        OrderFacade orderFacade = new OrderFacade();
        orderFacade.orderFood();
    }
}

class Waiter{
    public void takeOrder(){
        System.out.println("Take Order from client");
    }

    public void takeOrderToCook(){
        System.out.println("Send order to cook");
    }

    public void serveCustomer(){
        System.out.println("Serve Food");
    }
}

class Kitchen{
    public void cookFood(){
        System.out.println("Cook the food");
    }

    public void signalReady(){
        System.out.println("Signal Food is Ready");
    }
}

class OrderFacade{
    private Waiter waiter;
    private Kitchen kitchen;

    public OrderFacade(){
        waiter = new Waiter();
        kitchen = new Kitchen();
    }
    public void orderFood(){
        waiter.takeOrder();
        waiter.takeOrderToCook();
        kitchen.cookFood();
        kitchen.signalReady();
        waiter.serveCustomer();
    }
}