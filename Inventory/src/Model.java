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
		String sql;
		try {
			statement = conn.createStatement();
			sql = "CREATE TABLE IF NOT EXISTS Teams" +
				  "(name VARCHAR(30) NOT NULL," +
				  "total_members INTEGER NOT NULL," +
				  "PRIMARY KEY(name))";
			statement.addBatch(sql);
			
			
			sql = "CREATE TABLE IF NOT EXISTS Members" +
				  "(name VARCHAR(30) NOT NULL," +
				  "team VARCHAR(30) NOT NULL," +
				  "password VARCHAR(50) NOT NULL," +
				  "PRIMARY KEY(name)," +
				  "FOREIGN KEY(team) REFERENCES Teams(name))";
			statement.addBatch(sql);
			
			sql = "CREATE TABLE IF NOT EXISTS Items" +
				  "(name VARCHAR(20) NOT NULL," +
				  "quantity INTEGER NOT NULL," +
				  "current_quantity INTEGER NOT NULL," +
				  "PRIMARY KEY(name))";
			statement.addBatch(sql);
			
			sql = "CREATE TABLE IF NOT EXISTS Log" +
				  "(indx INTEGER NOT NULL," +
				  "item VARCHAR(20) NOT NULL," +
				  "time_out TIME NOT NULL," +
				  "time_in TIME DEFAULT NULL," +
				  "name VARCHAR(30) NOT NULL," +
				  "quantity INTEGER NOT NULL," +
				  "PRIMARY KEY(indx)," +
				  "FOREIGN KEY(name) REFERENCES Members(name))";
			statement.addBatch(sql);
		
			statement.executeBatch();
			System.out.println("Tables created successfully");
		} catch(Exception e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    //System.exit(0);
		}
		
	}
	
	public void addItem(){
		///
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
