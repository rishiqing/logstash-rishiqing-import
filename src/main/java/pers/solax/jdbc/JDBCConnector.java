package pers.solax.jdbc;

import pers.solax.util.ConfigUtils;

import java.sql.*;

/**
 * Created by solax on 2017-7-11.
 */
public class JDBCConnector {
    private static final String url =  ConfigUtils.JDBC_CONNECTION_STRING;
    private static final String name = "com.mysql.jdbc.Driver";
    private static final String user = ConfigUtils.JDBC_USER;
    private static final String password = ConfigUtils.JDBC_PASSWORD;

    private Connection conn = null;
    private PreparedStatement preparedStatement = null;

    private static JDBCConnector jdbcConnector;

    public static JDBCConnector getConnector () {
        if (jdbcConnector == null) {
            jdbcConnector = new JDBCConnector();
        }
        return  jdbcConnector;
    }


    private JDBCConnector() {
        try {
            Class.forName(name);//指定连接类型
            conn = DriverManager.getConnection(url, user, password);//获取连接
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            this.conn.close();
            this.preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery (String sql) {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    public void setPreparedStatement(PreparedStatement preparedStatement) {
        this.preparedStatement = preparedStatement;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
}
