package Generics;
//This demo shows a way to overcome the issue we faced with extension to generics and type casting

import java.util.*;
abstract class AbstractShape {
    abstract void draw();
}

 class Shape extends  AbstractShape {
     void draw(){};
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
    //creating a method that accepts only child class of Shape and Shape itself
    public static void drawShapesWithUpperBound(List<? extends Shape> lists) {
        for (Shape s : lists) {
            s.draw();//calling method of Shape class by child class instance
        }
    }
//By below we can put list types which are shape and above shape
    //TODO a important thing that we see like abnove took Shape here it does not take anything other than Object in line 36 the reason of which is yet I m unable to understand
    public static void drawShapesWithLowerBound(List<? super Shape> lists) {
        for (Object s : lists) {
            //TODO Also we cannot call draw because it can be called
            // /s.draw();//calling method of Shape class by child class instance
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

        List<Shape> list = new ArrayList<Shape>();
        circleList.add(new Circle());
        circleList.add(new Circle());


        drawShapesWithUpperBound(rectangleList);

        drawShapesWithUpperBound(circleList);
        //In both  Shape is inclusive   Triangle            <? extends Shape>
        drawShapesWithUpperBound(list);

        drawShapesWithLowerBound(list);
    }
}
