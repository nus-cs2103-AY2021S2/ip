package duke;

import java.io.IOException;

public class StorageStub extends Storage {
    public StorageStub() throws IOException {
        super("data/test.txt");
    }

    @Override
    public void write(String text) {}
}
