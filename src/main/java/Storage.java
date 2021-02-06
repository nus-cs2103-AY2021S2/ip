import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates a new file to store tasks is the file does not exit yet.
     */
    public void taskRecorder() {
        File taskHistory = new File(filePath);
        if (!taskHistory.exists()) {
            try {
                taskHistory.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Prints the tasks stored from last usage of Duke.
     *
     * @param path of the file to store user history.
     */
    public void taskHistory(String path) {
        try {
            File history = new File(path);
            Scanner s = new Scanner(history);
            while (s.hasNext()) {
                String str = s.nextLine();
                System.out.println(str);
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("user history is empty");
        }

    }

    /**
     * Stores data of tasks as user history.
     *
     * @param taskList that stores tasks.
     */
    public void record(ArrayList<Task> taskList) {
        try {
            new FileWriter(filePath, false).close();
            int i = 1;
            FileWriter fw = new FileWriter(filePath);
            for (Task t : taskList) {
                fw.write(i + ". "
                        + t.toString() + System.lineSeparator());
                i++;
            }
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parses user history duke.txt and loads taskList when Duke starts up.
     *
     * @param taskList that stores tasks.
     */
    public void load(ArrayList<Task> taskList)  {
        try {
            File history = new File(filePath);
            Scanner s = new Scanner(history);
            while(s.hasNext()) {
                String task = s.nextLine();
                if (task.contains("[T]")) {
                    Todo myTask = Todo.parseTodo(task);
                    taskList.add(myTask);
                } else if (task.contains("[D]")) {
                    Deadline myTask = Deadline.parseDeadline(task);
                    taskList.add(myTask);
                } else if (task.contains("[E]")) {
                    Event myTask = Event.parseEvent(task);
                    taskList.add(myTask);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
