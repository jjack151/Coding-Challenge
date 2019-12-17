package coding_challenge; //Package coding_challenge establishes a namespace that organizes the set of classes related to this program

import java.io.FileNotFoundException; // imports FileNotFoundException library class
import java.io.File; // imports File library class
import java.util.Scanner; // imports Scanner library class

/** Coding_Challenge
 * This program main file executes the implementation of CSV_RW and SQLite class
 * to carry out the following:
 * Consume csv file, parse data, and insert valid records into a SQLite database
 * Write bad records to another csv file
 * Keep count of records received: successful or failed and write to a log file
 * 
 */
public class Coding_Challenge {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException when a file with a specified pathname does not exist
     */
    public static void main(String[] args) throws FileNotFoundException{
        
        Scanner fileDirInput = new Scanner(System.in);
        Scanner fileName = new Scanner(System.in);
        System.out.print("Please enter the file directory where your CSV input file resides and other files are to be written to: "); // Asks for file directory.
        String file_Dir = fileDirInput.next();
        
        File DB_fileName = new File (file_Dir + "Entry Level Coding Challenge.db"); //File name for SQLite Database to be created
        String badCsv_Path = file_Dir + "Entry Level Coding Challenge-bad.csv"; // File name for Bad CSV File to be created
        String CSV_Path = file_Dir + "Entry Level Coding Challenge.csv"; // File name for CSV File to be read from
        String log_Path = file_Dir + "Entry Level Coding Challenge.log";  // File name for Statistics log to be created
     
        /*if(DB_fileName.exists()){
             SQLite_DB.deleteDatabase(DB_fileName);  // Calls method to delete SQLite Database if it exists
        }*/
        
        /* Calls createNewDatabase Method that resides in SQLite_DB class
           Passes DB_fileName of File type as a parameter
        */
        SQLite_DB.createNewDatabase(DB_fileName);
        
        /* Creates an object from the CSV_RW class
           Passes CSV_Path, badCsv_Path, and log_Path of String type as parameters
        */
        CSV_RW csV = new CSV_RW(CSV_Path, badCsv_Path, log_Path);
        
        csV.CSV_Read_Write(); //Calls the CSV_Read_Write Method that reside in the CSV_RW class
        
        
    }
    
}
