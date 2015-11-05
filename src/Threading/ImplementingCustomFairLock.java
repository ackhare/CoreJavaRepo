package Threading;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chetan on 3/11/15.
 */
public class ImplementingCustomFairLock {
}

class FairLock {
    private boolean isLocked = false;
    private Thread lockingThread = null;
    private List<QueueObject> waitingThreads =
            new ArrayList<QueueObject>();

    public void lock() throws InterruptedException {
        //FairLock creates a new instance of QueueObject and enqueue it for each thread calling lock().
        QueueObject queueObject = new QueueObject();
        boolean isLockedForThisThread = true;
        synchronized (this) {
            waitingThreads.add(queueObject);
        }
/*
 that every thread calling lock() is now queued, and only the first thread in the queue is allowed to lock the FairLock
 instance, if it is unlocked. All other threads are parked waiting until they reach the top of the queue.

 */
        while (isLockedForThisThread) {
            synchronized (this) {
                isLockedForThisThread =
                        isLocked || waitingThreads.get(0) != queueObject;
                //if it is not the first object or if isLockedForThisThread is false
                if (!isLockedForThisThread) {
                    isLocked = true;
                    //this is to bring the thread in queue to the top we need to remove some oibjects
                    waitingThreads.remove(queueObject);
                    lockingThread = Thread.currentThread();
                    return;
                }
            }
            try {
                queueObject.doWait();
            } catch (InterruptedException e) {
                synchronized (this) {
                    waitingThreads.remove(queueObject);
                }
                throw e;
            }
        }
    }

    public synchronized void unlock() {
        if (this.lockingThread != Thread.currentThread()) {
            throw new IllegalMonitorStateException(
                    "Calling thread has not locked this lock");
        }
        isLocked = false;
        lockingThread = null;
//The thread calling unlock() will take the top QueueObject in the queue and call doNotify() on it, to awaken the thread
//waiting on that object. This way only one waiting thread is awakened at a time, rather than all waiting threads. This
//part is what governs the fairness of the FairLock.

        if (waitingThreads.size() > 0) {
            waitingThreads.get(0).doNotify();
        }
    }
}


class QueueObject {

    private boolean isNotified = false;

    public synchronized void doWait() throws InterruptedException {
        while (!isNotified) {
            this.wait();
        }
        this.isNotified = false;
    }

    public synchronized void doNotify() {
        this.isNotified = true;
        this.notify();
    }

    public boolean equals(Object o) {
        return this == o;
    }
}
