package problem1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * This class represents a file processor. It retrieves and stores the data from the CSV file
 * and stores it into a collection.
 */
public class FileProcessor {
  private static final int MAX_HEADER_VALUE = 6;
    
  private String csvFile;
  private List<List<String>> dataRows;

  private List<ToDoList> listOfToDos;
  private List<String> header;


  /**
   * Constructor that holds a CSV file and initializes a list to hold rows of data
   * @param csvFile - a CSV file
   */
  public FileProcessor(String csvFile) {
    this.csvFile = csvFile;
    this.dataRows = new ArrayList<>();
    this.listOfToDos = new ArrayList<>();
    this.header = new ArrayList<>();
  }

  /**
   * Get the CSV file
   * @return the csv file
   */
  public String getCsvFile() {
    return this.csvFile;
  }


  /**
   * Get the list of list of data rows
   * @return a lists of data rows
   */
  public List<List<String>> getDataRows() {
    return this.dataRows;
  }


  /**
   * Get the list of ToDoLists
   * @return a lists of ToDoLists
   */
  public List<ToDoList> getListOfToDos() {
    return this.listOfToDos;
  }



  /**
   * Method that reads the CSV file and stores it into a list that holds a list of ToDoList objects
   */
  public void readFile(){
    BufferedReader inputFile = null;
    String errString = "Failed to close input stream in finally block";

    try {
      inputFile = new BufferedReader(new FileReader(this.csvFile));

      String line;

      while ((line = inputFile.readLine()) != null) {
        List<String> items = Arrays.asList(line.substring(1, line.length() - 1).split("\",\""));
        this.dataRows.add(items);
      }
    } catch (FileNotFoundException fnfe) {
      System.out.println("*** OOPS! A file was not found : " + fnfe.getMessage());
      fnfe.printStackTrace();
    } catch (IOException ioe) {
      System.out.println("Something went wrong! : " + ioe.getMessage());
      ioe.printStackTrace();
    } finally {
      if (inputFile != null) {
        try {
          inputFile.close();
        } catch (IOException e) {
          System.out.println(errString);
          e.printStackTrace();
        }
      }
    }
    this.setHeaders();
    this.createToDos();

  }

  /**
   * Method returns the header values
   * @return the header
   */
  public List<String> getHeader() {
    this.convertToString();
    return this.header;
  }


  /**
   * Helper method that adds quotes to header values
   */
  private void convertToString() {

    this.header = Arrays.asList('"' + header.get(0) + '"', '"' + header.get(1) + '"',
        '"' + header.get(2) + '"', '"' + header.get(3) + '"', '"' + header.get(4) + '"',
        '"' + header.get(5) + '"');

  }

  /**
   * Method removes the header values from list of data rows
   */
  private void setHeaders() {
     List<List<String>> x = Arrays.asList(this.dataRows.remove(0));

     this.header = x.get(0);

  }

  /**
   * Helper method adds list of data rows into list of ToDoLists objects
   */
  private void createToDos() {
    for (List<String> data : this.dataRows) {
      for (int i = 0 ; i < MAX_HEADER_VALUE ; i++){
        if (data.get(i).equals("?")) {

          data.set(i, null);
        }
      }

      Integer id = Integer.parseInt(data.get(0));
      String text = data.get(1);
      Boolean isComplete = Boolean.parseBoolean(data.get(2));
      String date = data.get(3);
      String priority = data.get(4);
      String category = data.get(5);



      ToDoList toDoList = new ToDoList(id, text, isComplete,
          date, priority, category);

      this.listOfToDos.add(toDoList);
    }
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileProcessor that = (FileProcessor) o;
    return Objects.equals(csvFile, that.csvFile) && Objects
        .equals(dataRows, that.dataRows);
  }

  @Override
  public int hashCode() {
    return Objects.hash(csvFile, dataRows);
  }

  @Override
  public String toString() {
    return "FileProcessor{" +
        "csvFile='" + csvFile + '\'' +
        ", dataRows=" + dataRows +
        '}';
  }
}
