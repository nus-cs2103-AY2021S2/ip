package duke;

import java.util.Scanner;

public class Ui {
    public static final String logo = " ____        _\n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String divider = "____________________________________________________________\n";
    private final Scanner in;
    public Ui() {
        in = new Scanner(System.in);
    }

    public void showInputError() {
        showMessage("I'm sorry, but I don't know what that means.");
    }

    public void showLoadingError() {
        showMessage("Failed to load file. Exiting...");
    }

    public void showSavingError() {
        showMessage("Failed to save file. Exiting...");
    }

    public void showWelcome() {
        showMessage(logo + "\nHello! I'm Duke!\n" +
                "What can I do for you?\n" +
                "Type \"help\" to view the list of available commands.");
    }

    public void showGoodBye() {
        showMessage("Bye. Hope to see you again soon!");
    }

    public void showHelpMessage() {
        showMessage("List of Available Commands:\n" +
                "bye\t\t\t\t\t\t\t\t\t->\tTo exit application\n" +
                "list\t\t\t\t\t\t\t\t->\tTo list tasks\n" +
                "done <index>\t\t\t\t\t\t->\tTo mark task as done \n" +
                "delete <index>\t\t\t\t\t\t->\tTo delete task\n" +
                "todo <description>\t\t\t\t\t->\tTo create todo\n" +
                "deadline <description> <date>\t\t->\tTo create deadline\n" +
                "event <description> <date/time>\t\t->\tTo create event");
    }

    public void showTasks(TaskList tasks) {
        StringBuilder message = new StringBuilder();
        if(tasks.isEmpty()) {
            message.append("List is empty.");
        } else {
            message.append("Here are your list of tasks:\n");
            for(int i = 1; i <= tasks.getSize(); i++) {
                message.append("  " + i + ". " + tasks.getTask(i - 1));
                if(i < tasks.getSize()) {
                    message.append("\n");
                }
            }
        }
        showMessage(message.toString());
    }

    public void showMessage(String message) {
        System.out.println(divider + message + "\n" + divider);
    }

    public void showErrorMessage(String errorMessage) {
        showMessage("Error! " + errorMessage);
    }
}
