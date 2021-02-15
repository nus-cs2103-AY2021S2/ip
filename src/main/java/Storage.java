import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }


    /**
     * Stores the tasks in the TaskList object into a file.
     * Tasks will be stored in a specific string format,
     * each task occupying one line.
     *
     * @param list TaskList containing all the tasks of the user.
     * @throws IOException If a file cannot be created with the given path.
     */
    void store(TaskList list) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        ArrayList<Task> tasks = list.getList();
        for (Task task : tasks) {
            String s = "";
            if (task.type.equals("T")) {
                s = task.isDone
                        ? "T" + " , " + "1" + " , " + task.description
                        : "T" + " , " + "0" + " , " + task.description;
            } else if (task.type.equals("E")) {
                Event myTask = (Event) task;
                s = task.isDone
                        ? "E" + " , " + "1" + " , " + task.description + " , " + myTask.date + " , " + myTask.duration
                        : "E" + " , " + "0" + " , " + task.description + " , " + myTask.date + " , " + myTask.duration;

            } else if (task.type.equals("D")) {
                Deadline myTask = (Deadline) task;
                s = task.isDone
                        ? "D" + " , " + "1" + " , " + task.description + " , " + myTask.by + " , " + myTask.time
                        : "D" + " , " + "0" + " , " + task.description + " , " + myTask.by + " , " + myTask.time;

            }
            if (task.priority != null) {
                s = s + " , " + task.priority;
            }
            fw.write(s + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Loads the tasks stored in the file into a ArrayList of Tasks.
     * If file is empty, empty list is returned.
     *
     * @return ArrayList of Tasks given by the user previously.
     * @throws IOException If the file is misread or file cannot be found.
     */
    ArrayList<Task> load() throws IOException {
        ArrayList<Task> list = new ArrayList<>();
        File file = new File(filePath);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String task = br.readLine();
        while (task != null) {
            Task taskObject = null;
            String[] taskInfo = task.split(" , ");
            int taskInfoLength = taskInfo.length;
            if (taskInfo[0].equals("T")) {
                taskObject = new ToDo(taskInfo[2], "T");
            } else if (taskInfo[0].equals("E")) {
                Date date = new Date(taskInfo[3]);
                taskObject = new Event(taskInfo[2], date, taskInfo[4], "E");
            } else if (taskInfo[0].equals("D") && taskInfo[4].equals("null")) {
                Date date = new Date(taskInfo[3]);
                taskObject = new Deadline(taskInfo[2], date, "D");
            } else if (taskInfo[0].equals("D") && !taskInfo[4].equals("null")) {
                Date date = new Date(taskInfo[3]);
                taskObject = new Deadline(taskInfo[2], date, taskInfo[4], "D");
            }
            if (taskInfo[1].equals("1")) {
                taskObject.completed();
            }
            String lastInfo = taskInfo[taskInfoLength - 1];
            if (lastInfo.equals(Priority.HIGH.toString())) {
                taskObject.setPriority(Priority.HIGH);
            } else if (lastInfo.equals(Priority.MEDIUM.toString())) {
                taskObject.setPriority(Priority.MEDIUM);
            } else if (lastInfo.equals(Priority.LOW.toString())) {
                taskObject.setPriority(Priority.LOW);
            }
            assert taskObject != null : "task cannot be null";
            list.add(taskObject);
            task = br.readLine();
        }
        return list;
    }
}
