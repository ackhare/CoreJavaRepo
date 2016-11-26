package Threading.JavaWorldThreadingCore.Part1;

/**
 * Created by chetan on 26/11/16.
 */
//Listing 2. NameThatThread.java
// NameThatThread.java

   /*
   what this code does is that allows you to name a thread through command line
   You can pass an optional name argument to MyThread on the command line. For example, java NameThatThread X
   establishes X as the thread's name. If you fail to specify a name, you'll see the following output: My name is:
   Thread-1


If you prefer, you can change the super (name); call in the MyThread (String name) constructor to a call to setName
(String name)—as in setName (name);. That latter method call achieves the same objective—establishing the thread's
name—as super (name);. I leave that as an exercise for you.
    */
class NameThatThread
{
   public static void main (String [] args)
   {
      MyThread1 mt;
      if (args.length == 0)
          mt = new MyThread1 ();
      else
          mt = new MyThread1 (args [0]);
      mt.start ();
   }
}
class MyThread1 extends Thread
{
   MyThread1 ()
   {
      // The compiler creates the byte code equivalent of super ();
       System.out.println("Thread name!!!!!!!!!!!!!!!!!");
   }
   MyThread1 (String name)
   {
      super (name); // Pass name to Thread superclass
       System.out.println("Thread name"+name);
   }
   public void run ()
   {
      System.out.println ("My name is: " + getName ());
   }
}

/*
output

//As this program was run by idea and no argument was given so appropriate constructor is called
Thread name!!!!!!!!!!!!!!!!!
My name is: Thread-0
 */
