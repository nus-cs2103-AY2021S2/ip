import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

/**
 * Todo:
 * - exceptions yet to be handled:
 * - number of tasks > 100
 * - multiple spaces in between tokens
 * - done command
 * - w/o number
 * - number out of range
 * - help command
 * - Task as abstract class with 3 subclasses (T/D/E)
 * - TaskList as a class !!!
 */

public class Duke {

    public static final String fileName = "tasks.data";

    /**
     * The task list
     */
    public static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * All exit commands are listed here
     */
    private static final Set<String> EXIT_COMMANDS = Set.of(
            "bye", "exit", "quit"
    );

    /**
     * Tokenize a command
     *
     * @param command a line of command
     * @return tokenized command as a String array
     */
    public static String[] tokenizeCommand(String command) {
        ArrayList<String> tokens = new ArrayList<>();
        boolean newToken = true;
        boolean unmatchedQuote = false;
        char quotationMark = ' ';
        for (char ch : command.toCharArray()) {
            if (!unmatchedQuote) {
                // if not a whitespace character
                if (ch != ' ' && ch != '\t') {
                    // if right after a whitespace character
                    if (newToken) {
                        // start of a quoted token
                        if (ch == '"' || ch == '\'') {
                            quotationMark = ch;
                            unmatchedQuote = true;
                            tokens.add("");
                        } else {
                            tokens.add(String.valueOf(ch));
                            newToken = false;
                        }
                    } else {
                        tokens.set(tokens.size() - 1, tokens.get(tokens.size() - 1) + ch);
                    }
                } else {
                    newToken = true;
                }
            } else {
                if (ch != quotationMark) {
                    tokens.set(tokens.size() - 1, tokens.get(tokens.size() - 1) + ch);
                } else {
                    unmatchedQuote = false;
                    newToken = true;
                }
            }
        }
        return (String[]) tokens.toArray();
    }

    /**
     * Save the task list to disk
     */
    public static void saveToFile() {
        try {
            File fileObj = new File(fileName);
            fileObj.createNewFile();
            FileWriter fileWriter = new FileWriter(fileName);
            // Convert task list to a string
            StringBuilder data = new StringBuilder();
            for (Task task : tasks) {
                data.append(task.toSavedString());
            }
            fileWriter.write(data.toString());
        } catch (IOException e) {
            Ui.printError(e);
        }
    }

    public static void readFromFile() {
        try {
            File fileObj = new File(fileName);
            fileObj.createNewFile();
            Scanner fileReader = new Scanner(fileObj);
            StringBuilder data = new StringBuilder();
            while (fileReader.hasNextLine()) {
                data.append(fileReader.nextLine());
            }
            fileReader.close();
            tasks.clear();
            String[] lines = data.toString().split("\n");
            for (String line:lines) {
                String[] sections = line.split(" | ");
                if (sections[0].equals("T")) {
                    Task task = new TodoTask(sections[2]);
                    if (sections[1].equals("1")) {
                        task.setIsDone(true);
                    }
                    tasks.add(task);
                } else if (sections[0].equals("D")) {
                    Task task = new DeadlineTask(sections[2], sections[3]);
                    if (sections[1].equals("1")) {
                        task.setIsDone(true);
                    }
                    tasks.add(task);
                } else if (sections[0].equals("E")) {
                    Task task = new EventTask(sections[2], sections[3]);
                    if (sections[1].equals("1")) {
                        task.setIsDone(true);
                    }
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            Ui.printError(e);
        }
    }

    /**
     * Process a command
     *
     * @param command the command to process
     * @return whether the program should continue (<code>true</code> if not an exit command)
     */
    public static boolean processCommand(String command) throws Exception {
        String[] tokens = command.split(" ");
        Ui.printHorizontalLine();
        if (EXIT_COMMANDS.contains(command)) {
            Ui.printLine("Bye. Hope to see you again soon!");
        } else {
            if (tokens[0].equals("list")) {
                Ui.printLine("Here are the tasks in your list:");
                int index = 0;
                for (Task task : tasks) {
                    Ui.printLine(String.format("%d.%s", ++index, task.toString()));
                }
            } else if (tokens[0].equals("done")) {
                if (tokens.length < 2) {
                    throw new Exception("Please provide a valid task number!");
                }
                try {
                    int taskNumber = Integer.parseInt(tokens[1]);
                    if (taskNumber < 1) {
                        throw new Exception("Please provide a valid task number!");
                    }
                    if (taskNumber <= tasks.size()) {
                        tasks.get(taskNumber - 1).setIsDone(true);
                        Ui.printLine("Nice! I've marked this task as done:");
                        Ui.printLine(String.format("  %s", tasks.get(Integer.parseInt(tokens[1]) - 1)));
                    } else {
                        throw new Exception("Task does not exist!");
                    }
                } catch (NumberFormatException e) {
                    throw new Exception("Please provide a valid task number!");
                }
            } else if (tokens[0].equals("delete")) {
                if (tokens.length < 2) {
                    throw new Exception("Please provide a valid task number!");
                }
                try {
                    int taskNumber = Integer.parseInt(tokens[1]);
                    if (taskNumber < 1) {
                        throw new Exception("Please provide a valid task number!");
                    }
                    if (taskNumber <= tasks.size()) {
                        Task task = tasks.get(taskNumber - 1);
                        tasks.remove(taskNumber - 1);
                        Ui.printLine("Noted. I've removed this task:");
                        Ui.printLine(String.format("  %s", task));
                        Ui.printLine(String.format("Now you have %d tasks in the list.", tasks.size()));
                    } else {
                        throw new Exception("Task does not exist!");
                    }
                } catch (NumberFormatException e) {
                    throw new Exception("Please provide a valid task number!");
                }
            } else if (tokens[0].equals("todo")) {
                if (tokens.length < 2) {
                    throw new Exception("Please provide a task name!");
                }
                Task task = new TodoTask(tokens[1]);
                tasks.add(task);
                Ui.printLine("Got it! I've added this task:");
                Ui.printLine("  " + task);
                Ui.printLine(String.format("Now you have %d tasks in the list.", tasks.size()));
            } else if (tokens[0].equals("deadline")) {
                if (tokens.length < 2) {
                    throw new Exception("Please provide a task name!");
                }
                boolean dueTimeNotProvided = true;
                for (int index = 2; index < tokens.length; index++) {
                    if (tokens[index].equals("/by")) {
                        dueTimeNotProvided = false;
                        if (index + 1 < tokens.length) {
                            Task task = new DeadlineTask(tokens[1], tokens[index + 1]);
                            tasks.add(task);
                            Ui.printLine("Got it! I've added this task:");
                            Ui.printLine("  " + task);
                            Ui.printLine(String.format("Now you have %d tasks in the list.", tasks.size()));
                        } else {
                            throw new Exception("Please provide a valid due time!");
                        }
                        break;
                    }
                }
                if (dueTimeNotProvided) {
                    Task task = new DeadlineTask(tokens[1]);
                    tasks.add(task);
                    Ui.printLine("Got it! I've added this task:");
                    Ui.printLine("  " + task);
                    Ui.printLine(String.format("Now you have %d tasks in the list.", tasks.size()));
                }
            } else if (tokens[0].equals("event")) {
                if (tokens.length < 2) {
                    throw new Exception("Please provide a task name!");
                }
                boolean eventTimeNotProvided = true;
                for (int index = 2; index < tokens.length; index++) {
                    if (tokens[index].equals("/at")) {
                        eventTimeNotProvided = false;
                        if (index + 1 < tokens.length) {
                            Task task = new EventTask(tokens[1], tokens[index + 1]);
                            tasks.add(task);
                            Ui.printLine("Got it! I've added this task:");
                            Ui.printLine("  " + task);
                            Ui.printLine(String.format("Now you have %d tasks in the list.", tasks.size()));
                        } else {
                            throw new Exception("Please provide a valid event time!");
                        }
                        break;
                    }
                }
                if (eventTimeNotProvided) {
                    Task task = new EventTask(tokens[1]);
                    tasks.add(task);
                    Ui.printLine("Got it! I've added this task:");
                    Ui.printLine("  " + task);
                    Ui.printLine(String.format("Now you have %d tasks in the list.", tasks.size()));
                }
            } else {
                throw new Exception("Unknown command!");
            }
        }
        saveToFile();
        Ui.printHorizontalLine();
        Ui.printEmptyLine();
        return !EXIT_COMMANDS.contains(command);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Ui.printHorizontalLine();
        Ui.printEmptyLine();
        Ui.printLogo();
        Ui.printEmptyLine();
        Ui.printLine("Who is the ultimate Personal Assistant Chatbot that helps keep track of various things?");
        Ui.printLine("Sou, watashi desu!");
        Ui.printHorizontalLine();
        Ui.printEmptyLine();
        readFromFile();
        for (; ; ) {
            try {
                if (!processCommand(sc.nextLine())) {
                    break;
                }
            } catch (Exception e) {
                Ui.printError(e);
            }
        }
    }
}
