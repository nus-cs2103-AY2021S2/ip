import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private final List<Task> database = new ArrayList<>();

    void tellAdd() {
        System.out.println("Got it. I've added this task:");
    }

    void tellSize() {
        String task = database.size() > 1 ? " tasks" : " task";
        System.out.println("Now you have " + database.size() + task + " in the list");
    }

    void addToDB(Task task) {
        database.add(task);
        tellAdd();
        System.out.println("  " + database.get(database.size() - 1));
        tellSize();
    }

    void deleteFromDB(String inputNum) {
        try {
            int num = Parser.taskNumber(inputNum);
            Task currentTask = database.get(num - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println(currentTask);
            database.remove(currentTask);
            tellSize();
        } catch (HahaTaskNumberNotIntException ex) {
            System.out.println(ex);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("OOPS! Wrong number!\n Try specify the right task number");
        }
    }

    Task parseLine(String line) {
        String[] tokens = line.split("\\|");
        String type = tokens[0];
        boolean isDone = tokens[1].equals("true");

        String description = tokens[2];
        Task task;
        switch (type) {
        case "T":
            task = new Todo(isDone, description);
            break;
        case "D":
            task = new Deadline(isDone, description);
            break;
        case "E":
            task = new Event(isDone, description);
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + type);
        }
        return task;
    }

    void readTasks(List<String> file) {
        List<Task> tasks = new ArrayList<>();
        file.forEach(line -> tasks.add(parseLine(line)));
        database.addAll(tasks);
    }

    void updateFile() {
        List<String> str = new ArrayList<>();
        database.forEach(task -> str.add(task.fileStorageFormat()));
        try {
            Files.write(Paths.get(System.getProperty("user.dir"), "data", "database.txt"),
                    str, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void listFromDB() {
        if (database.size() == 0) {
            System.out.println("You have nothing going on!");
        } else {
            System.out.println("Here are your list of tasks:");
            for (int i = 0; i < database.size(); i++) {
                String idx = Integer.toString(i + 1) + '.';
                String task = idx + database.get(i);
                System.out.println(task);
            }
        }
    }

    void markDoneToDB(String inputNum) {
        try {
            int givenIndex = Parser.taskNumber(inputNum) - 1;
            if (givenIndex < 0 || givenIndex >= database.size()) {
                System.out.println("OOPS! Wrong number!\nTry specify the right task number");
            } else {
                Task currentTask = database.get(givenIndex);
                if (currentTask.getIsDone()) {
                    System.out.println("OOPS! I've marked this task as done ALREADY");
                } else {
                    System.out.println("Nice! I've marked this task as done:");
                    currentTask.setDone(true);
                    System.out.println(currentTask);
                }
            }
        } catch (HahaTaskNumberNotIntException ex) {
            System.out.println(ex);
        }
    }
}
