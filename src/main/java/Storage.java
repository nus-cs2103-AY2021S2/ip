import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static String DEFAULT_PATH = "./data.txt";

    public static void ensureFileExists() {
        File file = new File(DEFAULT_PATH);
        if (file.exists()) return;

        try {
            file.createNewFile();
        } catch (IOException e) {

        }
    }

    public static Task fromText(String str) {
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

    public static ArrayList<Task> loadTasks() {
        try {
            File file = new File(DEFAULT_PATH);
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

    public static void saveTasks(ArrayList<Task> xs) {
        ensureFileExists();
        try {
            PrintWriter writer = new PrintWriter(DEFAULT_PATH);
            for (Task t : xs) {
                writer.println(t.toText());
            }

            writer.close();
        } catch (FileNotFoundException e) {

        }
    }
}
