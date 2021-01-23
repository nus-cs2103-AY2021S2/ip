package duke;

import duke.task.Task;

import java.util.List;

public class Ui {
    private static final String BORDER = "___________________________________________________________";

    public Ui() {

    }

    public void printMsg(String msg) {
        System.out.println(BORDER);
        System.out.println(msg);
        System.out.println(BORDER + "\n");
    }

    public void printWelcomeMsg(String botName) {
        printMsg(String.format("Meow, I'm %s\nWhat can I do for you today?", botName));
    }

    public void printGoodbyeMsg() {
        printMsg("Meow. Hope to see you again soon!");
    }

    public void printAddMsg(Task task, int tasksSize) {
        System.out.println(BORDER);
        System.out.println("Got it meow. I've added this task:");
        System.out.printf("  [%s][%s] %s\n", task.getTypeSymbol(), task.getStatusSymbol(), task.getDesc());
        System.out.printf("Now you have %d tasks in the list.\n", tasksSize);
        System.out.println(BORDER + "\n");
    }

    public void printDoneMsg(int index, Task task) {
        System.out.println(BORDER);
        System.out.println("Good job meow, I've marked this task as done:");
        System.out.printf("%d.[%s][%s] %s\n", index + 1, task.getTypeSymbol(), task.getStatusSymbol(),
                task.getDesc());
        System.out.println(BORDER + "\n");
    }

    public void printDeleteMsg(Task task, int tasksSize) {
        System.out.println("___________________________________________________________");
        System.out.println("Noted meow. I've removed this task:");
        System.out.printf("  [%s][%s] %s\n", task.getTypeSymbol(), task.getStatusSymbol(),
                task.getDesc());
        System.out.printf("Now you have %d tasks in the list.\n", tasksSize);
        System.out.println("___________________________________________________________\n");
    }

    public void printTaskList(List<Task> tasks) {
        System.out.println(BORDER);
        System.out.println("Meow, here are the tasks in your list:");
        for(int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.printf("%d.[%s][%s] %s\n", i + 1, task.getTypeSymbol(), task.getStatusSymbol(), task.getDesc());
        }
        System.out.println(BORDER + "\n");
    }

    public void printError(String msg) {
        printMsg(String.format("ERROR MEOW! %s", msg));
    }
}
