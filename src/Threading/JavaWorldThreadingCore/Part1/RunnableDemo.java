package Threading.JavaWorldThreadingCore.Part1;

/**
 * Created by chetan on 26/11/16.
 */
// RunnableDemo.java

public class RunnableDemo extends java.applet.Applet implements Runnable
{
   private Thread t;

   public void run ()
   {
      while (t == Thread.currentThread ())
      {
           int width = rnd (30);
           if (width < 2)
               width += 2;

           int height = rnd (10);
           if (height < 2)
               height += 2;

           draw (width, height);
      }
   }
//this start is the applet method inside which goes our code to create a new thread
    //this is ofcourse our runnable class
   public void start ()
   {
      if (t == null)
      {
         t = new Thread (this);
         t.start ();
      }
   }
//applet stop method
   public void stop ()
   {
      if (t != null)
         t = null;
   }

   //applet draw method
   private void draw (int width, int height)
   {
      for (int c = 0; c < width; c++)
           System.out.print ('*');

      System.out.print ('\n');

      for (int r = 0; r < height - 2; r++)
      {
           System.out.print ('*');

           for (int c = 0; c < width - 2; c++)
                System.out.print (' ');

           System.out.print ('*');

           System.out.print ('\n');
      }

      for (int c = 0; c < width; c++)
           System.out.print ('*');

      System.out.print ('\n');
   }

   private int rnd (int limit)
   {
      // Return a random number x in the range 0 <= x < limit.

      return (int) (Math.random () * limit);
   }
}
