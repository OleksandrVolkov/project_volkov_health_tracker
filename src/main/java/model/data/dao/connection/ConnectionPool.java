package model.data.dao.connection;

import javax.sql.DataSource;
import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;

/**
 *   <h1>ConnectionPool class</h1>
 *  This class is used to handle various connection created in order to access the relative database.
 *  It provides an opportunity to set up the connection via pre-set properties of one (password, url ect)
 *  and get connection pool
 */
public class ConnectionPool {
    /**
     * JDBC Driver Name
     */
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    /**
     * Database URL
     */
    static final String JDBC_DB_URL = "jdbc:mysql://127.0.0.1:3306/final_project";
    /**
     * JDBC Database user
     */
    static final String JDBC_USER = "root";
    /**
     * JDBC Database password
     */
    static final String JDBC_PASS = "";

    /**
     * Generic Object Pool to set up
     */
    private static GenericObjectPool gPool = null;

    /**
     * This method sets up properties to the generic pool
     * @return DataSource The data source with the pre-set values
     * @throws Exception
     */
    @SuppressWarnings("unused")
    public DataSource setUpPool() throws Exception {
        Class.forName(JDBC_DRIVER);

        // Creates an Instance of GenericObjectPool That Holds Our Pool of Connections Object!
        gPool = new GenericObjectPool();
        gPool.setMaxActive(5);

        // Creates a ConnectionFactory Object Which Will Be Use by the Pool to Create the Connection Object!
        ConnectionFactory cf = new DriverManagerConnectionFactory(JDBC_DB_URL, JDBC_USER, JDBC_PASS);

        // Creates a PoolableConnectionFactory That Will Wraps the Connection Object Created by the ConnectionFactory to Add Object Pooling Functionality!
        PoolableConnectionFactory pcf = new PoolableConnectionFactory(cf, gPool, null, null, false, true);
        return new PoolingDataSource(gPool);
    }

    /**
     * This method is used to get generic connection pool
     * @return GenericObjectPool return generic connection pool
     */
    public GenericObjectPool getConnectionPool() {
        return gPool;
    }

    /**
     * This Method Is Used To Print The Connection Pool Status
     */
    public void printDbStatus() {
        System.out.println("Max.: " + getConnectionPool().getMaxActive() + "; Active: " + getConnectionPool().getNumActive() + "; Idle: " + getConnectionPool().getNumIdle());
    }
}
