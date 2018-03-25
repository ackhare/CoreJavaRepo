package oops.AbstractClass;

/**
 * Created by chetan on 25/3/18.
 */
abstract class Bike{
    Bike(){System.out.println("bike is created");}
    abstract void run();
    void changeGear(){System.out.println("gear changed");}

    public static void main(String[] args) {
        System.out.println("mmmm");
    }
}

class Honda extends Bike{
    public Honda() {
        System.out.println("honda is created");
    }

    void run(){System.out.println("running safely..");}
}
class TestAbstraction2{
    public static void main(String args[]){
        Bike obj = new Honda();
        obj.run();
        obj.changeGear();
    }
}
/*

bike is created
running safely..
gear changed
 */