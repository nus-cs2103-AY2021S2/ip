import java.util.Scanner;

public class Duke {
    public static String[] taskArray = new String[100];
    public static int taskArraySize = 0;

    public static void initialGreeting() {
        System.out.println("I am Meme Man. Whoms't be entering the VIMension?");
    }

    public static String inputCommand(Scanner sc) {
        String command = sc.nextLine();
        return command;
    }

    public static void addItem(String command) {
        taskArray[taskArraySize] = command;
        taskArraySize++;
        System.out.println("Meme Man is now adding task: " + command);
    }

    public static void printList() {
        if (Duke.taskArraySize == 0) {
            System.out.println("I have nothing to print. Not stonks");
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
        String userCommand = null;
        while (true) {
            userCommand = inputCommand(sc);
            if (userCommand.equals("bye")) {
                break;
            } else if (userCommand.equals("list")){
                Duke.printList();
            } else {
                Duke.addItem(userCommand);
            }
        }
        sc.close();
        Duke.exitProgram();
    }
}
