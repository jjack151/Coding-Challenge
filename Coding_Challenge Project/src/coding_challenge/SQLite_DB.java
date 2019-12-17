package coding_challenge; //Package coding_challenge establishes a namespace that organizes the set of classes related to this program.

import java.sql.Connection; // imports SQLite Connection library class
import java.sql.DriverManager; // imports SQLite Driver Manager library class
import java.sql.SQLException; // imports SQLite Exception library class
import java.sql.Statement; // imports SQLite Statement library class
import java.sql.PreparedStatement; // imports SQLite PreparedStatement library class
import java.io.File; // imports File library class

/** SQLite_DB Class
 *  Used online resource https://www.javatpoint.com/java-sqlite to implement some code
 *  for this class.
 * 
 *  This class implements the use of six methods: "createNewDatabase(File fileName)",
 *  "deleteDatabase(FileName)", "deleteTable()", "insert(String [] data)", and "closeConnection()"
 *  to handle SQLite database part of the program. Creates SQLite Database, creates the table within
 *  database, and inserts data parsed from csv file into the table. If the table exists already
 *  the old table is delete to prevent duplication of the records within the database. Upon completion,
 *  the connection to the database should be closed.
 */
public class SQLite_DB {
    
    private static String url = "jdbc:sqlite:";
    private static Connection conn = null; // Sets connection to SQL to null
    
    /** createNewDatabase Method
     *  Creates database, establishes connection to database, and calls for other
     *  methods such as delete table if table exist, create table if it does not exist
     *  and sets AutoCommit to false in order to optimize runtime by manual committing at the end
     * @param fileName fileName to be add to url so driver can establish connection to file
     */
    public static void createNewDatabase(File fileName){
        
        url += fileName; //Adds fileName to url
    
        try {
           conn = DriverManager.getConnection(url); // Establishes connection to DB
            
            if (conn != null){
                
                deleteTable(); // Calls method to delete table if it exist
                createNewTable(); // Calls method to create table if it doesn't exist
                conn.setAutoCommit(false); // Sets AutoCommit to false
                
            }
        } catch (SQLException e){
            System.out.println(e.getMessage()); // Prints error Message if database error occurs
        }
    }
    
    /** deleteDatabase Method
     *  Deletes database file
     * @param fileName Database file
     */
    public static void deleteDatabase(File fileName){
        fileName.delete(); // Deletes database file
    }
    
    /** deleteTable()
     * Deletes table if it exists
     */
    public static void deleteTable(){
        
        String sql = "DROP TABLE IF EXISTS CSV_Data";       
        try{    
            Statement stmt = conn.createStatement();  
            stmt.execute(sql);  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
        
    }
    
    /** createNewTable() Method
     *  Creates database table w/ 10 columns that corresponds to the CSV column header names
     */
    public static void createNewTable() {    
          
        // SQL statement for creating a new table  
        String sql = "CREATE TABLE IF NOT EXISTS CSV_Data (\n" 
                + " A text NOT NULL,\n"  
                + " B text NOT NULL,\n"  
                + " C text NOT NULL,\n"
                + " D text NOT NULL,\n"
                + " E text NOT NULL,\n"
                + " F text NOT NULL,\n"
                + " G text NOT NULL,\n"
                + " H text NOT NULL,\n"
                + " I text NOT NULL,\n"
                + " J text NOT NULL\n"
                + ");";
          
        try{    
            Statement stmt = conn.createStatement();  
            stmt.execute(sql);  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    
    }
    
    /** insert Method
     * Inserts data elements into table column by column for the given row
     * @param data String array that contains all data elements for one row
     */
    public static void insert(String [] data) {  
        String sql = "INSERT INTO CSV_Data(A,B,C,D,E,F,G,H,I,J) VALUES(?,?,?,?,?,?,?,?,?,?)";  
        try (PreparedStatement pstmt = conn.prepareStatement(sql)){  
            
            pstmt.setString(1, data[0]);  
            pstmt.setString(2, data[1]);
            pstmt.setString(3, data[2]);             
            pstmt.setString(4, data[3]); 
            pstmt.setString(5, data[4]); 
            pstmt.setString(6, data[5]); 
            pstmt.setString(7, data[6]); 
            pstmt.setString(8, data[7]); 
            pstmt.setString(9, data[8]);
            pstmt.setString(10,data[9]); 
            
            int rowAffected = pstmt.executeUpdate();  //Updates row within table. Returns int value for the # of rows updated
            
            //Checks that row was updated 
            if(rowAffected != 1){
                conn.rollback(); // undoes all changes made in current transaction and releases any database locks currently held by conn
            }
            
        } catch (SQLException e) {  
            System.out.println(e.getMessage()); 
        }   
     
    }
    
    /** closeConnection() Method
     * Used to commit all changes to the database prior to closing the connection.
     * Throws an exception if something goes wrong
     */
    public static void closeConnection(){
        try{
            if(conn != null){
                conn.commit();
                conn.close();            
            }
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        

    }
}
