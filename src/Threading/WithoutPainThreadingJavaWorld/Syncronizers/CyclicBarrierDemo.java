package Threading.WithoutPainThreadingJavaWorld.Syncronizers;

/**
 * Created by chetan on 28/11/16.
 */





/*
A cyclic barrier is a thread-synchronization construct that lets a set of t
hreads wait for each other to reach a common barrier point.
 */


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierDemo {
    public static void main(String[] args) {

        // The above main() method first creates a barrier action that's run by the
// last thread to reach the barrier
        Runnable action = new Runnable() {
            //this will be run by last thread
            @Override
            public void run() {
                String name =
                        Thread.currentThread().getName();
                System.out.printf("Thread %s " + "executing barrier action.%n",
                        name);
            }
        };


        /*
        // Next, a cyclic barrier is created.
// When three players arrive it trips and executes the barrier action.
         */

        /*
    CyclicBarrier(int nthreads, Runnable barrierAction)
    causes a maximum of nthreads-1
    threads to wait at the barrier.
    When one more thread arrives,
    it executes the nonnull barrierAction and then all threads proceed.
    This action is
    useful for updating shared state before any of the threads continue.
         */
        final CyclicBarrier barrier = new CyclicBarrier(4, action);
        //if a nonnull Runnable was passed to barrierAction, the final thread executes
        // the runnable before the other threads continue.


        //main() now creates a runnable that outputs various status
        // messages and invokes await(), followed by a three-executor array.
        // Each executor runs this runnable and shuts down after the runnable finishes.
//This runnable will run when the
        Runnable task = new Runnable() {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                System.out.printf("%s about to join game...%n",
                        name);
                try {
                    /*
                    CyclicBarrier declares an int await() method that typically causes the calling thread
                    to wait unless the thread is the final thread
                     */
                    System.out.println(Thread.currentThread().getName()+" before awit");
                    System.out.println(barrier.await());
                    System.out.println(Thread.currentThread().getName()+" after awit");
                } catch (BrokenBarrierException bbe) {
                    System.out.println("barrier is broken");
                    return;
                } catch (InterruptedException ie) {
                    System.out.println("thread interrupted");
                    return;
                }
                catch(Exception e)
                {
                    System.out.println(e.getCause()+"exception");
                }
                System.out.printf("%s has joined game%n", name);
            }
        };
        //because 3 threads are given in cyclic barrier
        ExecutorService[] executors = new ExecutorService[]
                {

                    Executors.newSingleThreadExecutor(),
                        Executors.newSingleThreadExecutor(),
                        Executors.newSingleThreadExecutor()
                };
        for (ExecutorService executor : executors) {
            System.out.println("Inside executor");
            executor.execute(task);
            executor.shutdown();
        }
    }
}
/*
/usr/lib/jvm/java-7-oracle/bin/java -Didea.launcher.port=7539 -Didea.launcher.bin.path=/home/chetan/Downloads/idea-IU-162.1121.32/bin -Dfile.encoding=UTF-8 -classpath /usr/lib/jvm/java-7-oracle/jre/lib/charsets.jar:/usr/lib/jvm/java-7-oracle/jre/lib/deploy.jar:/usr/lib/jvm/java-7-oracle/jre/lib/ext/dnsns.jar:/usr/lib/jvm/java-7-oracle/jre/lib/ext/localedata.jar:/usr/lib/jvm/java-7-oracle/jre/lib/ext/sunec.jar:/usr/lib/jvm/java-7-oracle/jre/lib/ext/sunjce_provider.jar:/usr/lib/jvm/java-7-oracle/jre/lib/ext/sunpkcs11.jar:/usr/lib/jvm/java-7-oracle/jre/lib/ext/zipfs.jar:/usr/lib/jvm/java-7-oracle/jre/lib/javaws.jar:/usr/lib/jvm/java-7-oracle/jre/lib/jce.jar:/usr/lib/jvm/java-7-oracle/jre/lib/jfr.jar:/usr/lib/jvm/java-7-oracle/jre/lib/jfxrt.jar:/usr/lib/jvm/java-7-oracle/jre/lib/jsse.jar:/usr/lib/jvm/java-7-oracle/jre/lib/management-agent.jar:/usr/lib/jvm/java-7-oracle/jre/lib/plugin.jar:/usr/lib/jvm/java-7-oracle/jre/lib/resources.jar:/usr/lib/jvm/java-7-oracle/jre/lib/rt.jar:/home/chetan/IdeaProjects/CoreJavaRepo/out/production/CoreJavaRepo:/home/chetan/Downloads/openutils-log4j-2.0.5.jar:/home/chetan/Downloads/apache-log4j-1.2.17/log4j-1.2.17.jar:/home/chetan/Downloads/idea-IU-162.1121.32/lib/idea_rt.jar com.intellij.rt.execution.application.AppMain Threading.WithoutPainThreadingJavaWorld.Syncronizers.CyclicBarrierDemo
Inside executor
Inside executor
pool-1-thread-1 about to join game...
pool-2-thread-1 about to join game...
Inside executor
Thread pool-1-thread-1 executing barrier action.
pool-1-thread-1 has joined game
pool-2-thread-1 has joined game
pool-3-thread-1 about to join game...

 */