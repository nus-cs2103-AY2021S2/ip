import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Storage {
    private File file;

    Storage() {
        this.file = new File("data", "duke.txt");
    }

    /**
     * Creates a file if the given file is not available
     */
    public void createFile() {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
            System.out.println("Directory created");
        }

        if (!file.exists()) {
            System.out.println("New file created");
        }

    }

    /**
     * Return file
     */

    public File getFile() throws IOException {
        createFile();
        return this.file;
    }

    /**
     * Update all the task in taskList to file before exiting
     * @param taskList
     */

    public void update(TaskList taskList) throws IOException {
        assert taskList != null : "the taskList should not be null";
        FileWriter fw = new FileWriter(this.file);
        for (Task task : taskList.getList()) {
            fw.write(task.toSaveFormat() + "\n");

        }
        fw.close();

    }

}
