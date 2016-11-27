package Threading.JavaWorldThreadingCore.Part4;

/**
 * Created by chetan on 27/11/16.
 */
// InheritableThreadLocalDemo.java
    /*

 A child cannot inherit a parent's thread-local values that the parent thread establishes
 via the ThreadLocal class.
 However, a parent can use java.lang.InheritableThreadLocal (which extends ThreadLocal) to pass the values of
 inheritable thread-local variables to a child,
     */
class InheritableThreadLocalDemo implements Runnable
{
    static InheritableThreadLocal itl = new InheritableThreadLocal ();
    static ThreadLocal tl = new ThreadLocal ();
    public static void main (String [] args)
    {
        itl.set ("parent thread thread-local value passed to child thread");
        tl.set ("parent thread thread-local value not passed to child thread");
        InheritableThreadLocalDemo itld;
        itld = new InheritableThreadLocalDemo ();
        Thread child1 = new Thread (itld);
        Thread child2 = new Thread (itld);
        child1.start ();
        child2.start ();
    }
    public void run ()
    {
        /*
        each child thread printing parent thread thread-local value passed to child thread,
        which is the inheritable
 thread-local variable's value. However, null prints as the ThreadLocal variable's value.
         */
        System.out.println (itl.get ()+"!!");//this gives output
        System.out.println (tl.get ());//this does not
    }
}
/*
parent thread thread-local value passed to child thread
null
parent thread thread-local value passed to child thread
null
 */