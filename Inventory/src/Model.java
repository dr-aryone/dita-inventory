/*
 * This is the model of the program. It handles all connections to and from
 * the database
 */
import java.sql.*;
import java.lang.reflect.Method;

public class Model {
	private static String DRIVER = "com.mysql.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost/sports";
	private static String USER = "root";
	private static String PASSWORD = "root";
	private static Connection conn = null;
	private static Statement statement = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet result = null;
	
	Model() {
		startConnection();
		createTables();
		closeConnection();
	}
	public ResultSet transactions(Model obj, Method t, Object...args) throws Exception {
		/*
		 * Carries out all transactions to and from the database
		 */
		startConnection();
		t.invoke(obj, args);
		closeConnection();
		return result;
	}
	
	public void createTables() {
		/*
		 * Creates tables in the database if they don't exist
		 */
		//
	}
	
	public void addItem(){
		//
	}

	public void startConnection() {
		/*
		 * Initiates connection to the database
		 */
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connection established");
		} catch (Exception e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    System.exit(0);
		}
	}
	
	public void closeConnection() {
		/*
		 * Closes connection to the database
		 */
		try {
			if(result != null)
				result.close();
			
			if(statement != null)
				statement.close();
			
			if(preparedStatement != null)
				preparedStatement.close();
			
			if(conn != null)
				conn.close();
			System.out.println("Connection closed");
		} catch (Exception e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    System.exit(0);
		}
	}
	

}
