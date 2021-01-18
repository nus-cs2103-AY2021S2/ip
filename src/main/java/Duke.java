import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static List<Task> taskList = new ArrayList<Task>();

    private static void initialGreeting() {
        System.out.println("I am Meme Man. Whoms't be entering the VIMension?\n");
    }

    /**
     * Used to obtain the first one-word command from input line.
     * @param sc - The scanner. Used to obtain the command
     * @return command - command for Meme Man
     */
    private static String getInputCommand(Scanner sc) {
        String command = sc.next();
        return command;
    }

    /**
     * Used to obtain the task description from remainder of input line
     * @param sc - The scanner. Used to obtain the task description
     * @return taskDescription - Description used to create task
     */
    private static String getInputDescription(Scanner sc) {
        String taskDescription = sc.nextLine();
        return taskDescription; //Note: Every task description comes with a space
    }

    /**
     * Used to obtain number from a given description
     * @param description - The raw description
     * @return taskNumber - The number to find task in the array
     */
    private static int getInputNumber(String description) {
        description = description.trim();
        if (description.isEmpty()) {
            throw new NoSuchElementException("Did you forget to put a number for the command you just typed in? Not stonks!");
        } else {
            try {
                Integer taskNumber = Integer.valueOf(description);
                return taskNumber;
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Did you put something other than a number or did you put a number incorrectly? Not stonks!");
            }
        }
    }

    private static void addToDo(String taskDescription) {
        if (taskDescription.isEmpty()) {
            throw new NoSuchElementException("Empty todo task description. Not stonks!");
        } else {
            ToDoTask newTask = new ToDoTask(taskDescription.trim());
            System.out.printf("Meme Man is now adding to-do task: %s\n", newTask);
            Duke.addTask(newTask);
        }
    }

    private static void addDeadline(String taskDescription) {
        if (taskDescription.isEmpty()) {
            throw new NoSuchElementException("Empty deadline task description. Not stonks!");
        } else {
            String[] descriptionSplitArray = taskDescription.split("/by");
            try {
                DeadlineTask newTask = new DeadlineTask(descriptionSplitArray[0].trim(), descriptionSplitArray[1].trim());
                System.out.printf("Meme Man is now adding deadline task: %s\n", newTask);
                Duke.addTask(newTask);
            } catch (ArrayIndexOutOfBoundsException e) { //Happens if split does not occur
                throw new ArrayIndexOutOfBoundsException("Wrong formatting. Did you forget to put '/by'? Not stonks!");
            }
        }
    }

    private static void addEvent(String taskDescription) {
        if (taskDescription.isEmpty()) {
            throw new NoSuchElementException("Empty event task description. Not stonks!");
        } else {
            String[] descriptionSplitArray = taskDescription.split("/at");
            try {
                EventTask newTask = new EventTask(descriptionSplitArray[0].trim(), descriptionSplitArray[1].trim());
                System.out.printf("Meme Man is now adding event task: %s\n", newTask);
                Duke.addTask(newTask);
            } catch (ArrayIndexOutOfBoundsException e) { //Happens if split does not occur
                throw new ArrayIndexOutOfBoundsException("Wrong formatting. Did you forget to put '/at'? Not stonks!");
            }
        }
    }

    private static void addTask(Task task) {
        Duke.taskList.add(task);
        System.out.printf("Total number of tasks: %d\n\n", Duke.taskList.size());
    }

    private static boolean checkInvalidTaskNumber(int taskNumber) {
        return ((taskNumber <= 0) || (taskNumber > Duke.taskList.size()));
    }

    private static void markAsDone(int taskNumber) {
        if (Duke.checkInvalidTaskNumber(taskNumber)) {
            throw new IllegalArgumentException("Invalid task number. Not stonks!");
        } else {
            Task doneTask = Duke.taskList.get(taskNumber - 1);
            doneTask.markAsDone();
            System.out.println("Stonks! You've done this task:");
            System.out.println(doneTask.getDescription() + "\n");
            Duke.taskList.set(taskNumber - 1, doneTask);
        }
    }

    private static void markAsUndone(int taskNumber) {
        if (Duke.checkInvalidTaskNumber(taskNumber)) {
            throw new IllegalArgumentException("Invalid task number. Not stonks!");
        } else {
            Task undoneTask = Duke.taskList.get(taskNumber - 1);
            undoneTask.markAsUndone();
            System.out.println("Not stonks! This task has been marked as undone:");
            System.out.println(undoneTask.getDescription() + "\n");
            Duke.taskList.set(taskNumber - 1, undoneTask);
        }
    }

    private static void deleteTask(int taskNumber) {
        if (Duke.checkInvalidTaskNumber(taskNumber)) {
            throw new IllegalArgumentException("Invalid task number. Not stonks!");
        } else {
            Task deletedTask = Duke.taskList.remove(taskNumber - 1);
            System.out.println("This task has been deleted:");
            System.out.println(deletedTask);
            System.out.printf("Total number of tasks: %d\n\n", Duke.taskList.size());
        }
    }

    private static void printList() {
        if (Duke.taskList.isEmpty()) {
            throw new NoSuchElementException("I have nothing to print. Not stonks!");
        } else {
            System.out.println("I print the tasks:");
            for (int i = 1; i <= Duke.taskList.size(); i++) {
                System.out.println(i + ". " + Duke.taskList.get(i - 1));
            }
            System.out.println("Hmmst've... Stonks\n");
        }
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

    private static boolean commandLogic(boolean maintainLoop, String userCommand, Scanner sc) {
        switch(userCommand) {
            case "bye":
                maintainLoop = false; //Break out of infinite loop
                break;
            case"list":
                Duke.printList();
                break;
            case "todo":
                String description = Duke.getInputDescription(sc);
                Duke.addToDo(description);
                break;
            case "deadline":
                description = Duke.getInputDescription(sc);
                Duke.addDeadline(description);
                break;
            case "event":
                description = Duke.getInputDescription(sc);
                Duke.addEvent(description);
                break;
            case "done":
                description = Duke.getInputDescription(sc); //Get raw form
                int taskNumber = Duke.getInputNumber(description); //Process to obtain int
                Duke.markAsDone(taskNumber);
                break;
            case "undone":
                description = Duke.getInputDescription(sc); //Get raw form
                taskNumber = Duke.getInputNumber(description); //Process to obtain int
                Duke.markAsUndone(taskNumber);
                break;
            case "delete":
                description = Duke.getInputDescription(sc); //Get raw form
                taskNumber = Duke.getInputNumber(description); //Process to obtain int
                Duke.deleteTask(taskNumber);
                break;
            case "orang":
                sc.nextLine(); //Clear input line
                Duke.orangEasterEgg();
                break;
            case "vegetal":
                sc.nextLine(); //Clear input line
                Duke.vegetalEasterEgg();
                break;
            default:
                sc.nextLine(); //Clear input line
                throw new UnsupportedOperationException("Command not recognised. Not stonks!");
        }
        return maintainLoop;
    }

    /**
     * The driver code for Meme Man chatbot.
     * @param args - Optional argument
     */
    public static void main(String[] args) {
        Duke.initialGreeting();
        Scanner sc = new Scanner(System.in);
        String userCommand;
        boolean maintainLoop = true;
        while (maintainLoop) {
            userCommand = getInputCommand(sc);
            try {
                maintainLoop = Duke.commandLogic(maintainLoop, userCommand, sc);
            } catch (Exception e) {
                System.err.println(e.getMessage() + "\n");
            }
        }
        sc.close();
        Duke.exitProgram();
    }
}
