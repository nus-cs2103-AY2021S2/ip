package format;

import tasklist.TaskList;

public class Ui {
    // formatting for print messages
    public static final String EXTRA_INDENT = "  "; // an extra indent for listing tasks, is used outside this class
    private static final String DIVIDER_LINE = "    ______________________________________________";
    private static final String DEFAULT_INDENT = "      ";

    /**
     * Helper method to format chat bot responses
     *
     * @param messages An array containing all messages to print in response to one command
     */
    public static void print(String[] messages) {
        System.out.println(DIVIDER_LINE);

        for (String message : messages) {
            System.out.println(DEFAULT_INDENT + message);
        }

        System.out.println(DIVIDER_LINE);
    }

    /**
     * Provides a string with a formatted intro message
     *
     * @return
     */
    public static String introMessage() {
        String logo = " ______\n"
                + "/______\\ Kiwi's\n"
                + "|______|     Inn\n"
                + "####################\n";

        // intro message
        return logo + indent("Welcome, traveller. I'm Kiwi.",
                "What would you like to do today?",
                "Psst if you're unsure you can type 'help'.");
    }

    /**
     * Helper method to format error messages
     *
     * @param errMsg A message to print in response to one command
     */
    public static String formatException(String errMsg) {
        String s = "Oops, Kiwi couldn't process that.";
        return addDividers(
                indent(s, addExtraIndent(errMsg)));
    }

    public static String getExitMessage() {
        return formatSingleLineMsg("Bye. See you again soon!");
    }

    /**
     * Adds intends to each string provided as argument
     *
     * @param messages Objects whose toString need to be displayed on the gui
     * @return
     */
    private static String indent(Object... messages) {
        String res = "";
        for (Object s : messages) {
            res += DEFAULT_INDENT;
            res += s;
            res += '\n';
        }
        return res;
    }


    private static String addExtraIndent(Object... messages) {
        String res = "";
        for (Object s : messages) {
            res += EXTRA_INDENT;
            res += s;
            res += '\n';
        }
        return res;
    }

    private static String addDividers(String message) {
        // assumption is that individual lines of messages already have been indented and have line breaks
        return DIVIDER_LINE + '\n' + message + DIVIDER_LINE;
    }

    /**
     * Formats messages that span multi-lines
     *
     * @param firstLine
     * @param moreIndentedLines
     * @return
     */
    public static String formatMultiLineMessages(String firstLine, Object... moreIndentedLines) {
        return addDividers(indent(firstLine, addExtraIndent(moreIndentedLines)));
    }

    /**
     * Formats a single line message
     *
     * @param firstLine
     * @return
     */
    public static String formatSingleLineMsg(String firstLine) {
        return addDividers(indent(firstLine));
    }

    /**
     * Formats a task list into a string for easy displaying.
     *
     * @param taskList
     * @return
     */
    public static String stringifyTaskList(TaskList taskList) {
        String s = "";
        String nl = "\n";
        s += (DIVIDER_LINE);
        s += nl;

        s += (DEFAULT_INDENT + "Your tasks:");
        s += nl;

        for (int i = 0; i < taskList.size(); i++) {
            s += (EXTRA_INDENT);
            s += (DEFAULT_INDENT + (i + 1) + "." + taskList.get(i));
            s += nl;
        }

        s += nl;
        s += (DEFAULT_INDENT + "You have " + taskList.size() + " tasks. ");
        s += nl;

        s += (DIVIDER_LINE);
        s += nl;
        return s;
    }
}
