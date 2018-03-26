package Threading.OutputQuiz.output1;

/**
 * Created by chetan on 26/3/18.
 */

public class Main implements Runnable
{
    public void run()
    {
        System.out.printf("GFG ");
        System.out.printf("Geeks ");
    }
    public static void main(String[] args)
    {
        Main obj = new Main();
        Thread thread = new Thread(obj);

        thread.start();
        System.out.printf("Geeks ");
        try
        {
            thread.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("for ");
    }
}
