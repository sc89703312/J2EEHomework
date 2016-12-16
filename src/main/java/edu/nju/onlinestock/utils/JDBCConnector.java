package main.java.edu.nju.onlinestock.utils;


import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by echo on 16/12/16.
 */
public class JDBCConnector {

    //private static DataSource dataSource = null;

    private JDBCConnector(){

    }

    public static DataSource getDataSourceInstance(){

        DataSource dataSource = null;

        InitialContext jndiContext = null;
        Properties properties = new Properties();
        properties.put(javax.naming.Context.PROVIDER_URL, "jnp:///");
        properties.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
        try {
            jndiContext = new InitialContext(properties);
            dataSource = (DataSource) jndiContext.lookup("java:comp/env/jdbc/onlinestock");
            System.out.println("got context");
            System.out.println("About to get ds---ShowMyStock");
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return dataSource;

    }
}
