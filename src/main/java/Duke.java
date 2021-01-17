import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Task> taskList = new ArrayList<>();

    private static void introduction() {
        String loading = "Hello from\n"
                + " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  /\n"
                + "|____/ \\,_|_|\\_\\___|\n";
        System.out.println(loading);
        String welcomeMessage = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(welcomeMessage);
    }

    private static void printList() {
        int counter = 1;
        for (Task t : taskList) {
            System.out.println(counter + ". " + t);
            counter++;
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
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            if (input.equals(Commands.BYE.getCommand())) {
                endProgram();
                break;
            } else if (input.equals(Commands.LIST.getCommand())) {
                printList();
            } else {
                String[] inputWords = input.split(" ");
                if (inputWords.length == 2 && inputWords[0].equals(Commands.DONE.getCommand())) {
                    try {
                        int pos = Integer.parseInt(inputWords[1]) - 1;
                        setTaskDone(pos);
                    } catch (NumberFormatException numEx) {
                        System.err.println("'done' is command word; please pass a numerical index or start your task with another word!");
                    } catch (IndexOutOfBoundsException arrEx) {
                        System.err.println("Please pass a valid index!");
                    }
                } else {
                    Task t = new Task(input);
                    taskList.add(t);
                    System.out.println("added: " + t.getDescription());
                }
            }
        }
    }

    public static void main(String[] args) {
        run();
    }
}