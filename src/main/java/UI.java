import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class UI {
    private static final String DIVIDER = "____________________________________________________________";
    private static final String WELCOME_MESSAGE =  " ____        _        \n" + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n" + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n"
            + "\nHello! I'm Duke\nWhat can I do for you today?";
    private static final String GOODBYE_MESSAGE = "Good bye! Stay calm and keep coding o7";
    private static final String LS = System.lineSeparator();
    private final Scanner in;
    private boolean isExit;

    public UI() {
        this.in = new Scanner(System.in);
        this.isExit = false;
    }

    public void WelcomeMessage() {
        showToUser(DIVIDER + LS + WELCOME_MESSAGE);
    }

    public void GoodByeMessage() {
        showToUser(GOODBYE_MESSAGE + LS + DIVIDER);
    }

    public void commandMessage(String[] commandAndParams, DukeList list) {
        switch(commandAndParams[0]) {
        case "done":
            doneMessage();
            showNoOfTaskLeft(list);
            break;
        case "delete":
            deleteMessage();
            showNoOfTaskLeft(list);
            break;
        case "reset":
            resetMessage();
            break;
        case "show":
            showMessage(list);
            break;
        case "todo":
        case "event":
        case "deadline":
            addTaskMessage();
            showNoOfTaskLeft(list);
            break;
        case "list":
            listMessage(list);
            break;
        case "bye":
            isExit = true;
            break;
        case "unknown":
            unknownCommandMessage();
            break;
        }
    }

    public void listMessage(DukeList list) {
        int size = list.getSize();
        showToUser("Here are your task!");
        for (int i = 0; i < size; i++) {
            showToUser((i + 1) + "." + list.get(i));
        }
    }

    public void doneMessage() {
        showToUser("Good job! I have marked this task as done");
    }

    public void deleteMessage() {
        showToUser("Got it! The task has been deleted");
    }

    public void resetMessage() {
        showToUser("Got it! All tasks have been deleted");
    }

    public void showMessage(DukeList list) {
        LocalDate day = list.getDate();
        int counter = 1;
        showToUser("Here are your task on " + day.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        for (int i = 0; i < list.getSize(); i++) {
            Task curr = list.get(i);
            if (curr instanceof Deadlines) {
                if (((Deadlines) curr).getBy().equals(day)) {
                    showToUser(counter + "." + curr);
                    counter++;
                }
            } else if (curr instanceof Events) {
                if (((Events) curr).getDuration().equals(day)) {
                    showToUser(counter + "." + curr);
                    counter++;
                }
            }
        }
    }

    public void addTaskMessage() {
        showToUser("Got it! The task has been added");
    }

    public void showNoOfTaskLeft(DukeList list) {
        showToUser("You have " + list.getSize() + " Task left in the list");
    }

    public void unknownCommandMessage() {
        showToUser("I'm sorry, I do not know what that means");
    }

    public String readCommand() {
        return in.nextLine();
    }

    public void showDivider() {
        showToUser(DIVIDER + LS);
    }

    public void showMissingArgsError() {
        showToUser("Error! Your command has missing argument(s)!\nTry Again!");
    }

    public void showWrongArgsError() {
        showToUser("Error! Your command has invalid arguement(s)!\nTry Again!");
    }
    public void showToUser(String message) {
        System.out.println(message);
    }
    public boolean getIsExit() {
        return isExit;
    }
}
