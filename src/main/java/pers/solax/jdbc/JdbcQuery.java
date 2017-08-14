package pers.solax.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by solax on 2017-7-11.
 */
public class JdbcQuery {

    JDBCConnector jdbcConnector = JDBCConnector.getConnector();

    public int getMaxNum (String sql) {
        System.out.println("exec sql max -> " + sql);
        ResultSet rs = jdbcConnector.executeQuery(sql);
        int max = 0;
        try {
             max = rs.getInt("max");
            System.out.println("exec sql max result -> " + max);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return max;
    }
}
