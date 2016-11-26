package Threading.JavaWorldThreadingCore.Part2;

/**
 * Created by chetan on 26/11/16.
 */
// SynchronizationDemo1.java
class SynchronizationDemo1
{
   public static void main (String [] args)
   {
      FinTrans1 ft = new FinTrans1 ();
      TransThread1 tt1 = new TransThread1 (ft, "Deposit Thread");
      TransThread1 tt2 = new TransThread1 (ft, "Withdrawal Thread");
      tt1.start ();
      tt2.start ();
   }
}
class FinTrans1
{
   public static String transName;
   public static double amount;
}
class TransThread1 extends Thread
{
   private FinTrans1 ft;
   TransThread1 (FinTrans1 ft, String name)
   {
      super (name); // Save thread's name
      this.ft = ft; // Save reference to financial transaction object
   }
   //Two critical section with two syncronized
   public void run ()
   {
      for (int i = 0; i < 100; i++)
      {
           if (getName ().equals ("Deposit Thread"))
           {
               synchronized (ft)
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
           else
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

/*
output
/usr/lib/jvm/java-7-oracle/bin/java -Didea.launcher.port=7532 -Didea.launcher.bin.path=/home/chetan/Downloads/idea-IU-162.1121.32/bin -Dfile.encoding=UTF-8 -classpath /usr/lib/jvm/java-7-oracle/jre/lib/charsets.jar:/usr/lib/jvm/java-7-oracle/jre/lib/deploy.jar:/usr/lib/jvm/java-7-oracle/jre/lib/ext/dnsns.jar:/usr/lib/jvm/java-7-oracle/jre/lib/ext/localedata.jar:/usr/lib/jvm/java-7-oracle/jre/lib/ext/sunec.jar:/usr/lib/jvm/java-7-oracle/jre/lib/ext/sunjce_provider.jar:/usr/lib/jvm/java-7-oracle/jre/lib/ext/sunpkcs11.jar:/usr/lib/jvm/java-7-oracle/jre/lib/ext/zipfs.jar:/usr/lib/jvm/java-7-oracle/jre/lib/javaws.jar:/usr/lib/jvm/java-7-oracle/jre/lib/jce.jar:/usr/lib/jvm/java-7-oracle/jre/lib/jfr.jar:/usr/lib/jvm/java-7-oracle/jre/lib/jfxrt.jar:/usr/lib/jvm/java-7-oracle/jre/lib/jsse.jar:/usr/lib/jvm/java-7-oracle/jre/lib/management-agent.jar:/usr/lib/jvm/java-7-oracle/jre/lib/plugin.jar:/usr/lib/jvm/java-7-oracle/jre/lib/resources.jar:/usr/lib/jvm/java-7-oracle/jre/lib/rt.jar:/home/chetan/IdeaProjects/CoreJavaRepo/out/production/CoreJavaRepo:/home/chetan/Downloads/openutils-log4j-2.0.5.jar:/home/chetan/Downloads/apache-log4j-1.2.17/log4j-1.2.17.jar:/home/chetan/Downloads/idea-IU-162.1121.32/lib/idea_rt.jar com.intellij.rt.execution.application.AppMain Threading.JavaWorldThreadingCore.Part2.SynchronizationDemo1
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Withdrawal 250.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Withdrawal 250.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0
Deposit 2000.0

Process finished with exit code 0

 */