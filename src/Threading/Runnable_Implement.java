package Threading;

/**
 * Created by chetan on 1/11/15.
 */
public class Runnable_Implement {
    public static void main(String[] args) {
        Runnable myRunnable = new Runnable(){

            public void run(){
                System.out.println("Runnable running");
            }
        };

        Thread thread = new Thread(myRunnable);
        /*
        When the thread is started it will call the run() method of the MyRunnable instance instead of
        executing it's own run() method. The above example would print out the text "MyRunnable running".
         */
        thread.start();
        System.out.println(thread.getName());
    }
}
