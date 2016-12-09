package com;


import java.util.*;

abstract class Shape {
    abstract void draw();
}

class Rectangle extends Shape {
    void draw() {
        System.out.println("drawing rectangle");
    }
}

class Circle extends Shape {
    void draw() {
        System.out.println("drawing circle");
    }
}


class WildCardDemo2 {
    //creating a method that accepts only child class of Shape
    public static void drawShapes(List<? extends Shape> lists) {
        for (Shape s : lists) {
            s.draw();//calling method of Shape class by child class instance
        }
    }

//    wild cards can be used only with reference parameters
//    public static void drawSingleShape(<? extends Shape> shape){
//
//    }

    public static void main(String args[]) {
        List<Rectangle> rectangleList = new ArrayList<Rectangle>();
        rectangleList.add(new Rectangle());

        List<Circle> circleList = new ArrayList<Circle>();
        circleList.add(new Circle());
        circleList.add(new Circle());

        drawShapes(rectangleList);

        drawShapes(circleList);
    }
}
