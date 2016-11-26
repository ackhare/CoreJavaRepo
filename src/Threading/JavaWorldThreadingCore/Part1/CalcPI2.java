package Threading.JavaWorldThreadingCore.Part1;

/**
 * Created by chetan on 26/11/16.
 */
class CalcPI2
{
   public static void main (String [] args)
   {
      MyThread4 mt = new MyThread4 ();
      mt.start ();
       //Very important technique
       /*
       isAlive() proves helpful in situations where a thread(main ) needs to wait
        for another thread(mt) to finish its run() method before
the first thread can examine the other thread's results.
Essentially, the thread that needs to wait() enters a while loop.
        */
      while (mt.isAlive()) {//my thread
          //main does not exists because i gets struck in a type of loop
             try {

                 /*
           While isAlive() returns true for the other thread(mt), the waiting (main)
           thread calls sleep(long millis) (or sleep(long millis,
  int nanos)) to periodically sleep (and avoid wasting many CPU cycles).
           */
              System.out.println(Thread.currentThread().getName());//main thread(for 5 execution)
              Thread.sleep(10); // Sleep for 10 milliseconds
          } catch (InterruptedException e) {
          }
      }
      System.out.println ("pi = " + mt.pi);
   }
}
class MyThread4 extends Thread
{
   boolean negative = true;
   double pi; // Initializes to 0.0, by default
   public void run ()
   {
      for (int i = 3; i < 100000; i += 2)
      {
           if (negative)
               pi -= (1.0 / i);
           else
               pi += (1.0 / i);
           negative = !negative;
      }
      pi += 1.0;
      pi *= 4.0;
      System.out.println ("Finished calculating PI");
   }
}
/*
output

main
Finished calculating PI
pi = 3.1415726535897894
 */