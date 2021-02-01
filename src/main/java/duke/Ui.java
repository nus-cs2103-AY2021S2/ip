package duke;

import duke.Task;
import duke.TaskList;
import duke.ToDo;
import duke.Deadline;
import duke.Event;
import duke.Duke;


public class Ui {
    private static final String HORIZONTAL_RULE = "____________________________________________________________";

    public void printWelcomeGreeting() {
        System.out.println(HORIZONTAL_RULE + "\nHello! I am Duke\n" + "What can I do for you?\n" + HORIZONTAL_RULE);
    }

    public void printExitMessage() {
        System.out.println("GoodBye. Hope to see you again soon!\n" + HORIZONTAL_RULE);
    }

    public void printHorizontalRule() {
        System.out.println(HORIZONTAL_RULE);
    }

    public void printTaskList(TaskList userList) {
        for (int i = 0; i < userList.getTaskListSize(); i++) {
            System.out.println(i + 1 + "." + userList.getTask(i).toString());
        }
        System.out.println(HORIZONTAL_RULE);
    }

    public void printDoneTask(TaskList userTaskList, int taskNumber) {
        Task doneTask = userTaskList.getTask(taskNumber - 1);
        doneTask.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[" + doneTask.getStatusIcon() + "] " + doneTask.getTaskDetail());
        System.out.println(HORIZONTAL_RULE);
    }

    public void printAddedTask(TaskList userTaskList, Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        userTaskList.addTask(task);
        System.out.println("Now you have " + (userTaskList.getTaskListSize()) + " tasks in the list.");
        System.out.println(HORIZONTAL_RULE);
    }

    public static void printDeletedTask(TaskList userTaskList, int taskNumber) {
        Task taskToBeDeleted = userTaskList.removeTask(taskNumber - 1);
        System.out.println("Noted. I've removed this task");
        System.out.println(taskToBeDeleted);
        System.out.println("Now you have " + (userTaskList.getTaskListSize()) + " tasks in the list.");
        System.out.println(HORIZONTAL_RULE);
    }

}
