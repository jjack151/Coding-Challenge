# Coding-Challenge
Entry Level Coding Challenge for Mountain State Solutions


A. Summary of the purpose of this repo

The purpose of this repo is to demonstrate my knowledge of the Java Language by writing a Java program that will consume a CSV file, parse the data from that file, and insert valid records into a SQLite database. Essentially the program must create a SQLite datbase with a 10-column table, read a CSV file and parse the data so that it is insert into to the proper column for each row read. At the same time the program must check that each column of the row read contains data. If not that row will be written to bad csv file. As the program reads the CSV line by line, each valid record and failed record is counted with those numbers being written to a log file. Towards the end of the the total number of records is also counted and written to the same log file.

B. Steps for getting this app running (for fellow developers)

I used the Netbeans IDE to write, compile, and execute my program. Therefore, the steps I list below are using the Netbeans IDE. I have tested it on the command line and will include the step on how to execute it through the command line. However, I had trouble compiling the files on there, so I suggest using an IDE to do so.

1. Download CSV file provided for testing and the Netbeans Project I have pushed to Github.
   The project is zipped so to open it: 
          a. Go to file directory where it was downloaded and right click and select Extract All
          b. After extract the files in the zipped folder, launch Netbeans
          c. Click File Open Project select the project from the file directory.
   
2. Check that the name of the file remains the same "Entry Level Coding Challenge Page 2.csv" to ensure that the file is found.
   
3. Prior to compiling and running the project, ensure that you have downloaded the latest version of sqlite-jdbc-(VERISON).jar from sqlite-jdbc repository and that it is added to the class path
        a. Right-click Project
        b. Select Properties
        c. On the left-hand side click Libraries
        d. Under Compile-tab ensure that the jar file is there if not click Add Jar/Folder button
        
4. Compile and run.

5. You will be prompt to enter the file directory in which you download the CSV file to. Note that the bad csv file, log file, and database file will all be created and stored in that same file directory.

To run on command line: (jar file is update every time after the program being compiled.)

Simply go to the dist file located in the Netbeans project folder by using the cd to get to that file directory.
Then use the command: "java -jar Coding_Challenge.jar"

For example.

      a. C:\Users\username> cd Desktop/Coding_Challenge Project/dist
      b. C:\Users\username\Desktop\Coding_Challenge Project\dist> java -jar Coding_Challenge.jar

C. Overview of approach, design choices, and assumptions.

I read the requirements of the program and began write two algorithms on a piece of paper.

   SQLite Algorithm:
    Create database -> Create table
   CSV Read and Write Algorithm
    Read CSV File -> Parse Data -> Check Data
    -> Valid Data, False Data -> Insert into DB table, Write line to bad CSV

From these algorithms, I created two classes, SQLite_DB and CSV_RW. The first to handle the SQLite portion of the program and the second to handle reading and writing to CSV files as well as my log file.

Due to my unfamiliarity with SQLite Databases, I began to conduct research by googling SQLite Databases using Java.
There I came across the site https://www.javatpoint.com/java-sqlite which I used as a reference to writing the code the SQLite and made modifications as to work for this particular program. 

For the CSV portion, I open the file on Notepad++ to see what it contained in terms of data. For optmization, I decided to read the file line by line as it contained several rows of data. Moreover, I noticed that the 5th column had "" marks around the data. With this in mind, I could not just parse the data in one shot using the split(",",10) I had to do it in steps. I would split(",",5) so that the last index of the first array contained the rest of the data. I would do perform one more split ("\"",3) to separate the 5th column from the remaining data. Then carry out one last split (",",6) to parse the rest of the data. By performing the split method three times, I would have three arrays; therefore, I decided to form one from the three so I could just pass one to the insert method to insert the data to its corresponding column in the SQLite Database table. Each time I parse the data, I would check that the data was valid prior to calling the insert method if not the entire line would be written to a bad CSV. With that being said I implement two counts one for successful and one for failed. Each count would increment depending on the case. At the end they would be added to receive the total number of records and each count including the total would be written to a log file.

Upon testing my program multiple times, I noticed that if the SQLite DB file existed, the DB table would update with the same records. The solution would be to either delete the previous records using select method, drop the table if it exist or delete the database to prevent any altercations to the results if the program was to be run again. For optmization purposes, I made methods for the latter two solutions to tackle this, but decide to just drop the table and comment out the code that would delete the DB. This two methods would be faster than select and delete. Moreover, I search for ways to reduce runtime and upon searching https://docs.oracle.com/javase/8/docs/api/ I found that I could manual commit everytime there was a change to the SQLite database table. To optmize runtime, I disable AutoCommit, and implement the rollback method to prevent any issues with an exception being thrown as well as the commit method to manually commit changes at the end instead each time there was a change. Lastly I had hardcoded the file paths for the files I would be using or creating and decided that the user should have input as he or she might store the input file needed for this program elsewhere. 

To confirm the results and make sure my database was created correctly, I also used https://inloop.github.io/sqlite-viewer/
