package Threading.WithoutPainThreadingJavaWorld.Syncronizers;

/**
 * Created by chetan on 14/11/15.
 */
/*
 SemaphoreDemo drives the application by creating executors and having them execute
  a runnable that repeatedly acquires
 string item resources from a pool (implemented by Pool) and then returns them.
 */

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo
{
    public static void main(String[] args)
    {
        final Pool pool = new Pool();
        Runnable r = new Runnable()
        {
            @Override
            public void run()
            {
                String name = Thread.currentThread().getName();
                try
                {
                    while (true)
                    {
                        String item;

                        System.out.printf("%s acquiring %s%n", name,
                                item = pool.getItem());
                        Thread.sleep(200+(int)(Math.random()*100));
                        System.out.printf("%s putting back %s%n",
                                name,
                                item);
                        pool.putItem(item);
                    }
                }
                catch (InterruptedException ie)
                {
                    System.out.printf("%s interrupted%n", name);
                }
            }
        };
        ExecutorService[] executors = new
                ExecutorService[Pool.MAX_AVAILABLE+1];
        for (int i = 0; i < executors.length; i++)
        {
            executors[i] = Executors.newSingleThreadExecutor();
            executors[i].execute(r);
            //executor executing runnable
        }
    }
}

final class Pool
{
    public static final int MAX_AVAILABLE = 10;

    private Semaphore available = new Semaphore(MAX_AVAILABLE, true);
    private String[] items;
    private boolean[] used = new boolean[MAX_AVAILABLE];
    /*
                            Pool provides String getItem() and void putItem(String item) methods for obtaining and returning
                             resources.


                             */
    Pool()
    {
        items = new String[MAX_AVAILABLE];
        for (int i = 0; i < items.length; i++)
            items[i] = "ITEM"+i;
    }

    String getItem() throws InterruptedException
    {
       /*
       Before obtaining an item in getItem(), a thread must acquire a permit from the semaphore, guaranteeing that an
              item is available for use.
        */
        available.acquire();
        return getNextAvailableItem();
    }

    void putItem(String item)
    {
       /* When the thread has finished with the item, it calls putItem(String), which returns
        the item to the pool and then returns a permit to the semaphore, which lets another thread acquire that item.
        */
        if (markAsUnused(item))
            available.release();
    }
    /*No synchronization lock is held when acquire() is called because that would prevent an item from being returned to the
pool. However, String getNextAvailableItem() and boolean markAsUnused(String item) are synchronized to maintain pool
consistency. (The semaphore encapsulates the synchronization needed to restrict access to the pool separately from the
synchronization needed to maintain pool consistency.)
   */
    private synchronized String getNextAvailableItem()
    {
        for (int i = 0; i < MAX_AVAILABLE; ++i)
        {
            if (!used[i])
            {
                used[i] = true;
                return items[i];
            }
        }
        return null; // not reached
    }

    private synchronized boolean markAsUnused(String item)
    {
        for (int i = 0; i < MAX_AVAILABLE; ++i)
        {
            if (item == items[i])
            {
                if (used[i])
                {
                    used[i] = false;
                    return true;
                }
                else
                    return false;
            }
        }
        return false;
    }
}
