package problem1;


import java.util.logging.Logger;

/**
 * Exception class for handling invalid command line arguments
 */
public class IllegalArgumentsException extends Exception {
  Logger log = Logger.getLogger(IllegalArgumentsException.class.getName());

  /**
   * Constructs a new exception with the specified detail message.  The cause is not initialized, and
   * may subsequently be initialized by a call to {@link #initCause}.
   *
   * @param message the detail message. The detail message is saved for later retrieval by the {@link
   *                #getMessage()} method.
   */
  public IllegalArgumentsException(String message) {
    super(message);

    if (message.equals("display error")) {
      this.printDisplayMessage();
    }
    if (message.equals("CSV error")) {
      this.printCSVErrorMessage();
    }
    if (message.equals("ID error")) {
      this.printIDErrorMessage();
    }
  }


  /**
   * Display warning if optional display arguments were provided
   * without the display argument
   */
  public void printDisplayMessage() {
    String message = " Error: no --display options are available.";

    log.warning(message);
  }

  /**
   * Display warning if csv file or todos text was not provided
   */
  public void printCSVErrorMessage() {
    String message = "Error: no --csv-file or todo text was given. Please read message on usage \n"
        + "Usage: \n"
        + "--csv-file <path/to/file> The CSV file containing todos. This option is required \n"
        + "--add-todo Add a new todo (can only add one at a time). If this is provided, then --todo-text must also be provided \n"
        + "--todo-text 'description of todo' a description of the todo, please put text inside quotes \n"
        + "--completed (Optional) sets the completed status of a new todo to true \n"
        + "--due <due date> (Optional) sets the due date of a new todo. Format: MM/DD/YY example: 04/12/21 \n"
        + "--priority <1, 2, 3> Sets the priority of a new todo \n"
        + "--category 'a category' (Optional) Sets the category of a new todo, please put text inside quotes \n"
        + "--complete-todo <id> Mark the ToDo with the provided ID as complete \n"
        + "\n"
        + "Examples: \n"
        + "--csv-file customer.csv \n"
        + "--add-todo \n"
        + "--todo-text 'take out garbage'";

    log.warning(message);
  }

  /**
   * Display message if user attempts to complete a TodoList that doesn't exist
   */
  public void printIDErrorMessage() {
  String message = "ID doesn't exist";

  log.warning(message);
  }
}
