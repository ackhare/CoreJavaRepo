package Generics;

//methods generics tutorial
public class DemoGenericsMethod {
//Here instead of T we havge used E because here it is used for elemts
    public static <E> void printArray(E[] elements) {
        for (E element : elements) {
            System.out.print(element + " ");
        }

        System.out.println();
    }
//here we see even in  static E is used because it is not a type but used as for an element
    public static <E> String sum(E[] elements) {
        String string = "";
        for (E element : elements) {
            string = string + element.toString();
        }
        return string;
    }

    public static void main(String args[]) {
        Integer[] intArray = {10, 20, 30, 40, 50};
        Character[] charArray = {'d', 'e', 's', 'h', 'r', 'a', 'j'};
//Because the array type is E any array can be insrted here
        System.out.println("Printing Integer Array");
        printArray(intArray);

        System.out.println("Printing Character Array");
        printArray(charArray);

        System.out.println("Sum of intArray = " + sum(intArray));

        System.out.println("Sum of charArray = " + sum(charArray));
    }
}
