package com.design.patterns.corepatterns.isp;

// ISP: Interface Segregation Principle
//how to split interfaces into smaller interfaces
public class ISP_Demo {
 // no need to run it..just rough code
}

interface Machine{
    void print(Document d);
    void fax(Document d);
    void scan(Document d);
}
class Document{

}

class MultiFunctionPrinter implements Machine{
    @Override
    public void print(Document d) {

    }

    @Override
    public void fax(Document d) {

    }

    @Override
    public void scan(Document d) {

    }
}
// now client only want to print, no fax or scan
class OldFashionedPrinter implements Machine{

    @Override
    public void print(Document d) {

    }
// we don't want to implement fax, scan methods as we
    // don't need it for old Printer

    //1. we can leave as it is: no implementation-
    // but still giving user idea that they can invoke these methods

    //2. another option we can throw exception:
    // but exceptions have there own issues: like it need to be propagated
    //, we need to tell methods in inteface that it could throw excpetion.

    //3. split interface;
    @Override
    public void fax(Document d) {

    }

    @Override
    public void scan(Document d) {

    }
}
// using the ISP:
interface Printer{
    void print(Document d);
}
interface Scanner{
    void scan(Document d);
}

// YAGNI: You Ain't Going to Need It
// only implements what needed.
class JustAPrinter implements Printer{
    @Override
    public void print(Document d) {

    }
}

class Photocopier implements Printer, Scanner{
    @Override
    public void print(Document d) {

    }

    @Override
    public void scan(Document d) {

    }
}

interface MuliFunctionDevice extends Printer, Scanner{

}

class MultiFunctionMachine implements MuliFunctionDevice{

    private Printer printer;
    private Scanner scanner;

    public MultiFunctionMachine(Printer printer, Scanner scanner) {
        this.printer = printer;
        this.scanner = scanner;
    }

    @Override
    public void print(Document d) {
        printer.print(d);
    }

    @Override
    public void scan(Document d) {
        scanner.scan(d);
    }
}