package Threading;

import java.util.concurrent.Semaphore;

/**
 * Created by chetan on 4/11/15.
 */
/*
Here is a simplified example of two threads signaling each other using a Semaphore:

 */
public class SemaphoreSignalling {
    public static void main(String[] args) {

        /**
         * We allow 10 concurrent threads to access the cache at any given time
         */
        Semaphore semaphore = new Semaphore(2);

        SendingThread sender = new SendingThread(semaphore);

        ReceivingThread receiver = new ReceivingThread(semaphore);

        receiver.start();
        sender.start();
    }
}
class ReceivingThread extends Thread {
    Semaphore semaphore = null;

    public ReceivingThread(Semaphore semaphore){
        this.semaphore = semaphore;
    }

    public void run(){
        while(true){
            this.semaphore.release();
            //receive signal, then do something...
        }
    }
}

class SendingThread extends Thread {
    Semaphore semaphore = null;

    public SendingThread(Semaphore semaphore){
        this.semaphore = semaphore;
    }

    public void run(){
        while(true){
            //do something, then signal
            try {
                this.semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

