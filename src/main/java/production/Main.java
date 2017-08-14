package production;

import pers.solax.entry.Entry;

/**
 * Created by solax on 2017-3-30.
 */
public class Main {
    public static  void main (String [] args) {
        if (args != null) {
            System.out.println(args[0]);
            Entry entry = Entry.valueOf(args[0]);
            entry.exec();
       } else {
            System.out.println("command not found -");
        }
    }
}
