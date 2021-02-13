package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskType;
import duke.task.ToDo;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs the storage.
     *
     * @param filePath the filepath of the storage file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads the data of tasks from previous session's file as TaskList.
     * If there's no file yet, create an empty file, and return empty list.
     *
     * @return the list of tasks from previous session
     */
    public TaskList readFile() {
        List<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (file.createNewFile()) {
                return new TaskList();
            }

            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String[] taskString = myReader.nextLine().split(" \\| ");
                addTask(taskString, tasks);
            }
            myReader.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println("(Your tasks from previous session has been successfully loaded.)");
        return new TaskList(tasks);
    }

    /**
     * Formats the task String and adds it intro the task list.
     *
     * @param taskString the task string
     * @param tasks the task list
     */
    public void addTask(String[] taskString, List<Task> tasks) {
        if (taskString.length == 0) {
            return;
        }
        switch (taskString[0]) {
        case "TODO":
            tasks.add(new ToDo(taskString[2], taskString[1].equals("1")));
            break;
        case "DEADLINE":
            tasks.add(new Deadline(taskString[2], LocalDate.parse(taskString[3]), taskString[1].equals("1")));
            break;
        case "EVENT":
            tasks.add(new Event(taskString[2], LocalDate.parse(taskString[3]), taskString[1].equals("1")));
            break;
        default:
        }
    }

    /**
     * Updates the file with newest task String, write the updated tasks into the file.
     *
     * @param tasks the updated task list
     */
    public void updateFile(TaskList tasks) {
        String updatedString = "";
        for (Task task : tasks.getTaskList()) {
            updatedString += String.format("%s | %s | %s", task.getType(), (task.isDone() ? 1 : 0), task.getName());
            if (task.getType().equals(TaskType.DEADLINE)) {
                updatedString += " | " + ((Deadline) task).getTime().toString();
            } else if (task.getType().equals(TaskType.EVENT)) {
                updatedString += " | " + ((Event) task).getTime().toString();
            }
            updatedString += "\n";
        }
        try {
            FileWriter file = new FileWriter(filePath);
            file.write(updatedString);
            file.flush();
            file.close();
            System.out.println("(Your tasks are successfully saved to the file.)");
        } catch (IOException e) {
            System.out.println("(Your tasks fail to saved to the file.)");
        }

    }
}
