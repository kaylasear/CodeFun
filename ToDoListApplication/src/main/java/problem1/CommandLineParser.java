package problem1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


/**
 * This class parses in the command line arguments from the user and stores
 * data for a new ToDoList object
 */
public class CommandLineParser {
  private String[] commandArgs;
  private List<String> argumentsList;
  private String toDoText;
  private String toDoDate = null;
  private String csvPath = null;
  private String toDoPriority = null;
  private String toDoCategory = null;
  private List<Integer> toDoIDs;

  /***
   * The Constructor for CommanndLineParser class
   * @param commandArgs a string array for arguments
   */
  public CommandLineParser(String[] commandArgs) {
    this.commandArgs = commandArgs;
    this.argumentsList = new ArrayList<>();
    this.toDoIDs = new ArrayList<>();
  }

  /***
   * The getter method for CommandArgs
   * @return the commandArgs
   */
  public String[] getCommandArgs() {
    return this.commandArgs;
  }

  /***
   * The getter method for ArgumentsList
   * @return argumentsList
   */
  public List<String> getArgumentsList() {
    return this.argumentsList;
  }

  /***
   * The getter method for ToDoText, which is the context of a todo
   * @return toDoText
   */
  public String getToDoText() {
    return this.toDoText;
  }

  /***
   * Getter method for ToDoDate, the date that a toDo is due
   * @return the todoDate
   */
  public String getToDoDate() {
    return this.toDoDate;
  }

  /***
   * The getter method for getting the Csv path
   * @return csv path
   */
  public String getCsvPath() {
    return this.csvPath;
  }

  /***
   * The getter method for ToDoPriority
   * @return
   */
  public String getToDoPriority() {
    return this.toDoPriority;
  }

  /***
   * The Getter method for ToDoCategory
   * @return
   */
  public String getToDoCategory() {
    return this.toDoCategory;
  }

  /***
   * The getter method for to do IDs
   * @return
   */
  public List<Integer> getToDoIDs() {
    return this.toDoIDs;
  }


  /**
   * Method parses the arguments from the command line and
   * stores it into argumentsList field
   */
  public void parseArguments() throws IllegalArgumentsException {
    int i;

    try {
      for (i = 0; i < this.commandArgs.length; i++) {
        if (this.commandArgs[i].startsWith("--")) {
          this.argumentsList.add(this.commandArgs[i]);
        }
        if (this.commandArgs[i].equals("--todo-text")) {
          this.toDoText = this.commandArgs[i + 1];
        }
        if (this.commandArgs[i].equals("--due")) {
          this.toDoDate = this.commandArgs[i + 1];
        }
        if (this.commandArgs[i].equals("--csv-file")) {
          this.csvPath = this.commandArgs[i + 1];
        }
        if (this.commandArgs[i].equals("--priority")) {
          this.toDoPriority = this.commandArgs[i + 1];
        }
        if (this.commandArgs[i].equals("--category")) {
          this.toDoCategory = this.commandArgs[i + 1];
        }
        if (this.commandArgs[i].equals("--complete-todo")) {
          Integer id = Integer.parseInt(this.commandArgs[i+1]);
          this.toDoIDs.add(id);
        }
      }
    } catch (Exception e) {
      System.out.println("Error");
    }

    checkArguments();
  }



  /**
   * This method checks for the required arguments throws error message if the required argument(s) was not provided
   * @throws IllegalArgumentException - if required arguments not provided
   */
  private void checkArguments() throws IllegalArgumentsException {
    if (!this.argumentsList.contains("--csv-file") || this.csvPath.equals(null)) {
      throw new IllegalArgumentsException("CSV error");
    }
    if (this.argumentsList.contains("--add-todo")) {
      if (!this.argumentsList.contains("--todo-text")) {
        throw new IllegalArgumentsException("CSV error");
      }
    }
    if (this.argumentsList.contains("--todo-text")) {
      if (!this.argumentsList.contains("--add-todo")) {
        throw new IllegalArgumentsException("CSV error");
      }
    }
    if (this.argumentsList.contains("--show-incomplete")) {
        throw new IllegalArgumentsException("display error");
    }
    if (this.argumentsList.contains("--show-category")) {
        throw new IllegalArgumentsException("display error");
    }
    if (this.argumentsList.contains("--sort-by-date")) {
        throw new IllegalArgumentsException("display error");
    }
    if (this.argumentsList.contains("--sort-by-priority")) {
        throw new IllegalArgumentsException("display error");
    }
    if (this.argumentsList.contains("--display")) {
        throw new IllegalArgumentsException("display error");
    }
  }

  /***
   * Equals methos, checks the equality of the object
   * @param o object
   * @return T/F
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CommandLineParser that = (CommandLineParser) o;
    return Arrays.equals(commandArgs, that.commandArgs) && Objects
        .equals(argumentsList, that.argumentsList) && Objects
        .equals(toDoText, that.toDoText) && Objects.equals(toDoDate, that.toDoDate)
        && Objects.equals(csvPath, that.csvPath) && Objects
        .equals(toDoPriority, that.toDoPriority) && Objects
        .equals(toDoCategory, that.toDoCategory) && Objects.equals(toDoIDs, that.toDoIDs);
  }

  /***
   * Calculates an integer hashCode of the object
   * @return an integer
   */
  @Override
  public int hashCode() {
    int result = Objects
        .hash(argumentsList, toDoText, toDoDate, csvPath, toDoPriority, toDoCategory, toDoIDs);
    result = 31 * result + Arrays.hashCode(commandArgs);
    return result;
  }

  /***
   * Converts the object to String
   * @return the string version
   */
  @Override
  public String toString() {
    return "CommandLineParser{" +
        "commandArgs=" + Arrays.toString(commandArgs) +
        '}';
  }
}
