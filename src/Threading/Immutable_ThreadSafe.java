package Threading;

/**
 * Created by chetan on 1/11/15.
 */
public class Immutable_ThreadSafe {
    public static void main(String[] args) {

    }
}

class ImmutableValue {

    private int value = 0;

    public ImmutableValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
    public ImmutableValue add(int valueToAdd){
        return new ImmutableValue(this.value + valueToAdd);
    }
}
    /* Notice how the value for the ImmutableValue instance is passed in the constructor. Notice also how there is no
 * setter method. Once an ImmutableValue instance is created you cannot change its value. It is immutable. You can read
 * it however, using the getValue() method.

    If you need to perform operations on the ImmutableValue instance you can do so by returning a new instance with the
    value resulting from the operation
    */


/*
even if an object is immutable and thereby thread safe, the reference to this object may not be thread safe.
 */
class Calculator{
    private ImmutableValue currentValue = null;

    public ImmutableValue getValue(){
        return currentValue;
    }

    public void setValue(ImmutableValue newValue){
        this.currentValue = newValue;
    }

    public void add(int newValue){
        this.currentValue = this.currentValue.add(newValue);
    }
}