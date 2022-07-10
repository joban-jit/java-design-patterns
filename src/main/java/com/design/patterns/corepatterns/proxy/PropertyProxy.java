package com.design.patterns.corepatterns.proxy;

// so idea here to replace every field that you store with a specialized kind of construct, (in this example
// it is Property), which allows you more control because a "field" with its equals operator for assignment
// and it's direct access to underlying memory, it doesn't allow you much control in terms of things
// like logging or controlling access or maybe even substitute different value.
public class PropertyProxy {
    public static void main(String[] args) {
        int x = 0;
        // now here we are assigning a new value to x , but at no point we are recording that we are recording
        // that we are assigning a new value to x.
        // so if you want to perform a logging on this particular operation, you will have to perform that
        // logging in front of every single assignment.
        x = 5;

        // with property proxy.
        NewCreature newCreature = new NewCreature();
        newCreature.setAgility(12);
    }
    // we can't do below in main method
    // newCreature.agility = 13; // even if we make this field public
    // because there is no automatic conversion between Property and actual value
    // typical way to handle this situation is : we use getter and setter
    // so we effectively turn our fields into properties.
    // and add log into the setter method
    //however field itself is still available to access through the equals operator
    // like if we do agility = 123, in our constructor so which we are still NOT logging.
    class Creature {
        private int agility;

        public Creature() {
            agility = 123;
        }
        public Creature(int agility) {

            this.agility = agility;
        }
        public int getAgility() {
            return agility;
        }
        public void setAgility(int agility) {
            this.agility = agility;
        }
    }
}

// how do we avoid it..
// we use a PropertyProxy
class Property<T> {
    private T value;
    public Property(T value){
        this.value = value;
    }
    public T getValue(){
        return value;
    }
    public void setValue(T value){
        // logging
        System.out.println("setting a new value: "+value);
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Property<?> property = (Property<?>) o;

        return value != null ? value.equals(property.value) : property.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
// now we can't use = equal operator to assign the value
// like we can't do Property<Integer> x = 5;
// so we have to use constructor or getter/setters

class NewCreature{
    private Property<Integer> agility = new Property<>(10);

    public int getAgility(){
        return agility.getValue();
    }

    public void setAgility(int value) {
        agility.setValue(value);

    }
}


