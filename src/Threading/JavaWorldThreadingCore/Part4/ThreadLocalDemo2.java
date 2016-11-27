package Threading.JavaWorldThreadingCore.Part4;

/**
 * Created by chetan on 27/11/16.
 */
// ThreadLocalDemo2.java
    /*
    ThreadLocalDemo2 is nearly identical to ThreadLocalDemo1. However, instead of overriding
    initialValue() to establish
each thread's initial value to a unique serial number,
ThreadLocalDemo2 uses a tl.set ("" + sernum++); method call.
     */
class ThreadLocalDemo2
{
    public static void main (String [] args)
    {
        MyThread11 mt1 = new MyThread11 ("A");
        MyThread11 mt2 = new MyThread11 ("B");
        MyThread11 mt3 = new MyThread11 ("C");
        mt1.start ();
        mt2.start ();
        mt3.start ();
    }
}
class MyThread11 extends Thread
{
    private static ThreadLocal tl = new ThreadLocal ();
    private static int sernum = 100;
    MyThread11 (String name)
    {
        super (name);
    }
    public void run ()
    {
        //here anonymous class was not used
//Though this works without syncronized it is added for brevity and safety
        synchronized ("A")
       {

            tl.set ("" + sernum++);
        }
        for (int i = 0; i < 10; i++)
            System.out.println (getName () + " " + tl.get ());
    }
}
/*
/usr/lib/jvm/java-7-oracle/bin/java -Didea.launcher.port=7539 -Didea.launcher.bin.path=/home/chetan/Downloads/idea-IU-162.1121.32/bin -Dfile.encoding=UTF-8 -classpath /usr/lib/jvm/java-7-oracle/jre/lib/charsets.jar:/usr/lib/jvm/java-7-oracle/jre/lib/deploy.jar:/usr/lib/jvm/java-7-oracle/jre/lib/ext/dnsns.jar:/usr/lib/jvm/java-7-oracle/jre/lib/ext/localedata.jar:/usr/lib/jvm/java-7-oracle/jre/lib/ext/sunec.jar:/usr/lib/jvm/java-7-oracle/jre/lib/ext/sunjce_provider.jar:/usr/lib/jvm/java-7-oracle/jre/lib/ext/sunpkcs11.jar:/usr/lib/jvm/java-7-oracle/jre/lib/ext/zipfs.jar:/usr/lib/jvm/java-7-oracle/jre/lib/javaws.jar:/usr/lib/jvm/java-7-oracle/jre/lib/jce.jar:/usr/lib/jvm/java-7-oracle/jre/lib/jfr.jar:/usr/lib/jvm/java-7-oracle/jre/lib/jfxrt.jar:/usr/lib/jvm/java-7-oracle/jre/lib/jsse.jar:/usr/lib/jvm/java-7-oracle/jre/lib/management-agent.jar:/usr/lib/jvm/java-7-oracle/jre/lib/plugin.jar:/usr/lib/jvm/java-7-oracle/jre/lib/resources.jar:/usr/lib/jvm/java-7-oracle/jre/lib/rt.jar:/home/chetan/IdeaProjects/CoreJavaRepo/out/production/CoreJavaRepo:/home/chetan/Downloads/openutils-log4j-2.0.5.jar:/home/chetan/Downloads/apache-log4j-1.2.17/log4j-1.2.17.jar:/home/chetan/Downloads/idea-IU-162.1121.32/lib/idea_rt.jar com.intellij.rt.execution.application.AppMain Threading.JavaWorldThreadingCore.Part4.ThreadLocalDemo2
B 100
A 101
A 101
C 102
B 100
A 101
C 102
B 100
A 101
C 102
B 100
A 101
C 102
B 100
A 101
C 102
B 100
A 101
C 102
B 100
A 101
C 102
B 100
A 101
C 102
B 100
A 101
C 102
C 102
B 100

Process finished with exit code 0
 */