import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private static final String PATH = "/data/";
    private static final String FILENAME = "duke.txt";
    private static final String ROOT = System.getProperty("user.dir");

    public Storage() {

    }

    public void save(ArrayList<Task> taskList) {

        try {
            PrintWriter writer = new PrintWriter(ROOT + PATH + FILENAME, "UTF-8");

            for (Task task: taskList) {
                writer.println(task.tokenize());
            }

            writer.close();

        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Task> load() {

        try {
            File file = new File(ROOT + PATH + FILENAME);
            Scanner sc = new Scanner(file);

            ArrayList<Task> taskList = new ArrayList<>();

            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                Task task = parseData(data);

                if (task != null) {
                    taskList.add(task);
                }
            }

            return taskList;

        } catch (FileNotFoundException e) {
            createNewFile();
            return new ArrayList<>();
        }

    }

    private Task parseData(String data) {
        String[] tokens = data.split("\\|", 2);
        String taskSymbol = tokens[0];
        String taskDetails = tokens[1];

        switch (taskSymbol) {
            case "T":
                return createTodo(taskDetails);
            case "D":
                return createDeadline(taskDetails);
            case "E":
                return createEvent(taskDetails);
            default:
                return null;
        }
    }

    private Task createEvent(String taskDetails) {
        String[] tokens = taskDetails.split("\\|");
        boolean isDone = !tokens[0].equals("0");

        String details;
        String at;

        if (tokens.length > 2) {
            details = tokens[1];
            at = tokens[2];
        } else {
            details = tokens[1];
            at = null;
        }

        return new Events(isDone, details, at);
    }

    private Task createDeadline(String taskDetails) {
        String[] tokens = taskDetails.split("\\|");
        boolean isDone = !tokens[0].equals("0");

        String details;
        String by;

        if (tokens.length > 2) {
            details = tokens[1];
            by = tokens[2];
        } else {
            details = tokens[1];
            by = null;
        }

        return new Events(isDone, details, by);

    }

    private Task createTodo(String taskDetails) {
        String[] tokens = taskDetails.split("\\|");
        boolean isDone = !tokens[0].equals("0");
        String details = tokens[1];

        return new Todo(isDone, details);
    }


    private void createNewFile() {
        File directory = new File(ROOT + PATH);
        if (!directory.exists()) {
            directory.mkdir();
        }

        try {
            File file = new File (ROOT + PATH + FILENAME);
            if (file.createNewFile()) {
                System.out.println("Created File: " + ROOT + PATH + FILENAME);
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
