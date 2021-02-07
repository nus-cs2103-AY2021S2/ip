package justin;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class performs JUnit test on Storage.java
 * to ensure that input and output are of
 * expected values
 *
 * @author Goh Wei Kiat aka github : mrweikiat
 * @version CS2103T AY20/21 Semester 2, Individual Project 'IP'
 */

public class StorageTest {

    String testPath = "./testdata/";
    String testFileName = "data.txt";
    String saveData = "savedata.txt";

    String filePathLoad = testPath + testFileName;
    String filePathSave = testPath + saveData;

    @Test
    public void testLoadFile() throws IOException {

        Storage storage = new Storage(filePathLoad);

        String testerLoadFile = "[T][✓] storage test";
        TaskList taskList = storage.loadFile(filePathLoad);

        String testDataValue = taskList.getList().get(0).toString();

        assertEquals(testerLoadFile, testDataValue );

    }

    @Test
    public void testSaveFile() throws IOException {

        Storage storageSave = new Storage(filePathSave);

        TaskList taskList = new TaskList();
        taskList.addToDo("storage test");
        taskList.getList().get(0).markAsDone();

        storageSave.saveFile(taskList, filePathSave);

        BufferedReader br = new BufferedReader(new FileReader(filePathSave));

        String results = "" ;
        String line;

        while ((line = br.readLine()) != null) {
            results += line;

        }

        assertEquals("T|1|storage test", results);






    }


}