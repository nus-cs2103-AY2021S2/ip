package dukeproject;

public class Ui {

    Ui() {
        this.showInitialGreeting();
    }

    /** Shows initial greetings when the user first open duke */
    public void showInitialGreeting() {
        String initialGreeting = "Hello I'm Duke \n"
                + "What can I do for you? \n";
        System.out.println(initialGreeting);
    }

    // Good bye message before duke terminates
    public void goodByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    // General error message for the user
    public void printLoadingError() {
        System.out.println("OOPS! There is no storage device we can load the data from");
    }

    // General error message for the user
    public void printUnreadableError() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    // Error message when description is empty
    public void printDescriptionError() {
        System.out.println("OOPS!!! The description cannot be empty");
    }

    // Error message when file could not be found
    public void printFileError() {
        System.out.println("OOPS!!! The file could not be found");
    }

    // Error message when date format is wrong
    public void printDateFormatError() {
        System.out.println("OOPS!! Please input a value date time format of yyyy-MM-dd HHmm");
    }

    // Error message when task number is invalid
    public void printTaskNumError() {
        System.out.println("Please enter a valid task number");
    }

    /**
     * Prints a success message after marking a task as done.
     *
     * @param taskList List of all the task.
     * @param taskIndex Index of the task this message is for.
     */
    public void printTaskSuccess(TaskList taskList, int taskIndex) {
        System.out.println("Nice! I've marked this task as done:\n "
                + "   " + taskList.get(taskIndex - 1).toString() + "\n");
    }

    /**
     * Prints the list of task for the user.
     *
     * @param taskList List of all the task.
     */
    // Print the entire list of task
    public void printTaskList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");

        // Shows the list of user inputs with its numbering starting from 1
        for (int index = 0; index < taskList.size(); index++) {
            System.out.println(index + 1 + ". " + taskList.get(index).toString());
        }
        System.out.println(); // Blank line
    }

    /**
     * PrintS the list of tasks that matches the keyword.
     *
     * @param taskList List of task.
     * @param keyword Keyword to search for.
     */
    public void printKeywordTaskList(TaskList taskList, String keyword) {
        System.out.println("Here are the matching tasks in your list:");

        int counter = 1;

        // Shows the list of user inputs with its numbering starting from 1
        for (int index = 0; index < taskList.size(); index++) {
            /* Only print the listing and increment the counter if the task descriptions
            contains the keyword */
            if (taskList.get(index).description.contains(keyword)) {
                System.out.println(counter + ". " + taskList.get(index).toString());
                counter++;
            }
        }

        // Print "None" if there is no task that match the keyword
        if (counter == 1) {
            System.out.println("None");
        }
        System.out.println(); // Blank line
    }

    // General Printing
    public void generalPrint(String stringToPrint) {
        System.out.println(stringToPrint);
    }
}
