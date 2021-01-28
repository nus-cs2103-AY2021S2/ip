import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        String currDirectory = System.getProperty("user.dir");
        java.nio.file.Path path = java.nio.file.Paths.get(currDirectory, "data");

        ArrayList<Task> taskList = new ArrayList<>();
        File tasksFile = new File(filePath);
        try {
            if (!java.nio.file.Files.exists(path)) {
                java.nio.file.Files.createDirectory(path);
            }
            if (!tasksFile.exists()) {
                tasksFile.createNewFile();
            }
        } catch (IOException e) {
            throw new DukeException("Unable to create file.", e);
        }

        try {
            Scanner readFile = new Scanner(tasksFile);

            while (readFile.hasNextLine()) {
                String taskName;
                String nextTask = readFile.nextLine();
                boolean done = false;
                if (nextTask.charAt(4) == '1') {
                    done = true;
                }
                if (nextTask.startsWith("T")) {
                    taskName = nextTask.substring(8);
                    taskList.add(new Todo(taskName, done));
                } else {
                    int index = nextTask.lastIndexOf("|");
                    taskName = nextTask.substring(8, index - 1);
                    String dateTime = nextTask.substring(index + 2);
                    String dateString = dateTime.substring(0, 11);
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
                    LocalDate date = LocalDate.parse(dateString, dateFormatter);
                    String time = dateTime.substring(12);

                    if (nextTask.startsWith("E")) {
                        taskList.add(new Event(taskName, done, date, time));
                    } else if (nextTask.startsWith("D")) {
                        taskList.add(new Deadline(taskName, done, date, time));
                    }
                }

            }
        } catch (FileNotFoundException e) {
            throw new DukeException("No suitable file found.", e);
        }
        return taskList;
    }

    public void appendToDo(Todo task) throws DukeException {
        try {
            FileWriter fw = new FileWriter("duke.txt", true);
            fw.write("T | 0 | " + task.name + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("File cannot be found.", e);
        }
    }

    public void appendEvent(Event task) throws DukeException {
        try {
            FileWriter fw = new FileWriter("duke.txt", true);
            fw.write("E | 0 | " + task.name + " | " + task.date + " "
                    + task.time + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("File cannot be found.", e);
        }
    }

    public void appendDeadline(Deadline task) throws DukeException {
        try {
            FileWriter fw = new FileWriter("duke.txt", true);
            fw.write("D | 0 | " + task.name + " | " + task.deadline + " "
                    + task.time + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("File cannot be found.", e);
        }
    }

    public void rewrite(TaskList task) throws DukeException {
        try {
            FileWriter fw = new FileWriter("duke.txt");
            for (Task nextTask : task.getTaskList()) {
                String done = nextTask.done ? "1" : "0";
                String type = nextTask.toString().substring(1, 2);
                String name = nextTask.name;
                String time = "";
                if (type.equals("E")) {
                    int index = nextTask.toString().indexOf("at: ");
                    time = nextTask.toString().substring(index + 4, nextTask.toString().length() - 1);
                } else if (type.equals("D")) {
                    int index = nextTask.toString().indexOf("by: ");
                    time = nextTask.toString().substring(index + 4, nextTask.toString().length() - 1);
                }
                fw.write(type + " | " + done + " | " + name + " "
                        + time + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("File cannot be opened.", e);
        }
    }
}
