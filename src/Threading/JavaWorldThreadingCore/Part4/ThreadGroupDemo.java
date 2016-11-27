package Threading.JavaWorldThreadingCore.Part4;

/**
 * Created by chetan on 27/11/16.
 */
// ThreadGroupDemo.java
class ThreadGroupDemo
{
    public static void main (String [] args)
    {
        //First thread group
        ThreadGroup tg = new ThreadGroup ("subgroup 1");
        Thread t1 = new Thread (tg, "thread 1");
        Thread t2 = new Thread (tg, "thread 2");
        Thread t3 = new Thread (tg, "thread 3");
        //Second thread group
        tg = new ThreadGroup ("subgroup 2");
        Thread t4 = new Thread (tg, "my thread");
        tg = Thread.currentThread ().getThreadGroup ();
        int agc = tg.activeGroupCount ();
        System.out.println ("Active thread groups in " + tg.getName () +
                " thread group: " + agc);
        tg.list ();
    }
}
/*
Active thread groups in main thread group: 2
java.lang.ThreadGroup[name=main,maxpri=10]
    Thread[main,5,main]
    Thread[Monitor Ctrl-Break,5,main]
    java.lang.ThreadGroup[name=subgroup 1,maxpri=10]
    java.lang.ThreadGroup[name=subgroup 2,maxpri=10]
 */
