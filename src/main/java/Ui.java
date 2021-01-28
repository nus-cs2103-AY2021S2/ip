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

    public static void printError(String error) {
        System.out.print(LINE + erroer + LINE);
    }

    public String readLine() {
        return sc.nextLine();
    }

    public void printGreeting() {
        String greeting = "     Hello! I'm Duke\n" + "     What can I do for you?\n";
        System.out.print(LINE + greeting + LINE);
    }

    public void printBye() {
        String byeMessage = "     Bye. Hope to see you again soon!\n";
        System.out.print(LINE + byeMessage + LINE);
    }

    public void printTasks(TaskList tl) {
        int index = 1;
        System.out.print(LINE);
        for (Task t : taskList) {
            System.out.print(String.format("     %d. %s\n",
                    index++, t.toString()));
        }
        System.out.print(LINE);
    }

    private String taskListMessage(int numOfTasks) {
        return String.format("     Now you have %d task(s) in the list\n", numOfTasks);
    }

    public void printAddedTask(Task t) {
        String addMsg = "     Got it. I've added this task:\n";
        String taskMsg = "\t" + t.toString() + "\n";
        System.our.print(LINE + addMsg + taskMsg + LINE);
    }

    public void printMarkedDone(Task t) {
        String doneMsg = "     Nice! I've marked this task as done:\n";
        String taskMsg = "\t" + t.toString() + "\n";
        System.our.print(LINE + doneMsg + taskMsg + LINE);
    }

    public void printDeletedTask(Task t) {
        String deleteMsg = "     Noted. I've removed this task:\n";
        String taskMsg = "\t" + t.toString() + "\n";
        System.our.print(LINE + deleteMsg + taskMsg + LINE);
    }
}