package Threading.JavaWorldThreadingCore.Part4;

/**
 * Created by chetan on 27/11/16.
 */
// Clock1.java
// Type Ctrl+C (or equivalent keystroke combination on non-Windows platform)
// to terminate
import java.util.*;
class Clock
{
   public static void main (String [] args)
   {
      Timer t = new Timer ();
       //will run infinitly
      t.schedule (new TimerTask ()
                  {
                      public void run ()
                      {
                          //This is the task we want to schedule
                         System.out.println (new Date ().toString ());
                      }
                  },
                  0,
                  1000);
   }
}
