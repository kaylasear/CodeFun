package problem1;

import java.io.IOException;
import java.util.List;

/**
 * Interface that serves similar to an Observer behavior, in which it updates the CSV file.
 */
public interface ToDoListObserver {

  /**
   * Add a new ToDoList to the CSV file
   */

  void addNewToDo() throws IOException;


  /**
   * Complete an existing ToDoList, CSV file is also updated
   * @param existingToDos - an existing ToDoList
   */
  void completeToDo(List<Integer> existingToDos)
      throws IOException, IllegalArgumentsException;
}
