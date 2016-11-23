//package Threading.javaRevistedFutureDemo;
//
///**
// * Created by chetan on 21/11/15.
// */
//
//
//import junit.framework.Test;
//
//import java.util.concurrent.Callable;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.Future;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///**
// * Java program to show how to use Future in Java. Future allows to write
// * asynchronous code in Java, where Future promises result to be available in
// * future
// *
// * @author Javin
// */
//
///*
//Here is our simple test program, which demonstrate how you can use these two classes to do asynchronous execution in
//Java. First we have created a FutureTask which is nothing but a nested static class which implements Callable interface.
//In this method we call our factorial method to calculate factorial of a number. To make this method long running, we
//have also introduced a small sleep duration. This will help you to better understand how Future works.
//
//
//
// */
//public class FutureDemo {
//
//    private static final ExecutorService threadpool = Executors.newFixedThreadPool(3);
//
//    public static void main(String args[]) throws InterruptedException, ExecutionException {
//
//        FactorialCalculator task = new FactorialCalculator(10);
//        System.out.println("Submitting Task ...");
//
//        Future future = threadpool.submit(task);
//
//        System.out.println("Task is submitted");
//
//        while (!future.isDone()) {
//            System.out.println("Task is not completed yet....");
//            Thread.sleep(1); //sleep for 1 millisecond before checking again
//        }
//
//        System.out.println("Task is completed, let's check result");
//        Object factorial = future.get();
//        System.out.println("Factorial of 1000000 is : " + factorial);
//
//        threadpool.shutdown();
//    }
///*
//First we have created a FutureTask which is nothing but a nested static class which implements Callable interface.
//In this method we call our factorial method to calculate factorial of a number.
//we have a created a FactorialCalculator task, which wraps calculation of factorial
//under Callable interface's call() method
// */
//    private static class FactorialCalculator implements Callable {
//
//        private final int number;
//
//        public FactorialCalculator(int number) {
//            this.number = number;
//        }
//
//        @Override
//        public Long call() {
//            long output = 0;
//            try {
//                output =  factorial(number);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//            return output;
//        }
//
//        private long factorial(int number) throws InterruptedException {
//            if (number < 0) {
//                throw new IllegalArgumentException("Number must be greater than zero");
//            }
//            long result = 1;
//            while (number > 0) {
//                Thread.sleep(1); // adding delay for example
//                result = result * number;
//                number--;
//            }
//            return result;
//        }
//    }
//
//}
//
////Output
///*
//Submitting Task ...
//Task is submitted
//Task is not completed yet....
//Task is not completed yet....
//Task is not completed yet....
//Task is not completed yet....
//Task is not completed yet....
//Task is not completed yet....
//Task is not completed yet....
//Task is not completed yet....
//Task is not completed yet....
//Task is not completed yet....
//Task is completed, let's check result
//Factorial of 1000000 is : 3628800
//
//Process finished with exit code 0
//
// */
