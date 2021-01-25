import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Storage {
    String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void retrieveTasks(TaskList lst) throws IOException {
        File data = new File(filePath);
        if (!data.exists()) {
            data.createNewFile();
        } else {
            Scanner s = new Scanner(data);
            while (s.hasNextLine()) {
                String txt = s.nextLine();
                lst.readTask(txt);
            }
        }
    }

    public void storeTasks(TaskList lst) throws IOException {
        File data = new File(filePath);
        // store Tasks in a file
        FileWriter fw = new FileWriter(data, false);
        List<Task> taskList = lst.fetchTasks();

        for (Task item : taskList) {
            fw.write(item.getDescription() + "\n");
        }
        fw.close();
    }

}
