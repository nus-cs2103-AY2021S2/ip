import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Provides the methods to create a storage object and relevant methods to interact with storage.
 */
public class Storage {
    private final String filePath;

    /**
     * Initialises Storage object.
     *
     * @param filePath the file path that specifies location in hard disk for storage.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the data in the hard disk location specified by file path.
     *
     * @return an array list of the tasks saved in the hard disk.
     */
    public ArrayList<Task> loadData() {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            File file = new File(filePath);

            if (!file.exists()) {
                file.createNewFile();
            }

            Scanner scan = new Scanner(file);
            while (scan.hasNext()) {
                String input = scan.nextLine();
                taskList.add(stringToTask(input));
            }
        } catch (IOException e) {
            System.out.println("Oops sorry, unable to create a new file.");
        }
        return taskList;
    }

    /**
     * Converts the data being stored as strings in the hard disk into Task objects.
     *
     * @param input each individual string task data from the file in the hard disk.
     * @return the Task object of the String description.
     */
    public Task stringToTask(String input) {
        String[] splitTask = input.split(" \\| ");
        String taskType = splitTask[0];
        String isDone = splitTask[1];
        String description = splitTask[2];
        Task task = new Task(" ", " ");

        switch(taskType) {
        case "D":
            String by = splitTask[3];
            task = new Deadline(description, by);
            break;
        case "E":
            String at = splitTask[3];
            task = new Event(description, at);
            break;
        case "T":
            task = new Todo(description);
            break;
        default:
            System.out.println("I don't recognise this task type!");
        }

        if (isDone.equals("1")) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Writes the specified taskList into the specified file in the hard disk.
     *
     * @param taskList the list of tasks to be written into file.
     */
    public void writeToFile(ArrayList<Task> taskList) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                fw.write(taskToString(task) + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Oops, unable to write to file!");
        }
    }

    /**
     * Converts the Task objects into formatted String objects.
     *
     * @param task the individual tasks from the taskList to be converted.
     * @return a String object of the task.
     */
    public String taskToString(Task task) {
        int isDone;
        String converted = " ";
        String taskType = task.getTaskType();
        String description = task.getDescription();

        if (task.isDone()) {
            isDone = 1;
        } else {
            isDone = 0;
        }

        switch (taskType) {
        case "Deadline":
            String by = ((Deadline) task).getBy();
            converted = String.format("D | %d | %s | %s", isDone, description, by);
            break;
        case "Event":
            String at = ((Event) task).getAt();
            converted = String.format("E | %d | %s | %s", isDone, description, at);
            break;
        case "Todo":
            converted = String.format("T | %d | %s", isDone, description);
            break;
        default:
            System.out.println("I don't recognise this task type!");
        }
        return converted;
    }
}
