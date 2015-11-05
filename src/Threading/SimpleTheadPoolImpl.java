package Threading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by chetan on 5/11/15.
 */
public class SimpleTheadPoolImpl {
}
/*
 A ThreadPool class which is the public interface to the thread pool, and a PoolThread class which implements the
 threads that execute the tasks.

 */
class ThreadPool {

    private BlockingQueue taskQueue = null;
    private List<PoolThread> threads = new ArrayList<PoolThread>();
    private boolean isStopped = false;

    public ThreadPool(int noOfThreads, int maxNoOfTasks){
        taskQueue = new PriorityBlockingQueue(maxNoOfTasks);

        for(int i=0; i<noOfThreads; i++){
            threads.add(new PoolThread(taskQueue));
        }
        for(PoolThread thread : threads){
            thread.start();
        }
    }
  /*  To execute a task the method ThreadPool.execute(Runnable r) is
  called with a Runnable implementation as parameter.
  The Runnable is enqueued in the blocking queue internally, waiting to be dequeued.
*/
    public synchronized void  execute(Runnable task) throws Exception{
        if(this.isStopped) throw
            new IllegalStateException("ThreadPool is stopped");

        this.taskQueue.enqueue(task);
    }
  /*  To stop the ThreadPool the method ThreadPool.stop() is called.
  The stop called is noted internally in the isStopped member.
  Then each thread in the pool is stopped by calling doStop() on each thread.(by using interrupt)
  Notice how the execute() method will throw an IllegalStateException
   if execute() is called after stop() has been called.
*/
   public synchronized void stop(){
        this.isStopped = true;
        for(PoolThread thread : threads){
           thread.doStop();
        }
    }

}


class PoolThread extends Thread {

    private BlockingQueue taskQueue = null;
    private boolean       isStopped = false;

    public PoolThread(BlockingQueue queue){
        taskQueue = queue;
    }

    public void run(){
        while(!isStopped()){
            try{
            /* The Runnable will be dequeued by an idle PoolThread and executed. You can see this in the
             * PoolThread.run() method. After execution the PoolThread loops and tries to dequeue a task again, until
             * stopped.
   */
                Runnable runnable = (Runnable) taskQueue.dequeue();
                runnable.run();
            } catch(Exception e){
                //log or otherwise report exception,
                //but keep pool thread alive.
            }
        }
    }

    public synchronized void doStop(){
        isStopped = true;
        this.interrupt(); //break pool thread out of dequeue() call.
    }

    public synchronized boolean isStopped(){
        return isStopped;
    }
}


