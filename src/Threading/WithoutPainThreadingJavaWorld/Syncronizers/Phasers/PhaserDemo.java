package Threading.WithoutPainThreadingJavaWorld.Syncronizers.Phasers;

/**
 * Created by chetan on 11/12/16.
 */
import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.Phaser;
/*

Listing 8 is based on the first code example in Phaser's Javadoc.

This example shows how to use Phaser instead of CountDownLatch to
control a one-shot action serving a variable number of threads.
 */
public class PhaserDemo
{
   public static void main(String[] args)
   {
       /*
       The application creates a pair of runnable tasks that each report the time
       (in milliseconds relative to the Unix epoch) at which its starts to run
        */
      List<Runnable> tasks = new ArrayList<>();
       //2 tasks are addedd
      tasks.add(new Runnable()
                {
                   @Override
                   public void run()
                   {
                      System.out.printf("%s running at %d%n",
                                        Thread.currentThread().getName(),
                                        System.currentTimeMillis());
                   }
                });
      tasks.add(new Runnable()
                {
                   @Override
                   public void run()
                   {
                      System.out.printf("%s running at %d%n",
                                        Thread.currentThread().getName(),
                                        System.currentTimeMillis());
                   }
                });

       //runnable created above but not started
      runTasks(tasks);
   }

   static void runTasks(List<Runnable> tasks)
   {
      final Phaser phaser = new Phaser(1); // "1" to register self
      // create and start threads
      for (final Runnable task: tasks)
      {
          //The int register() method adds a new unarrived thread to this phaser and returns the phase
          // number to which
         //the arrival applies. This number is known as the arrival phase number.

          phaser.register();
         new Thread()
         {
            @Override
            public void run()
            {
               try
               {
                  Thread.sleep(50+(int)(Math.random()*300));
               }
               catch (InterruptedException ie)
               {
                  System.out.println("interrupted thread");
               }
               /*
               The int arriveAndAwaitAdvance() method records arrival and waits for
               the phaser to advance (which happens after the other threads have arrived).
               It returns the phase number to which the arrival applies.

                */
               phaser.arriveAndAwaitAdvance(); // await all creation
                /*
                Comment out phaser.arriveAndAwaitAdvance();
                // await all creation and you should now observe the threads
                starting at radically different times, as illustrated below:
Thread-1 running at 1366315428871
Thread-0 running at 1366315429100
                 */
               task.run();
            }
         }.start();
      }
/*
The int arriveAndDeregister() method arrives at this phaser and deregisters from
it without waiting for others to arrive, reducing the number of threads required
to advance in future phases.
 */
      // allow threads to start and deregister self
      //phaser.arriveAndDeregister();
   //commenting the above will not allow the program to finish and run in a infinite loop
   }
}
