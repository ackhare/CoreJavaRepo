package com;


public class DemoGenericsMethod {

    public static <E> void printArray(E[] elements) {
        for (E element : elements) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    public static <T> String sum(T[] elements) {
        String string = "";
        for (T element : elements) {
            string = string + element.toString();
        }
        return string;
    }

    public static void main(String args[]) {
        Integer[] intArray = {10, 20, 30, 40, 50};
        Character[] charArray = {'d', 'e', 's', 'h', 'r', 'a', 'j'};

        System.out.println("Printing Integer Array");
        printArray(intArray);

        System.out.println("Printing Character Array");
        printArray(charArray);

        System.out.println("Sum of intArray = " + sum(intArray));

        System.out.println("Sum of charArray = " + sum(charArray));
    }
}
