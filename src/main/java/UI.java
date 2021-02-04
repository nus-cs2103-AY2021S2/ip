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
    public void commandMessage(String command, DukeList list) {
        switch(command) {
        case "done":
            showMessage();
            showNoOfTaskLeft(list);
        case "delete":
            deleteMessage();
            showNoOfTaskLeft(list);
        case "reset":
            resetMessage();
        case "todo":
            addTaskMessage();
            showNoOfTaskLeft(list);
        case "deadline":
            addTaskMessage();
            showNoOfTaskLeft(list);
        case "event":
            addTaskMessage();
            showNoOfTaskLeft(list);
        case "list":
            listMessage(list);
        case "bye":
            GoodByeMessage();
        case "unknown":
            unknownCommandMessage();
        }
    }

    public void listMessage(DukeList list) {
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
        showToUser(DIVIDER + LS + "Got it! The task has been deleted" + LS  + DIVIDER);
    }

    public void resetMessage() {
        showToUser(DIVIDER + LS + "Got it! All tasks have been deleted" + LS + DIVIDER);
    }

    public void showMessage() {
        showToUser(DIVIDER + LS + "Here are your tasks on this day" + LS  + DIVIDER);
    }

    public void addTaskMessage() {
        showToUser((DIVIDER + LS + "Got it! The task has been added" + LS + DIVIDER));
    }

    public void showNoOfTaskLeft(DukeList list) {
        showToUser(DIVIDER + LS + "You have " + list.getSize() + " Task left." + LS + DIVIDER);
    }

    public void unknownCommandMessage() {
        showToUser(DIVIDER + LS + "I'm sorry, I do not know what that means" + LS + DIVIDER);
    }
    public String readCommand() {
        return in.nextLine(); //parser will break down this command
    }

    public void showToUser(String message) {
        System.out.println(message);
    }
}
