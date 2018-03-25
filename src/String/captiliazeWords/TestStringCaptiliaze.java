package String.captiliazeWords;

/**
 * Created by chetan on 25/3/18.
 */
public class TestStringCaptiliaze {
    public static String capitalizeWord(String str){
        String words[]=str.split("\\s");
        String capitalizeWord="";
        for(String w:words){
            String first=w.substring(0,1);
            String afterfirst=w.substring(1);
            capitalizeWord+=first.toUpperCase()+afterfirst+" ";
        }
        return capitalizeWord.trim();
    }
    public static void main(String[] args) {
        System.out.println(capitalizeWord("my name is khan"));
        System.out.println(capitalizeWord("I am sonoo jaiswal"));
    }
}
