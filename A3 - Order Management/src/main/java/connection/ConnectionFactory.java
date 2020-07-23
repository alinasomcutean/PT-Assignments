package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class contains all the information needed by the application such that it can connect to the database
 * @author Alina Somcutean
 *
 */
public class ConnectionFactory {
	private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/order_management?autoReconnect=true&useSSL=false";
	private static final String USER = "root";
	private static final String PASS = "";
	
	private static ConnectionFactory singleInstance = new ConnectionFactory();
	
	private ConnectionFactory() {
		try {
			Class.forName(DRIVER);
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Method which creates a connection with the database
	 * @return connection created
	 */
	private Connection createConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DBURL, USER, PASS);
		} catch(SQLException e) {
			LOGGER.log(Level.WARNING, "An error occured while trying to connect to the database");
			e.printStackTrace();
		}
		return connection;
	}
	
	public static Connection getConnection() {
		return singleInstance.createConnection();
	}
	
	/**
	 * Method which close a connection
	 * @param connection connection that has to be close
	 */
	public static void close(Connection connection) {
		if(connection != null) {
			try {
				connection.close();
			} catch(SQLException e) {
				LOGGER.log(Level.WARNING, "An error occured while trying to close the connection");
			}
		}
	}
	
	/**
	 * Method which close a statement
	 * @param statement statement that has to be close
	 */
	public static void close(Statement statement) {
		if(statement != null) {
			try {
				statement.close();
			} catch(SQLException e) {
				LOGGER.log(Level.WARNING, "An error occured while trying to close the statement");
			}
		}
	}
	
	/**
	 * Method which close a result set
	 * @param resultSet result set that has to be close
	 */
	public static void close(ResultSet resultSet) {
		if(resultSet != null) {
			try {
				resultSet.close();
			} catch(SQLException e) {
				LOGGER.log(Level.WARNING, "An error occured while trying to close the result set");
			}
		}
	}
}