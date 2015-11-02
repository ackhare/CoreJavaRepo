package Threading;

/**
 * Created by chetan on 1/11/15.
 */
//http://tutorials.jenkov.com/java-concurrency/thread-safety.html
class NotThreadSafe{
    StringBuilder builder = new StringBuilder();

    public void add(String text){
        this.builder.append(text);
    }
}
public class RaceCondition {
    public static void main(String[] args) {
        NotThreadSafe sharedInstance = new NotThreadSafe();

        new Thread(new MyRunnable(sharedInstance)).start();
        new Thread(new MyRunnable(sharedInstance)).start();
    }}
class MyRunnable implements Runnable{
  NotThreadSafe instance = null;

  public MyRunnable(NotThreadSafe instance){
    this.instance = instance;
  }

  public void run(){
    this.instance.add("some text");
  }
}

/*Notice how the two MyRunnable instances share the same NotThreadSafe instance.
Therefore, when they call the add() method on the NotThreadSafe instance it leads to race condition.
However, if two threads call the add() method simultaneously on different instances then
it does not lead to race condition. Here is the example from before, but slightly modified:
new Thread(new MyRunnable(new NotThreadSafe())).start();
new Thread(new MyRunnable(new NotThreadSafe())).start();

        */