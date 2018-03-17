package Collection.Sorting.Comparator;

/**
 * Created by chetan on 17/3/18.
 */
//In this class, we are printing the objects values by sorting on the
// basis of name and age.

import java.util.*;
import java.io.*;

class Simple {
    public static void main(String args[]) {

        ArrayList<Student> al = new ArrayList<Student>();
        al.add(new Student(101, "Vijay", 23));
        al.add(new Student(106, "Ajay", 27));
        al.add(new Student(105, "Jai", 21));

        System.out.println("Sorting by Name...");

        Collections.sort(al, new NameComparator());
        for (Student st : al) {
            System.out.println(st.rollno + " " + st.name + " " + st.age);
        }

        System.out.println("sorting by age...");

        Collections.sort(al, new AgeComparator());
        for (Student st : al) {
            System.out.println(st.rollno + " " + st.name + " " + st.age);
        }

    }
}
