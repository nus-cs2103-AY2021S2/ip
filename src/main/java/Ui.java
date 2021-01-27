import java.util.ArrayList;

public class Ui {
    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static String line = "____________________________________________"+
            "________________\n";

    public static String lineGetter() {
        return line;
    }

    public static String intro() {
        return "Hello from\n" + logo + "\n" + line + " Hey there! I'm Duke\n" +
                " How can I help you?\n" + line;
    }

    public static String bye() {

        return line + " Bye, see you again!\n" + line;
    }

    public static String onlySpaces() {

        return line + " Please enter a task\n" + line;
    }

    public static String printList(ArrayList<Task> tasks) {
        String s = "";
        if(tasks.size() < 1) {
            throw new IllegalArgumentException(line + " No tasks so far!\n" + line);
        }
        s = s + line + " Here are the tasks in your list:\n";
        for (int j = 1; j <= tasks.size(); j++) {
            s = s + " " + j + ". " + tasks.get(j - 1) + "\n";
        }
        return s + line;
    }

    public static String illegalArgExc() {
        return line + " Please enter 'todo (your task)', " +
                "or 'deadline (your task) / (deadline date time)',\n or " +
                "'event (event name) / (event date time)' to add tasks.\n " +
                "To see your tasks enter 'list'.\n To complete a task enter " +
                "'done (number of the task in the list)'.\n And to close Duke " +
                "enter 'bye'.\n"+ line;
    }

    public static String dateTimeParseExc() {
        return line + " Enter date and time in this format yyyy-mm-dd hh:mm\n" + line;
    }

    public static String unableSave() {
        return line + " Unable to save to hard drive\n" + line;
    }

    public static String addTask(Task task, int size) {
        return line + " Got it. I've added this task:\n" + " " + task + "\n"
                + " Now you have " + size + " tasks in the list\n" + line;
    }

    public static String deleteTask(Task task, int size) {
        return line + " I've removed the following task:\n" + " " + task
                + "\n" + " You now have "+ size + " tasks in the list\n" + line;
    }

    public static String doneTask(Task task) {
        return line + " Good job on completing this task!\n" + " " +
                task + "\n" + line;
    }
}
