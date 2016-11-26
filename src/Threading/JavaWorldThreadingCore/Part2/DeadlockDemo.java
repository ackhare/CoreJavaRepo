package Threading.JavaWorldThreadingCore.Part2;

/**
 * Created by chetan on 26/11/16.
 */
// DeadlockDemo.java
class DeadlockDemo
{
   public static void main (String [] args)
   {
      FinTrans5 ft = new FinTrans5 ();
      TransThread5 tt1 = new TransThread5 (ft, "Deposit Thread");
       TransThread5 tt2 = new TransThread5 (ft, "Withdrawal Thread");
      tt1.start ();
      tt2.start ();
   }
}
class FinTrans5
{
   public static String transName;
   public static double amount;
}
class TransThread5 extends Thread
{
   private FinTrans5 ft;
   private static String anotherSharedLock = "";
    TransThread5 (FinTrans5 ft, String name)
   {
      super (name); // Save thread's name
      this.ft = ft; // Save reference to financial transaction object
   }
   public void run ()
   {
      for (int i = 0; i < 100; i++)
      {
           if (getName ().equals ("Deposit Thread"))
           {
               synchronized (ft)
               {
                  synchronized (anotherSharedLock)
                  {
                     ft.transName = "Deposit";
                     try
                     {
                        Thread.sleep ((int) (Math.random () * 1000));
                     }
                     catch (InterruptedException e)
                     {
                     }
                     ft.amount = 2000.0;
                     System.out.println (ft.transName + " " + ft.amount);
                  }
               }
           }
           else
           {
               synchronized (anotherSharedLock)
               {
                  synchronized (ft)
                  {
                     ft.transName = "Withdrawal";
                     try
                     {
                        Thread.sleep ((int) (Math.random () * 1000));
                     }
                     catch (InterruptedException e)
                     {
                     }
                     ft.amount = 250.0;
                     System.out.println (ft.transName + " " + ft.amount);
                  }
               }
           }
      }
   }
}
