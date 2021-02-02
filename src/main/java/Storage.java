import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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
