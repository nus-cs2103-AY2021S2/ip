import java.util.Scanner;

public class Duke {
    public static void initialGreeting() {
        System.out.println("I am Meme Man. Whoms't be entering the VIMension?");
    }

    public static String inputCommand(Scanner sc) {
        String command = sc.next();
        return command;
    }

    public static void executeCommand(String command) {
        System.out.println(command);
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
            } else {
                Duke.executeCommand(userCommand);
            }
        }
        sc.close();
        Duke.exitProgram();
    }
}
