package com;


class Box<T>{
    T value;
    Box(T value){
        this.value = value;
    }
    void setValue(T value){
        this.value = value;
    }
    T getValue(){
        return this.value;
    }
    void printBox(){
        System.out.println("This is the value = " + value.toString());
    }

//    public static T display(){
//        Box<Character> characterBox = new Box<>('a');
//        return characterBox.value;
//    }
}

public class DemoGenerics {
    public static void main(String... args){
        Box<Integer> integerBox = new Box<Integer>(123);
        integerBox.printBox();
        Box<String> stringBox = new Box<String>("Deshraj");
        stringBox.printBox();


//        Diamond
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
