package com;


import java.util.ArrayList;
import java.util.Collection;

public class WildCardDemo1 {

    public static void main(String[] args){

        Collection<String> stringCollection = new ArrayList<>();
        stringCollection.add("abc");
        stringCollection.add("xyz");
        stringCollection.add("pqr");
//        travers1(stringCollection);
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
