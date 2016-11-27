package Threading.JavaWorldThreadingCore.Part3;

/**
 * Created by chetan on 26/11/16.
 */
// ProdCons2.java

class ProdCons2
{
   public static void main (String [] args)
   {
      Shared1 s = new Shared1 ();
      new Producer1 (s).start ();
      new Consumer1 (s).start ();
   }
}

class Shared1
{
   private char c = '\u0000';
   private boolean writeable = true;
//Now both the methods are synchronised and because they are syncronised we can use wait and notify
   synchronized void setSharedChar (char c)
   {
      while (!writeable)
         try
         {
            wait ();
         }
         catch (InterruptedException e) {}

      this.c = c;
       //below is the condition that changes which leads to notify as explained in theory before
      writeable = false;
      notify ();
   }

   synchronized char getSharedChar ()
   {
      while (writeable)
         try
         {
            wait ();
         }
         catch (InterruptedException e) { }

      writeable = true;
      notify ();

      return c;
   }
}

class Producer1 extends Thread
{
   private Shared1 s;

   Producer1 (Shared1 s)
   {
      this.s = s;
   }

   public void run ()
   {
      for (char ch = 'A'; ch <= 'Z'; ch++)
      {
           try
           {
              Thread.sleep ((int) (Math.random () * 4000));
           }
           catch (InterruptedException e) {}

           s.setSharedChar (ch);
           System.out.println (ch + " produced by producer.");
      }
   }
}

class Consumer1 extends Thread
{
   private Shared1 s;

   Consumer1 (Shared1 s)
   {
      this.s = s;
   }

   public void run ()
   {
      char ch;

      do
      {
         try
         {
            Thread.sleep ((int) (Math.random () * 4000));
         }
         catch (InterruptedException e) {}

         ch = s.getSharedChar ();
         System.out.println (ch + " consumed by consumer.");
      }
      while (ch != 'Z');
   }
}

