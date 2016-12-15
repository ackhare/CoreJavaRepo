package Threading.WithoutPainThreadingJavaWorld.Locks;

/**
 * Created by chetan on 15/12/16.
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/*
Listing 3 describes an application where a writer thread appends names to a list of names
and a pair of reader threads repeatedly dump this list to the standard output.
 */
public class RWLockDemo {
    final static int DELAY = 80;
    final static int NUMITER = 5;

    public static void main(String[] args) {
        final Names names = new Names();
/*
public interface ThreadFactory
An object that creates new threads on demand. Using thread factories
 removes hardwiring of calls to new Thread,
 enabling applications to use special thread subclasses, priorities, etc.
The simplest implementation of this interface is just:
   class SimpleThreadFactory implements ThreadFactory {
     public Thread newThread(Runnable r) {
       return new Thread(r);
     }
   }
 */

//Start of a thread factory which is used both  as a writer and reader threads
        class NamedThread implements ThreadFactory {
            private String name;

            NamedThread(String name) {
                this.name = name;
            }
//newThread(Runnable r) method, which returns a new thread whose name was
// previously passed to the NamedThread(String name) constructor.
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, name);
            }
        }
//End of a thread factory which is used both  as a writer and reader threads
        ExecutorService writer;
        //method to create an executor for the writer thread. The name is obtained from the NamedThread instance.
        writer = Executors.newSingleThreadExecutor(new NamedThread("writer"));
        Runnable wrunnable = new Runnable() {
            // runnable for the writer thread is then created and submitted to the executor.
            // The runnable repeatedly creates and adds a name to the list of names,(5 times)
            // and then delays for a short amount of time to give the reader threads a chance to run
            @Override
            public void run() {
                for (int i = 0; i < NUMITER; i++) {
                    names.add(Thread.currentThread().getName(),
                            "A" + i);
                    System.out.println("write waala run "+Thread.currentThread().getName()+" A " + i);
                    try {
                        Thread.sleep(DELAY);
                    } catch (InterruptedException ie) {
                    }
                }
            }
        };
        //submiited for execution
        writer.submit(wrunnable);
//A pair of executors for the reader threads are now created along with a shared runnable for
// repeatedly dumping the names list. This runnable is submitted to each of the reader executors.
        ExecutorService reader1;
        reader1 = Executors.newSingleThreadExecutor(new NamedThread("reader1"));
        ExecutorService reader2;
        reader2 = Executors.newSingleThreadExecutor(new NamedThread("reader2"));
        Runnable rrunnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < NUMITER; i++)
                    System.out.println("read waala run "+Thread.currentThread().getName()+" A " + i);
                    names.dump(Thread.currentThread().getName());
            }
        };
        reader1.submit(rrunnable);
        reader2.submit(rrunnable);
//important that writer is shutdown before writer
        // main() invokes shutdown() on each executor to initiate an orderly shutdown
        // of the executor as soon as it finishes.
        reader1.shutdown();
        reader2.shutdown();
        writer.shutdown();
    }
}
//Names is a simple class that demonstrates read-write locks.

class Names {
    // It first declares list-of-names,
    // reentrant read-write lock,
// read-lock, and write-lock fields
// followed by a constructor that initializes these fields.
    private final List<String> names;
//
    private final ReentrantReadWriteLock lock;
    private final Lock readLock, writeLock;

    Names() {
        names = new ArrayList<>();
        lock = new ReentrantReadWriteLock();
        readLock = lock.readLock();
        writeLock = lock.writeLock();
    }
//is used in writer's thread
    /*
    The void add(String threadName, String name) method is invoked by the writer thread to
    add a new name. The threadName argument is used to identify the writer thread
    (perhaps we might want to add more writer threads) and the name argument identifies the name
    to be added to the list.
     */
    void add(String threadName, String name) {
        //This method first executes writeLock.lock(); to acquire the write lock and
        // then outputs the number of threads waiting to acquire the read (0 to 2) or write (1) lock.
        writeLock.lock();
        try {
            System.out.printf("%s: num waiting threads in add = %d%n",
                    threadName, lock.getQueueLength());
            // After adding the name to the list, it executes writeLock.unlock(); to release the write lock.
            names.add(name);
        } finally {
            writeLock.unlock();
        }
    }
    //is used in readers's thread
    //The void dump(String threadName) method is similar to add() except for iterating over the list of names,
    // outputting each name, and sleeping for a random amount of time.
    void dump(String threadName) {
        readLock.lock();
        try {
            System.out.printf("%s: num waiting threads in dunp = %d%n",
                    threadName, lock.getQueueLength());
            Iterator<String> iter = names.iterator();
            while (iter.hasNext()) {
                System.out.printf(" dump %s: %s%n", threadName, iter.next());
                try {
                    Thread.sleep((int) (Math.random() * 100));
                } catch (InterruptedException ie) {
                }
            }
        } finally {
            readLock.unlock();
        }
    }
}
//In this example condition is not used looks like it is appropriate for producer consumer type examples

/*
writer: num waiting threads in add = 0
writer A 0
reader1: num waiting threads in dunp = 1
reader2: num waiting threads in dunp = 0
 dump reader2: A0
 dump reader1: A0
writer: num waiting threads in add = 2
writer A 1
reader2: num waiting threads in dunp = 1
 dump reader2: A0
reader1: num waiting threads in dunp = 0
 dump reader1: A0
 dump reader2: A1
reader2: num waiting threads in dunp = 0
 dump reader2: A0
 dump reader2: A1
 dump reader1: A1
reader2: num waiting threads in dunp = 0
 dump reader2: A0
 dump reader2: A1
writer: num waiting threads in add = 2
writer A 2
reader1: num waiting threads in dunp = 1
 dump reader1: A0
reader2: num waiting threads in dunp = 0
 dump reader2: A0
 dump reader1: A1
 dump reader2: A1
 dump reader2: A2
 dump reader1: A2
writer: num waiting threads in add = 1
writer A 3
reader1: num waiting threads in dunp = 0
 dump reader1: A0
 dump reader1: A1
 dump reader1: A2
 dump reader1: A3
writer: num waiting threads in add = 1
writer A 4
reader1: num waiting threads in dunp = 0
 dump reader1: A0
 dump reader1: A1
 dump reader1: A2
 dump reader1: A3
 dump reader1: A4
 */