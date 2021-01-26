import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private File file;

    public Storage() {
        this.file = new File("data", "duke.txt");
    }

    public void createNewFile() throws DukeException {
        try {
            if (!this.file.getParentFile().exists()) {
                this.file.getParentFile().mkdirs();
            }
            if (!this.file.exists()) {
                this.file.createNewFile();
            }
        } catch (IOException e) {
            throw new DukeException("There is an error creating" +
                    "file: " + this.file.getName());
        }
    }

    public void readFile(TaskList tasks) throws DukeException {
        createNewFile();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String[] taskSplit = sc.nextLine().split("\\|");
                switch (taskSplit[0].trim()) {
                case "T":
                    Todo t = new Todo(taskSplit[2]);
                    addToTasks(tasks, t, taskSplit[1]);
                    break;
                case "D":
                    Deadline d = new Deadline(taskSplit[2],
                            Parser.parseSaveToDateTime(taskSplit[3].trim()));
                    addToTasks(tasks, d, taskSplit[1]);
                    break;
                case "E":
                    Event e = new Event(taskSplit[2],
                            Parser.parseSaveToDateTime(taskSplit[3].trim()));
                    addToTasks(tasks, e, taskSplit[1]);
                    break;
                default:
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found!");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Index OOB error!");
        }
    }

    private void addToTasks(TaskList tasks, Task t, String isDone) {
        if (isDone.trim().equals("1")) {
            t.setDone(true);
        }
        tasks.addTask(t);
    }

    public void write(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(this.file);
            for (Task t : tasks.getTaskList()) {
                fw.write(t.getSaveFormat() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("There is an error writing to file.");
        }
    }

}
