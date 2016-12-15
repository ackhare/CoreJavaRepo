package Threading.WithoutPainThreadingJavaWorld.Locks;

/**
 * Created by chetan on 15/12/16.
 */

/*
 Listing 1 creates two worker threads.
 Each thread first acquires a lock to ensure that it has
 complete access to the
 critical section. It then outputs some messages and sleeps for
 two seconds to simulate work. After outputting another
 message, it releases the lock.
 */

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {
    public static void main(String[] args) {
        //creates 2 threads from a fixed thread pool
        ExecutorService executor = Executors.newFixedThreadPool(2);
        //a new lock is created with fair to false
        final ReentrantLock rl = new ReentrantLock();

        class Worker implements Runnable {
            private String name;

            Worker(String name) {
                this.name = name;
            }

            @Override
            public void run() {
                // Each thread first acquires a lock to ensure that it has complete access to the critical section.
                rl.lock();
                rl.lock();
                //If we lock it two times getHeldCount is increased and is 2
                try {
                    if (rl.isHeldByCurrentThread())
                        System.out.printf("Thread %s has entered its critical section.%n",
                                name);
                    System.out.printf("Thread %s is performing work for 2 seconds.%n", name);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                    System.out.printf("Thread %s has finished working.%n", name);
                    System.out.println("Thread is held for "+rl.getHoldCount()+" times");
                } finally {
                    //now the thread is unlocked
                    rl.unlock();
                    rl.unlock();
                    //If above thread is locked for two times you need to unlock it two times
                    // or an infinite loop is
                    //ensured
                }
            }
        }
//this is where the execution starts
        System.out.println("Executors in action");
        executor.execute(new Worker("A"));
        executor.execute(new Worker("B"));
        try {
            /*
               awaitTermination---> * Blocks until all tasks have completed execution after a shutdown request,
                or the timeout occurs, or the current thread is interrupted, whichever happens first.
             */
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        executor.shutdownNow();
    }
}



/*
output

Executors in action
Thread A has entered its critical section.
Thread A is performing work for 2 seconds.
Thread A has finished working.
Thread is held for 2 times
Thread B has entered its critical section.
Thread B is performing work for 2 seconds.
Thread B has finished working.
Thread is held for 2 times

Process finished with exit code 0
 */