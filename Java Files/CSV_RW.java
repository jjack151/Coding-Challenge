package coding_challenge; //Package coding_challenge establishes a namespace that organizes the set of classes related to this program.

import java.io.File; // imports the File library class
import java.io.FileNotFoundException; // imports the FileNotFoundException library class
import java.util.Scanner; // imports the Scanner library class
import java.io.PrintWriter; // imports the PrintWriter library class


/** CSV_RW Class
 *  This class implements the use of three methods: "CSV_Read_Write()",
 *  "setRecords()" and "getRecords()" to handle CSV part of the program as well as the statistic file.
 *  Reads the input CSV provided, parses the data from the file, and calls a method from the SQLite
 *  library class to insert valid records into our table stored in our SQLite Database.
 *  Non-valid records are written to a bad CSV file and the statistics of records received successful
 *  or failed are written to a log file.
 */
public class CSV_RW{
    
    String[] finalData = new String [10]; // Creates string array to store 10 elements that will be inserted in our SQLite Database
    File inputFile; // Declares inputFile as File type
    File outputFile; // Declares outputFIle as File type
    File logFile; // Declares logFile as File type
    
    /** CSV_RW Constructor
    * Constructs CSV_RW Object w/ the File variables set to its corresponding file path.
    */
    CSV_RW(String pathToCsv, String pathTobadCsv, String pathTolog){
        
        inputFile = new File(pathToCsv); // Initializes inputFile w/ the file path for CSV
        outputFile = new File (pathTobadCsv); // Initializes outputFile w/ the file path for Bad CSV
        logFile = new File (pathTolog); // Initializes logFile w/ the file path for statistics file
          
    }
    
    /** CSV_Read_WRite Method
     * Reads the CSV file provided line by line and parses through the data. As it does this
     * it verifies that each line contains the right # of data elements to match the columns in our SQLite
     * Database Table. If it doesn't, that line is then written to a badCSV file. Moreover, it writes to a log file
     * each record received, and whether or not they were successful or failed. 
     * @throws FileNotFoundException when a file with a specified pathname does not exist
     */
    public void CSV_Read_Write() throws FileNotFoundException{
        
        Scanner csv = new Scanner(inputFile, "UTF-8"); // Opens our CSV file to be read from
        PrintWriter badCSV = new PrintWriter(outputFile); // Opens our badCSV file to be written to
        PrintWriter log = new PrintWriter(logFile); // OPens our statistics log file to be written too
        
        String header = csv.nextLine(); // Stores header from CSV file
        badCSV.write(header + "\r\n"); // Writes header to badCSV file
        
        int successfulRecords = 0; // Used to keep count of successful records received
        int failedRecords = 0; // Used to keep count of failed records received
   
        //while loop that will iterate through our csv file until End of File is reached
        while(csv.hasNextLine()){
            
            String row = csv.nextLine(); // Stores each line of the csv file as a string

            if (!row.isEmpty()){ // Checks to make sure row is not empty

                // Breaks the string around matches of the given regular expression "," into 5 elements and stores it into our string array data
                String[] data = row.split(",", 5); 
                
                // Checks to see that the contents of the first 4 indexes of the array are not empty. Proceeds if they are not. Else writes to bad CSV file
                if((!data[0].isEmpty())&&(!data[1].isEmpty())&&(!data[2].isEmpty())&&(!data[3].isEmpty())){
                    
                    String[] image = data[4].split("\"",3); // Removes the "" around the element in the 5th column if it exist and stores the rest of the remaining data into string array image
                    
                    // Checks the length of array is 3 if it is not, 5th element did not exist therefore write to badCSV file
                    if(image.length == 3){
                    
                        String[] data2 = image[2].split(",", 6); // Breals up what remains of the line read into 5 more elements and stores it into our string array data2
                        
                        // Checks to see that the contents of the last 5 indexes of the array are not empty. Proceeds if they are not. Else writes to bad CSV file
                        if((!data2[1].isEmpty())&&(!data2[2].isEmpty())&&(!data2[3].isEmpty())&&(!data2[4].isEmpty())&&(!data2[5].isEmpty())){
                            
                            setRecords(data, image, data2); //Calls method setRecords and passes our three string array that hold different elements from the line read
                            SQLite_DB.insert(getRecords()); // Calls the insert method from SQLite_DB class and passes array that contains all elements
                            ++successfulRecords; // Increments successful records received count
                        }
                        
                        else{
                            badCSV.write(row + "\r\n"); // writes line to bad csv
                            failedRecords++; // Increments count for failed records received
                        }
                        
                    }
                    
                    else{
                        badCSV.write(row + "\r\n"); // writes line to bad csv
                        failedRecords++; // Increments count for failed records received
                    }
                    
                }
                
                else{
                    badCSV.write(row + "\r\n"); // writes line to bad csv
                    failedRecords++; // Increments count for failed records received
                }
                
            }
        }
        
        csv.close(); // Closes given csv file to read from
        badCSV.close(); // Closes bad csv that was written to
        SQLite_DB.closeConnection(); // Calls method closeConnection from SQLite class to close connection to database
        
        int totalRecords = successfulRecords + failedRecords; // Adds up total records received
        
        log.println("# of records received: " + totalRecords); //Writes to log file total # of records received
        log.println("# of records successful: " + successfulRecords); //Writes to log file # of records that were successful
        log.println("# of records failed: " + failedRecords); //Writes to log file # of records that failed
        log.close(); // Closes log file that was written to
        
        
    }
    
    /** setRecords Method
     * Stores all data elements taken from the three different string array into one
     * @param array1 Contains the first 4 data elements
     * @param array2 Contains the 5th data element
     * @param array3 Contains the last 5 data elements
     */
    private void setRecords(String[] array1, String[] array2, String [] array3){
        int i = 0; // Used to iterate through our three arrays
        
        /* for loop use for storing all data elements into one array
           Stores the data from particular array based on the condition met.
        */
        for(int count = 0; count < finalData.length; count++){
            // Stores the first 4 data elements                    
            if(count < 4){
                finalData[count] = array1[i];
                i++;
            }
            // Stores 5th data element
            else if (count == 4){
                finalData[count] = array2[1];
                i = 1;
            }
            // Stores the last 5 data elements                   
            else{
               finalData[count] = array3[i];
               i++;
           }
        }
        
    }
    
    /** getRecords Method
     * 
     * @return String array containing all data elements to be inserted in our SQLite Database Table
     */
    public String[] getRecords(){
        
        return finalData;
    }
    
    
}