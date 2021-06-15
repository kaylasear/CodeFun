package problem1;
import java.util.List;
import java.util.Objects;

/**
 * This class represents a To Do list that has a text description and ID, along with
 * optional fields such as date, priority, completeness, and category.
 */
public class ToDoList {
  private static final int INCREMENT_VALUE = 1;
  
  private Integer id;
  private String text;
  private boolean completed;
  private String dueDate;
  private String priority;
  private String category;
  private List<ToDoList> listOfToDos;



  /***
   * The constructor for ToDoList class in case the optional fields are provided
   * @param text text definition of the todo
   * @param completed boolean representing whether task is completed
   * @param dueDate the dueDate of the todo
   * @param priority the priority of the todo
   * @param category the category of the todo
   */
  public ToDoList(String text, boolean completed, String dueDate, String priority, String category, List<ToDoList> listOfToDos) {
    this.id = listOfToDos.size() + INCREMENT_VALUE;
    this.text = text;
    this.completed = completed;
    this.dueDate = dueDate;
    this.priority = priority;
    this.category = category;
    this.listOfToDos = listOfToDos;
  }

  /***
   * A constructor for the ToDoList class
   * @param id of the todo
   * @param text text definition of the todo
   * @param completed boolean representing whether task is completed
   * @param dueDate the dueDate of the todo
   * @param priority the priority of the todo
   * @param category of the todo
   */
  public ToDoList(Integer id, String text, boolean completed, String dueDate, String priority, String category) {
    this.id = id;
    this.text = text;
    this.completed = completed;
    this.dueDate = dueDate;
    this.priority = priority;
    this.category = category;
  }

  /***
   * Getter method for ID
   * @return id
   */
  public Integer getId() {
    return id;
  }

  /***
   * Setter method for ID
   * @param id of the todo
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /***
   * Getter method for text
   * @return text
   */
  public String getText() {
    return text;
  }

  /***
   * boolean method whether or not it is completed
   * @return T/F
   */
  public boolean isCompleted() {
    return completed;
  }
  /***
   * setter method for setting the task as completed
   * @param completed T/F
   */
  public void setCompleted(boolean completed) {
    this.completed = completed;
  }
  /***
   * Getter method for getting due date
   * @return dueDate
   */
  public String getDueDate() {
    return dueDate;
  }

  /***
   * getting the priority of the todo
   * @return an integer priority
   */
  public String getPriority() {
    return priority;
  }

  /***
   * Getter for Category of the todo
   * @return the category
   */
  public String getCategory() {
    return category;
  }

  /***
   * Getter method for getting the list of To Dos
   * @return the list of to dos
   */
  public List<ToDoList> getToDoList() {
    return listOfToDos;
  }



  /***
   * Checks the equality
   * @param o
   * @return T/F
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ToDoList)) {
      return false;
    }
    ToDoList toDoList = (ToDoList) o;
    return isCompleted() == toDoList.isCompleted() && Objects
        .equals(getId(), toDoList.getId()) && Objects.equals(getText(), toDoList.getText())
        && Objects.equals(getDueDate(), toDoList.getDueDate()) && Objects
        .equals(getPriority(), toDoList.getPriority()) && Objects
        .equals(getCategory(), toDoList.getCategory()) && Objects
        .equals(getToDoList(), toDoList.getToDoList());
  }
  /***
   * calculates the hashCode of the class
   * @return an integer hashCode
   */
  @Override
  public int hashCode() {
    return Objects
        .hash(getId(), getText(), isCompleted(), getDueDate(), getPriority(), getCategory(),
            getToDoList());
  }
  /***
   * The toString method to convert the class to a string
   * @return a string version of the class
   */
 @Override
  public String toString() {
    return "ToDoList{" +
        "id=" + id +
        ", text='" + text + '\'' +
        ", completed=" + completed +
        ", dueDate='" + dueDate + '\'' +
        ", priority=" + priority +
        ", category='" + category + '\'' +
        ", listOfToDos=" + listOfToDos +
        '}';
  }
}
