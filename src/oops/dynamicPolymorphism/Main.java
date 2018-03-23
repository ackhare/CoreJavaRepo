package oops.dynamicPolymorphism;

/**
 * Created by chetan on 23/3/18.
 */
public class Main {

    Parent parent = new Parent();//no error

    public static void main(String[] args) {

        //Child child = new Parent();
        //compile time error(Incompatible types)

        Parent parent1 = new Child();
        //parent.x   //compile time error as non static cannot be refrenced fromstatic
        System.out.println(parent1.x);//child's x
        System.out.println(parent1.m());//parent's method
    }
}

