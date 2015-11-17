package Threading.WithoutPainThreadingJavaWorld.WithExecutor1;

/**
 * Created by chetan on 14/11/15.
 */
import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

class Server
{
    static Executor pool = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws IOException
    {
        ServerSocket socket = new ServerSocket(9000);
        while (true)
        {
            final Socket s = socket.accept();
            Runnable r = new Runnable()
            {
                @Override
                public void run()
                {
                    doWork(s);
                }
            };
            pool.execute(r);
        }
    }

    static void doWork(Socket s)
    {
    }
}
/*
Listing 2 uses newFixedThreadPool(int) to obtain a thread pool-based executor that reuses five threads. It also replaces
new Thread(r).start(); with pool.execute(r); for executing runnable tasks via any of these threads. Listing 3 presents
another example in which an application reads the contents of an arbitrary web page. It outputs the resulting lines or
an error message if the contents aren't available within a maximum of five seconds.
 */


