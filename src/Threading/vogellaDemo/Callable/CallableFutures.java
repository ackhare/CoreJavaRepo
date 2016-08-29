package Threading.vogellaDemo.Callable;

/**
 * Created by chetan on 26/8/16.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class MyCallable implements Callable<Long> {
    @Override
    public Long call() throws Exception {
        long sum = 0;
        for (long i = 0; i <= 100; i++) {
            sum += i;
        }
        return sum;
    }

}


public class CallableFutures {
    private static final int NTHREDS = 10;

    public static void main(String[] args) {
/*


The Callable object uses generics to define the type of object which is returned.

 */
        ExecutorService executor = Executors.newFixedThreadPool(NTHREDS);
        List<Future<Long>> list = new ArrayList<Future<Long>>();
        for (int i = 0; i < 20000; i++) {
            Callable<Long> worker = new MyCallable();
//If you submit a Callable object to an Executor the framework returns an object of type java.util.concurrent.Future .
//On the Executor you can use the method submit to submit a Callable and to get a future.
            Future<Long> submit = executor.submit(worker);

            /* Future exposes methods allowing a client to monitor the progress of a task being executed by a
            different thread.
      Therefore a Future object can be used to check the status of a Callable and to retrieve the result from the
            Callable .
            */
            list.add(submit);
        }

        long sum = 0;
        System.out.println(list.size());
        // now retrieve the result
        /*

        To retrieve the result of the future use the get() method.
                */
        for (Future<Long> future : list) {
            try {
                sum += future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println(sum);
        executor.shutdown();
    }
}

