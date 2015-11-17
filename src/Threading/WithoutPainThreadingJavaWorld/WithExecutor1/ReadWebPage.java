package Threading.WithoutPainThreadingJavaWorld.WithExecutor1;

/**
 * Created by chetan on 14/11/15.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.TimeUnit;

public class ReadWebPage
{
    public static void main(final String[] args)
    {
        /*
   Listing 3's main() method first verifies that a single (URL-based)
   command-line argument has been specified.


         */
        if (args.length != 1)
        {
            System.err.println("usage: java ReadWebPage url");
            return;
        }
        ExecutorService executor = Executors.newSingleThreadExecutor();
        /*
        It then creates a single-thread executor and a callable that tries
    to open a connection to this URL, read its contents line by line,
    and save these lines in a list, which it returns.
         */

        Callable<List<String>> callable;

        callable = new Callable<List<String>>()
        {
            @Override
            public List<String> call()
                    throws IOException, MalformedURLException
            {
                List<String> lines = new ArrayList<>();
                URL url = new URL(args[0]);
                HttpURLConnection con;
                con = (HttpURLConnection) url.openConnection();
                InputStreamReader isr;
                isr = new InputStreamReader(con.getInputStream());
                BufferedReader br;
                br = new BufferedReader(isr);
                String line;
                while ((line = br.readLine()) != null)
                    lines.add(line);
                return lines;
            }
        };
            /*
        The callable is subsequently submitted to the executor
        and a future representing the list of strings is returned
         */
        Future<List<String>> future = executor.submit(callable);
        try
        {
            /*
             main() invokes the future's V get(long timeout, TimeUnit unit)
              method to obtain this list.

             */
            List<String> lines = future.get(5, TimeUnit.SECONDS);
            /*
            get() throws TimeoutException when the callable doesn't finish within five seconds.
            It throws ExecutionException when the callable throws an exception (for instance,
            the callable will throw java.net.MalformedURLException when the URL is invalid).
             */
            for (String line: lines)
                System.out.println(line);
        }
        catch (ExecutionException ee)
        {
            System.err.println("Callable through exception: "+ee.getMessage());
        }
        catch (InterruptedException | TimeoutException eite)
        {
            System.err.println("URL not responding");
        }
        executor.shutdown();
        /*
        Regardless of whether an exception is thrown or not,
        the executor must be shut down before the application exits.
        If the executor isn't shut down, the application won't exit
        because the non-daemon thread-pool threads are still executing.
         */
    }
}


