package Threading.WithoutPainThreadingJavaWorld.PhaserDemo;

/**
 * Created by chetan on 17/11/15.
 */
import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.Phaser;
/*
Listing 8 is based on the first code example in Phaser's Javadoc. This example shows how to use Phaser instead of
CountDownLatch to control a one-shot action serving a variable number of threads.

        The application creates a pair of runnable tasks that each report the time (in milliseconds relative to the Unix
        epoch) at which its starts to run. Compile and run this application, and you should observe output that's
        similar to the following:

        Thread-0 running at 1366315297635 Thread-1 running at 1366315297635

        As you would expect from countdown latch behavior, both threads start running at (in this case) the same time
        even though a thread may have been delayed by as much as 349 milliseconds thanks to the presence of
        Thread.sleep().

        Comment out phaser.arriveAndAwaitAdvance(); // await all creation and you should now observe the threads
        starting at radically different times, as illustrated below:

        Thread-1 running at 1366315428871 Thread-0 running at 1366315429100
*/
public class PhaserDemo
{
    public static void main(String[] args)
    {
        List<Runnable> tasks = new ArrayList<>();
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
        runTasks(tasks);
    }
//Most have same methods just mechanism is diffremt
    static void runTasks(List<Runnable> tasks)
    {
        final Phaser phaser = new Phaser(1); // "1" to register self
        // create and start threads
        for (final Runnable task: tasks)
        {
            phaser.register();//registring thread with phraser
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
                    phaser.arriveAndAwaitAdvance(); // await all creation
                    task.run();
                }
            }.start();
        }

        // allow threads to start and deregister self
        phaser.arriveAndDeregister();
    }
}


/*
Thread-0 running at 1481476918807
Thread-1 running at 1481476918807

Process finished with exit code 0
 */