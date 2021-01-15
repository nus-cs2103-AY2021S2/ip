import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println(makeOutput("Hello! I'm Duke\n\t What can I do for you?"));

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            System.out.println(makeOutput(input));
            input = sc.nextLine();
        }
        System.out.println(makeOutput("Bye. Hope to see you again soon!"));
    }

    public static String makeOutput(String input) {
        return "\t____________________________________________________________"
            + "\n\t " + input
            + "\n\t____________________________________________________________";
    }

}
