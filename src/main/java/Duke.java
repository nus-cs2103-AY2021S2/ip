import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// @@author: VRSoorya (??)
// adapted from GitHub repo nus-cs2103-AY2021S2/ip

public class Duke {
    private static List<Task> tasks = new ArrayList<>();
    // source: https://www.sghill.net/how-do-i-make-cross-platform-file-paths-in-java.html
    // inserts correct file path separator on *nix and Windows
    private static java.nio.file.Path dataPath = java.nio.file.Paths.get(
            "../../../data",
            "olaf.txt");

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String logo = "           ___  _           ,_,\n"
                + "         |  _  | |   ___   / /\n"
                + "         | | | | |  / _ \\ =; ;=,\n"
                + "         | |_| | |_| |_| \\ | |\n"
                + "          \\___/\\___/---;_| |_|\n";

        String border = " --*---*---*---*---*---*---*---*---*---*--";
        String help = "  Please use the following to update me:\n\n" +
                "    todo <task description>\n      \\_ adds a task to be done\n" +
                "    deadline <task description> /by <DD-MM-YYYY>\n      \\_ adds a deadline\n" +
                "    event <task description> /at <DD-MM-YYYY HHMM> to <DD-MM-YYYY HHMM>" +
                "\n      \\_ adds an event\n" +
                "    list\n      \\_ lists all tasks, deadlines and events\n" +
                "    done <task number>\n      \\_ marks a task as done\n" +
                "    delete <task number>\n      \\_ deletes a task from the list\n" +
                "    bye\n      \\_ exists this application\n";

        System.out.println(logo + "\n" + border + "\n  Hey there, Olaf here!" +
                "\n  What will we be doing today?");

        while(true) {
            String command = bf.readLine();
            if(command.equalsIgnoreCase("bye")){
                String out = "Aww hope to see you soon, goodbye!";
                System.out.println(border + "\n  " + out + "\n" + border);
                break;
            } else if(command.equalsIgnoreCase("list")){
                try {
                    readData();
                    if (tasks.size()>0) {
                        System.out.println(border + "\n  Here are all the tasks in your list:\n");
                        int idx = 0;
                        int undone = 0;
                        // adapted from: https://howtodoinjava.com/java/collections/arraylist/iterate-through-objects/
                        while (tasks.size() > idx) {
                            Task toPrint = tasks.get(idx++);
                            System.out.printf("  %s. %s\n", idx, toPrint);
                            undone += toPrint.isNotDone();
                        }
                        System.out.printf("\n  Only %s tasks left to be done!\n%s\n", undone, border);
                    }
                } catch (IOException e) {
                    System.out.println(border + "\n  You don't have any tasks yet :)" +
                            "\n  Type 'help' to see how you can add a task\n" + border);
                }
            } else if(command.toLowerCase().startsWith("done")){
                try {
                    int idx = Integer.parseInt(command.split(" ")[1]);
                    tasks.get(idx - 1).markAsDone();
                    saveData();

                    int undone = tasks.stream()
                            .mapToInt(Task::isNotDone)
                            .reduce(0, Integer::sum);
                    System.out.println(border + "\n  Great job! You're done with:\n");
                    System.out.printf("  %s. %s\n", idx, tasks.get(idx - 1));
                    System.out.printf("\n  Now %s tasks are left to be done!\n", undone);
                    System.out.println(border);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(border + "\n  Oops! Please state the task number to mark as done:\n");
                    System.out.println("  done <task number>\n");
                    System.out.println("  Type 'list' to view all tasks\n" +
                            "  and their respective numbers\n" + border);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(border + "\n  Oops! No such task found...\n");
                    System.out.printf("  There are only %s tasks in your list.\n", tasks.size());
                    System.out.println(border);
                } catch (NumberFormatException e) {
                    System.out.println(border + "\n  Oops! No such task found...");
                    System.out.println("  Please state the task number to mark as done:\n");
                    System.out.println("  done <task number>\n");
                    System.out.println("  Type 'list' to view all tasks\n" +
                            "  and their respective numbers\n" + border);
                }
            } else if(command.toLowerCase().startsWith("delete")){
                try {
                    int idx = Integer.parseInt(command.split(" ")[1]);
                    Task deleted = tasks.remove(idx - 1);
                    saveData();

                    System.out.println(border + "\n  Got it, this task is now deleted:\n");
                    System.out.printf("  %s. %s\n", idx, deleted);
                    System.out.printf("\n  You now have %s tasks left if your list.\n", tasks.size());
                    System.out.println(border);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(border + "\n  Oops! Please state the task number to delete:\n");
                    System.out.println("  delete <task number>\n");
                    System.out.println("  Type 'list' to view all tasks\n" +
                            "  and their respective numbers\n" + border);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(border + "\n  Oops! No such task found...\n");
                    System.out.printf("  There are only %s tasks in your list.\n", tasks.size());
                    System.out.println(border);
                } catch (NumberFormatException e) {
                    System.out.println(border + "\n  Oops! No such task found...");
                    System.out.println("  Please state the task number to delete:\n");
                    System.out.println("  done <task number>\n");
                    System.out.println("  Type 'list' to view all tasks\n" +
                            "  and their respective numbers\n" + border);
                }
            } else if(command.toLowerCase().startsWith("todo")){
                try {
                    String expression = command.split(" ", 2)[1];
                    tasks.add(new Todo(expression));
                    saveData();

                    int total = tasks.size();
                    int undone = tasks.stream()
                            .mapToInt(Task::isNotDone)
                            .reduce(0, Integer::sum);
                    System.out.println(border + "\n  Okie added new task:\n");
                    System.out.printf("  %s. %s\n", total, tasks.get(total-1));
                    System.out.printf("\n  Total %s tasks, only %s left to be done!\n", total, undone);
                    System.out.println(border);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(border + "\n  Oops! Please add a task description as follows:\n");
                    System.out.println("  todo <task description>\n" + border);
                }
            } else if(command.toLowerCase().startsWith("deadline")){
                try {
                    String expression = command.split(" ", 2)[1];
                    String[] parts = expression.split("/by", 2);
                    tasks.add(new Deadline(parts[0], parts[1]));
                    saveData();

                    int total = tasks.size();
                    int undone = tasks.stream()
                            .mapToInt(Task::isNotDone)
                            .reduce(0, Integer::sum);
                    System.out.println(border + "\n  Okie added new task:\n");
                    System.out.printf("  %s. %s\n", total, tasks.get(total-1));
                    System.out.printf("\n  Total %s tasks, only %s left to be done!\n", total, undone);
                    System.out.println(border);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(border + "\n  Oops! Please add a deadline using" +
                            "\n  the 24-hour time format as follows:\n");
                    System.out.println("  deadline <task description> /by <DD-MM-YYYY HHMM>\n" + border);
                }
            } else if(command.toLowerCase().startsWith("event")) {
                try {
                    String expression = command.split(" ", 2)[1];
                    String[] parts = expression.split("/at", 2);
                    tasks.add(new Event(parts[0], parts[1]));
                    saveData();

                    int total = tasks.size();
                    int undone = tasks.stream()
                            .mapToInt(Task::isNotDone)
                            .reduce(0, Integer::sum);
                    System.out.println(border + "\n  Okie added new task:\n");
                    System.out.printf("  %s. %s\n", total, tasks.get(total - 1));
                    System.out.printf("\n  Total %s tasks, only %s left to be done!\n", total, undone);
                    System.out.println(border);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(border + "\n  Oops! Please add an event using" +
                            "\n  the 24-hour time format as follows:\n");
                    System.out.println("  event <task description> /at <DD-MM-YYYY HHMM> to <DD-MM-YYYY HHMM>\n" + border);
                }
            } else if (command.equalsIgnoreCase("help")) {
                System.out.println(border + "\n" + help + border);
            } else {
                System.out.println(border + "\n  Hmm sorry I don't understand :(\n");
                System.out.println(help + border);
            }
        }
    }

    private static void saveData() {
        try {
            if (tasks.size() > 0) {
                String tasksToStringIterable = tasks.stream()
                        .map(Task::toString)
                        .reduce((a, b) -> a + "\n" + b)
                        .get();

                // adapted from: https://attacomsian.com/blog/java-save-string-to-text-file
                // write string to a file
                Files.writeString(dataPath,
                        tasksToStringIterable,
                        StandardCharsets.UTF_8,
                        StandardOpenOption.CREATE,
                        StandardOpenOption.TRUNCATE_EXISTING);
            } else {
                Files.deleteIfExists(dataPath);
            }
        } catch (IOException ignore) {
            // program is safe to continue
            // StandardOpenOption.CREATE handles the case when file does not exist
        }
    }

    private static void readData() throws IOException {
        tasks = Files.readAllLines(dataPath,
                StandardCharsets.UTF_8)
                .stream()
                .map(Duke::readTaskFromData)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private static Task readTaskFromData(String line) {
        if (line.startsWith("[T]")) {
            String expression = line.split("] ", 2)[1];
            return new Todo(expression);
        } else if (line.startsWith("[D]")) {
            String expression = line.split("] ", 2)[1];
            String[] parts = expression.split("by:", 2);
            return new Deadline(parts[0], parts[1]);
        } else if (line.startsWith("[E]")) {
            String expression = line.split("] ", 2)[1];
            String[] parts = expression.split("at:", 2);
            return new Event(parts[0], parts[1]);
        }
        return null;
    }
}
