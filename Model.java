/*
 * This is the main model of the program. All methods for accessing 
 * and manipulating the database are defined here
 */
 import java.sql.*;
 import java.lang.reflect.Method;
 
 public class Model {
    private static String DRIVER = "com.mysql.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost/sports";
    private static String USER = "root";
    private static String PASSWORD = "root";
    private Connection conn = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet result = null;
    
    public void transactions(Model obj,Method t, Object...args) throws Exception {
    	startConnection();
        t.invoke(obj,args);
        closeConnection();
    }
    
    private void startConnection(){
    	/*
    	 * Setups and starts connection to the database
    	 */
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("Connection established");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    
    private void closeConnection(){
    	/*
    	 * Closes all active connections to the database
    	 */
        try {
            if(result != null) {
                result.close();
            }
            
            if(statement != null) {
                statement.close();
            }
            
            if(preparedStatement != null) {
                preparedStatement.close();
            }
            
            if(conn != null) {
                conn.close()
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
