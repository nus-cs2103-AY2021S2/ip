package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

public class Ui {
    private static final String ADD_TASK_ACK = "Got it. I've added this task:";

    public String formatString(String... msg) {
        String outputString = "";
        for (String m : msg) {
            outputString += m + "\n";
        }
        return outputString;
    }

    public String getGreeetingMsg() {
        return formatString("Hello from Hayate! How may I help you?");
    }

    public String getError(String msg) {
        return formatString(msg);
    }

    public String getAddTaskAck(Task t, TaskList tasks) {
        String taskCountMsg = "You now have " + tasks.getTasksSize() + " task(s) in the list.";
        return formatString(ADD_TASK_ACK, " " + t.toString(), taskCountMsg);
    }

    public String getExitMsg() {
        return formatString("Bye. Hope to see you again soon!");
    }

    public String printTaskList(TaskList tasks) {
        String[] msg = new String[tasks.getTasksSize()];
        for (int i = 0; i < tasks.getTasksSize(); i++) {
            msg[i] = (i + 1) + ". " + tasks.getTask(i + 1);
        }
        return formatString(msg);
    }

    public String getDoneMsg(Task task) {
        String[] msg = new String[2];
        msg[0] = "Nice! I've marked this task as done.";
        msg[1] = task.toString();
        return formatString(msg);
    }

    public String getDeleteMsg(Task task, TaskList tasks) {
        String[] msg = new String[3];
        msg[0] = "Noted. I've removed this task.";
        msg[1] = " " + task.toString();
        msg[2] = "Now you have " + tasks.getTasksSize() +
                " task(s) in the list.";
        return formatString(msg);
    }

    public void printError(String msg) {
        formatString(msg);
    }

    public String getUpdatedTaskMsg(Task task, TaskList tasks) {
        String[] msg = new String[2];
        msg[0] = "Noted. I've updated this task.";
        msg[1] = " " + task.toString();
        return formatString(msg);
    }
}
