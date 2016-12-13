package Threading.WithoutPainThreadingJavaWorld.Concurrent_Collections;

/**
 * Created by chetan on 11/12/16.
 */

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListDemo {
    public static void main(String[] args) {
        List<String> empList = new ArrayList<>();
        empList.add("John Doe");
        empList.add("Jane Doe");
        empList.add("Rita Smith");
        Iterator<String> empIter = empList.iterator();
        while (empIter.hasNext())
            try {
                System.out.println(empIter.next());
                if (!empList.contains("Tom Smith"))
                    empList.add("Tom Smith");
                //so as we see tom smith was not added to result , it just got ignored
            } catch (ConcurrentModificationException cme) {
                System.err.println("attempt to modify list during iteration");
                break;
            }
/*
CopyOnWriteArrayList<E>: This class describes a thread-safe variant of ArrayList
in which all mutative operations (e.g.,
add and set) are implemented by making a fresh copy of the
underlying array whenever an element is added or removed.
However, in-progress iterations continue to work on the previous copy
 (when the iterator was created). Although there's
some cost to copying the array, this cost is acceptable in situations where there are many more iterations than
modifications.
 */
        List<String> empList2 = new CopyOnWriteArrayList<>();
        empList2.add("John Doe");
        empList2.add("Jane Doe");
        empList2.add("Rita Smith");
        empIter = empList2.iterator();
        while (empIter.hasNext()) {
            System.out.println(empIter.next());
            if (!empList2.contains("Tom Smith"))
                empList2.add("Tom Smith");
        }

    }
}

/*
 If you compile and run this application you should see the following output:

John Doe
attempt to modify list during iteration
John Doe
Jane Doe
Rita Smith

 */