package Threading.JavaWorldThreadingCore.Part4;

/**
 * Created by chetan on 27/11/16.
 */
// ThreadDeathDemo.java
class ThreadDeathDemo
{
    public static void main (String [] args)
    {
        MyThreadGroup mtg = new MyThreadGroup ("My Group");
        new MyThread12 (mtg, "My Thread").start ();
    }
}
//As defined above this thread falls inside thread group mtg and hence are related
class MyThread12 extends Thread
{
    MyThread12 (ThreadGroup tg, String name)
    {
        super (tg, name);
    }
    public void run ()
    {
        System.out.println ("About to do something.");
        doSomething ();
        System.out.println ("Something done.");
    }

    //both below will be called as they will be called in run
    void doSomething ()
    {
        doSomethingHelper ();
    }
    void doSomethingHelper ()
    {
        throw new MyThreadDeath (MyThreadDeath.REASON2);
    }
}
class MyThreadDeath extends ThreadDeath
{
    final static int REASON1 = 1;
    final static int REASON2 = 2;
    final static int REASON3 = 3;
    int reason;
    MyThreadDeath (int reason)
    {
        this.reason = reason;
    }
}
class MyThreadGroup extends ThreadGroup
{
    MyThreadGroup (String name)
    {
        super (name);
    }

    /*
     Called by the Java Virtual Machine when a thread in this
     * thread group stops because of an uncaught exception, and the thread
     * does not have a specific {@link Thread.UncaughtExceptionHandler}
     * installed.
     *Applications can override this method in subclasses of
     * <code>ThreadGroup</code> to provide alternative handling of
     * uncaught exceptions.
     */
    /*
         * @param   t   the thread that is about to exit.
     * @param   e   the uncaught exception.
     */
    //basically it used to call the other two methods defined below that is report error and clean
    public void uncaughtException (Thread t, Throwable e)
    {
        if (e instanceof MyThreadDeath)
        {
            reportError (t, e);
            cleanup ();
        }
        super.uncaughtException (t, e);
    }
    void reportError (Thread t, Throwable e)
    {
        System.out.print (t.getName () + " unable to do something. Reason: ");
        switch (((MyThreadDeath) e).reason)
        {
            case MyThreadDeath.REASON1:
                System.out.println ("First reason.");
                break;
            case MyThreadDeath.REASON2:
                System.out.println ("Second reason.");
                break;
            case MyThreadDeath.REASON3:
                System.out.println ("Third reason.");
        }
    }
    void cleanup ()
    {
        System.out.println ("Cleaning up");
    }
}
/*
About to do something.
My Thread unable to do something. Reason: Second reason.
Cleaning up
 */