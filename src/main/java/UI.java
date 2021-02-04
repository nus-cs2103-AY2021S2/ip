import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Deals with User interactions, reads user inputs and outputs text
 */
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

    /**
     * Output welcome message
     */
    public void WelcomeMessage() {
        showToUser(DIVIDER + LS + WELCOME_MESSAGE);
    }

    /**
     * Output goodbye message
     */
    public void GoodByeMessage() {
        showToUser(GOODBYE_MESSAGE + LS + DIVIDER);
    }

    /**
     * reads parsed commands to output appropriate response to user
     * @param command string command parsed by Parser
     * @param list DukeList object
     */
    public void commandMessage(String command, DukeList list) {
        switch(command) {
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

    /**
     * Output all tasks in DukeList object
     * @param list DukeList object
     */
    public void listMessage(DukeList list) {
        int size = list.getSize();
        showToUser("Here are your task!");
        for (int i = 0; i < size; i++) {
            showToUser((i + 1) + "." + list.get(i));
        }
    }

    /**
     * Output message for "done" command
     */
    public void doneMessage() {
        showToUser("Good job! I have marked this task as done");
    }

    /**
     * Output message for "delete" command
     */
    public void deleteMessage() {
        showToUser("Got it! The task has been deleted");
    }
    /**
     * Output message for "reset" command
     */
    public void resetMessage() {
        showToUser("Got it! All tasks have been deleted");
    }

    /**
     * Output message for "show command
     * @param list DukeList object
     */
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

    /**
     * Output message after "todo" or "deadline" or "event" command
     */
    public void addTaskMessage() {
        showToUser("Got it! The task has been added");
    }

    /**
     * Output message and the number of items in DukeList object
     * @param list DukeList object
     */
    public void showNoOfTaskLeft(DukeList list) {
        showToUser("You have " + list.getSize() + " Task left in the list");
    }

    /**
     * Output message when an unknown command is received
     */
    public void unknownCommandMessage() {
        showToUser("I'm sorry, I do not know what that means");
    }

    /**
     * reads the next line of user input
     * @return String of user input
     */
    public String readCommand() {
        return in.nextLine();
    }

    public void showDivider() {
        showToUser(DIVIDER + LS);
    }

    /**
     * Output error message when ArrayIndexOutOfBoundsException is caught
     */
    public void showMissingArgsError() {
        showToUser("Error! Your command has missing argument(s)!\nTry Again!");
    }

    /**
     * Output error message when NumberFormatException | DateTimeParseException | IndexOutOfBoundsException is caught
     */
    public void showWrongArgsError() {
        showToUser("Error! Your command has invalid arguement(s)!\nTry Again!");
    }

    /**
     * prints the string parameter
     * @param message string of message
     */
    public void showToUser(String message) {
        System.out.println(message);
    }

    /**
     * signals whether "bye command has been called"
     * @return
     */
    public boolean getIsExit() {
        return isExit;
    }
}
