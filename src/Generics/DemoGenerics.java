package Generics;

//class generics tutorial
class Box<T> {
    T value;

    Box(T value) {
        this.value = value;
    }

    void setValue(T value) {
        this.value = value;
    }

    T getValue() {
        return this.value;
    }

    void printBox() {
        System.out.println("This is the value = " + value.toString());
    }

    //T can only be used with  a instance method not with a static method

//    public static T display(){
//        Box<Character> characterBox = new Box<>('a');
//        return characterBox.value;
//    }
}

public class DemoGenerics {
    public static void main(String... args) {
        Box<Integer> integerBox = new Box<Integer>(123);
        integerBox.printBox();
        Box<String> stringBox = new Box<String>("Deshraj");
        stringBox.printBox();


//On RHS we can avoid putting a generics
//Also box was defined as T and not as a defenite type so we can pass anything String , Integer , Double
//As in above on RHS there ois a type given but it can be ommitted
//        Diamond <>
        Box<Double> doubleBox = new Box<>(new Double(1.2));
        doubleBox.printBox();


//        Raw types
        Box box = new Box(13);
        box.printBox();

//
        stringBox = box;
        stringBox.printBox();

    }
}
