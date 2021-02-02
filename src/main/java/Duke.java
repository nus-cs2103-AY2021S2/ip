import java.util.Scanner;

public class Duke {

    /**
     * main method to run the program
     *
     * @param args command line arguments taken in
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\n"+ "What can I do for you?");

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                bye();
                break;
            } else {
                echo(input);
            }
        }
    }

    /**
     * Echos the input message.
     *
     * @param msg the user input to be echoed
     */
    public static void echo(String msg) {
        System.out.println(msg);
    }

    /**
     * prints Goodbye message.
     */
    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
