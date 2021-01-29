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
     * stores the tasks in the TaskList object into a file.
     * tasks will be stored in a specific string format,
     * each task occupying one line.
     *
     * @param list TaskList containing all the tasks of the user
     * @throws IOException if filepath is wrong
     */
    void store(TaskList list) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        ArrayList<Task> tasks = list.getList();
        for (Task task : tasks) {
            String s = "";
            if (task.type.equals("T")) {
                s = task.isDone ? "T" + " , " + "1" + " , " + task.description
                        : "T" + " , " + "0" + " , " + task.description;

            } else if (task.type.equals("E")) {
                Event myTask = (Event) task;
                s = task.isDone
                        ? "E" + " , " + "1" + " , " + task.description + " , " + myTask.date + " , " + myTask.duration
                        : "E" + " , " + "0" + " , " + task.description + " , " + myTask.date + " , " + myTask.duration;

            } else if (task.type.equals("D")) {
                Deadline myTask = (Deadline) task;
                s = task.isDone ? "D" + " , " + "1" + " , " + task.description + " , " + myTask.by
                        : "D" + " , " + "0" + " , " + task.description + " , " + myTask.by;

            }
            fw.write(s + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * loads the tasks stored in the file into a ArrayList of Tasks.
     * if file is empty, empty list is returned
     *
     * @return ArrayList of Tasks given by the user previously
     * @throws IOException if the file is misread or file cannot be found
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
            if (taskInfo[0].equals("T")) {
                taskObject = new ToDo(taskInfo[2], "T");
                taskObject.isDone = taskInfo[1].equals("1") ? true : false;
            } else if (taskInfo[0].equals("E")) {
                Date date = new Date(taskInfo[3]);
                taskObject = new Event(taskInfo[2], date, taskInfo[4], "E");
                taskObject.isDone = taskInfo[1].equals("1") ? true : false;
            } else if (taskInfo[0].equals("D")) {
                Date date = new Date(taskInfo[3]);
                taskObject = new Deadline(taskInfo[2], date, "D");
                taskObject.isDone = taskInfo[1].equals("1") ? true : false;
            }
            list.add(taskObject);
            task = br.readLine();
        }
        return list;
    }
}
