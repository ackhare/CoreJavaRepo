package Threading.JavaWorldThreadingCore.Part1;

import Utility.CustomLogger;
import org.apache.log4j.Logger;

/**
 * Created by chetan on 26/11/16.
 */
/*
Listing 1 presents source code to an application consisting of classes ThreadDemo and MyThread.

In contrast, MyThread overrides Thread's run() method to print (on the standard output
stream) a right-angle triangle composed of asterisk characters.
 */
class ThreadDemo
{

    public static void main (String [] args)
   {
    //   Class ThreadDemo drives the application by creating a MyThread object,
//       starting a thread that associates with that object,
//       and executing some code to print a table of squares.
        org.apache.log4j.Logger log= (Logger) CustomLogger.intiateLogging(ThreadDemo.class);
//       log.info("Intiating thread");
      MyThread mt = new MyThread();
       //a thread is started
      mt.start ();
       log.info("thread started");
      for (int i = 0; i < 50; i++) {
          System.out.println("In class main "+Thread.currentThread().getName());
          System.out.println("i = " + i + ", i * i = " + i * i);
      }
   }
}
class MyThread extends Thread
{
    //orignal run of thread class has nothing so it is always nice to override it
   org.apache.log4j.Logger log= (Logger) CustomLogger.intiateLogging(ThreadDemo.class);
   public void run ()
   {
      for (int count = 1, row = 1; row < 20; row++, count++)
      {
           for (int i = 0; i < count; i++)
                System.out.println ('*');
          System.out.println("In class MyThread  "+Thread.currentThread().getName());
         // log.info("thread in loop");
           System.out.print ('\n');
      }
   }
}
/*

//As can be see output the outputs of both thread collide with each
other and you can not guess when wil which be called

*
In class main main
In class MyThread  Thread-0
i = 0, i * i = 0

*
*
In class main main
In class MyThread  Thread-0

*
*
*
i = 1, i * i = 1
In class MyThread  Thread-0

*
*
*
*
In class main main
In class MyThread  Thread-0

*
*
*
*
*
i = 2, i * i = 4
In class MyThread  Thread-0

*
*
*
*
*
*
In class main main
In class MyThread  Thread-0

*
*
*
*
*
*
*
i = 3, i * i = 9
In class MyThread  Thread-0

*
*
*
*
*
*
*
*
In class main main
In class MyThread  Thread-0

*
*
*
*
*
*
*
*
*
i = 4, i * i = 16
In class MyThread  Thread-0

*
*
*
*
*
*
*
*
*
*
In class main main
In class MyThread  Thread-0

*
*
*
*
*
*
*
*
*
*
*
i = 5, i * i = 25
In class MyThread  Thread-0

*
*
*
*
*
*
*
*
*
*
*
*
In class main main
In class MyThread  Thread-0

*
*
*
*
*
*
*
*
*
*
*
*
*
i = 6, i * i = 36
In class MyThread  Thread-0

*
*
*
*
*
*
*
*
*
*
*
*
*
*
In class main main
In class MyThread  Thread-0

*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
i = 7, i * i = 49
In class MyThread  Thread-0

*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
In class main main
In class MyThread  Thread-0

*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
i = 8, i * i = 64
In class MyThread  Thread-0

*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
In class main main
In class MyThread  Thread-0

*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
i = 9, i * i = 81
In class MyThread  Thread-0

In class main main
i = 10, i * i = 100
In class main main
i = 11, i * i = 121
In class main main
i = 12, i * i = 144
In class main main
i = 13, i * i = 169
In class main main
i = 14, i * i = 196
In class main main
i = 15, i * i = 225
In class main main
i = 16, i * i = 256
In class main main
i = 17, i * i = 289
In class main main
i = 18, i * i = 324
In class main main
i = 19, i * i = 361
In class main main
i = 20, i * i = 400
In class main main
i = 21, i * i = 441
In class main main
i = 22, i * i = 484
In class main main
i = 23, i * i = 529
In class main main
i = 24, i * i = 576
In class main main
i = 25, i * i = 625
In class main main
i = 26, i * i = 676
In class main main
i = 27, i * i = 729
In class main main
i = 28, i * i = 784
In class main main
i = 29, i * i = 841
In class main main
i = 30, i * i = 900
In class main main
i = 31, i * i = 961
In class main main
i = 32, i * i = 1024
In class main main
i = 33, i * i = 1089
In class main main
i = 34, i * i = 1156
In class main main
i = 35, i * i = 1225
In class main main
i = 36, i * i = 1296
In class main main
i = 37, i * i = 1369
In class main main
i = 38, i * i = 1444
In class main main
i = 39, i * i = 1521
In class main main
i = 40, i * i = 1600
In class main main
i = 41, i * i = 1681
In class main main
i = 42, i * i = 1764
In class main main
i = 43, i * i = 1849
In class main main
i = 44, i * i = 1936
In class main main
i = 45, i * i = 2025
In class main main
i = 46, i * i = 2116
In class main main
i = 47, i * i = 2209
In class main main
i = 48, i * i = 2304
In class main main
i = 49, i * i = 2401

Process finished with exit code 0


 */