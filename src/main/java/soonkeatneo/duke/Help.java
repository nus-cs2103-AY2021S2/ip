package soonkeatneo.duke;

/**
 * Implementation for printing of help documentation.
 *  @author Soon Keat Neo
 *  @version CS2103T AY20/21 Sem 2 iP
 */

public class Help {

    /**
     * Returns the help text defined.
     * @return A String containing the formatted help-text.
     */
    public static String print() {
        String helpText = "Thank you for perusing the help documentation for " + Duke.BOT_NAME + "!\n"
                + "The following are the implemented functions in the application:\n"
                + "list - List all Tasks added to the bot.\n"
                + "find - Search for a Task containing the given string.\n"
                + "todo - Add a new ToDo Task.\n"
                + "event - Add a new Event Task.\n"
                + "deadline - Add a new Deadline Task.\n"
                + "done - Mark a Task as completed.\n"
                + "delete - Removes a Task from the application.\n"
                + "bye - Exit the application.";
        return helpText;
    }


}
