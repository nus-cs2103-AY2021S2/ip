import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskStorage {
    File file;
    FileWriter writer;
    public TaskStorage() {
        try {
            file = new File("data/tasks.txt");
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void storeData(List<Task> tasks) {
        try {
            writer = new FileWriter(file);
            for (Task task : tasks) {
                char taskType = task.toString().charAt(1);
                int done = task.isDone() ? 1 : 0;
                String description = task.getDescription();
                String details = "";
                switch (taskType) {
                    case 'D':
                        Deadline deadline = (Deadline) task;
                        details = deadline.getDetails();
                        break;
                    case 'E':
                        Event event = (Event) task;
                        details = event.getDetails();
                        break;
                    default:
                }
                writer.write(taskType + " | " + done + " | " +  description +
                        (details.isBlank() ? "" : " | " + details));
                writer.write(System.lineSeparator());
            }
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Task> retrieveData() {
        List<Task> retrievedTasks = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] taskInfo = line.split(" \\| ");
                String taskType = taskInfo[0];
                String isDone = taskInfo[1];
                String description = taskInfo[2];
                switch (taskType) {
                    case "T":
                        retrievedTasks.add(new ToDo(description, isDone));
                        break;
                    case "D":
                        String deadlineDetails = taskInfo[3];
                        retrievedTasks.add(new Deadline(description, isDone, deadlineDetails));
                        break;
                    case "E":
                        String eventDetails = taskInfo[3];
                        retrievedTasks.add(new Event(description, isDone, eventDetails));
                        break;
                    default:
                }
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return retrievedTasks;
    }
}
