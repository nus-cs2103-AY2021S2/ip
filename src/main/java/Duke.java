import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Task> taskList = new ArrayList<>();

    private static void introduction() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        String welcomeMessage = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(welcomeMessage);
    }

    private static void printList() {
        System.out.println("Here are the tasks in your list:");
        int counter = 1;
        for (Task t : taskList) {
            System.out.println(counter + ". " + t);
            counter++;
        }
    }

    private static boolean isDoneCommand(String command) {
        return command.equals(Commands.DONE.getCommand());
    }

    private static Task defineTask(String command, String[] taskInputAndDate) {
        if (command.equals(TaskTypes.TODO.getType())) {
            return new ToDo(taskInputAndDate[0].trim());
        } else if (command.equals(TaskTypes.DEADLINE.getType())) {
            return new Deadline(taskInputAndDate[0].trim(), taskInputAndDate[1].trim());
        } else if (command.equals(TaskTypes.EVENT.getType())) {
            return new Event(taskInputAndDate[0].trim(), taskInputAndDate[1].trim());
        } else {
            return null;
        }
    }

    private static void endProgram() {
        String endMessage = "Bye. Hope to see you again soon!";
        System.out.println(endMessage);
        scanner.close();
    }

    private static void setTaskDone(int pos) {
        taskList.get(pos).markAsDone();
        System.out.println("Nice! I've marked this task as done:\n" + taskList.get(pos));
    }

    private static void run() {
        introduction();
        while (scanner.hasNextLine()) {
            String command = scanner.next();
            if (command.equals(Commands.BYE.getCommand())) {
                endProgram();
                break;
            } else if (command.equals(Commands.LIST.getCommand())) {
                printList();
            } else {
                String input = scanner.nextLine();
                String[] taskInputAndDate = input.split("/");
                if (isDoneCommand(command)) {
                    try {
                        int pos = Integer.parseInt(taskInputAndDate[0].trim()) - 1;
                        setTaskDone(pos);
                    } catch (NumberFormatException numEx) {
                        System.err.println("'done' is command word; please pass a numerical index or start your task"
                                + " with another word!");
                    } catch (IndexOutOfBoundsException arrEx) {
                        System.err.println("Please pass a valid index!");
                    }
                } else {
                    Task t = defineTask(command, taskInputAndDate);
                    if (t != null) {
                        taskList.add(t);
                        System.out.println("Got it. I've added this task:\n" + t);
                        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    } else {
                        System.err.println("Please enter a valid task command (todo, deadline, event, list, done, bye)!");
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        run();
    }
}