package Collection.ArrayDeque;

/**
 * Created by chetan on 16/3/18.
 */
import java.util.*;
public class DequeExample {
    public static void main(String[] args) {
        Deque<String> deque=new ArrayDeque<String>();
        deque.add("arvind");
        deque.add("vimal");
        deque.add("mukul");
        deque.addFirst("jai");
        System.out.println("After offerFirst Traversal...");
        for(String s:deque){
            System.out.println(s);
        }
        //deque.poll();
        //deque.pollFirst();//it is same as poll()
        deque.pollLast();
        System.out.println("After pollLast() Traversal...");
        for(String s:deque){
            System.out.println(s);
        }
    }
}
