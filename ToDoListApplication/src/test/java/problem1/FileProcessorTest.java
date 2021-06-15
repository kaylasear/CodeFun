package problem1;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class FileProcessorTest {
  String csvFile;
  List<List<String>> dataRows;
  FileProcessor test;

  @Before
  public void setUp() throws Exception {
    //csvFile = "/Users/Zahra/Desktop/Group_Yalda_Zahra_Ali_Aghazadeh_Boyd_Anderson_Kayla_Sear/Assignment9/src/test/java/problem1/test.csv";
    //csvFile = "\\Users\\Zahra\\Desktop\\Group_Yalda_Zahra_Ali_Aghazadeh_Boyd_Anderson_Kayla_Sear\\Assignment9\\src\\test\\java\\problem1\\test.csv";
    csvFile = "C:\\Users\\Kayla\\Desktop\\Align\\cs5004\\Group_Yalda_Zahra_Ali_Aghazadeh_Boyd_Anderson_Kayla_Sear\\Assignment9\\src\\test\\java\\problem1\\test.csv";
    dataRows = new ArrayList<>();

    //List<String> row1 = Arrays.asList("id","text","completed","due","priority","category");
    List<String> row2 = Arrays.asList("1","Finish HW9","false","3/22/2020","1","school");
    List<String> row3 = Arrays.asList("2","Mail passport","true","2/28/2020","null","null");

    dataRows.add(row2);
    dataRows.add(row3);

    test = new FileProcessor(csvFile);
  }

  @Test
  public void getCsvFile() {
    assertEquals(csvFile, test.getCsvFile());
  }


  @Test
  public void getDataRows() {
    test.readFile();
    assertEquals(2, test.getDataRows().size());
  }

  @Test
  public void readFile() {
    test.readFile();
    assertEquals(2, test.getDataRows().size());
  }


  @Test (expected = java.lang.IndexOutOfBoundsException.class)
  public void readFileFail() throws Exception {
    String wrongFile = "random.csv";
    test = new FileProcessor(wrongFile);
    test.readFile();
  }



  @Test
  public void testEquals() throws IllegalArgumentsException {
    FileProcessor tester2 = test;
    FileProcessor tester3 = test;
    //Testing equals reflexivity and null
    TestCase.assertTrue(test.equals(test));
    TestCase.assertNotNull(test);
    assertFalse(test.equals(1));

    //Testing symmetry
    TestCase.assertTrue(test.equals(tester2) == tester2.equals(test));

    //Testing transitivity
    test = tester2 = tester3;
    TestCase.assertTrue(test.equals(tester2) && tester2.equals(tester3) && test.equals(tester3));

    //Testing consistency
    TestCase.assertTrue(test.equals(tester2));
    TestCase.assertTrue(test.equals(tester2));

    //Testing for values
    String wrongFile = "random.csv";
    tester2 = new FileProcessor(wrongFile);
    assertFalse(test.equals(tester2));




  }


  @Test
  public void testHashCode() {
    assertFalse(0 == test.hashCode());
  }

  @Test
  // testing consistency
  public void testHashCodeConsistency() {
    int testHashCode = test.hashCode();
    assertEquals(testHashCode, test.hashCode());
  }

  @Test
  // Testing consistency with equals
  public void testHashCodeConsistency2() {
    FileProcessor tester2 = test;
    assertTrue(test.equals(tester2) == (test.hashCode() == tester2.hashCode()));
  }

 @Test
 public void testToString() {
   String expected = "FileProcessor{csvFile='C:\\Users\\Kayla\\Desktop\\Align\\cs5004\\Group_Yalda_Zahra_Ali_Aghazadeh_Boyd_Anderson_Kayla_Sear\\Assignment9\\src\\test\\java\\problem1\\test.csv'"
       + ", dataRows=[]}";
    assertEquals(expected, test.toString());
  }
}