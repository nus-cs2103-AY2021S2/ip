import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Haha {
    private static final String LINE_BREAK = "____________________________________________________________\n";
    private static final String LOGO =
            " _    _          _    _          \n" +
            "| |  | |   /\\   | |  | |   /\\    \n" +
            "| |__| |  /  \\  | |__| |  /  \\   \n" +
            "|  __  | / /\\ \\ |  __  | / /\\ \\  \n" +
            "| |  | |/ ____ \\| |  | |/ ____ \\ \n"+
            "|_|  |_/_/    \\_\\_|  |_/_/    \\_\\\n";

    private static final String STARTER = "Hello from\n" + LOGO
            + LINE_BREAK
            + "Dude, I'm HAHA\n"
            + "What can I do for you?\n"
            + "(Oh when you are done, say bye)\n"
            + LINE_BREAK;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(STARTER);

        List<String> database = new ArrayList<>();


        while (sc.hasNext()) {
            String command = sc.nextLine();
            System.out.println(LINE_BREAK);

            if (command.equals("bye")) {
                System.out.println("Bye now!");
                System.out.println(LINE_BREAK);
                break;
            } else if (command.equals("list")) {
                System.out.println("Here are your list of tasks:");
                for (int i = 0; i < database.size(); i++) {
                    String idx = Integer.toString(i + 1) + '.';
                    String task = idx + database.get(i);
                    System.out.println(task);
                }
                System.out.println(LINE_BREAK);
            } else {
                database.add(command);
                System.out.print("Added: ");
                System.out.println(command);
                System.out.println(LINE_BREAK);
            }
        }
    }
}
