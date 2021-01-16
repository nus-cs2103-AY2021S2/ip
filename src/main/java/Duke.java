import java.util.Scanner;

/**
 * The Duke project
 */
public class Duke {
    public static void main(String[] args) {
        greet();

        Scanner sc = new Scanner(System.in);

        String username = sc.nextLine();
        nextGreet(username);

        boolean endOfCycle = false;

        while(!endOfCycle) {
            System.out.print(username + ": ");
            String nextCommand = sc.nextLine();
            if (nextCommand.equals("bye")) {
                bye(username);
                endOfCycle = true;
            } else {
                echo(nextCommand);
            }
        }

        sc.close();
    }

    /**
     * Greets the user when the Duke is launched.
     */
    public static void greet() {
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Hello! I'm Jay \n" + "What is your name!");
        System.out.println("----------------------------------------------------------------------------------------");
    }

    /**
     * Greets the user again after knowing the user's name.
     * @param username The name of the user.
     */
    public static void nextGreet(String username) {
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Hi " + username + "!");
        System.out.println("What can I do for you?");
        System.out.println("----------------------------------------------------------------------------------------");
    }

    /**
     * Echo/Repeat what the user typed as input by printing out what the user keyed in.
     * @param command The user input.
     */
    public static void echo(String command) {
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println(command);
        System.out.println("----------------------------------------------------------------------------------------");
    }

    /**
     * Saying bye to the user when the user decides to quit.
     * @param username The name of the user.
     */
    public static void bye(String username) {
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Bye " + username + "! Hope to see you again soon!");
        System.out.println("----------------------------------------------------------------------------------------");
    }

}

