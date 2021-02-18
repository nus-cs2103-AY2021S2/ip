package jeff;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles loading/storing tasks from/to hard disk.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructor for Storage.
     *
     * @param filePath File path to read from/ write to.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads and returns tasks from hard disk.
     *
     * @return ArrayList of Task objects.
     * @throws JeffException If file is not found.
     */
    public ArrayList<Task> load() throws JeffException {
        try {
            File data = new File(filePath);
            Scanner sc = new Scanner(data);
            ArrayList<Task> savedTasks = new ArrayList<>();
            while (sc.hasNext()) {
                Task newTask = readLine(sc.nextLine());
                savedTasks.add(newTask);
            }
            return savedTasks;
        } catch (FileNotFoundException e) {
            throw new JeffException("No saved tasks");
        }
    }

    public Task readLine(String line) {
        String[] lineSplit = line.split("#");
        switch (lineSplit[0]) {
        case "T":
            ToDo savedToDo = new ToDo(lineSplit[2]);
            markTask(lineSplit[1], savedToDo);
            return savedToDo;
        case "D":
            Deadline savedDeadline = new Deadline(lineSplit[2], lineSplit[3], lineSplit[4]);
            markTask(lineSplit[1], savedDeadline);
            return savedDeadline;
        case "E":
            Event savedEvent = new Event(lineSplit[2], lineSplit[3], lineSplit[4]);
            markTask(lineSplit[1], savedEvent);
            return savedEvent;
        default:
            return null;
        }
    }

    public void markTask(String mark, Task task) {
        if (mark.equals("X")) {
            task.setDone();
        }
    }

    /**
     * Saves tasks to hard disk.
     *
     * @param tasks ArrayList of Task objects to be saved.
     * @throws JeffException If an error is encountered while saving.
     */
    public void save(ArrayList<Task> tasks) throws JeffException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task t : tasks) {
                String line = writeLine(t);
                fw.write(line);
            }
            fw.close();
        } catch (IOException e) {
            throw new JeffException("Cannot save tasks " + e.getMessage());
        }
    }

    public String writeLine(Task task) {
        String line = task.getSymbol() + "#" + task.getStatus() + "#" + task.getName();
        if (task instanceof Deadline) {
            Deadline d = (Deadline) task;
            line += "#" + d.getDate().toString() + "#" + d.getTime().toString();
        } else if (task instanceof Event) {
            Event e = (Event) task;
            line += "#" + e.getDate().toString() + "#" + e.getTime().toString();
        }
        line += "\n";
        return line;
    }
}
