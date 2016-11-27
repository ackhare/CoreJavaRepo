package Threading.JavaWorldThreadingCore.Part3;

/**
 * Created by chetan on 26/11/16.
 */
// WaitNotifyAllDemo.java
   /*
   So when we see output it is clear that when main sleeps other thread gets a chance to run ,
    then wait gets executed on
all one by one and after some time main will get awake and kick off notifyall

    One by one, each thread leaves its synchronized statement and run() method, then terminates.

    */
class WaitNotifyAllDemo
{
   public static void main (String [] args)
   {
      Object lock = new Object ();
      MyThread mt1 = new MyThread (lock);
      mt1.setName ("A");
      MyThread mt2 = new MyThread (lock);
      mt2.setName ("B");
      MyThread mt3 = new MyThread (lock);
      mt3.setName ("C");
      mt1.start ();
      mt2.start ();
      mt3.start ();
      System.out.println ("main thread sleeping");
      try
      {
         Thread.sleep (3000);
      }
      catch (InterruptedException e)
      {
      }
      System.out.println ("main thread awake");
      synchronized (lock)
      {
         lock.notifyAll ();
      }
   }
}
class MyThread extends Thread
{
   private Object o;
   MyThread (Object o)
   {
      this.o = o;
   }
   public void run ()
   {
      synchronized (o)
      {
         try
         {
             System.out.println (getName () + " before wait");
             o.wait ();
             System.out.println (getName () + " after wait");
         }
         catch (InterruptedException e)
         {
         }
      }
   }
}
