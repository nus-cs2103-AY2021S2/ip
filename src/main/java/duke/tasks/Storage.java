package duke.tasks;

import duke.ui.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Storage {
    private final String path;

    public Storage(String filePath) {
        this.path = filePath;
    }

    public TaskList loadTasks() {
        TaskList tasks = new TaskList();
        File file = new File(this.path);

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String taskDetails = sc.nextLine();
                Task newTask = createTaskFromSavedString(taskDetails);
                tasks.addTask(newTask);
            }
            return tasks;
        } catch (FileNotFoundException e) {
            return tasks;
        }
    }

    public void saveTasks(TaskList tasks) {
        try {
            FileWriter writer = new FileWriter(this.path);

            for (Task task : tasks.getListOfTasks()) {
                writer.write(convertTaskToSavableString(task));
            }

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Task createTaskFromSavedString(String taskDetails) {
        String[] taskDetailsArray = taskDetails.split("\\|", 4);
        String taskType = taskDetailsArray[0].trim();
        String done = taskDetailsArray[1].trim();
        String description = taskDetailsArray[2].trim();
        String dateTimeString = taskDetailsArray[3].trim();
        LocalDateTime dateTime = Parser.convertToDateTime(dateTimeString);

        Task newTask;
        if (taskType.equals("T")) {
            newTask = new ToDo(description);
        } else if (taskType.equals("D")) {
            newTask = new Deadline(description, dateTime);
        } else {
            newTask = new Event(description, dateTime);
        }

        if (done.equals("1")) {
            newTask.markAsDone();
        }

        return newTask;
    }

    private String convertTaskToSavableString(Task task) {
        String dateTimeString = "";
        String taskType;

        if (task instanceof Deadline) {
            taskType = "D";
            dateTimeString = ((Deadline) task).getByDateTimeString();
        } else if (task instanceof Event) {
            taskType = "E";
            dateTimeString = ((Event) task).getAtDateTimeString();
        } else {
            taskType = "T";
        }

        String done = task.isDone() ? "1" : "0";
        String description = task.getDescription();

        return taskType + " | " + done + " | " + description + " | " + dateTimeString + "\n";
    }
}