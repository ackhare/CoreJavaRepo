package Collection.HasHCodeEquals;

/**
 * Created by chetan on 21/3/18.
 */
public class TestEmployee {
    public static void main(String[] args) {
        Employee e1 = new Employee();
        Employee e2 = new Employee();

        e1.setId(100);
        e2.setId(100);

        //Prints false in console
        System.out.println(e1.equals(e2));//true only becausew we override equals
    }
}
