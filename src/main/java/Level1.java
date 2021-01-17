import java.util.Scanner;

public class Level1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("    _________________________________________________");
        System.out.println("     Hello! I'm Duke :)");
        System.out.println("     What can I do for you?");
        System.out.println("    _________________________________________________");

        while (sc.hasNext()) {
            String input = sc.next();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("    _________________________________________________");
                System.out.println("     Bye. Hope to see you again soon! :)");
                System.out.println("    _________________________________________________");
                break;
            } else {
                System.out.println("    _________________________________________________");
                System.out.println("     " + input);
                System.out.println("    _________________________________________________");
                System.out.println();
            }
        }
    }
}
