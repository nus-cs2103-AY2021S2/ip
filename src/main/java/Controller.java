import java.util.Scanner;

public class Controller {
    private final static String LINE = "\t__________________________________\n";
    private final static String GREETING = "\t Hello! I'm Duke\n\t What can I do for you? \n";
    private final static String ENDDUKE = "\t Bye. Hope to see you again soon!\n";

    public void run() {
        String startMsg = LINE + GREETING + LINE;
        System.out.println(startMsg);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while(!input.equals("bye")) {
            System.out.print(LINE);
            System.out.println("\t " + input);
            System.out.print(LINE);
            input = sc.nextLine();
        }

        System.out.println(LINE + ENDDUKE + LINE);
    }
}
