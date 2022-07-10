package com.design.patterns.corepatterns.composite;
import org.apache.catalina.authenticator.SingleSignOnEntry;

import java.util.*;
import java.util.function.Consumer;

public class CompositeExercise {
    public static void main(String[] args) {
        MyList singleValueList = new MyList(Collections.singletonList(new SingleValue(3)));
        ManyValues manyValues = new ManyValues();
        manyValues.add(1);
        manyValues.add(2);
        manyValues.add(3);
        MyList manyValueList = new MyList(Collections.singletonList(manyValues));
        System.out.println(singleValueList.sum());
        System.out.println(manyValueList.sum());

    }
}


interface ValueContainer extends Iterable<Integer> {

}

class SingleValue implements ValueContainer
{
    public int value;

    // please leave this constructor as-is
    public SingleValue(int value)
    {
        this.value = value;
    }


    @Override
    public void forEach(Consumer<? super Integer> action) {
        action.accept(this.value);
    }


    @Override
    public Iterator<Integer> iterator() {
        return this.iterator();
    }
}

class ManyValues extends ArrayList<Integer> implements ValueContainer
{
}


class MyList extends ArrayList<ValueContainer>
{
    private final Collection<? extends ValueContainer> collection;

    // please leave this constructor as-is
    public MyList(Collection<? extends ValueContainer> c)
    {
        super(c);
        this.collection = c;
    }

    public int sum()
    {
        ArrayList<Integer> valuesToAdd = new ArrayList<>();
        this.forEach(vc->{
            vc.forEach(valuesToAdd::add);
        });

        return valuesToAdd.stream().reduce(Integer::sum).orElse(0);
    }
}