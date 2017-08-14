package pers.solax.util;

import com.rishiqing.conn.ConnectClient;

/**
 * Created by solax on 2017-3-30.
 */
public class Connection {

    private static ConnectClient connectClient = null;

    private Connection () {

    }
    public static ConnectClient getConnectClient () {
        if (connectClient == null) {
            return connectClient = new ConnectClient();
        } else {
            return connectClient;
        }
    }
}
