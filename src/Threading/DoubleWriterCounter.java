package Threading;

/**
 * Created by chetan on 7/11/15.
 */
/*
http://tutorials.jenkov.com/java-concurrency/non-blocking-algorithms.html
Here is a simple double writer counter class that shows how that could look:
 */
public class DoubleWriterCounter {

    private volatile long countA = 0;
    private volatile long countB = 0;

    /**
     * Only one (and the same from thereon) thread may ever call this method,
     * or it will lead to race conditions.
     */
    public void incA() { this.countA++;  }


    /**
     * Only one (and the same from thereon) thread may ever call this method,
     * or it will lead to race conditions.
     */
    public void incB() { this.countB++;  }


    /**
     * Many reading threads may call this method
     */
    public long countA() { return this.countA; }


    /**
     * Many reading threads may call this method
     */
    public long countB() { return this.countB; }
}
