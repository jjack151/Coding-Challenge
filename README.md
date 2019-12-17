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

To run on command line: //jar file is update every time after the program being compiled.

Simply go to the dist file located in the Netbeans project folder by using the cd to get to that file directory.
Then use the command: "java -jar Coding_Challenge.jar"

For example.

      a. C:\Users\username> cd Desktop/Coding_Challenge Project/dist
      b. C:\Users\username\Desktop\Coding_Challenge Project\dist> java -jar Coding_Challenge.jar

C. Overview of approach, design choices, and assumptions.



