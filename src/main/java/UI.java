import java.util.Scanner;

public class UI {
    private static final String DIVIDER = "____________________________________________________________";
    private static final String WELCOME_MESSAGE =  " ____        _        \n" + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n" + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n"
            + "\nHello! I'm Duke\nWhat can I do for you today?";
    private static final String GOODBYE_MESSAGE = "Good bye! Stay calm and keep coding o7.";
    private static final String LS = System.lineSeparator();
    private final Scanner in;

    public UI() {
        this.in = new Scanner(System.in);
    }

    public void WelcomeMessage() {
        showToUser(DIVIDER + LS + WELCOME_MESSAGE + LS + DIVIDER);
    }

    public void GoodByeMessage() {
        showToUser(DIVIDER + LS + GOODBYE_MESSAGE + LS);
    }

    public void FailureMessage(String commandFailed) {
        //cases
    }
    public void commandMessage(String command) {
        switch(command) {
        case "done":

        }
    }

    public void ListMessage(DukeList list) {
        int size = list.getSize();
        showToUser(DIVIDER + LS);
        for (int i = 0; i < size; i++) {
            showToUser((i + 1) + "." + list.get(i));
        }
        showToUser(DIVIDER);
    }

    public void doneMessage() {
        showToUser(DIVIDER + LS + "Good job! I have marked this task as done" + LS + DIVIDER);
    }

    public void deleteMessage() {
        showToUser(DIVIDER + LS + "Got it! The task has been deleted" + LS + listSize() + DIVIDER);
    }

    public void resetMessage() {
        showToUser(DIVIDER + LS + "Got it! All tasks have been deleted" + LS + DIVIDER);
    }

    public void showMessage() {
        showToUser(DIVIDER + LS + "Here are your tasks on this day" + LS + DIVIDER);
    }

    public void addTaskMessage() {
        showToUser((DIVIDER + LS + "Got it! The task has been added" + LS + DIVIDER));
    }

    public String listSize(DukeList list) {
        return String.valueOf(list.getSize());
    }

    public String readCommand() {
        return in.nextLine(); //parser will break down this command
    }

    public void showToUser(String... message) {
        System.out.println(message);
    }
}
