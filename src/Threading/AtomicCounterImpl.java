package Threading;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by chetan on 7/11/15.
 */
/*
we want to avoid - synchronized blocks and wait() - notify() calls etc.
Instead of the two synchronized blocks we can use one of Java's atomic
variables. In this case the AtomicLong. Here is how the same counter
class could look using an AtomicLong instead:
 */
public class AtomicCounterImpl {
}
class AtomicCounter {
    private AtomicLong count = new AtomicLong(0);

    public void inc() {
        boolean updated = false;
        while(!updated){
            long prevCount = this.count.get();
            updated = this.count.compareAndSet(prevCount, prevCount + 1);
        }
    }

    public long count() {
        return this.count.get();
    }
}