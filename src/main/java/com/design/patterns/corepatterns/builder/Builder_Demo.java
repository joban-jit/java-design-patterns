package com.design.patterns.corepatterns.builder;

import java.util.*;

public class Builder_Demo {
    public static void main(String[] args) {
        HtmlBuilder builder = new HtmlBuilder("ul");
        builder
                .addChild("li", "hello")
                .addChild("li", "world");
        System.out.println(builder);

        StringBuilder sb = new StringBuilder();
        // here the return type of sb.append("foo") is also String builder
        // so we can again use Stringbuilder functions on it e.g sb.append("bar");
        sb.append("foo").append("bar");
        // it is called Fluent interface: it allow to make very long chains to build
    }

}

    class HtmlElement{
        public String name, text;
        public List<HtmlElement> elements = new ArrayList<>();
        private final int indentSize = 2;
        private final String newLine = System.lineSeparator();

        public HtmlElement() {
        }

        public HtmlElement(String name, String text) {
            this.name = name;
            this.text = text;
        }

        private String toStringImpl(int indent){
            StringBuilder sb = new StringBuilder();
            String i = String.join("", Collections.nCopies(indent*indentSize, " "));
//            System.out.println("i is:"+i+".");
            sb.append(String.format("%s<%s>%s", i, name, newLine));
            if(Optional.ofNullable(text).isPresent() && !text.isEmpty()){
                sb.append(String.join("", Collections.nCopies(indentSize*(indent+1), " ")))
                        .append(text)
                        .append(newLine);
            }
            elements.forEach(e-> {
                        sb.append(e.toStringImpl(indent+1));
                    }
            );
            sb.append(String.format("%s</%s>%s", i, name, newLine));
            return sb.toString();
        }
        @Override
        public String toString() {
            return toStringImpl(0);
        }
    }

    class HtmlBuilder{
        private String rootName;
        private HtmlElement root = new HtmlElement();

        public HtmlBuilder(String rootName){
            this.rootName = rootName;
            root.name = rootName;
        }
        public HtmlBuilder addChild(String childName, String childText){
            HtmlElement e = new HtmlElement(childName, childText);
            root.elements.add(e);
            return this;
        }
        public void clear(){
            root = new HtmlElement();
            root.name = rootName;
        }

        @Override
        public String toString() {
            return root.toString();
        }
    }

