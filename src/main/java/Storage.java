import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Storage {
    private String path;

    Storage(String path) {
        this.path = path;
    }

    public String getFilePath() {
        return this.path;
    }


    /**
     * Update all the task in taskList to file before exiting
     * @param taskList
     */

    public void update(TaskList taskList) throws IOException {
        assert taskList != null : "the taskList should not be null";
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileWriter fw = new FileWriter(this.path);
        for (Task task : taskList.getList()) {
            fw.write(task.toSaveFormat() + "\n");

        }
        fw.close();

    }

}
