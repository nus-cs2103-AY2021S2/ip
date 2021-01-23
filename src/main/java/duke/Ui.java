package duke;

import duke.task.Task;

import java.util.ArrayList;

public class Ui {
    private static final StringBuffer boundOfChatBox = new StringBuffer();

    public static void setBoundOfChatBox() {
        boundOfChatBox.append('\n');
        int lenOfChatBox = 50;
        boundOfChatBox.append("-".repeat(lenOfChatBox));
        boundOfChatBox.append('\n');
    }

    public static void formatInChatBox(String s) {
        System.out.println(boundOfChatBox + s + boundOfChatBox);
    }

    public static void showInitUi() {
        setBoundOfChatBox();
        String logo = "    __      __      ____ \n" +
                "   /  \\    /  \\    / __ \\\n" +
                "  / /\\ \\  / /\\ \\  | |  | |\n" +
                " / /  \\ \\/ /  \\ \\ | |__| |\n" +
                "/_/    \\__/    \\_\\ \\____/";
        String greeting = "Hello! I'm Momo\nWhat can I do for you?";
        formatInChatBox(logo);
        formatInChatBox(greeting + '\n');
    }

    public static void showExitUi() {
        String goodbye = "Bye. Hope to see you again soon!\n";
        formatInChatBox(goodbye);
    }

    public static void showList(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        int n = tasks.size();
        if (n == 0) {
            formatInChatBox("There is no task\n");
            return ;
        }
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < n; i++)
            buf.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        String res = new String(buf);
        formatInChatBox("Here are the tasks in your list:\n" + res);
    }

    public static void showMatchingResult(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        int n = tasks.size();
        if (n == 0) {
            formatInChatBox("There is no matching task\n");
            return ;
        }
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < n; i++)
            buf.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        String res = new String(buf);
        formatInChatBox("Here are the matching tasks in your list:\n" + res);
    }

    public static void showSuccessfulAdd(TaskList taskList, Task task) {
        formatInChatBox("Got it. I've added this task:" + '\n'
                + task + "\n"
                + "Now you have " + taskList.getTasks().size() + " tasks in the list.\n");
    }

    public static void showSuccessfulMark(Task task) {
        formatInChatBox("Nice! I've marked this duke.task as done:\n" + task + "\n");
    }

    public static void showSuccessfulDelete(TaskList taskList, Task task) {
        formatInChatBox("Got it. I've removed this duke.task:" + '\n'
                + task + "\n"
                + "Now you have " + taskList.getTasks().size() + " tasks in the list.\n");
    }

    public static void showDateParseError() {
        formatInChatBox("OOPS!!! Please use '/by YYYY-MM-DD' after description.\n");
    }

    public static void showGeneralError() {
        try {
            throw new ParseException("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        } catch (ParseException e) {
            formatInChatBox(e.getMsgDes());
        }
    }

    public static void showLoadingError() {
        formatInChatBox("OOPS!!! Something wrong happens when loading.\n");
    }

    public static void showIndexOutOfBoundsError(TaskList taskList) {
        formatInChatBox("OOPS! Now you have only " + taskList.getTasks().size() + "tasks, please mark/delete the added tasks.");
    }
}
