package pers.solax.util;

/**
 * Created by solax on 2017-4-7.
 */
public class LrUtils {
    public static boolean isWindows () {
        String os = getSystem();
        if(os.toLowerCase().startsWith("win")) return true;
        return false;
    }

    public static boolean isLinux () {
        String os = getSystem();
        if(os.toLowerCase().startsWith("Linux")) return true;
        return false;
    }

    public static String getSystem() {
        return System.getProperty("os.name");
    }
}
