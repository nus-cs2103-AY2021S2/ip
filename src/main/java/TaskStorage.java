import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskStorage {
    private File file;
    private FileWriter writer;
    private Ui ui;
    public TaskStorage() {
        try {
            Ui ui = new Ui();
            file = new File("data/tasks.txt");
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (Exception e) {
            ui.print(e.getMessage());
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
            ui.print(e.getMessage());
        }
    }

    public List<Task> retrieveData() {
        List<Task> retrievedTasks = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
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
                        LocalDateTime deadline = LocalDateTime.parse(deadlineDetails, formatter);
                        retrievedTasks.add(new Deadline(description, isDone, deadline));
                        break;
                    case "E":
                        String eventDetails = taskInfo[3];
                        LocalDateTime eventTime = LocalDateTime.parse(eventDetails, formatter);
                        retrievedTasks.add(new Event(description, isDone, eventTime));
                        break;
                    default:
                }
            }
        } catch (Exception e){
            ui.print(e.getMessage());
        }
        return retrievedTasks;
    }
}
