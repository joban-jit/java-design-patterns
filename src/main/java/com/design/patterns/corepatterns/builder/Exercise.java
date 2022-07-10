package com.design.patterns.corepatterns.builder;

public class Exercise {
    public static void main(String[] args) {
        CodeBuilder cb = new CodeBuilder("Person").addField("name", "String")
                .addField("age", "int");
        System.out.println(cb);
    }
}


class CodeBuilder
{
    StringBuilder sb ;
    public CodeBuilder(String className)
    {
        sb = new StringBuilder();
        sb.append("public class "+className+"\n{\n");
    }

    public CodeBuilder addField(String name, String type)
    {
        sb.append("  public "+type+" " +name+ ";\n");
        return this;
    }

    @Override
    public String toString()
    {
        return sb.append("}").toString();
    }
}
