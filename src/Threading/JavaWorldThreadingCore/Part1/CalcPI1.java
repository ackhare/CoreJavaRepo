package Threading.JavaWorldThreadingCore.Part1;

/**
 * Created by chetan on 26/11/16.
 */
// CalcPI1.java

    /*
    To demonstrate sleep(long millis), I've written a CalcPI1 application. T
    hat application starts a new thread that uses a mathematic algorithm to
    calculate the value of the mathematical constant pi.
    While the new thread calculates, the starting thread pauses for 10 milliseconds by calling sleep(long millis). After the
starting thread awakes, it prints the pi value, which the new thread stores in variable pi. Listing 3 presents CalcPI1's
source code:
     */
class CalcPI1 {
    public static void main(String[] args) {
        MyThread2 mt = new MyThread2();
        mt.start();
        try {
            Thread.sleep(10); // Sleep for 10 milliseconds
        } catch (InterruptedException e) {
        }
        //TODO
        //Quite important to note the way pi has been called
        //It can be used as Pi
        System.out.println("pi = " + mt.pi);
    }
}

class MyThread2 extends Thread {
    boolean negative = true;
    double pi; // Initializes to 0.0, by default

    //just a task that calculate pi
    public void run() {
        for (int i = 3; i < 100000; i += 2) {
            if (negative)
                pi -= (1.0 / i);
            else
                pi += (1.0 / i);
            negative = !negative;
        }
        pi += 1.0;
        pi *= 4.0;
        System.out.println("Finished calculating PI");
    }
}

/*
Finished calculating PI
pi = 3.1415726535897894
 */