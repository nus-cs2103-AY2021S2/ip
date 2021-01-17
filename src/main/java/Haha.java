import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Haha {
    private static final String LINE_BREAK = "____________________________________________________________\n";
    private static final String LOGO = " _    _          _    _          \n" +
            "| |  | |   /\\   | |  | |   /\\    \n" +
            "| |__| |  /  \\  | |__| |  /  \\   \n" +
            "|  __  | / /\\ \\ |  __  | / /\\ \\  \n" +
            "| |  | |/ ____ \\| |  | |/ ____ \\ \n" +
            "|_|  |_/_/    \\_\\_|  |_/_/    \\_\\\n";
    private static final String STARTER = "Hello from\n" + LOGO
            + LINE_BREAK
            + "Dude, I'm HAHA.\n"
            + "What can I do for you?\n"
            + "(Oh when you are done, say bye)\n"
            + LINE_BREAK;

    private static int taskNumber(String command) { // for "done" command
        return Integer.parseInt("" + command.charAt(command.length() - 1));
    }

    private static void tellAdd() {
        System.out.println("Got it. I've added this task:");
    }

    private static void tellSize(List<Task> database) {
        String task = database.size() > 1 ? " tasks" : " task";
        System.out.println("Now you have " + database.size() + task + " in the list");
    }

    private static void addToDB(List<Task> database, Task task) {
        database.add(task);
        tellAdd();
        System.out.println("  " + database.get(database.size() - 1));
        tellSize(database);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println(STARTER);

        List<Task> database = new ArrayList<>();

        while (sc.hasNext()) {
            String command = sc.nextLine();
            System.out.println(LINE_BREAK);

            if (command.equals("bye")) {
                System.out.println("Bye now!");
                break;
            } else if (command.equals("list") || command.equals("ls") ) {
                if (database.size() == 0) {
                    System.out.println("You have nothing going on!");
                } else {
                    System.out.println("Here are your list of tasks:");
                    for (int i = 0; i < database.size(); i++) {
                        String idx = Integer.toString(i + 1) + '.';
                        String task = idx + database.get(i);
                        System.out.println(task);
                    }
                }
            } else if (command.startsWith("done")) {
                // might blow up, solve at Level-5
                // done when it is already done,
                // task number out of bound
                // undone and done ?
                Task currentTask = database.get(taskNumber(command) - 1);
                System.out.println("Nice! I've marked this task as done:");
                currentTask.setDone(true);
                System.out.println(currentTask);

            } else if (command.startsWith("todo")) {
                addToDB(database, new Todo(false, command.substring(5)));

            } else if (command.startsWith("deadline")) {
                addToDB(database, new Deadline(false, command.substring(9)));

            } else if (command.startsWith("event")) {
                addToDB(database, new Event(false, command.substring(6)));

            } else {
                System.out.println("Command not recognized!");
            }
            System.out.println(LINE_BREAK);
        }
    }

}
