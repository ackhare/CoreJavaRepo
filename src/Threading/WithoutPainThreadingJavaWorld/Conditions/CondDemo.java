package Threading.WithoutPainThreadingJavaWorld.Conditions;

/**
 * Created by chetan on 15/12/16.
 */
/*
Listing 2 presents four classes: CondDemo, Shared, Producer, and Consumer.

CondDemo drives the application,
Shared encapsulates the logic for setting and getting a shared variable's value,
Producer describes the producer thread,
and Consumer describes the consumer thread.
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//CondDemo drives the application,
public class CondDemo {
    public static void main(String[] args) {
        //Shared encapsulates the logic for setting and getting a shared variable's value,
        Shared s = new Shared();
        /*
         It passes the Shared instance to the Producer and Consumer thread
         instance constructors and starts these threads.
         */
        /*
        The Producer and Consumer constructors are invoked on the main thread.
         */
        new Producer(s).start();
        new Consumer(s).start();
    }
}

//Shared encapsulates the logic for setting and getting a shared variable's value,
class Shared {
    // Fields c and available are volatile so that writes to them are visible to
    // the various threads. Fields lock and condition are final so that there
    // initial values are visible to the various threads.
    // TODO very important (The Java memory model promises that, after a final field has been initialized,] any thread will
    // see the same [correct] value.)

    private volatile char c;
    private volatile boolean available;
    //Lock and condition defined in here as final
    private final Lock lock;
    private final Condition condition;

    Shared() {
        c = '\u0000';
        //available is intialiazed as false and will be set to true when setting the value of c and then is again set to false while sertting the value
        available = false;
        //The Java memory model promises that, after a final field has been initialized,] any thread will
        // see the same [correct] value.)
        //Here both final valuers are intialiazed
        lock = new ReentrantLock();
        condition = lock.newCondition();
    }

    //This lock is made available to the producer and consumer threads via the Lock getLock() method
    Lock getLock() {
        return lock;
    }

    //Below both methods are setting and getting shared variable c value using shared
    // availve variable with both being volatile
    char getSharedChar() {
        //locking has been done
        System.out.println("locking in getter of Shared");
        lock.lock();
        try {
            //So if no avalbility wait for it
            while (!available)
                try {
                    // forces the current thread to wait until it's signalled or interrupted.
                    condition.await();
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            System.out.println("getting  the value of shared variable c and signalling");
            available = false;
            //wakes up one waiting thread.
//counterpart of notify()
            //The consumer will signal the condition to wake up the producer when it has consumed the character.
            condition.signal();
        } finally {
            lock.unlock();
            return c;
        }
    }

    void setSharedChar(char c) {
        System.out.println("locking in setter of Shared");
        lock.lock();
        try {
           while (available)
                try {
                    condition.await();
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
                //setting of shared variable c
            this.c = c;
            System.out.println("setting the value of shared variable c and signalling");
           //As long as available is true, the producer invokes the condition's
            // await() method to wait for available to become false

                     /*
            After exiting the loop, the producer records the new character,
            assigns true to available to indicate
            that a new character is available for consumption,
             and signals the condition to wake up a waiting consumer.
             */
            available = true;
            //wakes up one waiting thread.
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
}
//Both are quite same only one is calling setter method of Shared and one is calling mgetter method of Shared
class Producer extends Thread {
    // l is final because it's initialized on the main thread(via Shared where Condition also get''s intialiazed) and accessed on the
    // producer thread.

    private final Lock l;

    // s is final because it's initialized on the main thread and accessed on the
    // producer thread.
//it's necessary for this instance to be visible to them, especially when these threads run on different cores.
    private final Shared s;
//both constructores does same work and share same value
    Producer(Shared s) {
        this.s = s;
        //gets intialiazed lock
        l = s.getLock();
    }

    @Override
    public void run() {
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            System.out.println("locking in produser");
            l.lock();
            //The below is being done in a locked context
            s.setSharedChar(ch);

            System.out.println(ch + " produced by producer.");
            l.unlock();
        }
    }
}

class Consumer extends Thread {
    // l is final because it's initialized on the main thread and accessed on the
    // consumer thread.

    private final Lock l;

    // s is final because it's initialized on the main thread and accessed on the
    // consumer thread.
//it's necessary for this instance to be visible to them, especially when these threads run on different cores.
    private final Shared s;

    Consumer(Shared s) {
        this.s = s;
        l = s.getLock();
    }

    @Override
    public void run() {
        char ch;
        do {
            System.out.println("locking in consumer");
            l.lock();
            //The below is being done in a locked context
            ch = s.getSharedChar();
            System.out.println(ch + " consumed by consumer.");
            l.unlock();
        }
        while (ch != 'Z');
    }
}


/*
locking in produser
locking in setter of Shared
setting the value of shared variable c and signalling
A produced by producer.
locking in produser
locking in setter of Shared
locking in consumer
locking in getter of Shared
getting  the value of shared variable c and signalling
A consumed by consumer.
locking in consumer
locking in getter of Shared
setting the value of shared variable c and signalling
B produced by producer.
locking in produser
getting  the value of shared variable c and signalling
B consumed by consumer.
locking in consumer
locking in setter of Shared
setting the value of shared variable c and signalling
C produced by producer.
locking in produser
locking in getter of Shared
getting  the value of shared variable c and signalling
C consumed by consumer.
locking in consumer
locking in setter of Shared
setting the value of shared variable c and signalling
D produced by producer.
locking in produser
locking in getter of Shared
getting  the value of shared variable c and signalling
D consumed by consumer.
locking in consumer
locking in setter of Shared
setting the value of shared variable c and signalling
E produced by producer.
locking in produser
locking in getter of Shared
getting  the value of shared variable c and signalling
E consumed by consumer.
locking in consumer
locking in setter of Shared
setting the value of shared variable c and signalling
F produced by producer.
locking in produser
locking in getter of Shared
getting  the value of shared variable c and signalling
F consumed by consumer.
locking in consumer
locking in setter of Shared
setting the value of shared variable c and signalling
G produced by producer.
locking in produser
locking in getter of Shared
getting  the value of shared variable c and signalling
G consumed by consumer.
locking in consumer
locking in setter of Shared
setting the value of shared variable c and signalling
H produced by producer.
locking in produser
locking in getter of Shared
getting  the value of shared variable c and signalling
H consumed by consumer.
locking in consumer
locking in setter of Shared
setting the value of shared variable c and signalling
I produced by producer.
locking in produser
locking in getter of Shared
getting  the value of shared variable c and signalling
I consumed by consumer.
locking in consumer
locking in setter of Shared
setting the value of shared variable c and signalling
J produced by producer.
locking in produser
locking in getter of Shared
getting  the value of shared variable c and signalling
J consumed by consumer.
locking in consumer
locking in setter of Shared
setting the value of shared variable c and signalling
K produced by producer.
locking in produser
locking in getter of Shared
getting  the value of shared variable c and signalling
K consumed by consumer.
locking in consumer
locking in setter of Shared
setting the value of shared variable c and signalling
L produced by producer.
locking in produser
locking in setter of Shared
locking in getter of Shared
getting  the value of shared variable c and signalling
L consumed by consumer.
locking in consumer
locking in getter of Shared
setting the value of shared variable c and signalling
M produced by producer.
locking in produser
locking in setter of Shared
getting  the value of shared variable c and signalling
M consumed by consumer.
locking in consumer
locking in getter of Shared
setting the value of shared variable c and signalling
N produced by producer.
locking in produser
locking in setter of Shared
getting  the value of shared variable c and signalling
N consumed by consumer.
locking in consumer
locking in getter of Shared
setting the value of shared variable c and signalling
O produced by producer.
locking in produser
locking in setter of Shared
getting  the value of shared variable c and signalling
O consumed by consumer.
locking in consumer
locking in getter of Shared
setting the value of shared variable c and signalling
P produced by producer.
locking in produser
locking in setter of Shared
getting  the value of shared variable c and signalling
P consumed by consumer.
locking in consumer
locking in getter of Shared
setting the value of shared variable c and signalling
Q produced by producer.
locking in produser
locking in setter of Shared
getting  the value of shared variable c and signalling
Q consumed by consumer.
locking in consumer
locking in getter of Shared
setting the value of shared variable c and signalling
R produced by producer.
locking in produser
locking in setter of Shared
getting  the value of shared variable c and signalling
R consumed by consumer.
locking in consumer
locking in getter of Shared
setting the value of shared variable c and signalling
S produced by producer.
locking in produser
locking in setter of Shared
getting  the value of shared variable c and signalling
S consumed by consumer.
locking in consumer
locking in getter of Shared
setting the value of shared variable c and signalling
T produced by producer.
locking in produser
locking in setter of Shared
getting  the value of shared variable c and signalling
T consumed by consumer.
locking in consumer
locking in getter of Shared
setting the value of shared variable c and signalling
U produced by producer.
locking in produser
locking in setter of Shared
getting  the value of shared variable c and signalling
U consumed by consumer.
locking in consumer
locking in getter of Shared
setting the value of shared variable c and signalling
V produced by producer.
locking in produser
locking in setter of Shared
getting  the value of shared variable c and signalling
V consumed by consumer.
locking in consumer
locking in getter of Shared
setting the value of shared variable c and signalling
W produced by producer.
locking in produser
locking in setter of Shared
getting  the value of shared variable c and signalling
W consumed by consumer.
locking in consumer
locking in getter of Shared
setting the value of shared variable c and signalling
X produced by producer.
locking in produser
locking in setter of Shared
getting  the value of shared variable c and signalling
X consumed by consumer.
locking in consumer
locking in getter of Shared
setting the value of shared variable c and signalling
Y produced by producer.
locking in produser
locking in setter of Shared
getting  the value of shared variable c and signalling
Y consumed by consumer.
locking in consumer
locking in getter of Shared
setting the value of shared variable c and signalling
Z produced by producer.
getting  the value of shared variable c and signalling
Z consumed by consumer.

Process finished with exit code 0

 */