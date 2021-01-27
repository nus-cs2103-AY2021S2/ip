import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.nio.file.Path;

// @@author: VRSoorya
// adapted from GitHub repo nus-cs2103-AY2021S2/ip

public class Olaf {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Olaf(String filePath) {
        Storage data = new Storage(filePath);

        ui = new Ui();
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(PrintText.WELCOME_MESSAGE);

        while(true) {
            String command = null;
            try {
                command = bf.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(command.equalsIgnoreCase("bye")){
                String out = "Aww hope to see you soon, goodbye!";
                System.out.println(PrintText.BORDER + "\n  " + out + "\n" + PrintText.BORDER);
                break;
            } else if(command.equalsIgnoreCase("list")){
                if (tasks.hasTasks()) {
                    // print iteratively if there are tasks in the list
                    System.out.println(PrintText.BORDER
                            + "\n  Here are all the tasks in your list:\n"
                            + tasks.toString());
                    System.out.printf("\n  Only %s tasks left to be done!\n%s\n",
                            tasks.getTotalTasksUndone(), PrintText.BORDER);
                } else {
                    System.out.println(PrintText.EMPTY_TASKLIST_MESSAGE);
                }
            } else if(command.toLowerCase().startsWith("done")){
                try {
                    int idx = Integer.parseInt(command.split(" ")[1]);
                    tasks.markTaskAsDone(idx);

                    Storage.saveData(tasks.toString());

                    int undone = tasks.stream()
                            .mapToInt(Task::isNotDone)
                            .reduce(0, Integer::sum);
                    System.out.println(PrintText.BORDER + "\n  Great job! You're done with:\n");
                    System.out.printf("  %s. %s\n", idx, tasks.get(idx - 1));
                    System.out.printf("\n  Now %s tasks are left to be done!\n", undone);
                    System.out.println(PrintText.BORDER);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(PrintText.BORDER + "\n  Oops! Please state the task number to mark as done:\n");
                    System.out.println("  done <task number>\n");
                    System.out.println("  Type 'list' to view all tasks\n" +
                            "  and their respective numbers\n" + PrintText.BORDER);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(PrintText.BORDER + "\n  Oops! No such task found...\n");
                    System.out.printf("  There are only %s tasks in your list.\n", tasks.size());
                    System.out.println(PrintText.BORDER);
                } catch (NumberFormatException e) {
                    System.out.println(PrintText.BORDER + "\n  Oops! No such task found...");
                    System.out.println("  Please state the task number to mark as done:\n");
                    System.out.println("  done <task number>\n");
                    System.out.println("  Type 'list' to view all tasks\n" +
                            "  and their respective numbers\n" + PrintText.BORDER);
                }
            } else if(command.toLowerCase().startsWith("delete")){
                try {
                    int idx = Integer.parseInt(command.split(" ")[1]);
                    Task deleted = tasks.remove(idx - 1);
                    Storage.saveData(tasks.toString());

                    System.out.println(PrintText.BORDER + "\n  Got it, this task is now deleted:\n");
                    System.out.printf("  %s. %s\n", idx, deleted);
                    System.out.printf("\n  You now have %s tasks left if your list.\n", tasks.size());
                    System.out.println(PrintText.BORDER);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(PrintText.BORDER + "\n  Oops! Please state the task number to delete:\n");
                    System.out.println("  delete <task number>\n");
                    System.out.println("  Type 'list' to view all tasks\n" +
                            "  and their respective numbers\n" + PrintText.BORDER);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(PrintText.BORDER + "\n  Oops! No such task found...\n");
                    System.out.printf("  There are only %s tasks in your list.\n", tasks.size());
                    System.out.println(PrintText.BORDER);
                } catch (NumberFormatException e) {
                    System.out.println(PrintText.BORDER + "\n  Oops! No such task found...");
                    System.out.println("  Please state the task number to delete:\n");
                    System.out.println("  done <task number>\n");
                    System.out.println("  Type 'list' to view all tasks\n" +
                            "  and their respective numbers\n" + PrintText.BORDER);
                }
            } else if(command.toLowerCase().startsWith("todo")){
                try {
                    String expression = command.split(" ", 2)[1];
                    tasks.add(new Todo(expression));
                    Storage.saveData(tasks.toString());

                    int total = tasks.size();
                    int undone = tasks.stream()
                            .mapToInt(Task::isNotDone)
                            .reduce(0, Integer::sum);
                    System.out.println(PrintText.BORDER + "\n  Okie added new task:\n");
                    System.out.printf("  %s. %s\n", total, tasks.get(total-1));
                    System.out.printf("\n  Total %s tasks, only %s left to be done!\n", total, undone);
                    System.out.println(PrintText.BORDER);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(PrintText.BORDER + "\n  Oops! Please add a task description as follows:\n");
                    System.out.println("  todo <task description>\n" + PrintText.BORDER);
                }
            } else if(command.toLowerCase().startsWith("deadline")){
                try {
                    String expression = command.split(" ", 2)[1];
                    String[] descriptionSplit = expression.split("/by", 2);
                    // references: https://stackoverflow.com/questions/43845215/java-regex-to-check-for-date-and-time
                    LocalDateTime deadline = LocalDateTime.parse(descriptionSplit[1].trim(),
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    tasks.add(new Deadline(descriptionSplit[0], deadline));

                    Storage.saveData(tasks.toString());

                    int total = tasks.size();
                    int undone = tasks.stream()
                            .mapToInt(Task::isNotDone)
                            .reduce(0, Integer::sum);
                    System.out.println(PrintText.BORDER + "\n  Okie added new task:\n");
                    System.out.printf("  %s. %s\n", total, tasks.get(total-1));
                    System.out.printf("\n  Total %s tasks, only %s left to be done!\n", total, undone);
                    System.out.println(PrintText.BORDER);
                } catch (Exception e) {
                    // catches both ParseException and IndexOutOfBounds exception
                    System.out.println(PrintText.BORDER + "\n  Oops! Please add a deadline using" +
                            "\n  the 24-hour time format as follows:\n");
                    System.out.println("  deadline <task description> /by <YYYY-MM-DD> <HH:MM>\n" + PrintText.BORDER);
                }
            } else if(command.toLowerCase().startsWith("event")) {
                try {
                    String expression = command.split(" ", 2)[1];
                    String[] descriptionSplit = expression.split("/at", 2);
                    String[] durationSplit = descriptionSplit[1].trim().split(" to ");
                    LocalDateTime start = LocalDateTime.parse(durationSplit[0].trim(),
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    LocalDateTime end = LocalDateTime.parse(durationSplit[1].trim(),
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    tasks.add(new Event(descriptionSplit[0], start, end));

                    Storage.saveData(tasks.toString());

                    int total = tasks.size();
                    int undone = tasks.stream()
                            .mapToInt(Task::isNotDone)
                            .reduce(0, Integer::sum);
                    System.out.println(PrintText.BORDER + "\n  Okie added new task:\n");
                    System.out.printf("  %s. %s\n", total, tasks.get(total - 1));
                    System.out.printf("\n  Total %s tasks, only %s left to be done!\n", total, undone);
                    System.out.println(PrintText.BORDER);
                } catch (Exception e) {
                    // catches both ParseException and IndexOutOfBounds exception
                    System.out.println(PrintText.BORDER + "\n  Oops! Please add an event using" +
                            "\n  the 24-hour time format as follows:\n");
                    System.out.println("  event <task description> /at <YYYY-MM-DD> <HH:MM> to <YYYY-MM-DD> <HH:MM>\n" + PrintText.BORDER);
                }
            } else if (command.equalsIgnoreCase("help")) {
                System.out.println(PrintText.BORDER + "\n" + PrintText.HELP + PrintText.BORDER);
            } else {
                System.out.println(PrintText.BORDER + "\n  Hmm sorry I don't understand :(\n");
                System.out.println(PrintText.HELP.toString() + PrintText.BORDER.toString());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Olaf("../../../data/olaf.txt").run();
    }
}
