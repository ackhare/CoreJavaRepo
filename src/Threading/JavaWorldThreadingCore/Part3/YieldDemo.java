package Threading.JavaWorldThreadingCore.Part3;

/**
 * Created by chetan on 26/11/16.
 */
// YieldDemo.java
class YieldDemo extends Thread
{
    static boolean finished = false;
    static int sum = 0;
    public static void main (String [] args)
    {
        new YieldDemo ().start ();
        //The below is always run by main thread
        //Below I have reduced the length of loop to see the output clearly
        for (int i = 1; i <= 500; i++)
        {
            sum++;
            if (args.length == 0) {
                System.out.println("pre pre " + Thread.currentThread().getName());
                //TODO please check with setpriority as well
                Thread.yield();
                System.out.println("pre @@ " + Thread.currentThread().getName());
            }
        }
        System.out.println("pre post "+Thread.currentThread().getName());
        finished = true;
        //System.out.println("post post "+Thread.currentThread().getName());
    }
    public void run ()
    {

        while (!finished)
            //System.out.println("!!!!!!!!!!!!! "+Thread.currentThread().getName());//this will be output thread
            System.out.println ("sum = " + sum);
    }
}



