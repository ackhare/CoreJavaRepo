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
        //is use to put items
        final Pool pool = new Pool();
        Runnable runnable = new Runnable()
        {
            @Override
            public void run()
            {
                String name = Thread.currentThread().getName();
                try
                {
                    //infinite loop
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
            executors[i].execute(runnable);
            //executor executing runnable
        }
    }
}

final class Pool
{
    public static final int MAX_AVAILABLE = 10;

    /**
     * The below constructor Creates a {@code Semaphore} with the given number of
     * permits and the given fairness setting.
     *
     * @param permits the initial number of permits available.
     *        This value may be negative, in which case releases
     *        must occur before any acquires will be granted.
     * @param fair {@code true} if this semaphore will guarantee
     *        first-in first-out granting of permits under contention,
     *        else {@code false}
     */
    private Semaphore available = new Semaphore(MAX_AVAILABLE, true);
    private String[] items;
    private boolean[] used = new boolean[MAX_AVAILABLE];
//
    //Pool provides String getItem() and void putItem(String item) methods for obtaining and returning
                            // resources.

    Pool()
    {

        items = new String[MAX_AVAILABLE];
        for (int i = 0; i < items.length; i++)
            items[i] = "ITEM"+i;
    }

    String getItem() throws InterruptedException
    {
       /*
       Before obtaining an item in getItem(), a thread must acquire a permit from
       the semaphore, guaranteeing that an item is available for use.
        */
        available.acquire();//permit acquiring guaranteeing that an item is available for use.
        return getNextAvailableItem();
    }

    void putItem(String item)
    {
       /* When the thread has finished with the item, it calls putItem(String), which returns
        the item to the pool and then returns a permit to the semaphore,
        which lets another thread acquire that item.
        */
        if (markAsUnused(item))
            available.release();//returns a permit to the semaphore,
    }
    /*No synchronization lock is held when acquire() is called because that would prevent an item
    from being returned to the pool.
    However, String getNextAvailableItem() and boolean markAsUnused(String item) are synchronized
    to maintain pool consistency. (The semaphore encapsulates the synchronization needed to restrict
     access to the pool separately from the synchronization needed to maintain pool consistency.
   */

    //below both are syncronized
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
/*
A part of output

pool-1-thread-1 acquiring ITEM0
pool-10-thread-1 acquiring ITEM9
pool-9-thread-1 acquiring ITEM8
pool-8-thread-1 acquiring ITEM7
pool-7-thread-1 acquiring ITEM6
pool-6-thread-1 acquiring ITEM5
pool-5-thread-1 acquiring ITEM4
pool-4-thread-1 acquiring ITEM3
pool-3-thread-1 acquiring ITEM2
pool-2-thread-1 acquiring ITEM1
pool-6-thread-1 putting back ITEM5
pool-11-thread-1 acquiring ITEM5
pool-9-thread-1 putting back ITEM8
pool-6-thread-1 acquiring ITEM8
pool-1-thread-1 putting back ITEM0
pool-9-thread-1 acquiring ITEM0
pool-3-thread-1 putting back ITEM2
pool-1-thread-1 acquiring ITEM2
pool-4-thread-1 putting back ITEM3
pool-3-thread-1 acquiring ITEM3
pool-8-thread-1 putting back ITEM7
pool-4-thread-1 acquiring ITEM7
pool-5-thread-1 putting back ITEM4
pool-8-thread-1 acquiring ITEM4

 */