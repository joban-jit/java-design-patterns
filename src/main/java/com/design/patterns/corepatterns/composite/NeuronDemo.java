package com.design.patterns.corepatterns.composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class NeuronDemo {
    public static void main(String[] args) {
        Neuron neuron = new Neuron();
        Neuron neuron2 = new Neuron();
        NeuronLayer layer = new NeuronLayer();
        NeuronLayer layer2 = new NeuronLayer();

        neuron.connectTo(neuron2);
        // we need to cover below cases as well and we can't just create four different implementation of connectTo
        // as it is not realistic or recommended.
        neuron.connectTo(layer);
        layer.connectTo(neuron);
        layer.connectTo(layer2);
    }

}




interface SomeNeurons extends Iterable<Neuron> {

    default void connectTo(SomeNeurons other){
        if(this==other) return;
        for(Neuron from: this){
            for(Neuron to: other) {
                from.out.add(to);
                to.in.add(from);
            }
        }
    }
}


class Neuron implements SomeNeurons {
    public ArrayList<Neuron> in, out;

    @Override
    public Iterator<Neuron> iterator() {
        return Collections.singleton(this).iterator();
    }

    @Override
    public void forEach(Consumer<? super Neuron> action) {
        action.accept(this);
    }

    @Override
    public Spliterator<Neuron> spliterator() {
        return Collections.singleton(this).spliterator();
    }

//    public void connectTo(Neuron other){
//        out.add(other); // our neuron outward connection to other ...so it will be in "out" list of our neuron
//        other.in.add(this); // our neuron is inward connection for other neuron ..so it'll be "in" list of other neuron
//    }


}

class NeuronLayer extends ArrayList<Neuron> implements SomeNeurons {
    // here we don't need to implement methods of SomeNeurons
    // as NeuronLayer already extends the ArrayList and it already implements Iterable


}
