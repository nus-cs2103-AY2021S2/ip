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

    // todo rm
    public void add(Task t) {
        this.taskArrayList.add(t);
    }

    public int size() {
        return this.taskArrayList.size();
    }

    public Task get(int i) {
        return this.taskArrayList.get(i);
    }

    // todo rm
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


    public void addTask(Task t) {
        taskArrayList.add(t);

        String[] messages = {
                "Success. I've added this task:",
                Ui.taskIndent + t // standardize this indent,
        };

        Ui.print(messages);
    }

    // todo move to tasklist
    public void deleteTask(int i) throws InvalidArgumentException {
        if (i < 1 || i > taskArrayList.size()) {
            throw new InvalidArgumentException(invalidNumErrMsg(i, 1, taskArrayList.size()));
        }

        Ui.print(new String[]{"Got you. I've deleted this task:",
                Ui.taskIndent + taskArrayList.get(i - 1)});

        taskArrayList.remove(i - 1);
    }

    /**
     * Mark specified task done
     *
     * @param i off-by-one index of a task in array list
     */
    public void markDone(int i) throws InvalidArgumentException {
        if (i < 1 || i > taskArrayList.size()) {
            throw new InvalidArgumentException(invalidNumErrMsg(i, 1, taskArrayList.size()));
        }

        taskArrayList.get(i - 1).markAsDone();

        Ui.print(new String[]{"Good work! I've marked this task done:",
                Ui.taskIndent + taskArrayList.get(i - 1)});
    }




    // todo should all error handling related to arguments be done before tasklist?
    private static String invalidNumErrMsg(int i, int min, int max) {
        String errMsg = "Invalid list index given: " + i
                + ". Number needs to be between " + min + " and " + max + " (inclusive). ";
        return errMsg;
    }




}
