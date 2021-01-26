package lihua.commons;

import lihua.commands.*;

public class Messages {
    public static final String MESSAGE_HELLO =
            "Hello! My name is Lihua.\n" +
                    "What can I do for you today? (=V=)";
    public static final String MESSAGE_GET_HELP =
            "Sorry, I do not understand your command :')\n" +
                    "If you are stuck, type 'help' to get a list of operations available.";
    public static final String MESSAGE_LINE =
            "-------------------------------------------------";
    public static final String MESSAGE_INFORM_EXIT =
            "Exiting Lihua as requested...\n" +
                    "Goodbye! Hope to see you again soon! (=V=)";
    public static final String MESSAGE_REPORTING_ADDING_FAILURE =
            "Sorry, the task adding failed. :( Please check your storage devices.";
    public static final String MESSAGE_VIEW_COMMANDS =
            "No worries! Here are the commands available:\n"
                    + AddCommand.MESSAGE_USAGE
                    + "\n" + DoneCommand.MESSAGE_USAGE
                    + "\n" + DeleteCommand.MESSAGE_USAGE
                    + "\n" + ListCommand.MESSAGE_USAGE
                    + "\n" + HelpCommand.MESSAGE_USAGE
                    + "\n" + ExitCommand.MESSAGE_USAGE;
    public static final String MESSAGE_REPORTING_INVALID_INDEX =
            "Sorry, the index you specified does not exist :'), " +
                    "please type 'list' to see available indices to delete.";
}