import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    /**
     * main method to run the program
     *
     * @param args command line arguments taken in
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\n"+ "What can I do for you?");
        ArrayList<String> items = new ArrayList<>();

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                bye();
                break;
            } else if (input.equals("list")) {
                int n = 1;
                for (String item : items) {
                    System.out.println( n + ". " + item);
                    n++;
                }
            } else {
                items.add(input);
            }
        }
    }

    /**
     * prints Goodbye message.
     */
    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
