import java.util.List;

public class Ui {
    private static final String INDENTATION = "    ";
    private static final String REPLY_OUTLINE = INDENTATION + "____________________________________________________________";

    public Ui() {}

    public String formatLine(String line) {
        return INDENTATION + line + "\n";
    }

    public void reply(String msg) {
        System.out.println(REPLY_OUTLINE + "\n" + msg + REPLY_OUTLINE + "\n");
    }

    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String msg = formatLine("Hello! I'm Duke")
                + formatLine("What can I do for you?");
        reply(msg);
    }

    public void list(List<Task> tasks) {
        String msg = formatLine("Here are the tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            msg += formatLine((i + 1) + ". " + tasks.get(i));            
        }
        reply(msg);
    }

    public void exit() {
        reply(formatLine("Bye. Hope to see you again soon!"));
    }

    public void addedTaskReply(Task task, int numTasks) {
        String msg = formatLine("Got it. I've added this task:")
                + formatLine("  " + task)
                + formatLine("Now you have " + numTasks + " tasks in the list.");
        reply(msg);
    }

    public void markDoneReply(Task task) {
        String msg = formatLine("Nice! I've marked this task as done:")
                + formatLine("  " + task);
        reply(msg);
    }

    public void deleteReply(Task deletedTask, int numTasks) {
        String msg = formatLine("Noted. I've removed this task:")
                + formatLine("  " + deletedTask)
                + formatLine("Now you have " + numTasks + " tasks in the list.");
        reply(msg);
    }
}
