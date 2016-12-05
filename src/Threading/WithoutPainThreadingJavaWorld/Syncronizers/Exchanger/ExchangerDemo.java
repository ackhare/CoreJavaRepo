package Threading.WithoutPainThreadingJavaWorld.Syncronizers.Exchanger;

/**
 * Created by chetan on 17/11/15.
 */
/*
/*
Listing 7 is based on the example code in Exchanger's Javadoc.   For example,
The emptying thread continues until currentBuffer.isEmpty()
evaluates to true, and also invokes exchange(currentBuffer).
At this point, the buffers are swapped and the threads continue.


 */
import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.Exchanger;

public class ExchangerDemo
{

    //One thread fills one buffer with strings while another
    //thread empties another buffer.

    //There are two buffers and two threads that are acting on each other
    static Exchanger<DataBuffer> exchanger = new
            Exchanger<DataBuffer>();
    //Two buffers are created and a excxhanger is created to facilate the exchange between the buffers
    static DataBuffer initialEmptyBuffer = new DataBuffer();
    static DataBuffer initialFullBuffer = new DataBuffer("ITEM");
//When the respective buffer is full or empty, these threads meet at an exchange point and
//swap buffers.

    //This means that in both threads comes a point of exchange ,
    //In filling thread it is when currentBuffer.isFull())
    // or when emptying thread will execute currentBuffer.isEmpty()
    //
    public static void main(String[] args)
    {

        //This thread will fill the buffer
        class FillingLoop implements Runnable
        {
            int count = 0;

            @Override
            public void run()
            {
                DataBuffer currentBuffer = initialEmptyBuffer;
                try
                {
                    while (true)
                    {
                        addToBuffer(currentBuffer);
                        //when the filling thread's currentBuffer.isFull() expression is true, it executes
                        //currentBuffer = exchanger.exchange(currentBuffer) and waits.
                        if (currentBuffer.isFull())
                        {
                            //when the filling thread's currentBuffer.isFull() expression
                            // is true, it executes currentBuffer = exchanger.exchange(currentBuffer) and waits.
                            System.out.println("filling loop thread wants to exchange");
                            //this will be treated as exchange points
                                    currentBuffer = exchanger.exchange(currentBuffer);
                            System.out.println("filling loop thread observes an exchange");
                            ///Thread.sleep(2000);
                            //System.out.println(currentBuffer);
                        }
                    }
                }
                catch (InterruptedException ie)
                {
                    System.out.println("filling loop thread interrupted");
                }
            }

            void addToBuffer(DataBuffer buffer)
            {
                System.out.println("Enters addToBuffer");
                String item = "NEWITEM"+count++;
                System.out.printf("Adding %s%n", item);
                //adding item to buffer which is just a string item
                buffer.add(item);
                System.out.println("Exits addToBuffer");
            }
        }
//This thread will empty the buffer
        class EmptyingLoop implements Runnable
        {
            @Override
            public void run()
            {
                DataBuffer currentBuffer = initialFullBuffer;
                try
                {
                    while (true)
                    {
                        takeFromBuffer(currentBuffer);
                        if (currentBuffer.isEmpty())
                        {
                            System.out.println("emptying loop thread wants to exchange");
                                    currentBuffer = exchanger.exchange(currentBuffer);
                            System.out.println("emptying loop thread observes an exchange");

                        }
                    }
                }
                catch (InterruptedException ie)
                {
                    System.out.println("emptying loop thread interrupted");
                }
            }

            void takeFromBuffer(DataBuffer buffer)
            {
                System.out.println("takeFromBuffer enter");
                System.out.printf("taking %s%n", buffer.remove());
                System.out.println("takeFromBuffer exit");
            }
        }

        //start the threads
        System.out.println("start");
        new Thread(new EmptyingLoop()).start();
        new Thread(new FillingLoop()).start();

    }
}
//This data buffer is quite similar to a list and a list could be used as such
class DataBuffer
{
    private final static int MAX = 10;
    private List<String> items = new ArrayList<>();
//The empty buffer which is taken up by filling thread and then check for full
    DataBuffer()
    {
    }
//The full buffer which is filled up to sizer of 10 and is used for emptying up
    DataBuffer(String prefix)
    {
        for (int i = 0; i < MAX; i++)
        {
            String item = prefix+i;
            System.out.printf("Adding %s%n", item);
            items.add(item);
        }
    }

    void add(String s)
    {
        if (!isFull())
            items.add(s);
    }

    boolean isEmpty()
    {
        return items.size() == 0;
    }

    boolean isFull()
    {
        return items.size() == MAX;
    }

    String remove()
    {
        if (!isEmpty())
            return items.remove(0);
        return null;
    }
}

/*
 Compile and run this application. Your initial output should be similar to the following prefix:

Adding ITEM0
Adding ITEM1
Adding ITEM2
Adding ITEM3
Adding ITEM4
Adding ITEM5
Adding ITEM6
Adding ITEM7
Adding ITEM8
Adding ITEM9
start
takeFromBuffer enter
taking ITEM0
takeFromBuffer exit
takeFromBuffer enter
taking ITEM1
takeFromBuffer exit
takeFromBuffer enter
taking ITEM2
takeFromBuffer exit
takeFromBuffer enter
taking ITEM3
takeFromBuffer exit
takeFromBuffer enter
taking ITEM4
takeFromBuffer exit
takeFromBuffer enter
Enters addToBuffer
taking ITEM5
takeFromBuffer exit
takeFromBuffer enter
Adding NEWITEM0
taking ITEM6
takeFromBuffer exit
takeFromBuffer enter
Exits addToBuffer
taking ITEM7
takeFromBuffer exit
takeFromBuffer enter
Enters addToBuffer
taking ITEM8
takeFromBuffer exit
takeFromBuffer enter
Adding NEWITEM1
Exits addToBuffer
Enters addToBuffer
taking ITEM9
takeFromBuffer exit
Adding NEWITEM2
Exits addToBuffer
Enters addToBuffer
emptying loop thread wants to exchange
Adding NEWITEM3
Exits addToBuffer
Enters addToBuffer
Adding NEWITEM4
Exits addToBuffer
Enters addToBuffer
Adding NEWITEM5
Exits addToBuffer
Enters addToBuffer
Adding NEWITEM6
Exits addToBuffer
Enters addToBuffer
Adding NEWITEM7
Exits addToBuffer
Enters addToBuffer
Adding NEWITEM8
Exits addToBuffer
Enters addToBuffer
Adding NEWITEM9
Exits addToBuffer
filling loop thread wants to exchange
emptying loop thread observes an exchange
takeFromBuffer enter
taking NEWITEM0
takeFromBuffer exit
filling loop thread observes an exchange
Enters addToBuffer
takeFromBuffer enter
taking NEWITEM1
takeFromBuffer exit
takeFromBuffer enter
Adding NEWITEM10
Exits addToBuffer
Enters addToBuffer
Adding NEWITEM11
Exits addToBuffer
Enters addToBuffer
taking NEWITEM2
takeFromBuffer exit
takeFromBuffer enter
taking NEWITEM3
takeFromBuffer exit
takeFromBuffer enter
taking NEWITEM4
takeFromBuffer exit
takeFromBuffer enter
taking NEWITEM5
takeFromBuffer exit
takeFromBuffer enter
taking NEWITEM6
takeFromBuffer exit
takeFromBuffer enter
Adding NEWITEM12
Exits addToBuffer
Enters addToBuffer
taking NEWITEM7
takeFromBuffer exit
takeFromBuffer enter
Adding NEWITEM13
Exits addToBuffer
Enters addToBuffer
taking NEWITEM8
takeFromBuffer exit
takeFromBuffer enter
Adding NEWITEM14
Exits addToBuffer
Enters addToBuffer
Adding NEWITEM15
taking NEWITEM9
takeFromBuffer exit
emptying loop thread wants to exchange
Exits addToBuffer
Enters addToBuffer
Adding NEWITEM16
Exits addToBuffer
Enters addToBuffer
Adding NEWITEM17
Exits addToBuffer
Enters addToBuffer
Adding NEWITEM18
Exits addToBuffer
Enters addToBuffer
Adding NEWITEM19
Exits addToBuffer
filling loop thread wants to exchange
filling loop thread observes an exchange
Enters addToBuffer
Adding NEWITEM20
emptying loop thread observes an exchange
takeFromBuffer enter
Exits addToBuffer
Enters addToBuffer
taking NEWITEM10
takeFromBuffer exit
takeFromBuffer enter
Adding NEWITEM21
Exits addToBuffer
Enters addToBuffer
taking NEWITEM11
takeFromBuffer exit
takeFromBuffer enter
Adding NEWITEM22
Exits addToBuffer
Enters addToBuffer
taking NEWITEM12
takeFromBuffer exit
takeFromBuffer enter
Adding NEWITEM23
Exits addToBuffer
Enters addToBuffer
taking NEWITEM13
takeFromBuffer exit
takeFromBuffer enter
Adding NEWITEM24
Exits addToBuffer
Enters addToBuffer
taking NEWITEM14
takeFromBuffer exit
takeFromBuffer enter
Adding NEWITEM25
Exits addToBuffer
Enters addToBuffer
taking NEWITEM15
takeFromBuffer exit
takeFromBuffer enter
Adding NEWITEM26
Exits addToBuffer
Enters addToBuffer
taking NEWITEM16
takeFromBuffer exit
takeFromBuffer enter
Adding NEWITEM27
Exits addToBuffer
Enters addToBuffer
taking NEWITEM17
takeFromBuffer exit
takeFromBuffer enter
taking NEWITEM18
takeFromBuffer exit
takeFromBuffer enter
Adding NEWITEM28
Exits addToBuffer
Enters addToBuffer
taking NEWITEM19
takeFromBuffer exit
 */