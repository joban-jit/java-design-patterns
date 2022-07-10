package com.design.patterns.corepatterns.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

// it is powerful and somewhat computationally costly approach
// it is proxy which constructed a runtime as composed to compile time
// so at runtime, you take an existing object and you make a wrapper around it
// e.g. in order to intercept every single call to every single one of its methods
public class DynamicProxyForLogging {
    // now we are going to create a utility method here for constructing a dynamic proxy with
    //logging any kind of object. It doesn't have to be Person and can be virtually anything
    /**
     * @param target: object for the which the logging is required
     * @param interfce: type of interface/class we want to receive at output -means what should be the type of "target"
     */
    // we did this to get the particular interface and a dynamic proxy for the that interface
    // so you can't just take the underlying class and get that as the end result of that interface
    // But you can get an interface so you get a dynamic proxy which conforms precisely to "interfce"
    public static <T> T withLogging(T target, Class<T> interfce){
        return (T) Proxy.newProxyInstance(
                interfce.getClassLoader(), // class loader
                new Class<?> []{interfce}, // list of all the interfaces you are interested in
                new LoggingHandler(target) // actual type we are using for dynamic proxy
        );
    }
    public static void main(String[] args) {
        Person person = new Person();
        Human logged = withLogging(person, Human.class);
        logged.talk();
        logged.walk();
        logged.walk();
        System.out.println(logged);//{talk=1, walk=2}
    }
}

interface Human{
    void walk();
    void talk();
}

class Person implements Human{
    @Override
    public void walk() {
        System.out.println("I'm walking");
    }
    @Override
    public void talk() {
        System.out.println("I'm talking");
    }
}

// so we are going to build a Dynamic proxy which takes an existing object of type Person and counts
// the number of methods inside the person that have actually been called.
class LoggingHandler implements InvocationHandler{
    // InvocationHandler allows us to intercept different methods

    // 1. we are going to have a reference to the object which we are actually providing a proxy for.
    // as this all happening at runtime, so at runtime you have to give it an existing object and tell
    // it to basically take over its functionality while providing additional things
    private final Object target;

    //2. we need a Map which is going to record the number of method calls to the various methods that are called
    // on the underlying object.
    private Map<String, Integer>  calls = new HashMap<>();

    public LoggingHandler(Object target) {
        this.target = target;
    }
    // invoke method: we can use it invoke "method" with "args" as its arguments and proxy is the object
    // which can be used to invoke that method or you can use your own
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String name = method.getName();
        // lets say someone invoke "toString" on our decorated object = target
        // instead of returning the default implementation like calling the underlying object
        // we call the toString method of our Map
        if(name.contains("toString")){
            return calls.toString();
        }

//        calls.merge(name, 1, (oldValue , newValue)->oldValue+newValue);
        // above method can be written as belo
        calls.merge(name, 1, Integer::sum);


        // so here we use the reflection api to invoke the method

        return method.invoke(target, args);
    }
}






