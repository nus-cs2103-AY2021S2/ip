import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskList {
    private static List<Task> taskList;
    public TaskList() {
        taskList = new ArrayList<>();
        initializeTasks();
    }

    public void addTask(Task task) {
        taskList.add(task);
        Ui.printMessage("Wakarimashita! Task added to list:");
        Ui.printMessage(task.toString());
        Ui.printMessage("The size of your task list is now: " + this.getSize());
    }

    /**
     * Mark the task passed to the method as complete.
     * @param inputString User input string.
     */
    public void completeTask(String inputString) {
        try {
            int taskId = Integer.parseInt(String.valueOf(inputString.split(" ")[1])) - 1;
            Task doneTask = taskList.get(taskId).setDone();
            String typeOfTask = doneTask.getType();
            String completionOfTask = (doneTask.getDone() ? "1" : "0");
            String descriptionOfTask = doneTask.getDescription().strip();
            LocalDate date = LocalDate.now();
            if (doneTask instanceof Event) {
                date = ((Event) doneTask).getDate();
            } else if (doneTask instanceof Deadline) {
                date = ((Deadline) doneTask).getDate();
            }
            String oldString = typeOfTask + " | " + "0" + " | " + descriptionOfTask;
            String newString = typeOfTask + " | " + completionOfTask + " | " + descriptionOfTask;
            if (doneTask instanceof Event || doneTask instanceof Deadline) {
                oldString += " | " + date.toString();
                newString += " | " + date.toString();
            }
            Duke.deleteReplaceTaskFromDisk(oldString, newString);
            Ui.printMessage("Great~! Task completed:");
            Ui.printMessage(doneTask.toString());
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskException();
        }
    }

    /**
     * Delete the specified task from the list.
     * @param inputString User input string.
     */
    public void deleteTask(String inputString) {
        try {
            int taskId = Integer.parseInt(String.valueOf(inputString.split(" ")[1])) - 1;
            Task deletedTask = taskList.remove(taskId);
            String typeOfTask = deletedTask.getType();
            String completionOfTask = (deletedTask.getDone() ? "1" : "0");
            String descriptionOfTask = deletedTask.getDescription().strip();
            String oldString = typeOfTask + " | " + completionOfTask + " | " + descriptionOfTask;
            Duke.deleteReplaceTaskFromDisk(oldString, "");
            Ui.printMessage("Okie! I've deleted the task from your list:");
            Ui.printMessage(deletedTask.toString());
            Ui.printMessage("The size of your task list is now: " + taskList.size());
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskException();
        }
    }

    /**
     * Prints the list of tasks in the list, including the status.
     */
    public void print() {
        if (taskList.size() < 1) {
            Ui.printMessage("There are no tasks in your list! :c");
            return;
        }
        Ui.printMessage("Tasks in your list are~: ");
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            Ui.printMessage((i + 1) + "." + task);
        }
    }

    public int getSize() {
        return taskList.size();
    }

    /**
     * Initializes the task list from the data file.
     */
    public void initializeTasks() {
        List<String> fileContents;
        File dataDirectory = new File("data");
        if (!dataDirectory.exists()) {
            // mkdirs does not throw any exception even when failing
            dataDirectory.mkdirs();
        }
        File dataFile = new File(dataDirectory.getPath() + File.separator + "data.txt");
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                throw new DukeException();
            }
        }
        try {
            fileContents = Files.readAllLines(dataFile.toPath());
            for (int i = 0; i < fileContents.size(); i++) {
                String line = fileContents.get(i);
                if (line.length() < 1) {
                    continue;
                }
                String[] lineArray = line.split("\\|");
                boolean isDone = false;
                if (lineArray[1].strip().charAt(0) == '1') {
                    isDone = true;
                }
                if (lineArray[0].strip().charAt(0) == 'T') {
                    // todo
                    Todo newTodo = new Todo(lineArray[2].strip());
                    if (isDone) {
                        newTodo.setDone();
                    }
                    taskList.add(newTodo);
                } else if (lineArray[0].strip().charAt(0) == 'D') {
                    // deadline
                    Deadline newDeadline = new Deadline(lineArray[2].strip(), LocalDate.parse(lineArray[3].strip()));
                    if (isDone) {
                        newDeadline.setDone();
                    }
                    taskList.add(newDeadline);
                } else if (lineArray[0].strip().charAt(0) == 'E') {
                    // event
                    Event newEvent = new Event(lineArray[2].strip(), LocalDate.parse(lineArray[3].strip()));
                    if (isDone) {
                        newEvent.setDone();
                    }
                    taskList.add(newEvent);
                }
            }
        } catch (IOException e) {
            throw new DukeException();
        }
    }
}
