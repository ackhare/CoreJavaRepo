package Threading;

/**
 * Created by chetan on 2/11/15.
 */
public class Thread_Volatility {
}
/*
Developers may use this extended visibility guarantee to optimize the visibility of variables between threads.
Instead
of declaring each and every variable volatile, only one or a few need be declared volatile.
 */
class Exchanger {

    private Object   object       = null;
    private volatile Boolean hasNewObject = false;

    public void put(Object newObject) {
        while(hasNewObject) {
            //wait - do not overwrite existing new object
        }
        object = newObject;
        hasNewObject = true; //volatile write
    }

    public Object take(){
        while(!hasNewObject){ //volatile read
            //wait - don't take old object (or null)
        }
        Object obj = object;
        hasNewObject = false; //volatile write
        return obj;
    }
}
/*
Thread A may be putting objects from time to time by calling put(). Thread B may take objects from time to time by
calling take(). This Exchanger can work just fine using a volatile variable (without the use of synchronized blocks), as
long as only Thread A calls put() and only Thread B calls take().
*/
