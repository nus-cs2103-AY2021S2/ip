import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// @@author: VRSoorya
// adapted from GitHub repo nus-cs2103-AY2021S2/ip

public class Olaf {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(PrintText.WELCOME_MESSAGE);

        while(true) {
            String command = bf.readLine();
            if(command.equalsIgnoreCase("bye")){
                String out = "Aww hope to see you soon, goodbye!";
                System.out.println(PrintText.BORDER + "\n  " + out + "\n" + PrintText.BORDER);
                break;
            } else if(command.equalsIgnoreCase("list")){
                try {
                    Storage.readData();

                    // print iteratively if there are TaskList.tasks in the list
                    if (TaskList.tasks.size()>0) {
                        System.out.println(PrintText.BORDER + "\n  Here are all the TaskList.tasks in your list:\n");
                        int idx = 0;
                        int undone = 0;
                        // adapted from: https://howtodoinjava.com/java/collections/arraylist/iterate-through-objects/
                        while (TaskList.tasks.size() > idx) {
                            Task toPrint = TaskList.tasks.get(idx++);
                            System.out.printf("  %s. %s\n", idx, toPrint);
                            undone += toPrint.isNotDone();
                        }
                        System.out.printf("\n  Only %s TaskList.tasks left to be done!\n%s\n", undone, PrintText.BORDER);
                    }
                } catch (IOException e) {
                    System.out.println(PrintText.BORDER + "\n  You don't have any TaskList.tasks yet :)" +
                            "\n  Type 'help' to see how you can add a task\n" + PrintText.BORDER);
                }
            } else if(command.toLowerCase().startsWith("done")){
                try {
                    int idx = Integer.parseInt(command.split(" ")[1]);
                    TaskList.tasks.get(idx - 1).markAsDone();

                    Storage.saveData();

                    int undone = TaskList.tasks.stream()
                            .mapToInt(Task::isNotDone)
                            .reduce(0, Integer::sum);
                    System.out.println(PrintText.BORDER + "\n  Great job! You're done with:\n");
                    System.out.printf("  %s. %s\n", idx, TaskList.tasks.get(idx - 1));
                    System.out.printf("\n  Now %s TaskList.tasks are left to be done!\n", undone);
                    System.out.println(PrintText.BORDER);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(PrintText.BORDER + "\n  Oops! Please state the task number to mark as done:\n");
                    System.out.println("  done <task number>\n");
                    System.out.println("  Type 'list' to view all TaskList.tasks\n" +
                            "  and their respective numbers\n" + PrintText.BORDER);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(PrintText.BORDER + "\n  Oops! No such task found...\n");
                    System.out.printf("  There are only %s TaskList.tasks in your list.\n", TaskList.tasks.size());
                    System.out.println(PrintText.BORDER);
                } catch (NumberFormatException e) {
                    System.out.println(PrintText.BORDER + "\n  Oops! No such task found...");
                    System.out.println("  Please state the task number to mark as done:\n");
                    System.out.println("  done <task number>\n");
                    System.out.println("  Type 'list' to view all TaskList.tasks\n" +
                            "  and their respective numbers\n" + PrintText.BORDER);
                }
            } else if(command.toLowerCase().startsWith("delete")){
                try {
                    int idx = Integer.parseInt(command.split(" ")[1]);
                    Task deleted = TaskList.tasks.remove(idx - 1);
                    Storage.saveData();

                    System.out.println(PrintText.BORDER + "\n  Got it, this task is now deleted:\n");
                    System.out.printf("  %s. %s\n", idx, deleted);
                    System.out.printf("\n  You now have %s TaskList.tasks left if your list.\n", TaskList.tasks.size());
                    System.out.println(PrintText.BORDER);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(PrintText.BORDER + "\n  Oops! Please state the task number to delete:\n");
                    System.out.println("  delete <task number>\n");
                    System.out.println("  Type 'list' to view all TaskList.tasks\n" +
                            "  and their respective numbers\n" + PrintText.BORDER);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(PrintText.BORDER + "\n  Oops! No such task found...\n");
                    System.out.printf("  There are only %s TaskList.tasks in your list.\n", TaskList.tasks.size());
                    System.out.println(PrintText.BORDER);
                } catch (NumberFormatException e) {
                    System.out.println(PrintText.BORDER + "\n  Oops! No such task found...");
                    System.out.println("  Please state the task number to delete:\n");
                    System.out.println("  done <task number>\n");
                    System.out.println("  Type 'list' to view all TaskList.tasks\n" +
                            "  and their respective numbers\n" + PrintText.BORDER);
                }
            } else if(command.toLowerCase().startsWith("todo")){
                try {
                    String expression = command.split(" ", 2)[1];
                    TaskList.tasks.add(new Todo(expression));
                    Storage.saveData();

                    int total = TaskList.tasks.size();
                    int undone = TaskList.tasks.stream()
                            .mapToInt(Task::isNotDone)
                            .reduce(0, Integer::sum);
                    System.out.println(PrintText.BORDER + "\n  Okie added new task:\n");
                    System.out.printf("  %s. %s\n", total, TaskList.tasks.get(total-1));
                    System.out.printf("\n  Total %s TaskList.tasks, only %s left to be done!\n", total, undone);
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
                    TaskList.tasks.add(new Deadline(descriptionSplit[0], deadline));

                    Storage.saveData();

                    int total = TaskList.tasks.size();
                    int undone = TaskList.tasks.stream()
                            .mapToInt(Task::isNotDone)
                            .reduce(0, Integer::sum);
                    System.out.println(PrintText.BORDER + "\n  Okie added new task:\n");
                    System.out.printf("  %s. %s\n", total, TaskList.tasks.get(total-1));
                    System.out.printf("\n  Total %s TaskList.tasks, only %s left to be done!\n", total, undone);
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
                    TaskList.tasks.add(new Event(descriptionSplit[0], start, end));

                    Storage.saveData();

                    int total = TaskList.tasks.size();
                    int undone = TaskList.tasks.stream()
                            .mapToInt(Task::isNotDone)
                            .reduce(0, Integer::sum);
                    System.out.println(PrintText.BORDER + "\n  Okie added new task:\n");
                    System.out.printf("  %s. %s\n", total, TaskList.tasks.get(total - 1));
                    System.out.printf("\n  Total %s TaskList.tasks, only %s left to be done!\n", total, undone);
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
}
