import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static String tab = "     ";
    public static String line = "     ............................................................";
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static int numTasks = 0;

    public static void main(String[] args) {
        printIntro();
        Scanner scan = new Scanner(System.in);

        loop:
        while (scan.hasNextLine()) {
            String input = scan.nextLine();

            System.out.println(line);

            switch (input) {
                case "bye":
                    printBye();
                    break loop;
                case "list":
                    printList();
                    break;
                default:
                    try {
                        String[] inputArr = input.split(" ", 2);
                        String task = inputArr[0];

                        String scannedInput = inputArr[1];
                        String description, date;
                        int taskIndex;

                        switch (task) {
                            case "done":
                                taskIndex = Integer.parseInt(scannedInput) - 1;
                                tasks.get(taskIndex).markAsDone();

                                printDone(taskIndex);
                                break;
                            case "delete":
                                taskIndex = Integer.parseInt(scannedInput) - 1;
                                Task deletedTask = tasks.get(taskIndex);
                                tasks.remove(taskIndex);
                                printDelete(deletedTask.toString());

                                numTasks--;
                                printNumTasks();
                                break;
                            case "todo":
                                description = scannedInput;
                                ToDos todo = new ToDos(description);
                                tasks.add(todo);
                                printAdd(numTasks);

                                numTasks++;
                                printNumTasks();
                                break;
                            case "deadline":
                                String[] arrOfInputD = scannedInput.split("/by");
                                description = arrOfInputD[0];
                                date = arrOfInputD[1];
                                Deadlines deadline = new Deadlines(description, date);
                                tasks.add(deadline);
                                printAdd(numTasks);

                                numTasks++;
                                printNumTasks();
                                break;
                            case "event":
                                String[] arrOfInputE = scannedInput.split("/at");
                                description = arrOfInputE[0];
                                date = arrOfInputE[1];
                                Events event = new Events(description, date);
                                tasks.add(event);
                                printAdd(numTasks);

                                numTasks++;
                                printNumTasks();
                                break;
                            default:
                                printIdkError();
                                break;
                        }
                        break;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        if (input.equals("todo") || input.equals("deadline") || input.equals("event")
                                || input.equals("done") || input.equals("delete")) {
                            printEmptyDescError(input);
                        } else {
                            printIdkError();
                        }
                    }
            }
            System.out.println(line);
        }
    }

    public static void printIntro() {
        String logo =
                          " _____   _   _\n"
                        + "| ____| | | | |\n"
                        + "| |___  | | | | __   __\n"
                        + "|  ___| | | | | \\ \\ / /\n"
                        + "| |___  | | | |  \\ v /\n"
                        + "|_____| |_| |_|  /  /\n"
                        + "                /__/\n";

        System.out.println("   C H A T   W I T H\n" + logo);

        System.out.println(line + "\n"
                + tab + "Hi there! I'm Elly.\n"
                + tab + "How can I help you today?\n"
                + line);
    }

    public static void printList() {
        System.out.println(tab + "Here are the tasks in your list:");
        for (int i = 0; i < numTasks; i++) {
            int num = i + 1;
            Task task = tasks.get(i);
            System.out.println(
                    tab + num + "." + task.toString());
        }
    }

    public static void printDone(int index) {
        System.out.println(tab + "Nice! I've marked this task as done:");
        System.out.println(tab + tasks.get(index).toString());
    }

    public static void printDelete(String string) {
        System.out.println(tab + "Noted. I've removed this task:");
        System.out.println(tab + string);
    }

    public static void printAdd(int index) {
        System.out.println(tab + "Got it. I've added this task:");
        System.out.println(tab + tasks.get(index).toString());
    }

    public static void printNumTasks() {
        System.out.println(tab + "Now you have " + numTasks + " tasks in the list.");
    }

    public static void printBye() {
        System.out.println(tab + "Goodbye, can't wait to see you again!");
        System.out.println(line);
    }

    public static void printEmptyDescError(String task) {
        System.out.println(tab + "Oops! Description of " + task + " cannot be empty.");
    }

    public static void printIdkError() {
        System.out.println(tab + "I'm sorry, I'm not sure what that means.");
    }

}
