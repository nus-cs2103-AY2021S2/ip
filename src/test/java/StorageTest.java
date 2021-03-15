import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageTest {


    @Test
    public void saveSessionAbleToLoad() throws IOException {
        File testFile = new File(Storage.sessionFile);
        if (testFile.exists())
            testFile.delete();
        testFile.createNewFile();

        Duke.storage.tasks = new ArrayList<>(100);
        Duke.storage.inputs = new ArrayList<>(100);

        PrintWriter out = new PrintWriter(testFile);
        out.println("todo test history 1");
        out.println("todo test history 2");
        out.println("list");
        out.println("todo test history 3");
        out.println("todo test history 4");
        out.println("done 2");
        out.println("delete 3");
        out.close();

        Duke.storage.loadHistory();

        assertEquals(3, Duke.storage.tasks.size());
        assertTrue(Duke.storage.tasks.get(1).getCompletionState());
        assertEquals("test history 4", Duke.storage.tasks.get(2).getTaskInfo());
        assertEquals(7, Duke.storage.inputs.size());
    }

    @Test
    public void saveSessionAbleToSave() throws IOException, FileNotFoundException {
        File testFile = new File(Storage.sessionFile);
        if (testFile.exists())
            testFile.delete();
        testFile.createNewFile();

        Duke.storage.tasks = new ArrayList<>(100);
        Duke.storage.inputs = new ArrayList<>(100);

        Duke.storage.inputs.add("todo test history 1");
        Duke.storage.inputs.add("todo test history 2");
        Duke.storage.inputs.add("list");
        Duke.storage.inputs.add("todo test history 3");
        Duke.storage.inputs.add("todo test history 4");
        Duke.storage.inputs.add("done 2");
        Duke.storage.inputs.add("delete 3");

        Duke.storage.saveHistory();

        BufferedReader in = new BufferedReader(new FileReader(Storage.sessionFile));

        assertEquals("todo test history 1", in.readLine());
        assertEquals("todo test history 2", in.readLine());
        assertEquals("list", in.readLine());
        assertEquals("todo test history 3", in.readLine());
        assertEquals("todo test history 4", in.readLine());
        assertEquals("done 2", in.readLine());
        assertEquals("delete 3", in.readLine());

        in.close();
    }

    @Test
    public void archiveTaskWritesToArchiveFile() throws IOException {
        // Setup
        File actual = new File(Storage.archiveFile);
        File temp = new File(Storage.archiveFile+"_temp");
        actual.renameTo(temp);
        actual.createNewFile();

        //Test
        TodoTask t = new TodoTask("storage Test");
        Duke.storage.archiveTask(t);

        BufferedReader in = new BufferedReader(new FileReader(Storage.archiveFile));

        assertEquals(t.toString(), in.readLine());

        actual.delete();
        temp.renameTo(actual);
    }
}
