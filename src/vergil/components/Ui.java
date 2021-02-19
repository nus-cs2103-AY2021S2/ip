package vergil.components;

/**
 * Represents a response message retrieval system for Vergil.
 */
public class Ui {
    /**
     * Creates a new Ui object.
     */
    public Ui() {
        // Create a new Ui.
    }

    /**
     * Returns a welcoming message.
     *
     * @return  Vergil's welcoming message.
     */
    public String getWelcomeMessage() {
        return "Hello! I'm Vergil\n"
                + "What can I do for you today?";
    }

    /**
     * Returns a farewell message.
     *
     * @return  Vergil's farewell message.
     */
    public String getFarewellMessage() {
        return "Bye. See you soon!";
    }

    /**
     * Returns a response message to a 'list' command.
     *
     * @param   taskList    the list of tasks to be displayed.
     * @return              Vergil's response to a 'list' command.
     */
    public String getListResponse(TaskList taskList) {
        return "Here are your current tasks:\n"
                + taskList.toString();
    }

    /**
     * Returns a message indicating success with the given details.
     *
     * @param   details the details of the successful operation.
     * @return          Vergil's success message with the given details.
     */
    public String getSuccessMessage(String details) {
        return "Success! " + details;
    }

    /**
     * Returns a message displaying the results of a 'find' command.
     *
     * @param   taskList    the list of matching tasks to be displayed.
     * @return              Vergil's response to a 'find' command with one or more results.
     */
    public String getFoundMessage(TaskList taskList) {
        return "Here's what I found:\n"
                + taskList.toString();
    }

    /**
     * Returns a message signaling no matching tasks for a 'find' command.
     *
     * @return  Vergil's no-matches-found message.
     */
    public String getNotFoundMessage() {
        return "There are no tasks matching with the given keywords.";
    }
}
