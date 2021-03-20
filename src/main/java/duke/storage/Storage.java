package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class Storage {

    /** The path of the file storing all tasks */
    private String filePath;

    /**
     * Class constructor.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }


    /**
     * Reads data from a given file if it exists, otherwise, creates a new file.
     * Transforms data to tasks.
     *
     * @return an arraylist of tasks.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File myObj = new File(filePath);
        if (Files.notExists(Path.of("data/"))) {
            Files.createDirectories(Paths.get("data/"));
        }
        if (!myObj.exists()) {
            myObj.createNewFile();
        }
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            tasks = addTaskFromFile(tasks, data);
        }
        myReader.close();
        return tasks;
    }

    /**
     * Adds tasks into the task list.
     *
     * @param tasks a list of tasks.
     * @param taskToAdd the list to be added into the task list.
     *
     * @return an arraylist of tasks.
     */
    public ArrayList<Task> addTaskFromFile(ArrayList<Task> tasks, String taskToAdd) {
        Character type = taskToAdd.charAt(0);
        boolean isDone = false;
        if (taskToAdd.charAt(4) == '1') {
            isDone = true;
        }
        String taskWithoutTypeAndStatus = taskToAdd.substring(8);
        if (type == 'T') {
            tasks.add(new ToDo(taskWithoutTypeAndStatus, isDone));
        } else {
            int indexOfDivider = taskWithoutTypeAndStatus.indexOf("|");
            String name = taskWithoutTypeAndStatus.substring(0, indexOfDivider - 1);
            String dateString = taskWithoutTypeAndStatus.substring(indexOfDivider + 2);
            int year = Integer.valueOf(dateString.substring(0, 4));
            int mon = Integer.valueOf(dateString.substring(5, 7));
            int day = Integer.valueOf(dateString.substring(8));
            LocalDate date = LocalDate.of(year, mon, day);
            if (type == 'D') {
                tasks.add(new Deadline(name, date, isDone));
            } else {
                assert type == 'E';
                tasks.add(new Event(name, date, isDone));
            }
        }
        return tasks;
    }

    /**
     * Adds tasks in taskList into the datafile.
     *
     * @param tasks a list of tasks.
     *
     * @throws IOException If an input or output exception occurred
     */
    public void saveTasksToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        String tasksToPutInFile = "";
        for (Task task : tasks) {
            Character taskType = task.getType();
            Boolean taskStatus = task.getStatus();
            String taskName = task.getName();
            LocalDate date = task.getDate();
            if (taskStatus) {
                tasksToPutInFile += taskType + " | 1 | " + taskName;
            } else {
                tasksToPutInFile += taskType + " | 0 | " + taskName;
            }
            if (taskType != 'T') {
                tasksToPutInFile += " | " + date + "\n";
            } else {
                tasksToPutInFile += "\n";
            }
        }
        writer.write(tasksToPutInFile);
        writer.close();
    }
}
