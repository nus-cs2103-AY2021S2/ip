import Tasks.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Loader {
    private final String path = "data/duke.txt";

    public TaskCollection loadTasks() {
        TaskCollection tasks = new TaskCollection();
        File file = new File(this.path);

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String taskDetails = sc.nextLine();
                Task newTask = createTask(taskDetails);
                tasks.addTask(newTask);
            }
            return tasks;
        } catch (FileNotFoundException e) {
            return tasks;
        }
    }

    public void saveTasks(TaskCollection tasks) {
        try {
            FileWriter writer = new FileWriter(this.path);

            for (Task task : tasks.getListOfTasks()) {
                writer.write(convertToSavableString(task));
            }

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Task createTask(String taskDetails) {
        String[] taskDetailsArray = taskDetails.split("\\|", 4);
        String taskType = taskDetailsArray[0].trim();
        String done = taskDetailsArray[1].trim();
        String description = taskDetailsArray[2].trim();
        String time = taskDetailsArray[3].trim();

        Task newTask;
        if (taskType.equals("T")) {
            newTask = new ToDo(description);
        } else if (taskType.equals("D")) {
            newTask = new Deadline(description, time);
        } else {
            newTask = new Event(description, time);
        }

        if (done.equals("1")) {
            newTask.markAsDone();
        }

        return newTask;
    }

    private String convertToSavableString(Task task) {
        String time = "";
        String taskType;

        if (task instanceof Deadline) {
            taskType = "D";
            time = ((Deadline) task).getBy();
        } else if (task instanceof Event) {
            taskType = "E";
            time = ((Event) task).getAt();
        } else {
            taskType = "T";
        }

        String done = task.isDone() ? "1" : "0";
        String description = task.getDescription();

        return taskType + " | " + done + " | " + description + " | " + time + "\n";
    }
}
