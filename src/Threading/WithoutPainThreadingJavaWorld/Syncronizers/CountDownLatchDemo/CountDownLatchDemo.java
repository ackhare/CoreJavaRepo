package Threading.WithoutPainThreadingJavaWorld.Syncronizers.CountDownLatchDemo;

/**
 * Created by chetan on 17/11/15.
 */
import java.util.concurrent.CountDownLatch;

/*

------------Algo of Countlatch

 Countdown latches are useful for decomposing a problem into smaller pieces and giving a piece to a
 separate thread as follows:


 */
public class CountDownLatchDemo
{
    final static int N = 3;

    public static void main(String[] args) throws InterruptedException
    {
       /*
       CountDownLatchDemo's main() method creates a startSignal
 countdown latch initialized to 1 and a doneSignal countdown latch initialized to 3, the number of worker threads.
        */
       /*
        A main thread creates a countdown latch with a count of 1 that's
        used as a "starting gate" to start a group of
    worker threads simultaneously
        */
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(N);
        for (int i = 0; i < N; ++i)
        {
            // / create and start threads
        /*
        main() proceeds to create three worker threads described by Worker and then start these threads.
         After outputting a
message, main() executes startSignal.countDown() to tell the worker threads that they can proceed.
         */
            new Thread(new Worker(startSignal, doneSignal)).start();
        }
            System.out.println("about to let threads proceed");
        /*
           . Each worker thread waits on the latch and the main thread decrements this latch to
    let all worker threads proceed. The main thread waits on another countdown latch initialized to
    the number of worker  threads. When a worker thread completes, it decrements this count.
    After the count reaches zero (meaning that all
    worker threads have finished), the main thread proceeds and gathers the results.
         */
        startSignal.countDown(); // let all threads proceed
        System.out.println("doing work");
        System.out.println("waiting for threads to finish");
        /*
        After outputting a few more messages, main() executes doneSignal.await() to wait until
        all worker threads have  finished.

         */
        System.out.println(Thread.currentThread().getName()+" before main awit for worker threads");
        doneSignal.await(); // wait for all threads to finish
        System.out.println("main thread terminating");
    }
}
//worker thread


class Worker implements Runnable
{
    private final static int N = 5;

    private final CountDownLatch startSignal;
    private final CountDownLatch doneSignal;

    Worker(CountDownLatch startSignal, CountDownLatch doneSignal)
    {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
    }
    /*
    Worker's constructor saves these latches, and its run() method performs some work. Before performing this work,
    the thread executes startSignal.await() to block until the main thread allows it to proceed (by executing
    startSignal.countDown()).
     */
    @Override
    public void run()
    {
        try
        {
            String name = Thread.currentThread().getName();
            startSignal.await();
//            The worker then enters a loop to simulate doing some work by alternately outputting messages
//            and sleeping for random
//            amounts of time.
            for (int i = 0; i < N; i++)
            {
                System.out.printf("thread %s is working%n", name);
                try
                {
                    Thread.sleep((int)(Math.random()*300));
                }
                catch (InterruptedException ie)
                {
                }
            }
            System.out.printf("thread %s finishing%n", name);
            /*
           It then executes doneSignal.countDown() to decrement the doneSignal countdown latch
   so that the main
  thread will eventually wake up.
             */
            /*
            When a worker thread completes, it decrements this count.
            After the count reaches zero (meaning that all worker threads
 have finished), the main thread proceeds and gathers the results.

             */
          doneSignal.countDown();
        }
        catch (InterruptedException ie)
        {
            System.out.println("interrupted");
        }
    }
}

/*
about to let threads proceed
thread Thread-2 is working
thread Thread-1 is working
thread Thread-0 is working
doing work
waiting for threads to finish
thread Thread-1 is working
thread Thread-2 is working
thread Thread-0 is working
thread Thread-1 is working
thread Thread-2 is working
thread Thread-0 is working
thread Thread-2 is working
thread Thread-1 is working
thread Thread-1 is working
thread Thread-2 is working
thread Thread-0 is working
thread Thread-2 finishing
thread Thread-1 finishing
thread Thread-0 is working
thread Thread-0 finishing
main thread terminating

Process finished with exit code 0
 */