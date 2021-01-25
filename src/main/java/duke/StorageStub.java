package duke;

import java.io.File;
import java.io.IOException;

public class StorageStub extends Storage{
    private static final String PATH_NAME = "./data/storage_test.txt";

    public StorageStub() throws IOException {

        super(PATH_NAME);
        initializeTestFile();

    }

    private void initializeTestFile() throws IOException {
        File savedTaskList = new File(PATH_NAME);
        savedTaskList.getParentFile().mkdirs();
        savedTaskList.createNewFile();
    }
}
