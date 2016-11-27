package Threading.JavaWorldThreadingCore.Part4;

// EnumThreads.java
class EnumThreads
{
    public static void main (String [] args)
    {
        // Find system thread group
        ThreadGroup system = null;
        ThreadGroup tg = Thread.currentThread ().getThreadGroup ();
        while (tg != null)
        {
          //as there is no threadgroup above parent so this loop goes on recursion till last parent which is sytem
            system = tg;
            tg = tg.getParent ();
        }
        // Display a list of all system and application threads, and their
        // daemon status
        if (system != null)
        {

            Thread [] thds = new Thread [system.activeCount ()];
            //thds is the threrad and uses repective threads in running program
            int nthds = system.enumerate (thds);
            for (int i = 0; i < nthds; i++)
                System.out.println (thds [i] + " " + thds [i].isDaemon ());



        }
    }
}

/*

so here main and system are non-daemon threads while other are daemon threads running in background

Thread[Reference Handler,10,system] true
Thread[Finalizer,8,system]          true
Thread[Signal Dispatcher,9,system] true
Thread[main,5,main]                false
Thread[Monitor Ctrl-Break,5,main]  true

 */