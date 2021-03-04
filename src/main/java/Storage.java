import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;


public class Storage {
    private File file;

    /**
     * Constructor method
     * @param filePath of the saved file
     */
    public Storage(String filePath) throws IOException {
        this.file = new File(filePath);
        Path path = Paths.get(filePath);
        try {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    // @@wei-yutong reused with modifications
    public void saveTasks(TaskList list) throws IOException {
        FileWriter fw = new FileWriter(this.file);
        ArrayList<Task> copied = new ArrayList<>();
        for (int i = 0; i < list.numOfTasks(); i++) {
            Task added = list.getTask(i);
            copied.add(added);
        }
        for (Task temp : copied) {
            if (temp instanceof Deadline) {
                fw.write("D|" + temp.getStatusIcon() + "|" + temp.getDescription().trim() + "|"
                        + ((Deadline) temp).getDateString() + "\n");
            } else if (temp instanceof Event) {
                fw.write("E|" + temp.getStatusIcon() + "|" + temp.getDescription() + "|"
                        + ((Event) temp).getDatetime() + "\n");
            } else {
                fw.write("T|" + temp.getStatusIcon() + "|" + temp.getDescription() + "\n");
            }
        }
        fw.close();
    }

    // @@wei-yutong reused with modifications
    public ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(this.file);
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            s = s.trim();

            String[] strArray = s.split("\\|", 4);

            if (strArray[0].trim().equals("D")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(strArray[3], formatter);
                Deadline deadline = new Deadline(strArray[2].trim(), date);
                if (strArray[1].trim().equals("[Done]")) {
                    deadline.isDone = true;
                } else if (strArray[1].trim().equals("[Incompleted]")) {
                    deadline.isDone = false;
                }
                tasks.add(deadline);
            } else if (strArray[0].trim().equals("E")) {
                Event event = new Event(strArray[2].trim(), strArray[3].trim());
                if (strArray[1].trim().equals("[Done]")) {
                    event.isDone = true;
                } else if (strArray[1].trim().equals("[Incompleted]")) {
                    event.isDone = false;
                }
                tasks.add(event);
            } else {
                ToDo todo = new ToDo(strArray[2].trim(), "");
                if (strArray[1].trim().equals("[Done]")) {
                    todo.isDone = true;
                } else if (strArray[1].trim().equals("[Incompleted]")) {
                    todo.isDone = false;
                }
                tasks.add(todo);
            }
        }
        return tasks;
    }
}

