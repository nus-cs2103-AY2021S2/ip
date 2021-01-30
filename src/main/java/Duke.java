import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * the agent program to run Duke
 */
public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // load tasks from the file at the beginning
        try {
            loadFromFile();
        } catch (DukeException e) {
            printMsg(e.getMessage());
        }

        greeting();
        while(true) {
            String command = sc.nextLine();
            printLine();
            boolean canContinue = true;
            try {
                canContinue = processCommand(command);
            } catch (DukeException e) {
                printMsg(e.getMessage());
            }
            printLine();
            if (!canContinue) {
                break;
            }
        }
        sc.close();
    }

    /**
     * print message with proper indentations
     * @param msg the message without indentation
     */
    public static void printMsg(String msg) {
        System.out.println("     " + msg);
    }

    /**
     * @param command the input of user
     * @throws DukeException exception specific to Duke
     */
    public static void deleteTask(String command) throws DukeException {
        try {
            int idx = Integer.parseInt(command.split(" ")[1]) - 1;
            Task task = tasks.get(idx);
            tasks.remove(idx);
            printMsg("Noted. I've removed this task: ");
            printMsg("  " + task);
            printMsg("Now you have " + tasks.size() + " tasks in the list.");
        } catch (Exception e) {
            throw new DukeException();
        }
    }

    /**
     * @param command the input of user
     * @throws DukeException exception specific to Duke
     */
    public static void addTask(String command) throws DukeException {
        String type = command.split(" ")[0];
        if (command.split(" ").length < 2 && type.equals("todo")) {
            throw new EmptyTodoDescriptionException();
        }
        String substr = command.replaceFirst(type + " ", "");
        Task newTask;
        String title;
        switch (type) {
            case "todo":
                title = substr;
                newTask = new ToDo(title);
                tasks.add(newTask);
                break;
            case "deadline":
                int idxOfBy = substr.indexOf("/by");
                if (idxOfBy == -1) {
                    throw new DukeException();
                } else {
                    title = substr.substring(0, idxOfBy - 1);
                    String deadline = substr.substring(idxOfBy + 4);
                    newTask = new Deadline(title, LocalDate.parse(deadline));
                    tasks.add(newTask);
                }
                break;
            case "event":
                int idxOfAt = substr.indexOf("/at");
                if (idxOfAt == -1) {
                    throw new DukeException();
                } else {
                    title = substr.substring(0, idxOfAt - 1);
                    String time = substr.substring(idxOfAt + 4);
                    newTask = new Event(title, LocalDate.parse(time));
                    tasks.add(newTask);
                }
                break;
            default:
                throw new DukeException();
        }
        printMsg("Got it. I've added this task: ");
        printMsg("  " + newTask);
        printMsg("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * @param command the input of user
     * @throws DukeException exception specific to Duke
     */
    public static void doneTask(String command) throws DukeException {
        try {
            int idx = Integer.parseInt(command.split(" ")[1]) - 1;
            Task task = tasks.get(idx);
            task.done();
            printMsg("Nice! I've marked this task as done: ");
            printMsg("  " + task);
        } catch (Exception e) {
            throw new DukeException();
        }
    }

    public static void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * print greeting message
     */
    public static void greeting() {
        printLine();
        String logo = "      ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        printMsg("Hello! I'm Duke");
        printMsg("What can I do for you?");
        printLine();
    }

    /**
     * map commands to actions
     * @param command the input of user
     * @return false if command entered is "bye" else true
     */
    public static boolean processCommand(String command) throws DukeException {
        if (command.equals("bye")) {
            sayBye();
            return false;
        }
        
        if (command.equals("list")) {
            printTasks();
        } else {
            String[] substrs = command.split(" ");
            switch (substrs[0]) {
                case "todo", "event", "deadline":
                    addTask(command);
                    break;
                case "done":
                    doneTask(command);
                    break;
                case "delete":
                    deleteTask(command);
                    break;
                default:
                    throw new UnknownCommandException();
            }
            saveToFile();
        }
        return true;
    }

    /**
     * prints the farewell message
     */
    public static void sayBye() {
        printMsg("Bye. Hope to see you again soon!");
    }

    /**
     * print task list as well as their status
     */
    public static void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            printMsg((i + 1) + "." + tasks.get(i).toString());
        }
    }

    public static void saveToFile() {
        // make the data directory if not found
        File dir = new File("./data");
        if (!dir.exists() || !dir.isDirectory()) {
            dir.mkdir();
        }

        try {
            FileWriter writer = new FileWriter("./data/duke.txt");
            for (Task task : tasks) {
                writer.write(task.toString());
                writer.write('\n');
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            printMsg("can not save to file!");
        }
    }

    public static void loadFromFile() throws DukeException {
        // make the folder called data if not found

        File dir = new File("./data");
        if (!dir.exists() || !dir.isDirectory()) {
            dir.mkdir();
        }

        File file = new File("./data/duke.txt");
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                tasks.add(parseTask(sc.nextLine()));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("cannot load from file!");
        }
    }

    public static Task parseTask(String str) throws DukeException {
        try {
            char typeLabel = str.charAt(1);
            boolean isDone = str.charAt(4) == 'X';
            String body = str.substring(7);
            String pattern;
            Pattern r;
            Matcher m;
            switch (typeLabel) {
                case 'T':
                    return new ToDo(body, isDone);
                case 'D':
                    pattern = "(.*) \\(by: (.*)\\)";
                    r = Pattern.compile(pattern);
                    m = r.matcher(body);
                    if (m.find()) {
                        LocalDate deadline = LocalDate.parse(m.group(2), DateTimeFormatter.ofPattern("MMM dd yyyy"));
                        return new Deadline(m.group(1), isDone, deadline);
                    }
                case 'E':
                    pattern = "(.*) \\(at: (.*)\\)";
                    r = Pattern.compile(pattern);
                    m = r.matcher(body);
                    if (m.find()) {
                        System.out.println(m.group(2) + ".");
                        LocalDate time = LocalDate.parse(m.group(2), DateTimeFormatter.ofPattern("MMM dd yyyy"));
                        return new Event(m.group(1), isDone, time);
                    }
                default:
                    throw new Exception();
            }
        } catch (Exception e) {
            throw new DukeException("error occurs and loading from file is suspended!");
        }
    }
}
