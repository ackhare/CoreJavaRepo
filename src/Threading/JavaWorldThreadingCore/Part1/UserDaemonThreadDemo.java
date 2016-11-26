package Threading.JavaWorldThreadingCore.Part1;

/**
 * Created by chetan on 26/11/16.
 */
// UserDaemonThreadDemo.java
class UserDaemonThreadDemo
{
   public static void main (String [] args)
   {
      if (args.length == 0)
         new MyThread6().start();
      else
      {
         MyThread6 mt = new MyThread6();
         mt.setDaemon (true);
         mt.start();
      }

      //depending on above a thread can be user or daemon
      try
      {
         Thread.sleep (100);
      }
      catch (InterruptedException e)
      {
      }
   }
}
class MyThread6 extends Thread
{
   public void run ()
   {
       //check if thread daemon or not
      System.out.println ("Daemon is " + isDaemon ());
       System.out.println(Thread.currentThread().getName());//the custom thread Thread-0


      while (true);
/*
 When an application's starting thread (which is a user thread) terminates, the JVM checks whether any other
 user threads
are running. If some are, the JVM prevents the application from terminating.
Otherwise, the JVM terminates the
application regardless of whether daemon threads are running.

 */


   }
}

