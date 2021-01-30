package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;


/**
 * Storage class to handle saving and loading data in the TaskList
 */
public class Storage {

    private final String location = "./duke.txt";
    private final Path path;

    /**
     * A constructor for Storage
     * Attempts to get the file from the location, and if it does not exist, creates a new file.
     */
    public Storage() {
        this.path = Paths.get(location);
        File file = new File(location);
        if (Files.notExists(this.path)) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Stores the data to the text file at the location
     * @param list the list of tasks
     * @throws IOException when an invalid filename is used in FileWriter
     */
    public void storeData(ArrayList<Task> list) throws IOException {
        FileWriter file = new FileWriter(this.location);
        for (Task t: list) {
            String data = "";

            switch(t.getType()) {
            case "todo": {
                data = String.format("%s|%s|%s", t.getType(), t.getDescription(), t.getIsDone());
                break;
            }
            case "event": {
                data = String.format("%s|%s|%s|%s",
                        t.getType(), t.getDescription(), ((Event) t).getAt(), t.getIsDone());
                break;
            }
            case "deadline": {
                data = String.format("%s|%s|%s|%s",
                        t.getType(), t.getDescription(), ((Deadline) t).getBy(), t.getIsDone());
                break;
            }
            default: {
                break;
            }
            }
            file.write(data + "\n");
        }
        file.close();
    }

    /**
     * Loads the data from the saved text file
     * @return the list of Tasks as read from the text file
     * @throws FileNotFoundException when the scanner attempts to access an invalid file path
     */
    public ArrayList<Task> loadData() throws FileNotFoundException {
        try {
            Scanner sc = new Scanner(path.toFile());
            ArrayList<Task> list = new ArrayList<>();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] ar = line.split("\\|");
                switch (ar[0]) {
                case "todo": {
                    Todo t = new Todo(ar[1]);
                    list.add(t);
                    break;
                }
                case "event": {
                    list.add(new Event(ar[1], LocalDate.parse(ar[2])));
                    break;
                }
                case "deadline": {
                    list.add(new Deadline(ar[1], LocalDate.parse(ar[2])));
                    break;
                }
                default: {

                }
                }
                if (ar[ar.length - 1].equals("T")) {
                    list.get(list.size() - 1).done();
                }
            }
            return list;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
