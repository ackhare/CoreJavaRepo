package Threading.WithoutPainThreadingJavaWorld.ForKJoin;

/**
 * Created by chetan on 21/12/16.
 */

import java.util.Arrays;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/*
Listing 8's ForkJoinDemo class declares a SortedTask nested class that describes a resultless
fork/join task(RecursiveAction) for sorting a long integer array.

The key to this class is the overriding protected void compute() method,
which is called by a worker thread to sort part of the array.
 */
public class ForkJoinDemo {
    static class SortTask extends RecursiveAction {
        private final long[] array;
        //lower and upper bound of array
        private final int lo, hi;

        SortTask(long[] array, int lo, int hi) {
            this.array = array;
            this.lo = lo;
            this.hi = hi;
        }

        //without lowerr and upper bound
        SortTask(long[] array) {
            this(array, 0, array.length);
        }

        private final static int THRESHOLD = 1000;

        @Override
        protected void compute() {
            if (hi - lo < THRESHOLD) {
                //no fork and join
                sortSequentially(lo, hi);//Arrays.Sort
            } else {
                //
                int mid = (lo + hi) >>> 1;
                //fork , invoke and join
                /*
                    public static void invokeAll(ForkJoinTask<?> t1, ForkJoinTask<?> t2) {
        t2.fork();
        t1.invoke();
        t2.join();
    }
                 */


                //This task is divided into two sub task where we take from low to mid and then from mid two high ,
                invokeAll(new SortTask(array, lo, mid),
                        new SortTask(array, mid, hi));
                merge(lo, mid, hi);
            }
        }

        private void sortSequentially(int lo, int hi) {
            Arrays.sort(array, lo, hi);
        }

        private void merge(int lo, int mid, int hi) {
            // Arrays.copyOfRange--> Copies the specified range of the specified array into a new array.
            long[] buf = Arrays.copyOfRange(array, lo, mid);
            for (int i = 0, j = lo, k = mid; i < buf.length; j++)
                array[j] = (k == hi || buf[i] < array[k]) ? buf[i++] : array[k++];
        }
    }

    public static void main(String[] args) {
        long[] array = new long[300000];
        for (int i = 0; i < array.length; i++)
            array[i] = (long) (Math.random() * 10000000);
        long[] array2 = new long[array.length];
        System.arraycopy(array, 0, array2, 0, array.length);
//two arrays are created
        //one will be sorted sequentially and one will be sorted via forked join
        long startTime = System.currentTimeMillis();
        Arrays.sort(array, 0, array.length - 1);
        System.out.printf("sequential sort completed in %d millis%n",
                System.currentTimeMillis() - startTime);
        for (int i = 0; i < array.length; i++)
            System.out.println(array[i]);

        System.out.println();

        ForkJoinPool pool = new ForkJoinPool();
        startTime = System.currentTimeMillis();
        //Now the above sorttask of recursve action isa called
        pool.invoke(new SortTask(array2));
        /*
     poo.invoke()--->        * Performs the given task, returning its result upon completion.
     * If the computation encounters an unchecked Exception or Error,
     * it is rethrown as the outcome of this invocation.
         */
        System.out.printf("parallel sort completed in %d millis%n",
                System.currentTimeMillis() - startTime);
        for (int i = 0; i < array2.length; i++)
            System.out.println(array2[i]);
    }
}
