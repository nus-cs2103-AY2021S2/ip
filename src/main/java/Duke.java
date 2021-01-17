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

    private static void addToDo(String taskDescription) {
        ToDoTask newTask = new ToDoTask(taskDescription.trim());
        System.out.println("Meme Man is now adding to-do task: " + newTask);
        Duke.addTask(newTask);
    }

    private static void addDeadline(String taskDescription) {
        String[] descriptionSplitArray = taskDescription.split("/by");
        DeadlineTask newTask = new DeadlineTask(descriptionSplitArray[0].trim(), descriptionSplitArray[1].trim());
        System.out.println("Meme Man is now adding deadline task: " + newTask);
        Duke.addTask(newTask);
    }

    private static void addEvent(String taskDescription) {
        String[] descriptionSplitArray = taskDescription.split("/at");
        EventTask newTask = new EventTask(descriptionSplitArray[0].trim(), descriptionSplitArray[1].trim());
        System.out.println("Meme Man is now adding event task: " + newTask);
        Duke.addTask(newTask);
    }

    private static void addTask(Task task) {
        taskArray[taskArraySize] = task;
        taskArraySize++;
        System.out.printf("Total number of tasks: %d\n", Duke.taskArraySize);
    }

    private static boolean checkInvalidTaskNumber(int taskNumber) {
        return ((taskNumber <= 0) || (taskNumber > Duke.taskArraySize));
    }

    private static void markAsDone(int taskNumber) {
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

    private static void markAsUndone(int taskNumber) {
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

    private static void printList() {
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

    private static void exitProgram() {
        System.out.println("You have been EJECTED!");
    }

    private static void orangEasterEgg() {
        System.out.println("Meme Man: ORANG! IT S U...");
        System.out.println("Orang: No you can't SU");
        System.out.println("Meme Man: ANGERY!");
    }

    private static void vegetalEasterEgg() {
        System.out.println("Vegetal: Did someone said... NO VEGETALS?");
        System.out.println("Meme Man: I taste a vegetal... ANGERY!");
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
            } else if (userCommand.equals("todo")) {
                String description = Duke.getInputDescription(sc);
                Duke.addToDo(description);
            } else if (userCommand.equals("deadline")) {
                String description = Duke.getInputDescription(sc);
                Duke.addDeadline(description);
            } else if (userCommand.equals("event")) {
                String description = Duke.getInputDescription(sc);
                Duke.addEvent(description);
            } else if (userCommand.equals("done")) {
                int taskNumber = Duke.getInputNumber(sc);
                Duke.markAsDone(taskNumber);
            } else if (userCommand.equals("undone")) {
                int taskNumber = Duke.getInputNumber(sc);
                Duke.markAsUndone(taskNumber);
            } else if (userCommand.equals("orang")) {
                sc.nextLine(); //Clear input line
                Duke.orangEasterEgg();
            } else if (userCommand.equals("vegetal")) {
                sc.nextLine(); //Clear input line
                Duke.vegetalEasterEgg();
            } else {
                sc.nextLine(); //Clear input line
                System.out.println("Command not recognised. Not stonks!");
            }
        }
        sc.close();
        Duke.exitProgram();
    }
}
