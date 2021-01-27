import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskArrayList;

    public TaskList() {
        this.taskArrayList = new ArrayList<>();
    }

    public void add(Task t) {
        this.taskArrayList.add(t);
    }

    public int size() {
        return this.taskArrayList.size();
    }

    public Task get(int i) {
        return this.taskArrayList.get(i);
    }

    public Task remove(int i) {
        return this.taskArrayList.remove(i);
    }

    public boolean isEmpty() {
        return this.taskArrayList.isEmpty();
    }


    // setup at default location
    public static TaskList setupTaskList() throws IOException {
        if (Storage.doesTaskFileExist()) {
            TaskList t = new TaskList();
            Storage.loadFromHardDisk(t);
            return t;
        } else {
            return new TaskList();
        }
    }

    // should abstract away saving location
    public void saveTasksList() throws IOException {
        File f = new File(Storage.taskListFilePath.toString());
            // doesn't actually create a new file i think, converts an existing file

        FileWriter fw = new FileWriter(f);
        BufferedWriter bw = new BufferedWriter(fw);

        for (Task task : this.taskArrayList) {
            bw.write(task.unparse());
        }

        bw.flush();
        bw.close();
    }
}
