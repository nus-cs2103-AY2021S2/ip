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

        List<Task> database = new ArrayList<>();


        while (sc.hasNext()) {
            String command = sc.nextLine();
            System.out.println(LINE_BREAK);

            if (command.equals("bye")) {
                System.out.println("Bye now!");
                System.out.println(LINE_BREAK);
                break;
            } else if (command.equals("list") || command.equals("ls") ) {
                if (database.size() == 0) {
                    System.out.println("You have nothing going on!");
                } else {
                    System.out.println("Here are your list of tasks:");
                    for (int i = 0; i < database.size(); i++) {
                        String idx = Integer.toString(i + 1) + '.';
                        String task = idx + database.get(i).getDescription();
                        System.out.println(task);
                    }
                }

                System.out.println(LINE_BREAK);
            } else if (command.startsWith("done")) {
                // might blow up
                // done when it is already done,
                // task number out of bound
                // undone and done ?
                Task currentTask = database.get(taskNumber(command) - 1);
                System.out.println("Nice! I've marked this task as done:");
                currentTask.setDone(true);
                System.out.println(currentTask.getDescription());
                System.out.println(LINE_BREAK);
            }else {
                database.add(new Task(false, command));
                System.out.print("Added: ");
                System.out.println(command);
                System.out.println(LINE_BREAK);
            }
        }
    }

    private static int taskNumber(String command) { // for "done" command
        return Integer.parseInt("" + command.charAt(command.length() - 1));
    }
}
