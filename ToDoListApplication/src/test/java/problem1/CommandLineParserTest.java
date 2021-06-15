package problem1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class CommandLineParserTest {
  String[] commandArgs;
  CommandLineParser tester;
  List<String> argsList;
  List<Integer> toDoIDs;

  String csv = "--csv-file";
  String addToDo = "--add-todo";
  String toDoText = "--todo-text";
  String argPriority = "--priority";
  String argCategory = "--category";
  String argComplete = "--complete-todo";
  String argDate = "--due";


  String text = "some text";
  String priority = "1";
  String category = "home";
  String csvFile = "text.csv";
  String someID = "5";
  String date = "04/03/21";


  @Before
  public void setUp() throws Exception {
    commandArgs = new String[]{csv, csvFile, addToDo, toDoText, text, argCategory, category,
    argPriority, priority, argComplete, someID, argDate, date};


    argsList = Arrays.asList(csv, addToDo, toDoText, argCategory, argPriority, argComplete, argDate);
    toDoIDs = Arrays.asList(5);
    tester = new CommandLineParser(commandArgs);

    tester.parseArguments();
  }

  @Test
  public void getCommandArgs() {
    assertEquals(commandArgs, tester.getCommandArgs());
  }

  @Test
  public void getArgumentsList() {
    assertEquals(argsList, tester.getArgumentsList());
  }

  @Test
  public void getToDoText() {
    assertEquals(text, tester.getToDoText());
  }

  @Test
  public void getToDoDate() {
    assertEquals(date, tester.getToDoDate());
  }

  @Test
  public void getCsvPath() {
    assertEquals(csvFile, tester.getCsvPath());
  }

  @Test
  public void getToDoPriority() {
    assertEquals(priority, tester.getToDoPriority());
  }

  @Test
  public void getToDoCategory() {
    assertEquals(category, tester.getToDoCategory());
  }



  @Test
  public void getToDoIDs() {
  assertEquals(toDoIDs, tester.getToDoIDs());
    }


  @Test
  public void parseArguments() throws IllegalArgumentsException {
    assertEquals(argsList, tester.getArgumentsList());
  }


  @Test (expected = IllegalArgumentsException.class)
  public void parseArgumentsFail() throws IllegalArgumentsException {
    commandArgs = new String[]{csv, addToDo, argCategory, category,
        argPriority, priority, argComplete, someID, argDate, date};
    tester = new CommandLineParser(commandArgs);
    tester.parseArguments();
  }

  @Test (expected = IllegalArgumentsException.class)
  public void parseArgumentsFail2() throws IllegalArgumentsException {
    commandArgs = new String[]{csv, csvFile, "--sort-by-date", "--sort-by-priority", "--show-category", "--show-incomplete"};
    tester = new CommandLineParser(commandArgs);
    tester.parseArguments();
  }

  @Test (expected = IllegalArgumentsException.class)
  public void parseArgumentsFail3() throws IllegalArgumentsException {
    commandArgs = new String[]{argCategory, category,
        argPriority, priority, argComplete, someID, argDate, date};
    tester = new CommandLineParser(commandArgs);
    tester.parseArguments();
  }

  @Test (expected = IllegalArgumentsException.class)
  public void parseArgumentsFail4() throws IllegalArgumentsException {
    commandArgs = new String[]{csv, csvFile, "--display"};
    tester = new CommandLineParser(commandArgs);
    tester.parseArguments();
  }

  @Test (expected = IllegalArgumentsException.class)
  public void parseArgumentsFail5() throws IllegalArgumentsException {
    commandArgs = new String[]{csv, csvFile, "--show-category"};
    tester = new CommandLineParser(commandArgs);
    tester.parseArguments();
  }

  @Test (expected = IllegalArgumentsException.class)
  public void parseArgumentsFail6() throws IllegalArgumentsException {
    commandArgs = new String[]{csv, csvFile, "--sort-by-date"};
    tester = new CommandLineParser(commandArgs);
    tester.parseArguments();
  }

  @Test (expected = IllegalArgumentsException.class)
  public void parseArgumentsFail7() throws IllegalArgumentsException {
    commandArgs = new String[]{csv, csvFile, "--sort-by-priority"};
    tester = new CommandLineParser(commandArgs);
    tester.parseArguments();
  }

  @Test (expected = IllegalArgumentsException.class)
  public void parseArgumentsFail8() throws IllegalArgumentsException {
    commandArgs = new String[]{csv, csvFile, toDoText};
    tester = new CommandLineParser(commandArgs);
    tester.parseArguments();
  }

  @Test
  public void testEquals() throws IllegalArgumentsException {
    CommandLineParser tester2 = new CommandLineParser(commandArgs);
    CommandLineParser tester3 = new CommandLineParser(commandArgs);
    //Testing equals reflexivity and null
    TestCase.assertTrue(tester.equals(tester));
    TestCase.assertNotNull(tester);
    assertFalse(tester.equals(1));

    //Testing symmetry
    TestCase.assertTrue(tester.equals(tester2) == tester2.equals(tester));

    //Testing transitivity
    tester = tester2 = tester3;
    TestCase.assertTrue(tester.equals(tester2) && tester2.equals(tester3) && tester.equals(tester3));

    //Testing consistency
    TestCase.assertTrue(tester.equals(tester2));
    TestCase.assertTrue(tester.equals(tester2));

    //Testing for values
    String[] commandArgs2 = new String[]{csv, "random file", "--add-todo", "--todo-text", "random", "--priority", "r", "--category", "random Category", "--complete-todo", "10",
    "--due", "random date"};
    tester2 = new CommandLineParser(commandArgs2);
    assertFalse(tester.equals(tester2));

    tester.parseArguments();
    tester2.parseArguments();

    commandArgs2 = new String[]{csv, csvFile, addToDo, toDoText, "random", argCategory, category,
        argPriority, priority, argComplete, someID, argDate, date};

    tester2 = new CommandLineParser(commandArgs2);
    tester2.parseArguments();
    assertFalse(tester.getToDoText().equals(tester2.getToDoText()));

    commandArgs2 = new String[]{csv, csvFile, addToDo, toDoText, text, argCategory, "random",
        argPriority, priority, argComplete, someID, argDate, date};

    tester2 = new CommandLineParser(commandArgs2);
    tester2.parseArguments();
    assertFalse(tester.getToDoCategory().equals(tester2.getToDoCategory()));

    commandArgs2 = new String[]{csv, csvFile, addToDo, toDoText, text, argCategory, category,
        argPriority, "10", argComplete, someID, argDate, date};

    tester2 = new CommandLineParser(commandArgs2);
    tester2.parseArguments();
    assertFalse(tester.getToDoPriority().equals(tester2.getToDoPriority()));


  }

  @Test
  public void testHashCode() {
    assertFalse(0 == tester.hashCode());
  }

  @Test
  // testing consistency
  public void testHashCodeConsistency() {
    int testHashCode = tester.hashCode();
    assertEquals(testHashCode, tester.hashCode());
  }

  @Test
  // Testing consistency with equals
  public void testHashCodeConsistency2() {
    CommandLineParser tester2 = new CommandLineParser(commandArgs);
    assertTrue(tester.equals(tester2) == (tester.hashCode() == tester2.hashCode()));
  }

  @Test
  public void testToString() {
    String expected = "CommandLineParser{commandArgs=[--csv-file, text.csv, --add-todo, --todo-text, some text, --category,"
        + " home, --priority, 1, --complete-todo, 5, --due, 04/03/21]}";
    assertEquals(expected, tester.toString());
  }
}