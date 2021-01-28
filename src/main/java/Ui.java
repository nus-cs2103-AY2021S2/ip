import java.util.ArrayList;

public class Ui {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String LINE = "____________________________________________"
            + "________________\n";

    public static String lineGetter() {
        return LINE;
    }

    public static String introduce() {
        return "Hello from\n" + LOGO + "\n" + LINE + " Hey there! I'm Duke\n"
                + " How can I help you?\n" + LINE;
    }

    public static String exit() {
        return LINE + " Bye, see you again!\n" + LINE;
    }

    public static String informOnlySpaces() {
        return LINE + " Please enter a task\n" + LINE;
    }

    public static String printList(ArrayList<Task> tasks) {
        String list = "";
        if (tasks.size() < 1) {
            throw new IllegalArgumentException(LINE + " No tasks so far!\n" + LINE);
        }
        list = list + LINE + " Here are the tasks in your list:\n";
        for (int j = 1; j <= tasks.size(); j++) {
            list = list + " " + j + ". " + tasks.get(j - 1) + "\n";
        }
        return list + LINE;
    }

    public static String informIllegalArgExc() {
        return LINE + " Please enter 'todo (your task)',\n "
                + "or 'deadline (your task) / (deadline date time)',\n or "
                + "'event (event name) / (event date time)' to add tasks.\n "
                + "To see your tasks enter 'list'.\n To complete a task enter "
                + "'done (number of the task in the list)'.\n And to close Duke "
                + "enter 'bye'.\n" + LINE;
    }

    public static String informDateTimeParseExc() {
        return LINE + " Enter date and time in this format yyyy-mm-dd hh:mm\n" + LINE;
    }

    public static String informUnableSave() {
        return LINE + " Unable to save to hard drive\n" + LINE;
    }

    public static String addTask(Task task, int size) {
        return LINE + " Got it. I've added this task:\n" + " " + task + "\n"
                + " Now you have " + size + " tasks in the list\n" + LINE;
    }

    public static String deleteTask(Task task, int size) {
        return LINE + " I've removed the following task:\n" + " " + task
                + "\n" + " You now have " + size + " tasks in the list\n" + LINE;
    }

    public static String doneTask(Task task) {
        return LINE + " Good job on completing this task!\n" + " "
                + task + "\n" + LINE;
    }
}
