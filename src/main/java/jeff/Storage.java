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
                String[] line = sc.nextLine().split("#");
                switch (line[0]) {
                case "T":
                    ToDo savedToDo = new ToDo(line[2]);
                    if (line[1].equals("X")) {
                        savedToDo.setDone();
                    }
                    savedTasks.add(savedToDo);
                    break;
                case "D":
                    Deadline savedDeadline = new Deadline(line[2], line[3], line[4]);
                    if (line[1].equals("X")) {
                        savedDeadline.setDone();
                    }
                    savedTasks.add(savedDeadline);
                    break;
                case "E":
                    Event savedEvent = new Event(line[2], line[3], line[4]);
                    if (line[1].equals("X")) {
                        savedEvent.setDone();
                    }
                    savedTasks.add(savedEvent);
                    break;
                default:
                }
            }
            return savedTasks;
        } catch (FileNotFoundException e) {
            throw new JeffException("No saved tasks");
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
                String currLine = t.getSymbol() + "#" + t.getStatus() + "#" + t.getName();
                if (t instanceof Deadline) {
                    Deadline d = (Deadline) t;
                    currLine += "#" + d.getDate().toString() + "#" + d.getTime().toString();
                } else if (t instanceof Event) {
                    Event e = (Event) t;
                    currLine += "#" + e.getDate().toString() + "#" + e.getTime().toString();
                }
                currLine += "\n";
                fw.write(currLine);
            }
            fw.close();
        } catch (IOException e) {
            throw new JeffException("Cannot save tasks" + e.getMessage());
        }
    }
}
