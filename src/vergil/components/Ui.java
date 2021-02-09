package vergil.components;

public class Ui {
    public Ui() {
        // Create a new Ui.
    }

    public String getWelcomeMessage() {
        return "Hello! I'm Vergil\n"
                + "What can I do for you today?";
    }

    public String getFarewellMessage() {
        return "Bye. See you soon!";
    }

    public String getListResponse(TaskList taskList) {
        return "Here are your current tasks:\n"
                + taskList.toString();
    }

    public String getSuccessMessage(String details) {
        return "Success! " + details;
    }

    public String getFoundMessage(TaskList taskList) {
        return "Here's what I found:\n"
                + taskList.toString();
    }

    public String getNotFoundMessage() {
        return "There are no tasks matching with the given keywords.";
    }
}
