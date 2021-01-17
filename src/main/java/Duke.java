import java.util.Scanner;

public class Duke {
    public static Task[] taskArray = new Task[100];
    public static int taskArraySize = 0;

    private static void initialGreeting() {
        System.out.println("I am Meme Man. Whoms't be entering the VIMension?");
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
     * Used to obtain number to pull out task from array
     * @param sc - The scanner. Used to obtain the number in the user input.
     * @return taskNumber - The number to find task in the array
     */
    private static int getInputNumber(Scanner sc) {
        int taskNumber = sc.nextInt();
        sc.nextLine(); //Clears the line
        return taskNumber;
    }

    public static void addTask(String taskDescription) {
        Task addedTask = new Task(taskDescription);
        taskArray[taskArraySize] = addedTask;
        taskArraySize++;
        System.out.println("Meme Man is now adding task: " + taskDescription);
    }



    private static boolean checkInvalidTaskNumber(int taskNumber) {
        return ((taskNumber <= 0) || (taskNumber > Duke.taskArraySize));
    }

    public static void markAsDone(int taskNumber) {
        if (Duke.checkInvalidTaskNumber(taskNumber)) {
            System.out.println("Invalid task number. Not stonks!");
        } else {
            Task doneTask = taskArray[taskNumber - 1];
            doneTask.markAsDone();
            System.out.println("Stonks! You've done this task: ");
            System.out.println(doneTask.getDescription());
            taskArray[taskNumber - 1] = doneTask;
        }
    }

    public static void markAsUndone(int taskNumber) {
        if (Duke.checkInvalidTaskNumber(taskNumber)) {
            System.out.println("Invalid task number. Not stonks!");
        } else {
            Task undoneTask = taskArray[taskNumber - 1];
            undoneTask.markAsUndone();
            System.out.println("Not stonks! This task has been marked as undone: ");
            System.out.println(undoneTask.getDescription());
            taskArray[taskNumber - 1] = undoneTask;
        }
    }

    public static void printList() {
        if (Duke.taskArraySize == 0) {
            System.out.println("I have nothing to print. Not stonks!");
        } else {
            System.out.println("I print the tasks:");
            for (int i = 1; i <= taskArraySize; i++) {
                System.out.println(i + ". " + taskArray[i - 1]);
            }
            System.out.println("Hmmst've... Stonks");
        }
    }

    public static void exitProgram() {
        System.out.println("You have been EJECTED!");
    }

    public static void main(String[] args) {
        Duke.initialGreeting();
        Scanner sc = new Scanner(System.in);
        String userCommand;
        while (true) { //Maintains infinite loop
            userCommand = getInputCommand(sc);
            if (userCommand.equals("bye")) {
                break; //Only command that can leave the infinite loop
            } else if (userCommand.equals("list")){
                Duke.printList();
            } else if (userCommand.equals("add")) {
                String description = Duke.getInputDescription(sc);
                Duke.addTask(description);
            } else if (userCommand.equals("done")) {
                int taskNumber = Duke.getInputNumber(sc);
                Duke.markAsDone(taskNumber);
            } else if (userCommand.equals("undone")) {
                int taskNumber = Duke.getInputNumber(sc);
                Duke.markAsUndone(taskNumber);
            } else {
                sc.nextLine(); //Clear input line
                System.out.println("Command not recognised. Not stonks!");
            }
        }
        sc.close();
        Duke.exitProgram();
    }
}
