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
     * Print one line with spaces in front
     *
     * @param line the line to print
     */
    public static void printLine(String line) {
        System.out.println("     " + line);
    }

    /**
     * Print a horizontal line
     */
    public static void printHorizontalLine() {
        System.out.println("    " + "____________________________________________________________");
    }

    /**
     * Print an empty line
     */
    public static void printEmptyLine() {
        System.out.println();
    }

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
//                printLine(String.format("%d.%s", ++index, task.toString()));
                data.append(task.toSavedString());
            }
            fileWriter.write(data.toString());
//            Scanner fileReader = new Scanner(fileObj);
//            StringBuilder data = new StringBuilder();
//            while (fileReader.hasNextLine()) {
//                data.append(fileReader.nextLine());
//            }
//            fileReader.close();
//            String dataStr = data.toString();
        } catch (IOException e) {
            e.printStackTrace();
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
        printHorizontalLine();
        if (EXIT_COMMANDS.contains(command)) {
            printLine("Bye. Hope to see you again soon!");
        } else {
            if (tokens[0].equals("list")) {
                printLine("Here are the tasks in your list:");
                int index = 0;
                for (Task task : tasks) {
                    printLine(String.format("%d.%s", ++index, task.toString()));
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
                        printLine("Nice! I've marked this task as done:");
                        printLine(String.format("  %s", tasks.get(Integer.parseInt(tokens[1]) - 1)));
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
                        printLine("Noted. I've removed this task:");
                        printLine(String.format("  %s", task));
                        printLine(String.format("Now you have %d tasks in the list.", tasks.size()));
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
                printLine("Got it! I've added this task:");
                printLine("  " + task);
                printLine(String.format("Now you have %d tasks in the list.", tasks.size()));
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
                            printLine("Got it! I've added this task:");
                            printLine("  " + task);
                            printLine(String.format("Now you have %d tasks in the list.", tasks.size()));
                        } else {
                            throw new Exception("Please provide a valid due time!");
                        }
                        break;
                    }
                }
                if (dueTimeNotProvided) {
                    Task task = new DeadlineTask(tokens[1]);
                    tasks.add(task);
                    printLine("Got it! I've added this task:");
                    printLine("  " + task);
                    printLine(String.format("Now you have %d tasks in the list.", tasks.size()));
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
                            printLine("Got it! I've added this task:");
                            printLine("  " + task);
                            printLine(String.format("Now you have %d tasks in the list.", tasks.size()));
                        } else {
                            throw new Exception("Please provide a valid event time!");
                        }
                        break;
                    }
                }
                if (eventTimeNotProvided) {
                    Task task = new EventTask(tokens[1]);
                    tasks.add(task);
                    printLine("Got it! I've added this task:");
                    printLine("  " + task);
                    printLine(String.format("Now you have %d tasks in the list.", tasks.size()));
                }
            } else {
                throw new Exception("Unknown command!");
            }
        }
        saveToFile();
        printHorizontalLine();
        printEmptyLine();
        return !EXIT_COMMANDS.contains(command);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n" +
                "|  _ \\ _   _| | _____ \n" +
                "| | | | | | | |/ / _ \\\n" +
                "| |_| | |_| |   <  __/\n" +
                "|____/ \\__,_|_|\\_\\___|";
        printHorizontalLine();
        printEmptyLine();
        for (String line : logo.split("\n")) {
            printLine(line);
        }
        printEmptyLine();
        printLine("Who is the ultimate Personal Assistant Chatbot that helps keep track of various things?");
        printLine("Sou, watashi desu!");
        printHorizontalLine();
        printEmptyLine();
        for (; ; ) {
            try {
                if (!processCommand(sc.nextLine())) {
                    break;
                }
            } catch (Exception e) {
                printLine("Error: " + e.getMessage());
            }
        }
    }
}
