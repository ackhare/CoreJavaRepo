package Threading.JavaWorldThreadingCore.Part1;

/**
 * Created by chetan on 26/11/16.
 */
// Census.java
class Census
{
   public static void main (String [] args)
   {
      Thread [] threads = new Thread [Thread.activeCount ()];
      int n = Thread.enumerate (threads);
      for (int i = 0; i < n; i++)
           System.out.println (threads [i].toString ());
   }
}
/*

First one is name of thread
second one is thread's priority
third one is it's group


Thread[main,5,main]
Thread[Monitor Ctrl-Break,5,main]
 */
