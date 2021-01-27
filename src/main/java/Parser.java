import java.util.ArrayList;
import java.util.Set;

public class Parser {

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
     * Process a command
     *
     * @param command the command to process
     * @return whether the program should continue (<code>true</code> if not an exit command)
     */
    public static boolean processCommand(String command, ArrayList<Task> tasks) throws Exception {
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
        Storage.saveToFile(tasks);
        Ui.printHorizontalLine();
        Ui.printEmptyLine();
        return !EXIT_COMMANDS.contains(command);
    }
}
