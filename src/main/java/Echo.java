import java.util.*;

public class Echo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        while (sc.hasNext()) {
            String input = sc.nextLine();

            if (!input.equals("bye")) {
                System.out.println(input);
            } else {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }


        }
    }
}
