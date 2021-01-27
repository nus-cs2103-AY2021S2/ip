import java.util.ArrayList;

public class Ui {
    private static final String SPACE = "     ";
    private static final String LINE = "\n     <<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>>\n";

    Ui() {}

    public static void greet() {
        System.out.println(LINE + SPACE + "Heyyoo!! I am Luna :D\n" + SPACE + "What can I do for you today?" + LINE);
    }

    public static String  InvalidNumberExceptionMessage() {
        return LINE + SPACE + "ERROR! D: Please give a number greater than zero and smaller than the total number of tasks" + LINE;
    }

    public static String  InvalidTaskFormatExceptionMessage(String task) {
        return LINE + SPACE + "ERROR! D: The format for the following task is wrong: " + task + LINE;
    }

    public static void outputMessageTask(String keyword, Task task) {
        System.out.println(LINE + SPACE + "Done adding the " + keyword + " Task: " + task + LINE);
    }

    public static void outputMessageDone(Task task) {
        System.out.println(LINE + SPACE + "Good job! Another Task completed! I have marked it as done:\n" + SPACE + task + LINE);
    }

    public static void outputMessageDelete(Task task) {
        System.out.println(LINE + SPACE + "Deleted the following task: " + task + LINE);
    }

    public static void outputMessageList(ArrayList<Task> storage, int count) {
        System.out.println(LINE);
        for (int i = 1; i <= count; i++) {
            System.out.println(SPACE + i + ". " + storage.get(i-1));
        }
        System.out.println(LINE);
    }

    public static void outputMessageFind(ArrayList<Task> storage, String[] spl) {
        System.out.println(LINE);
        int i = 1;
        for (Task t : storage) {
            if (t.getDescription().contains(spl[1])) {
                System.out.println(SPACE + i + ". " + t);
                i++;
            }
        }
        System.out.println(LINE);
    }

    public static void outputMessageBye() {
        System.out.println(LINE + SPACE + "Byee, hope to see you again soon!" + LINE);
    }
}
