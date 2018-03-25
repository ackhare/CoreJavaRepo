package String.DuplicateCharFinder;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by chetan on 25/3/18.
 */
/*
How do you find duplicate characters in a string?
 */
public class DuplicateCharFinder {
    public void findIt(String str) {
        Map<Character, Integer> baseMap = new HashMap<Character, Integer>();
        char[] charArray = str.toCharArray();
        for (Character ch : charArray) {
            if (baseMap.containsKey(ch)) {
                baseMap.put(ch, baseMap.get(ch) + 1);
            } else {
                baseMap.put(ch, 1);
            }
        }
        Set<Character> keys = baseMap.keySet();
        for (Character ch : keys) {
            if (baseMap.get(ch) > 1) {
                System.out.println(ch + "  is " + baseMap.get(ch) + " times");
            }
        }
    }

    public static void main(String a[]) {
        DuplicateCharFinder dcf = new DuplicateCharFinder();
        dcf.findIt("India is my country");
    }
}