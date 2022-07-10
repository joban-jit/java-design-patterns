package com.design.patterns.corepatterns.prototype;



import org.apache.commons.lang3.SerializationUtils;
import java.io.Serializable;
// Problem with "Copy Constructor" is that you have to build it for every class type in that closs hierarchy
public class CopyThroughSerialization {

    public static void main(String[] args) {
        Foo foo = new Foo(42, "life");
        // SerializationUtils.roundtrip(someObj) - it serialize the object and then deserialize it
        // so when you do that , it will create a new copy of object
        // serialization takes care of serialization the entire object groph, so if you object contains other
        // objects, they all get serialized and deserialized,and you get a brand new copy - so it is kind copy by value
        Foo foo2 = SerializationUtils.roundtrip(foo);
        foo2.stuff = 45;
        System.out.println(foo);
        System.out.println(foo2);
    }
}
// using Serialization to copy
class Foo implements Serializable{
    public int stuff;
    public String whatever;
    public Foo(int stuff, String whatever) {
        this.stuff = stuff;
        this.whatever = whatever;
    }

    @Override
    public String toString() {
        return "Foo{" +
                "stuff=" + stuff +
                ", whatever='" + whatever + '\'' +
                '}';
    }
}

