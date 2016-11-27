package Threading.JavaWorldThreadingCore.Part4;

// MaxPriorityDemo.java
class MaxPriorityDemo
{
    public static void main (String [] args)
    {
        ThreadGroup tg = new ThreadGroup ("A");
        System.out.println ("tg maximum priority = " + tg.getMaxPriority ());

        Thread t1 = new Thread (tg, "X");
        System.out.println ("t1 priority = " + t1.getPriority ());
        t1.setPriority (Thread.NORM_PRIORITY + 1);
        System.out.println ("t1 priority after setPriority() = " +
                t1.getPriority ());

        tg.setMaxPriority (Thread.NORM_PRIORITY - 1);
        System.out.println ("tg maximum priority after setMaxPriority() = " +
                tg.getMaxPriority ());
        System.out.println ("t1 priority after setMaxPriority() = " +
                t1.getPriority ());
        //so as we see in this example that thread t1 had lower prioreity at time of creation and after creation
        // it can have
 //set priority higher than threrad group  whose priority got lowered
  //now when t2 thread is formed its priority is not 5 but depends upon priority of thread group
        Thread t2 = new Thread (tg, "Y");
        System.out.println ("t2 priority = " + t2.getPriority ());
        t2.setPriority (Thread.NORM_PRIORITY);
        System.out.println ("t2 priority after setPriority() = " +
                t2.getPriority ());
    }
}

/*

tg maximum priority = 10
t1 priority = 5
t1 priority after setPriority() = 6
tg maximum priority after setMaxPriority() = 4
t1 priority after setMaxPriority() = 6
t2 priority = 4
t2 priority after setPriority() = 4

*/