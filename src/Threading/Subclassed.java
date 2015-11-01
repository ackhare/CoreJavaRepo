package Threading;

/**
 * Created by chetan on 1/11/15.
 */
public class Subclassed {
    public static void main(String[] args) {
        //The start() call will return as soon as the thread is started. It will not wait until the run() method is done.
        // The run() method will execute as
        // if executed by a different CPU. When the run() method executes it will print out the text "MyThread running".
        Thread thread = new Thread(){
            public void run(){
                System.out.println("Thread Running");
            }
        };

        thread.start();
        System.out.println(thread.getName());
    }

}
