import java.util.ArrayList;
import java.util.Scanner;

public class SurrealChat {
    public static InputManagement inputManagement = new InputManagement(new Scanner(System.in));
    public static TaskManagement taskManagement = new TaskManagement(new ArrayList<Task>());

    private static void initialGreeting() {
        System.out.println("I am Meme Man. Whoms't be entering the VIMension?\n");
    }

    private static void exitProgram() {
        System.out.println("You have been EJECTED!");
    }

    private static void orangEasterEgg() {
        System.err.println("Meme Man: ORANG! IT S U...");
        System.err.println("Orang: No you can't SU");
        System.err.println("Meme Man: ANGERY!\n");
    }

    private static void vegetalEasterEgg() {
        System.err.println("Vegetal: Did someone said... NO VEGETALS?");
        System.err.println("Meme Man: I taste a vegetal... ANGERY!\n");
    }

    private static boolean commandLogic(boolean maintainLoop, String userCommand) {
        switch(userCommand) {
            case "bye":
                maintainLoop = false; //Break out of infinite loop
                break;
            case"list":
                SurrealChat.taskManagement.printList();
                break;
            case "todo":
                String description = SurrealChat.inputManagement.getInputDescription();
                SurrealChat.taskManagement.addToDo(description);
                break;
            case "deadline":
                description = SurrealChat.inputManagement.getInputDescription();
                SurrealChat.taskManagement.addDeadline(description);
                break;
            case "event":
                description = SurrealChat.inputManagement.getInputDescription();
                SurrealChat.taskManagement.addEvent(description);
                break;
            case "done":
                description = SurrealChat.inputManagement.getInputDescription(); //Get raw form
                int taskNumber = SurrealChat.inputManagement.getInputNumber(description); //Process to obtain int
                SurrealChat.taskManagement.markAsDone(taskNumber);
                break;
            case "undone":
                description = SurrealChat.inputManagement.getInputDescription(); //Get raw form
                taskNumber = SurrealChat.inputManagement.getInputNumber(description); //Process to obtain int
                SurrealChat.taskManagement.markAsUndone(taskNumber);
                break;
            case "delete":
                description = SurrealChat.inputManagement.getInputDescription(); //Get raw form
                taskNumber = SurrealChat.inputManagement.getInputNumber(description); //Process to obtain int
                SurrealChat.taskManagement.deleteTask(taskNumber);
                break;
            case "orang":
                SurrealChat.inputManagement.scannerNextLine(); //Clear input line
                SurrealChat.orangEasterEgg();
                break;
            case "vegetal":
                SurrealChat.inputManagement.scannerNextLine(); //Clear input line
                SurrealChat.vegetalEasterEgg();
                break;
            default:
                SurrealChat.inputManagement.scannerNextLine(); //Clear input line
                throw new UnsupportedOperationException("Command not recognised. Not stonks!");
        }
        return maintainLoop;
    }

    /**
     * The driver code for Meme Man chatbot.
     * @param args - Optional argument
     */
    public static void main(String[] args) {
        SurrealChat.initialGreeting();
        String userCommand;
        boolean maintainLoop = true;
        while (maintainLoop) {
            userCommand = SurrealChat.inputManagement.getInputCommand();
            try {
                maintainLoop = SurrealChat.commandLogic(maintainLoop, userCommand);
            } catch (Exception e) {
                System.err.println(e.getMessage() + "\n");
            }
        }
        SurrealChat.inputManagement.closeScanner();
        SurrealChat.exitProgram();
    }
}
