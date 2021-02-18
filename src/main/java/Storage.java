import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/** Describes the class that handles file I/O. */
public class Storage {
    private String path;

    /**
     * Returns a Storage object that takes in an argument.
     *
     * @param p The path to the storage file
     */
    public Storage(String p) {
        path = p;
    }

    private void ensureFileExists() {
        File file = new File(path);
        if (file.exists()) {
            return;
        }

        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private Task fromText(String str) {
        String[] xs = str.split(" \\| ");
        Task t;

        if (xs[0].equals("T")) {
            t = new Todo(xs[2]);
        } else if (xs[0].equals("D")) {
            t = new Deadline(xs[2], xs[3]);
        } else {
            t = new Event(xs[2], xs[3]);
        }

        if (xs[1].equals("+")) t.markAsDone();
        return t;
    }

    /**
     * Loads from the storage file
     *
     * @return An ArrayList of the Task objects
     */
    public ArrayList<Task> load() {
        try {
            File file = new File(path);
            Scanner reader = new Scanner(file);

            ArrayList<Task> tasks = new ArrayList<>();
            while (reader.hasNextLine()) {
                tasks.add(fromText(reader.nextLine()));
            }

            return tasks;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Saves to the storage file
     *
     * @param xs The TaskList object
     */
    public void save(TaskList xs) {
        this.ensureFileExists();
        try {
            PrintWriter writer = new PrintWriter(path);
            for (Task t : xs.data) {
                writer.println(t.toText());
            }

            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
