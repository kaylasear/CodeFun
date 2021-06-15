package problem1;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

  /***
   * The Main class
   * @param args arguments
   * @throws IllegalArgumentsException illegal arguments
   * @throws IOException IOE exception
   */
  public static void main(String[] args) throws IllegalArgumentsException, IOException {

    // Creating command line parser object to parse in arguments
    CommandLineParser commandLineParser = new CommandLineParser(args);

    // Calling method to parse arguments and generate a list of arguments
    commandLineParser.parseArguments();

    // Depending on arguments.. create objects to call on methods
    List<String> arguments = commandLineParser.getArgumentsList();

    // Create FileProcessor Class to retrieve and store TodoList data in a list of ToDos
    String csvFile = commandLineParser.getCsvPath();

    FileProcessor fileProcessor = new FileProcessor(csvFile);

    fileProcessor.readFile();

    List<ToDoList> listOfToDos = fileProcessor.getListOfToDos();
    List<List<String>> dataRows = new ArrayList<>();

    dataRows.add(0, fileProcessor.getHeader());


    ToDoListUpdator updator = new ToDoListUpdator(listOfToDos, csvFile, dataRows);


    // If user adds a new toDoList, create ToDoList object with required/optional fields from CommandLineParser object
    if (arguments.contains("--add-todo")) {
      String text = commandLineParser.getToDoText();
      String category = commandLineParser.getToDoCategory();
      String priority = commandLineParser.getToDoPriority();
      String date = commandLineParser.getToDoDate();

      // initialize toDoList object with the fields
      ToDoList newToDo = new ToDoList(text, false, date, priority, category, listOfToDos);

      listOfToDos.add(newToDo);
      updator.addNewToDo();
    }

    // If user wants to complete todoList(s), call method to set the to do object to true
    if (arguments.contains("--complete-todo")) {
      updator.completeToDo(commandLineParser.getToDoIDs());
    }

  }
}