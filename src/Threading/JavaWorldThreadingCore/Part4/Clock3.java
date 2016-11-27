package Threading.JavaWorldThreadingCore.Part4;

/**
 * Created by chetan on 27/11/16.
 */
// Clock3.java
// Type Ctrl+C (or equivalent keystroke combination on non-Windows platform)
// to terminate
/*
 A better approach allows the main thread to initiate a thread that checks for user input and waits
 for the input thread
to terminate once input occurs. The main thread then calls Timer's void cancel() method to terminate
the timer and
discard all scheduled tasks after the currently executing task leaves its run() method.
Listing 10 demonstrates this
approach:

 */
import java.util.*;
class Clock3
{
   public static void main (String [] args)
   {
      Timer t = new Timer ();
      t.scheduleAtFixedRate (new TimerTask ()
                             {
                                public void run ()
                                {
                                   System.out.println (new Date ().
                                                       toString ());
                                }
                             },
                             0,
                             1000);
       //thread is intiated by main thread which will check for user input and waits
       //for the input thread to terminate once input occurs
      InputThread it = new InputThread ();
      it.start ();
      try
      {
          // Wait for input thread to terminate
          System.out.println(Thread.currentThread().getName());
          it.join ();
      }
      catch (InterruptedException e)
      {
      }
      // Terminate the timer and discard all scheduled tasks after the
      // currently executing task leaves its run() method
      t.cancel ();
   }
}
class InputThread extends Thread {
    public void run() {
        try {
            // Wait for user to type Enter key
            System.in.read();
        } catch (java.io.IOException e) {
        }
    }

}