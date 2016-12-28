package main.java.edu.nju.onlinestock.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by echo on 16/12/28.
 */
public interface ConnectionHelper {

    Connection getConnection();

    void closeConnection(java.sql.Connection con);

    void closePreparedStatement(PreparedStatement stmt);

    void closeResult(ResultSet result);
}
