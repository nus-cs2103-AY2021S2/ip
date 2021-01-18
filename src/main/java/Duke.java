import java.util.ArrayList;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<String> command_history = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Greet User
        printGreetings();
        String command = scanner.nextLine();
        while (!command.equalsIgnoreCase("bye")) {
            printLine();
            switch (command) {
                case "list" :
                    //Display history of commands
                    listCommands();
                    break;
                default:
                    //Adding commands into history for listing later
                    command_history.add(command);
                    //Echo commands
                    System.out.printf("added: %s\n", command);
                    break;
            }
            printLine();
            command = scanner.nextLine();
        }  //Exits the program
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public static void listCommands() {
        for(int i = 0; i < command_history.size(); i++) {
            System.out.printf("%d. %s\n", (i+1) , command_history.get(i));
        }
    }

    public static void printLine() {
        System.out.println("----------------------------------------------");
    }
    public static void printGreetings() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printLine();
        System.out.println("Hello! I`m Duke");
        System.out.println("How can i help you?");
        printLine();
    }
}
