import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke\n"
                + "What can I do for you?");

        Scanner scan = new Scanner(System.in);
        String string = scan.nextLine();

        while (!string.equals("bye")) {
            System.out.println(string);
            string = scan.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon");

    }

}
