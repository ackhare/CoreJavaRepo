package Threading.vogellaDemo.ThreadPools;

/**
 * Created by chetan on 26/8/16.
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
        private static final int NTHREDS = 10;

        public static void main(String[] args) {

            /*
            The Executor framework provides example implementation of the java.util.concurrent.Executor interface, e.g.
Executors.newFixedThreadPool(int n) which will create n worker threads. The ExecutorService adds life cycle methods to
the Executor, which allows to shutdown the Executor and to wait for termination.
             */
                ExecutorService executor = Executors.newFixedThreadPool(NTHREDS);
                for (int i = 0; i < 500; i++) {
                        Runnable worker = new MyRunnable(10000000L + i);
                        executor.execute(worker);
                }
                // This will make the executor accept no new threads
                // and finish all existing threads in the queue
                executor.shutdown();


            /*


You should call shutdown first. Otherwise, you might be waiting for a very long time,
 since awaitTermination doesn't actually shut down your executor.

If you wanted to wait for tasks to complete, rather than wait for the executor to shut down,
then you should use invokeAll.

             */
                // Wait until all threads are finish
            try {
                executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ;

                System.out.println("Finished all threads");
        }
}
