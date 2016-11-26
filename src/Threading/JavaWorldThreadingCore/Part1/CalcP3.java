package Threading.JavaWorldThreadingCore.Part1;

/**
 * Created by chetan on 26/11/16.
 */
class CalcPI3
{
   public static void main (String [] args)
   {
      MyThread5 mt = new MyThread5 ();
      mt.start ();
//       CalcPI3's starting thread(main) waits for the thread that associates with the MyThread object,
// referenced by mt, to terminate.
//  The starting thread then prints pi' s value,
// which is identical to the value that CalcPI2 outputs
     try
      {
          System.out.println(Thread.currentThread().getName());
          mt.join ();

      }
      catch (InterruptedException e)
      {
      }
      System.out.println ("pi = " + mt.pi);
   }
}
class MyThread5 extends Thread
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