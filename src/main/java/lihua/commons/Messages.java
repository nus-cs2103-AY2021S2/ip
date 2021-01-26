package lihua.commons;

import lihua.commands.AddCommand;
import lihua.commands.DoneCommand;
import lihua.commands.DeleteCommand;
import lihua.commands.ListCommand;
import lihua.commands.HelpCommand;
import lihua.commands.ExitCommand;

/**
 * Messages class containing static messages of Lihua.
 */
public class Messages {
    /** Hello message */
    public static final String MESSAGE_HELLO =
            "Hello! My name is Lihua.\n"
                    + "What can I do for you today? (=~ω~=)";
    /** Help prompt message */
    public static final String MESSAGE_GET_HELP =
            "Sorry, I do not understand your command :')\n"
                    + "If you are stuck, type 'help' to get a list of operations available.";
    /** Line divider */
    public static final String MESSAGE_LINE =
            "-------------------------------------------------";
    /** Exit message */
    public static final String MESSAGE_INFORM_EXIT =
            "Exiting Lihua as requested...\n"
                    + "Goodbye! Hope to see you again soon! (=~ω~=)";
    /** Adding task failure message */
    public static final String MESSAGE_REPORTING_ADDING_FAILURE =
            "Sorry, the task adding failed. :( Please check your storage devices.";
    /** Help message */
    public static final String MESSAGE_VIEW_COMMANDS =
            "No worries! Here are the commands available:\n"
                    + AddCommand.MESSAGE_USAGE
                    + "\n" + DoneCommand.MESSAGE_USAGE
                    + "\n" + DeleteCommand.MESSAGE_USAGE
                    + "\n" + ListCommand.MESSAGE_USAGE
                    + "\n" + HelpCommand.MESSAGE_USAGE
                    + "\n" + ExitCommand.MESSAGE_USAGE;
    /** Invalid index report message */
    public static final String MESSAGE_REPORTING_INVALID_INDEX =
            "Sorry, the index you specified does not exist :'), "
                    + "please type 'list' to see available indices to delete.";
}