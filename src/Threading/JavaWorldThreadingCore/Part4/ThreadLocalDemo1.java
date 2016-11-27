package Threading.JavaWorldThreadingCore.Part4;

/**
 * Created by chetan on 27/11/16.
 */
// ThreadLocalDemo1.java
class ThreadLocalDemo1
{
   public static void main (String [] args)
   {
      MyThread9 mt1 = new MyThread9 ("A");
      MyThread9 mt2 = new MyThread9 ("B");
      MyThread9 mt3 = new MyThread9 ("C");
      mt1.start ();
      mt2.start ();
      mt3.start ();
   }
}
class MyThread9 extends Thread
{
    //ThreadLocalDemo1 creates a thread-local variable
   private static ThreadLocal tl = new ThreadLocal ()
          {
              /*
              ThreadLocal's default implementation of initialValue() returns null,
              you must subclass ThreadLocal and override this
 method to return a nonnull initial value.

               */
              /*
              That variable associates a unique serial number sernum with each thread
              that accesses the thread-local variable by calling
 tl.get ().
               */
              //anonymous ThreadLocal subclass.
             protected synchronized Object initialValue ()
             {
                return new Integer (sernum++);
             }
          };
          //static at this place is very important
   private static int sernum = 100;
   MyThread9 (String name)
   {
      super (name);
   }
   public void run ()
   {
      for (int i = 0; i < 10; i++) {
       // When a thread first calls tl.get (), ThreadLocal's get() method calls
          // the overridden initialValue() method
        //  in the anonymous ThreadLocal subclass.
          System.out.println(getName() + " " + tl.get());

      }
   }
}
/*
A 100
A 100
A 100
A 100
A 100
A 100
A 100
A 100
A 100
A 100
B 101
B 101
B 101
B 101
B 101
B 101
B 101
B 101
B 101
B 101
C 102
C 102
C 102
C 102
C 102
C 102
C 102
C 102
C 102
C 102

Process finished with exit code 0

 */