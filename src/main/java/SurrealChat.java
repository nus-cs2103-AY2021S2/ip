import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import java.io.File;
import java.io.IOException;

public class SurrealChat {
    public static final int TASK_UNDONE = 0;
    public static final String TASK_FILE_PATH = "tasks.txt";
    public static InputManagement inputManagement = new InputManagement(new Scanner(System.in));
    public static TaskManagement taskManagement = new TaskManagement(new ArrayList<Task>());

    private static void loadTaskFile() throws IOException {
        File f = new File(SurrealChat.TASK_FILE_PATH);
        f.createNewFile();
        Scanner fileScanner = new Scanner(f);
        while (fileScanner.hasNext()) {
            String nextTask = fileScanner.nextLine();
            String[] taskComponents = nextTask.split("/split/");
            String taskType = taskComponents[0];
            int taskDone = Integer.valueOf(taskComponents[1]);
            String description = taskComponents[2];
            switch(taskType) {
                case "T":
                    SurrealChat.taskManagement.addToDo(description, taskDone);
                    break;
                case "D":
                    SurrealChat.taskManagement.addDeadline(description, taskDone);
                    break;
                case "E":
                    SurrealChat.taskManagement.addEvent(description, taskDone);
                    break;
                default:
                    throw new InputMismatchException("The task type scanned from file is invalid. Not Stonks!");
            }
        }
    }

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
                SurrealChat.taskManagement.addToDo(description, SurrealChat.TASK_UNDONE);
                break;
            case "deadline":
                description = SurrealChat.inputManagement.getInputDescription();
                SurrealChat.taskManagement.addDeadline(description, SurrealChat.TASK_UNDONE);
                break;
            case "event":
                description = SurrealChat.inputManagement.getInputDescription();
                SurrealChat.taskManagement.addEvent(description, SurrealChat.TASK_UNDONE);
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
        try {
            SurrealChat.loadTaskFile();
        } catch (IOException e) {
            System.err.println(e + "\n");
        }
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
        SurrealChat.taskManagement.saveTasksToFile();
        SurrealChat.inputManagement.closeScanner();
        SurrealChat.exitProgram();
    }
}
