import java.util.Scanner;

public class Ui {
    private final String LINE = "    ____________________________________________________________\n";
    private final String HELP = LINE + "     These are the proper format of Duke's commands:\n"
            + "      -help\n"
            + "      -todo (insert task name here)\n"
            + "      -deadline (insert task name here) /by (insert time here)\n"
            + "      -event (insert event name here) /at (insert time here)\n"
            + "      eg: 'deadline typing /by 1pm'\n"
            + LINE;
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public void printGreeting() {
        String greeting = "     Hello! I'm Duke\n" + "     What can I do for you?\n";
        System.out.print(LINE + greeting + LINE);
    }

    public void printBye() {
        String byeMessage = "     Bye. Hope to see you again soon!\n";
        System.out.print(LINE + byeMessage + LINE);
    }

    private String taskMessage(int numOfTasks) {
        return String.format("     Now you have %d task(s) in the list\n", numOfTasks);
    }

    public void printAddedTask() {
        String message = "     Got it. I've added this task:\n";
    }

    public void printMarkedDone() {
        String message = "     Nice! I've marked this task as done:\n";
    }

    public void printDeletedTask() {
        String message = "     Got it. I've added this task:\n";
    }
}