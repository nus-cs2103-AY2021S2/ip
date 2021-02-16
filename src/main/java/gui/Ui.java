package gui;

import dukeproject.ContactList;
import dukeproject.TaskList;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class Ui {

    @FXML
    private final TextArea textOutput;

    /**
     * Constructor to create the text output and print initial greetings to the user.
     *
     * @param textOutput UI to output the text to the user.
     */
    public Ui(TextArea textOutput) {
        this.textOutput = textOutput;
        this.printInitialGreeting();
    }

    /**
     * Prints an initial greeting when the user first open the application.
     */
    public void printInitialGreeting() {
        String initialGreeting = "Hello I'm Duke \n"
                + "What can I do for you?";
        this.printWithSpace(initialGreeting);
        this.printWithSpace(""); //blank space
    }

    // General error message for the user
    public void printUnreadableError() {
        this.printWithSpace("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    // Error message when description is empty
    public void printDescriptionError() {
        this.printWithSpace("OOPS!!! The description cannot be empty");
    }

    //

    /**
     * Prints error message when file could not be found.
     */
    public void printFileError() {
        this.printWithSpace("OOPS!!! The file could not be found! "
            + "Please restart the application again.");
    }

    // Error message when date format is wrong
    public void printDateFormatError() {
        this.printWithSpace("OOPS!! Please input a value date time format of yyyy-MM-dd HHmm");
    }

    public void printTaskNumError() {
        this.printWithSpace("Please enter a valid task number");
    }

    public void printFindError() {
        this.printWithSpace("Please enter a valid find command");
    }

    public void printGeneralParseError() {
        this.printWithSpace("Please enter a valid command.");
    }
    /**
     * Prints a success message after marking a task as done.
     *
     * @param taskList List of all the task.
     * @param taskIndex Index of the task this message is for.
     */
    public void printTaskSuccess(TaskList taskList, int taskIndex) {
        this.printWithSpace("Nice! I've marked this task as done: "
            + "  \n " + taskList.getTask(taskIndex - 1).toString());
    }

    /**
     * Prints the list of task for the user.
     *
     * @param taskList List of all the task.
     */
    public void printTaskList(TaskList taskList) {
        this.printWithSpace("Here are the tasks in your list:");

        // Shows the list of user inputs with its numbering starting from 1
        for (int index = 0; index < taskList.size(); index++) {
            this.printWithSpace(index + 1 + ". " + taskList.getTask(index).toString());
        }
        this.printWithSpace("");
    }

    /**
     * Prints the list of contacts for the user.
     * @param contacts Represents the list of all the contacts.
     */
    public void printContactList(ContactList contacts) {
        this.printWithSpace("Here are the contacts in your list:");

        // Shows the list of user inputs with its numbering starting from 1
        for (int index = 0; index < contacts.getContactsSize(); index++) {
            this.printWithSpace(index + 1 + ". " + contacts.getContact(index).toString());
        }
        this.printWithSpace("");
    }

    /**
     * Prints the list of tasks that matches the keyword.
     *
     * @param taskList List of task.
     * @param keyword Keyword to search for.
     */
    public void printKeywordTaskList(TaskList taskList, String keyword) {
        this.printWithSpace("Here are the matching tasks in your list:");

        int counter = 1;

        // Shows the list of user inputs with its numbering starting from 1
        for (int index = 0; index < taskList.size(); index++) {
            /* Only print the listing and increment the counter if the task descriptions
            contains the keyword */
            if (taskList.getTask(index).getDescription().contains(keyword)) {
                this.printWithSpace(counter + ". " + taskList.getTask(index).toString());
                counter++;
            }
        }

        // Print "None" if there is no task that match the keyword
        if (counter == 1) {
            this.printWithSpace("None");
        }
        this.printWithSpace(""); // Blank line
    }

    public void printContactAdded() {
        this.printWithSpace("The contact has been successfully added.");
    }

    public void printContactDeleted() {
        this.printWithSpace("The contact has been successfully deleted.");
    }

    /**
     * Prints a string on the UI based on the given string to be printed.
     *
     * @param stringToPrint String to be printed.
     */
    public void printWithSpace(String stringToPrint) {
        assert textOutput != null : "The UI does not have a valid place to output its text.";
        textOutput.appendText(stringToPrint + "\n");
    }
}
