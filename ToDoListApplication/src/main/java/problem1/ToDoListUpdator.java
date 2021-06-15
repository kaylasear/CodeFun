package problem1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


/**
 * Class represents a Controller that updates the CSV file by adding a new ToDoList
 * or updating an existing ToDoList
 */
public class ToDoListUpdator implements ToDoListObserver {
  private List<ToDoList> listOfToDos;
  private String csvFile;
  private List<List<String>> dataRows;


  /**
   * Constructor that has a list of ToDos and the provided CSV file
   * @param listOfToDos - list of ToDoLists
   * @param csvFile - CSV file of ToDos
   */
  public ToDoListUpdator(List<ToDoList> listOfToDos, String csvFile,  List<List<String>> rows) {
    this.listOfToDos = listOfToDos;
    this.csvFile = csvFile;
    this.dataRows = rows;
  }

  /**
   * Get the list of toDos
   * @return the list of toDos
   */
  public List<ToDoList> getListOfToDos() {
    return this.listOfToDos;
  }

  /**
   * Get the CSV file
   * @return the CSV file
   */
  public String getCsvFile() {
    return this.csvFile;
  }

  /**
   * Get the data rows
   * @return the data rows
   */
  public List<List<String>> getDataRows() {
    return this.dataRows;
  }

  /**
   * Add a new ToDoList to the CSV file
   *

  @Override
  public void addNewToDo() throws IOException {
   */
  @Override
  public void addNewToDo() throws IOException {

    for (ToDoList todo : listOfToDos) {
      List<String> x = this.convertToString(todo);
      dataRows.add(x);
    }

    this.writeToCSVFile();
  }

  /**
   * Helper method that converts the new ToDoList into a list of strings
   * @param newToDo a new ToDoList object
   */
  private List<String> convertToString(ToDoList newToDo) {
    Integer id = (newToDo.getId());
    String stringID = id.toString();
    boolean isComplete = newToDo.isCompleted();
    String sIsComplete = String.valueOf(isComplete);


    List<String> rows = Arrays.asList('"' + stringID + '"', '"' + newToDo.getText() + '"',
        '"' + sIsComplete + '"', '"' + newToDo.getDueDate() + '"', '"' + newToDo.getPriority() + '"',
        '"' + newToDo.getCategory() + '"');

    return rows;
  }


  /**
   * Complete an existing ToDoList, CSV file is also updated, throw error
   * message if ID doesn't exist
   *
   * @param existingToDos - an existing ToDoList
   */
  @Override
  public void completeToDo(List<Integer> existingToDos)
      throws IOException, IllegalArgumentsException {
    boolean foundID = false;

    // Update the ToDoList object to complete
    for (ToDoList toDo : listOfToDos) {
      if (existingToDos.contains(toDo.getId())) {
        toDo.setCompleted(true);
        foundID = true;
      }
      dataRows.add(convertToString(toDo));
    }

    // If ID doesn't exist, throw error message
    if (!foundID) {
      throw new IllegalArgumentsException("ID error");
    }

    this.writeToCSVFile();
  }


  /**
   * Helper method that updates the CSV file
   */
  private void writeToCSVFile() {
    try {
      File file = new File(this.csvFile);
      FileWriter writer = new FileWriter(this.csvFile);


      // Render it back out to CSV
      for (List<String> dataRow : dataRows) {
        writer.append(String.join(",", dataRow));
        writer.append("\n");
      }

      writer.flush();
      writer.close();

    } catch (Exception e){
      System.out.println("Error");
      e.printStackTrace();
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
    ToDoListUpdator updator = (ToDoListUpdator) o;
    return Objects.equals(listOfToDos, updator.listOfToDos) && Objects
        .equals(csvFile, updator.csvFile) && Objects.equals(dataRows, updator.dataRows);
  }

  @Override
  public int hashCode() {
    return Objects.hash(listOfToDos, csvFile, dataRows);
  }

  @Override
  public String toString() {
    return "ToDoListUpdator{" +
        "listOfToDos=" + listOfToDos +
        ", csvFile='" + csvFile + '\'' +
        ", dataRows=" + dataRows +
        '}';
  }
}
