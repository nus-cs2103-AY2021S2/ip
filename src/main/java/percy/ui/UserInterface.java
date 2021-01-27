package percy.ui;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.PrintStream;
import java.io.InputStream;

import percy.task.TaskList;
import static percy.messages.Messages.*;
import percy.task.Task;

public class UserInterface {
    private static Scanner in;
    private static PrintStream out;

    private static final String INDENT = "    ";
    public static final String DIVIDER = "    ____________________________________________________________\n";

    public UserInterface(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public UserInterface() {
        this(System.in, System.out);
    }

    public static String readCommand() {
        String command = "";
        while(command.trim().isEmpty()) {
            command = in.nextLine();
        }
        return command;
    }

    /*
    public void Divider() {
        out.println(DIVIDER); // PrintStream class triggers println method
    }
    */

    public static void printStartUpMsg() {
        System.out.println(MESSAGE_LOGO + "\n" + DIVIDER
                + MESSAGE_WELCOME + "\n"
                + DIVIDER);
    }

    public static String makeStartUpMsg() {
        return MESSAGE_LOGO + "\n" + DIVIDER
                + MESSAGE_WELCOME + "\n"
                + DIVIDER;
    }

    public static String makeByeMsg() {
        return DIVIDER + INDENT + MESSAGE_GOODBYE + "\n" + DIVIDER;
    }

    public static String makeMsg(String s) {
        return DIVIDER + INDENT + s.toString() + "\n" + DIVIDER;
    }

    public static String makeMsg(ArrayList<String> stringArr) {
        String str = DIVIDER;
        for (String s: stringArr) {
            str += INDENT + s.toString() + "\n";
        }
        str += DIVIDER;
        return str;
    }

    public void showBlankLine() {
        out.println();
    }

    public static String makeListMsg(TaskList list) {
        String response;
        response =  DIVIDER + INDENT + "Here are the tasks in your list:\n";
        int i = 1;
        for (Task t : list.getTaskList()) {
            response += INDENT
                    + String.valueOf(i++) + ". "
                    +  t.toString() + "\n";
        }
        String taskString = (list.getTaskList().size() == 1) ? "task" : "tasks";
        response += INDENT + "Now you have " + list.getTaskList().size() + " " + taskString + " in the list.\n" + DIVIDER;
        return response;
    }

    public static String makeAddMsg(Task t, TaskList list) {
        return DIVIDER + INDENT + "Got it. I've added this task:\n"
                + INDENT + INDENT + t.toString() + "\n"
                + INDENT + "Now you have " + list.getTaskList().size() + " tasks in the list.\n"
                + DIVIDER;
    }
    public static String makeDeleteMsg(Task t, TaskList list) {
        return DIVIDER + INDENT + "Noted. I've removed this task:\n"
                + INDENT + INDENT + t.toString() + "\n"
                + INDENT + "Now you have " + list.getTaskList().size() + " tasks in the list.\n"
                + DIVIDER;
    }

    public void makeEchoMsg(String command) {
        System.out.println(DIVIDER);
        out.println(INDENT + command);
        System.out.println(DIVIDER);
        showBlankLine();
    }

    public static String makeDoneMsg(Task t) {
        return DIVIDER + INDENT + "Nice! I've marked this task as done:\n"
                + INDENT + INDENT + t.toString() + "\n"
                + DIVIDER;
    }
}
