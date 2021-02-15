import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage(String filePath){
        this.filePath = filePath;
    }

    /**
     * Return list of existing tasks stored in local text file.
     *
     * @return List of task details (type, status, description and time).
     * @throws DukeException If local file is not found.
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            File f = new File(this.filePath);
            Scanner scanner = new Scanner(f);

            while (scanner.hasNext()) {
                String[] taskDetails = scanner.nextLine().split(" \\| ", 4);
                Task newTask = loadTask(taskDetails);
                tasks.add(newTask);
            }
            return tasks;
        } catch (FileNotFoundException e){
            throw new DukeException("OOPS!! File is not found.");
        }
    }

    /**
     * Store the TaskList in local text file.
     *
     * @param taskList TaskList containing Task objects.
     * @throws DukeException If fail to write the file.
     */
    public void updateTaskList(TaskList taskList) throws DukeException {
        try {
            ArrayList<Task> tasks = taskList.getTaskList();
            FileWriter writer = new FileWriter(filePath);
            assert writer != null;
            // write tasks into file by line
            for (Task t : tasks) {
                writer.write(t.toString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e){
            throw new DukeException("OOPS!! Task list failed to update.");
        }
    }

    private Task loadTask(String[] taskDetails) {
        String taskType = taskDetails[0];
        boolean isCompleted = isDone(taskDetails[1]);
        String description = taskDetails[2];
        String time = "";
        Task newTask = new Task("");

        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a", Locale.ENGLISH);
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy-M-d H:mm");
        LocalDateTime dateTime = null;

        switch (taskType) {
        case "T":
            newTask = new ToDo(description, isCompleted);
            break;
        case "D":
            time = taskDetails[3].replace("am", "AM").replace("pm","PM");
            // re-format time from "MMM d yyyy hh:mm a" to "yyyy-m-d hh:mm"
            dateTime = LocalDateTime.parse(time, inputFormat);
            time = dateTime.format(outputFormat).replace("T", " ");
            newTask = new Deadline(description, time, isCompleted);
            break;
        case "E":
            time = taskDetails[3].replace("am", "AM").replace("pm","PM");
            // re-format time from "MMM d yyyy hh:mm a" to "yyyy-m-d hh:mm"
            dateTime = LocalDateTime.parse(time, inputFormat);
            time = dateTime.format(outputFormat).replace("T", " ");
            newTask = new Event(description, time, isCompleted);
            break;
        }
        return newTask;
    }

    private boolean isDone(String icon) {
        if (icon.equals("\u2713")){
            return true;
        }
        return false;
    }
}
