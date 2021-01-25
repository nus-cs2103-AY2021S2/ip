import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = 
                  " ____        _        \n" 
                + "|  _ \\ _   _| | _____ \n" 
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" 
                + "|____/ \\__,_|_|\\_\\___|\n";
        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();

        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm a bot called Duke. Beep boop. \nWhat do you want?\n");

        final String ADD_TASK_TEXT = "Got it. I've added this task:\n    ";
        final String DELETE_TASK_TEXT = "Noted. I have removed this task:\n    ";
        tasks = loadTaskListFromDisk();
        String command = sc.nextLine();
        while (true) {
            String[] commandList = command.split(" ", 2);
            String[] params;

            Task newTask;
            switch (DukeCommand.fromString(commandList[0])) {
                case UNKNOWN:
                    print("Command not recognised.");
                    break;
                case BYE:
                    print("BYE AND HAVE A GOOD DAY. Beep boop.");
                    sc.close();
                    return;
                case LIST:
                    print("Here are the tasks in your list:\n    " + listToString(tasks));
                    break;
                case DELETE:
                    // TODO handle NumberFormatException for Integer.valueOf
                    // TODO handle out of bounds exception for array indexing in commandList, tasks
                    Task deletedTask = tasks.remove(Integer.valueOf(commandList[1]) - 1);
                    print(DELETE_TASK_TEXT + deletedTask + "\n    Now you have " + tasks.size()
                            + " tasks in the list.");
                    break;
                case DONE:
                    // TODO handle out of bounds exception for array indexing in commandList, tasks
                    // TODO handle NumberFormatException for Integer.valueOf
                    Task task = tasks.get(Integer.valueOf(commandList[1]) - 1);
                    task.markAsDone();
                    print("Nice! I've marked this task as done:\n      " + task.toString());
                    break;
                case TODO:
                    try {
                        newTask = new Todo(commandList[1], TaskType.TODO);
                        tasks.add(newTask);
                        print(ADD_TASK_TEXT + newTask.toString() + "\n    Now you have " + tasks.size()
                                + " tasks in the list.");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        print("The description of a todo cannot be empty.");
                    }
                    break;
                case DEADLINE:
                    // TODO handle out of bounds for commandList
                    params = commandList[1].split(" /by ");
                    newTask = new Deadline(params[0], TaskType.DEADLINE, params[1]);
                    tasks.add(newTask);
                    print(ADD_TASK_TEXT + newTask.toString() + "\n    Now you have " + tasks.size()
                            + " tasks in the list.");
                    break;
                case EVENT:
                    // TODO handle out of bounds for commandList
                    params = commandList[1].split(" /at ");
                    newTask = new Event(params[0], TaskType.EVENT, params[1]);
                    tasks.add(newTask);
                    print(ADD_TASK_TEXT + newTask.toString() + "\n    Now you have " + tasks.size()
                            + " tasks in the list.");
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            saveTaskListToDisk(tasks);
            command = sc.nextLine();
        }
    }

    public static void print(String text) {
        String line = "    _______________________________________\n    ";
        System.out.println(line + text + "\n" + line);
    }

    public static String listToString(List<Task> tasks) {
        String str = "";
        if (tasks.size() == 0) {
            return str;
        }
        for (int i = 0; i < tasks.size(); i++) {
            str += String.valueOf(i + 1) + ": " + tasks.get(i) + "\n    ";
        }

        return str.substring(0, str.length() - 5);
    }

    public static Task taskCreate(String line) {
        String[] parsedLine = line.split(" ~ ");
        TaskType taskType = TaskType.fromString(parsedLine[0]);
        Boolean isDone = (Integer.valueOf(parsedLine[1]) == 1) ? true : false;
        String description = parsedLine[2];
        Task newTask;
        switch (taskType) {
        case TODO:
            newTask = new Todo(description, taskType);
            break;
        case EVENT:
            newTask = new Event(description, taskType, parsedLine[3]);
            break;
        case DEADLINE:
            newTask = new Deadline(description, taskType, parsedLine[3]);
            break;
        default:
            throw new IllegalArgumentException();
        }
        if (isDone) {
            newTask.markAsDone();
        }
        return newTask;
    }

    public static List<Task> loadTaskListFromDisk() {
        String rootDirectory = System.getProperty("user.dir");
        java.nio.file.Path directory = java.nio.file.Paths.get(rootDirectory, "data");
        java.nio.file.Path filePath = java.nio.file.Paths.get(rootDirectory, "data", "duke.txt");
        List<Task> tasks = new ArrayList<>();
        try {
            if (!java.nio.file.Files.exists(directory)) {
                java.nio.file.Files.createDirectory(directory);
            }
            if (!java.nio.file.Files.exists(filePath)) {
                java.nio.file.Files.createFile(filePath);
            }
            java.io.File taskFile = filePath.toFile();
            Scanner sc = new Scanner(taskFile);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                tasks.add(taskCreate(line));
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return tasks;
    }

    public static void saveTaskListToDisk(List<Task> tasks) {
        String rootDirectory = System.getProperty("user.dir");
        java.nio.file.Path filePath = java.nio.file.Paths.get(rootDirectory, "data", "duke.txt");
        try {
            Task newTask;
            FileWriter fw = new FileWriter(filePath.toString(), false);
            for (int i = 0; i < tasks.size(); i++) {
                newTask = tasks.get(i);
                fw.write(newTask.saveTaskString());
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}

enum DukeCommand {
    UNKNOWN,
    BYE,
    LIST,
    DELETE,
    DONE,
    TODO,
    EVENT,
    DEADLINE;
    
    public static DukeCommand fromString(String command) {
        for (int i = 1; i < DukeCommand.values().length; i++) {
            if (DukeCommand.values()[i].toString().equals(command.toUpperCase())) {
                return DukeCommand.values()[i];
            }
        }
        return DukeCommand.values()[0];
    }
}