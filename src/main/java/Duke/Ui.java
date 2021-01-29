package duke;

public class Ui {
    private final String spacer = "----------------------------------------------------";

    public void printSpacer() {
        System.out.println(spacer);
    }

    public void printGreeting() {
        printSpacer();
        System.out.println("Hello my name is Mr. C!" +
                "\nYou may type the command 'help' to see my list of commands." +
                "\nHow may I assist you?");
        printSpacer();
    }

    public void printBye() {
        printSpacer();
        System.out.println("Farewell sir/ma'am. I hope to see you again soon.");
        printSpacer();
    }

    public void printTask(Task task) {
        System.out.println(task);
    }

    public void printAddTask(Task task, int size) {
        printSpacer();
        System.out.println("I have added the following task to your list:");
        printTask(task);
        System.out.println(String.format("There are %d tasks in your list. I hope this pleases you.", size));
        printSpacer();
    }

    public void printDoneTask(Task task) {
        printSpacer();
        System.out.println("Congratulations on conquering this task:");
        printTask(task);
        System.out.println("You are one step closer to victory");
        printSpacer();
    }

    public void printDeleteTask(Task task) {
        printSpacer();
        System.out.println("I have removed the following task from your list:");
        printTask(task);
        System.out.println("Does this bring you the satisfaction you so crave?");
        printSpacer();
    }

    public void printTaskList(TaskList taskList) {
        printSpacer();
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            System.out.println(String.format("%d. ", i) + taskList.getTask(i - 1).toString());
        }
        System.out.println("\nDo what you will with this information.");
        printSpacer();
    }

    public void printError(DukeException e) {
        printSpacer();
        System.out.println(e);
        printSpacer();
    }

    public void printHelp() {
        printSpacer();
        System.out.println("These are the following commands that I understand:");
        System.out.println("'help' (shows the list of commands)");
        System.out.println("'list' (shows the list of tasks you have on your list)");
        System.out.println("'todo (task description)' (adds a To Do task to your list)");
        System.out.println("'deadline (task description) /by (DD/MM/YYYY TIME)' (adds a Deadline task to your list)");
        System.out.println("'event (task description) /from (DD/MM/YYYY TIME) /to (DD/MM/YYYY TIME)' (adds an Event task to your list)");
        System.out.println("'done (valid index)' (checks a task on your list)");
        System.out.println("'delete (valid index)' (removes a task from your list)");
        System.out.println("Do omit the parentheses in the actual command.");
        System.out.println("I pray that I will be of use to you.");
        printSpacer();
    }

    public void printFoundTaskList(TaskList taskList) {
        printSpacer();
        System.out.println("Here are the tasks that fit your search criteria:");
        for (int i = 1; i <= taskList.size(); i++) {
            System.out.println(String.format("%d. ", i) + taskList.getTask(i - 1).toString());
        }
        System.out.println("I can only pray that I have been of use to you.");
        printSpacer();
    }
}
