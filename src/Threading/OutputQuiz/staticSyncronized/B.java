package Threading.OutputQuiz.staticSyncronized;

/**
 * Created by chetan on 26/3/18.
 */
public class B {

    synchronized static void doThis()
    {
        System.out.println("1234");
    }
    synchronized  void doThisInstance()
    {
        System.out.println("5698");
    }

}
