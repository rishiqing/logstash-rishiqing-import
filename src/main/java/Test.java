import pers.solax.entry.Command;
import pers.solax.entry.Entry;

/**
 * Created by solax on 2017-5-7.
 */
public class Test {
    public static  void main (String [] args) {
        TestEntry entry = TestEntry.valueOf("todo");
        entry.exec();
    }
}
