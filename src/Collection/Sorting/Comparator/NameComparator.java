package Collection.Sorting.Comparator;

/**
 * Created by chetan on 17/3/18.
 */
import java.util.*;
class NameComparator implements Comparator<Student>{
    public int compare(Student s1,Student s2){
        return s1.name.compareTo(s2.name);
    }
}
