package net.fenton.core.database;

import java.sql.*;

/**
 *
 * Created by Encast (2016-12-10 2:05 PM)
 *
 */
public class SQLCore {

    private String host;
    private String port;
    private String database;
    private String username;
    private String password;
    private Connection connection;

    public SQLCore(String host, String port, String database, String username, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }

    public void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
            System.out.println("[DATABASE] Connected to MySQL Database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        try {
            if(!connection.isClosed()) {
                return connection;
            } else {
                connect();
                return connection;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
