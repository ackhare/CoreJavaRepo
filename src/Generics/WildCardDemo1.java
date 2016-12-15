package Generics;


import java.util.ArrayList;
import java.util.Collection;
//Thi sdemo actually tells the use of wild card and its implementation
public class WildCardDemo1 {

    public static void main(String[] args){

        Collection<String> stringCollection = new ArrayList<>();
        stringCollection.add("abc");
        stringCollection.add("xyz");
        stringCollection.add("pqr");
//Very important to note that thgough traverse1 hasd object and we think it can take any type but
// when er try to put string
        //This is the basic diffrence between types and generrics and that is the reason precise  we use woildcards
//        travers1(stringCollection);

        //Because in traverse 2 we are using wild cards we can give any thing

        //TODO it is very important to thus understand the diffrence between generics when used as a type and when used as a extension to type
        travers2(stringCollection);
        Collection<?> myCollec = new ArrayList<>();
//        myCollec.add("deshraj");
        myCollec = stringCollection;

    }

    public static void travers1(Collection<Object> collection){

        for (Object obj : collection){
            System.out.println(obj.toString());
        }
        System.out.println();

    }


    public static void travers2(Collection<?> collection){

        for (Object obj : collection){
            System.out.println(obj.toString());
        }
        System.out.println();

    }

}
