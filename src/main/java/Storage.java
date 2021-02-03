import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Storage {
    String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
//        fetchTasks()
        ArrayList<Task> result = new ArrayList<>();
        String currDir = System.getProperty("user.dir");
        String expectedDir = currDir + this.filePath;
//        Hardcoded to: "/data/modoc_tm.txt";

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(expectedDir));
            String lineRead = reader.readLine();
            while (lineRead != null) {
                char taskType = lineRead.charAt(0);
                String[] data = lineRead.split("/");
                Boolean isDone = (Integer.parseInt(data[1]) == 1);
                if (taskType == 'E') {
                    Event event = new Event(data[2], data[3], isDone);
                    result.add(event);
                } else if (taskType == 'D') {
                    String unparsedDate = data[3];
                    LocalDate date = LocalDate.parse(unparsedDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    Deadline deadline = new Deadline(data[2], date, isDone);
                    result.add(deadline);
                } else {
                    Todo todo = new Todo(data[2], isDone);
                    result.add(todo);
                }
                lineRead = reader.readLine();
            }
        } catch (IOException e) {
            throw new DukeException();
        }
        return result;
    }

//    Currently hard-coded into position
    public void save(TaskList tasks) {
        String currDir = System.getProperty("user.dir");
        String expectedDir = currDir + "/data";

        try {
//            Creates directory if doesn't exist
            Files.createDirectories(Paths.get(expectedDir));
//            Automatically creates file if it doesn't exist
            FileWriter writer = new FileWriter(expectedDir + "/modoc_tm.txt");

            ArrayList<Task> listTask = tasks.getTasks();

            for (Task task : listTask) {
                String result;

                Class taskType = task.getClass();
                boolean taskStatus = task.isDone;
                String description = task.name;

                if (taskType.equals(Event.class)) {
                    result = "E" + "/" +  (taskStatus ? "1" : "0") + "/" + description + "/" + ((Event) task).at;
                } else if (taskType.equals(Deadline.class)) {
                    result = "D" + "/" +  (taskStatus ? "1" : "0") + "/" + description + "/" + ((Deadline) task).by;
                } else {
                    result = "T" + "/" +  (taskStatus ? "1" : "0") + "/" + description;
                }
                writer.write(result + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("saveTask error");
            e.printStackTrace();
        }
    }
}
