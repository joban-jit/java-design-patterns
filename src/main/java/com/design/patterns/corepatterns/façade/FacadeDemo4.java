package com.design.patterns.corepatterns.façade;

import org.springframework.core.annotation.Order;

public class FacadeDemo4 {
    public static void main(String[] args) {
        // without facade
        OrderProcessor orderProcessor = new OrderProcessor();
        if(orderProcessor.checkStock("MacBook")){
            String orderId = orderProcessor.placeOrder("MacBook", 3);
            orderProcessor.shipOrder(orderId);
        }
        // with facade
        OrdersFacade facade = new OrdersFacade();
        facade.processOrder("macbook pro", 3);
    }
}
class OrderProcessor{
    public boolean checkStock(String name){
        System.out.println("Checking stock..");
        return true;
    }
    public String placeOrder(String name, int quantity){
        System.out.println("Order Placed for "+name+" and quantity: "+quantity);
        return "abc123";
    }
    public void shipOrder(String orderId){
        System.out.println("Order Shipped with order id: "+orderId);
    }
}

class OrdersFacade{

    OrderProcessor orderProcessor = new OrderProcessor();
    public void processOrder(String name, int quantity){

        if(orderProcessor.checkStock(name)){
            String orderId = orderProcessor.placeOrder(name, quantity);
            orderProcessor.shipOrder(orderId);
        }
    }
}
