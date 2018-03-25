package String.ReverseStringOfWord;

/**
 * Created by chetan on 25/3/18.
 */
//Write a java program to reverse each word in string?
public class TestStringFormatter {
    public static String reverseWord(String str){
        String words[]=str.split("\\s");
        String reverseWord="";
        for(String w:words){
            //System.out.println(w);
//            StringBuilder sb=new StringBuilder(w);
//            sb.reverse();
            String str1=new String(w);
            str1=reverseString(str1);
            reverseWord+=str1+" ";
        }
        return reverseWord.trim();
    }

    public static String reverseString(String str){
        char ch[]=str.toCharArray();
        String rev="";
        for(int i=ch.length-1;i>=0;i--){
            rev+=ch[i];
        }
        return rev;
    }
    public static void main(String[] args) {
        System.out.println(reverseWord("my name is khan"));
        System.out.println(reverseWord("I am sonoo jaiswal"));
    }
}
