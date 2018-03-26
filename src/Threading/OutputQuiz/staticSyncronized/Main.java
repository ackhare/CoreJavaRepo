package Threading.OutputQuiz.staticSyncronized;

/**
 * Created by chetan on 26/3/18.
 */
class Thread1 extends Thread {
B b= new B();


    public void run() {
        b.doThisInstance();
        B.doThis();

    }

}

public class Main {

    public static void main(String[] args) {
        Thread thread1 = new Thread1();
        Thread thread2 = new Thread1();
        thread1.start();
        thread2.start();
    }

}
