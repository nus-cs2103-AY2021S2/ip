import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.io.File;
// potential exceptions to catch:
// 1) deleting a non-existent task
// 2) marking a non-existent task as done
// 3) marking an already done task done again


public class Duke {
    public static ArrayList<Task> tasks;
    private static final String[] HELLO = {
            System.lineSeparator() +
            " _____ ____  ____  _  __ _____ ____ " + System.lineSeparator() +
            "/__ __Y  _ \\/ ___\\/ |/ //  __//  __\\" + System.lineSeparator() +
            "  / \\ | / \\||    \\|   / |  \\  |  \\/|" + System.lineSeparator() +
            "  | | | |-||\\___ ||   \\ |  /_ |    /" + System.lineSeparator() +
            "  \\_/ \\_/ \\|\\____/\\_|\\_\\\\____\\\\_/\\_\\",
            "Sup sup! I'm Tasker :)"
    };
    private static final String[] GOODBYE = {
            System.lineSeparator() +
                " _____ ____  ____  ____  ____ ___  _ _____" + System.lineSeparator() +
                "/  __//  _ \\/  _ \\/  _ \\/  _ \\\\  \\///  __/" + System.lineSeparator() +
                "| |  _| / \\|| / \\|| | \\|| | // \\  / |  \\  " + System.lineSeparator() +
                "| |_//| \\_/|| \\_/|| |_/|| |_\\\\ / /  |  /_ " + System.lineSeparator() +
                "\\____\\\\____/\\____/\\____/\\____//_/   \\____\\",
            "Till next time :)"
    };
    private static final String[] LOADING = {
            System.lineSeparator() +
            "Loading..."
    };
    private static final String[] READY = {
            System.lineSeparator() +
                    "Tasker is ready :)"
    };
    private static final String ROOT_PROJECT = System.getProperty("user.dir");
    private static Path saveFilePath =
            Paths.get(ROOT_PROJECT,"src", "data", "duke.txt");
    private static Path saveFolderPath =
            Paths.get(ROOT_PROJECT,"src", "data");
    private static final String LINE_PARTITION =
            "__________________________________________________________________" +
                    "_______________________________________________________"
            + "_____________________________________________________________" + System.lineSeparator();

    public enum Command {
        LIST,
        DONE,
        DELETE,
        TODO,
        DEADLINE,
        EVENT
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            init();
            String input = sc.nextLine();
            String[] command = input.split(" ");
            while (!input.equals("bye")) {
                String task = command[0].toUpperCase();
                Command c = Command.valueOf(task);
                switch(c) {
                case LIST:
                    listTasks(tasks);
                    break;
                case DONE:
                    int index = Integer.parseInt(command[1]);
                    markTaskDone(tasks, index);
                    break;
                case DELETE:
                    int i = Integer.parseInt(command[1]);
                    deleteTask(tasks, i);
                    break;
                case TODO:
                    createTodo(tasks, command);
                    break;
                case DEADLINE:
                    createDeadline(tasks, command);
                    break;
                case EVENT:
                     createEvent(tasks, command);
                    break;
                default:
                     throw(new IllegalArgumentException(input));
                }
                saveData();
                input = sc.nextLine();
                command = input.split(" ");
            }
            System.out.println(generateMessage(GOODBYE));
            sc.close();
        }
        catch(IllegalArgumentException e) {
            System.out.println("Enter a proper command :( " + e.getMessage());
        }
        catch(DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void init() {
        try {
            tasks = new ArrayList<>(100);
            System.out.println(generateMessage(HELLO));
            createDbIfNotFound();
            System.out.println(generateMessage(LOADING));
            loadData();
            System.out.println(generateMessage(READY));
        } catch (DukeException e) {
            System.out.println("Initialization failed. " + e);
        }
    }

    /**
     * Returns a String of generated message which will be printed later on.
     * @param messages a String array that contains the message body.
     * @return the generated message for the user.
     */
    private static String generateMessage(String[] messages) {
        StringBuilder output = new StringBuilder(LINE_PARTITION);
        for (String message : messages) {
            output.append(message).append(System.lineSeparator());
        }
        output.append(LINE_PARTITION);
        return output.toString();
    }


    protected static void saveData() throws DukeException {
        try {
            ArrayList<String> ledger = new ArrayList<>();
            for(Task t : tasks) {
                String s = t.getTaskDetails();
                ledger.add(s);
            }
            Files.write(saveFilePath, ledger);
        } catch (IOException e) {
            throw new DukeException("Fail to save data. " + e);
        }
    }

    protected static void loadData() throws DukeException {
        ArrayList<Task> ledger = new ArrayList<>();
        try {
            BufferedReader bufferedReader = Files.newBufferedReader(saveFilePath);
            String record = bufferedReader.readLine();
            while(record != null) {
                ledger.add(stringToTask(record));
                record = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new DukeException("Fail to load data. " + e);
        }
        tasks = ledger;
    }

    private static Task stringToTask(String taskInfo) throws DukeException {
        String[] savedRecord = taskInfo.split("\\|");
        Task output;
        String taskType = savedRecord[0].strip();
        boolean isDone = savedRecord[1].strip().equals("1");
        String description = savedRecord[2].strip();
        switch(taskType) {
        case "T":
            output = new ToDoTask(description, isDone);
            break;
        case "D":
            String by = savedRecord[3].strip();
            output = new DeadlineTask(description, isDone, by);
            break;
        case "E":
            String at = savedRecord[3].strip();
            output = new EventTask(description, isDone, at);
            break;
        default:
            throw new DukeException("Unexpected value: " + taskType);
        }
        return output;
    }

    protected static void createDbIfNotFound()
            throws DukeException{
        try {
            if (Files.notExists(saveFilePath)) {
                if (Files.notExists(saveFolderPath)) {
                    saveFolderPath = Files.createDirectories(saveFolderPath);
                }
                saveFilePath = Files.createFile(saveFilePath);
            }
        } catch (IOException e) {
            throw new DukeException("Unable to access data stored, access rights issue."
                    + e);
        }
    }

    public static void listTasks(ArrayList<Task> tasks) {
        System.out.println("----------------------------------------------");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println("----------------------------------------------");
    }
    public static void markTaskDone(ArrayList<Task> tasks, int index) {
        System.out.println("----------------------------------------------");
        Task t = tasks.get(index - 1);
        t.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
        System.out.println("----------------------------------------------");
    }
    public static void deleteTask(ArrayList<Task> tasks, int index) {
        System.out.println("----------------------------------------------");
        Task t = tasks.remove(index - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println(t);
        System.out.println("Now there are " + tasks.size() + " tasks on the list");
        System.out.println("----------------------------------------------");
    }
    public static void createTodo(ArrayList<Task> tasks, String[] com) {
        System.out.println("----------------------------------------------");
        String todo = "";
        for (int i = 1; i < com.length; i++) {
            todo += " " + com[i];
        }
        ToDoTask newTodo = new ToDoTask(todo, false);
        tasks.add(newTodo);
        System.out.println("Added this:");
        System.out.println(newTodo);
        System.out.println("Now there are " + tasks.size() + " tasks on the list");
        System.out.println("----------------------------------------------");
    }
    public static void createDeadline(ArrayList<Task> tasks, String[] com) {
        System.out.println("----------------------------------------------");
        String description = "";
        String by = "";
        for (int i = 1; i < com.length; i++) {
            if (com[i].equals("/by")) {
                for (int j = i + 1; j < com.length; j++) {
                    by += " " + com[j];
                }
                break;
            }
            description += " " + com[i];
        }
        DeadlineTask deadLine = new DeadlineTask(description, false, by);
        tasks.add(deadLine);
        System.out.println("Added this:");
        System.out.println(deadLine);
        System.out.println("Now there are " + tasks.size() + " tasks on the list");
        System.out.println("----------------------------------------------");
    }
    public static void createEvent(ArrayList<Task> tasks, String[] com) {
        System.out.println("----------------------------------------------");
        String description = "";
        String at = "";
        for (int i = 1; i < com.length; i++) {
            if (com[i].equals("/at")) {
                for (int j = i + 1; j < com.length; j++) {
                    at += " " + com[j];
                }
                break;
            }
            description += " " + com[i];
        }
        EventTask event = new EventTask(description, false, at);
        tasks.add(event);
        System.out.println("Added this:");
        System.out.println(event);
        System.out.println("Now there are " + tasks.size() + " tasks on the list");
        System.out.println("----------------------------------------------");
    }
}


