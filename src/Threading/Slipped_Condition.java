package Threading;

/**
 * Created by chetan on 3/11/15.
 */
public class Slipped_Condition {
}
class Lock2 {

    private boolean isLocked = true;

    public void lock(){
        synchronized(this){
            while(isLocked){
                try{
                    this.wait();
                } catch(InterruptedException e){
                    //do nothing, keep waiting
                }
            }
        }

        synchronized(this){
            isLocked = true;
        }
    }

    public synchronized void unlock(){
        isLocked = false;
        this.notify();
    }

}