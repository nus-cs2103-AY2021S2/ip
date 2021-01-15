import java.util.Scanner;

import exceptions.DukeException;
import exceptions.MissingInputException;
import exceptions.UnknownInputException;

public class Duke {

    public static void printDivider() {
        String divider = "    ___________________________________________";
        System.out.println(divider);
    }

    public static void welcome() {
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
    }

    public static void bye() {
        System.out.println("     Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        printDivider();
        welcome();
        printDivider();

        Scanner sc = new Scanner(System.in);

        boolean carryOn = true;
        TaskHandler handler = new TaskHandler();

        while (carryOn) {
            String action = sc.nextLine();
            String[] arr = action.split(" ");
            try {
                switch(arr[0]) {
                    case "todo":
                        if (arr.length <= 1)
                            throw new MissingInputException(arr[0]);
                        printDivider();
                        handler.handleToDoTask(action);
                        printDivider();
                        break;
                    case "deadline":
                        if (arr.length <= 1)
                            throw new MissingInputException(arr[0]);
                        printDivider();
                        handler.handleDeadlineTask(action);
                        printDivider();
                        break;
                    case "event":
                        if (arr.length <= 1)
                            throw new MissingInputException(arr[0]);
                        printDivider();
                        handler.handleEventTask(action);
                        printDivider();
                        break;
                    case "list":
                        printDivider();
                        handler.printStored();
                        printDivider();
                        break;
                    case "done":
                        int number = Integer.valueOf(arr[1]);
                        printDivider();
                        handler.handleDone(number);
                        printDivider();
                        break;
                    case "bye":
                        carryOn = false;
                        break;
                    default:
                        throw new UnknownInputException();
                }
            } catch (MissingInputException e) {
                printDivider();
                System.out.println(e.getMessage());
                printDivider();
            } catch (UnknownInputException e) {
                printDivider();
                System.out.println(e.getMessage());
                printDivider();
            } catch (DukeException e) {
                printDivider();
                System.out.println(e.getMessage());
                printDivider();
            }
        }

        printDivider();
        bye();
        printDivider();

        sc.close();
    }
}
