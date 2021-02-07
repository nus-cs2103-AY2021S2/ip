package format;

import tasklist.TaskList;

public class Ui {
    // formatting for print messages
    public static final String EXTRA_INDENT = "  "; // an extra indent for listing tasks, is used outside this class
    private static final String DIVIDER_LINE = "    ______________________________________________";
    private static final String DEFAULT_INDENT = "      ";

    /**
     * Prints introductory message.
     */
    public static void intro() {
        String logo = " ______\n"
                + "/______\\ Kiwi's\n"
                + "|______|     Inn\n"
                + "####################";

        // intro message
        System.out.println(logo);
        print(new String[]{"Welcome, traveller. I'm Kiwi.", "What would you like to do today?"});

    }

    /**
     * Prints a formatted task list.
     * @param taskList tasks.Task list to be printed
     */
    public static void printTaskList(TaskList taskList) {
        System.out.println(DIVIDER_LINE);

        System.out.println(DEFAULT_INDENT + "Your tasks:");

        for (int i = 0; i < taskList.size(); i++) {
            System.out.print(EXTRA_INDENT);
            System.out.println(DEFAULT_INDENT + (i + 1) + "." + taskList.get(i));
        }

        System.out.println();
        System.out.println(DEFAULT_INDENT + "You have " + taskList.size() + " tasks. ");

        System.out.println(DIVIDER_LINE);
    }

    /**
     * Helper method to format chat bot responses
     * @param messages An array containing all messages to print in response to one command
     */
    public static void print(String[] messages) {
        System.out.println(DIVIDER_LINE);

        for (int i = 0; i < messages.length; i++) {
            System.out.println(DEFAULT_INDENT + messages[i]);
        }

        System.out.println(DIVIDER_LINE);
    }

    /**
     * Helper method to format chat bot response
     * @param message A message to print in response to one command
     */
    public static void print(String message) {
        System.out.println(DIVIDER_LINE);

        System.out.println(DEFAULT_INDENT + message);

        System.out.println(DIVIDER_LINE);
    }


    /**
     * Formats printing of exception.
     * @param errMsg Error message from the exception
     */
    public static void printException(String errMsg) {
        print(new String[]{"Oops, Kiwi couldn't process that.", EXTRA_INDENT + errMsg});
    }

    /**
     * Prints exit message
     */
    public static void printExitMsg() {
        print("Bye. See you again soon!");
    }

    // STRING VERSIONS OF ALL THE ABOVE COMMANDS FOR GUI SAKE
    public static String introMessage() {
        String logo = " ______\n"
                + "/______\\ Kiwi's\n"
                + "|______|     Inn\n"
                + "####################\n";

        // intro message
//        System.out.println(logo);
//        print(new String[]{});
        return logo + indent("Welcome, traveller. I'm Kiwi.", "What would you like to do today?\n");
    }

    public static String formatException(String errMsg) {
        String s = "Oops, Kiwi couldn't process that.";
        return addDividers(
                indent(s, addExtraIndent(errMsg)));
    }

    public static String getExitMessage() {
        return formatSingleLineMsg("Bye. See you again soon!");
    }

    /** Formatter.
     *
     * @param messages
     * @return
     */
    private static String indent(String... messages) {
        String res = "";
        for (String s : messages) {
            res += DEFAULT_INDENT;
            res += s;
            res += '\n';
        }
        return res;
    }

    private static String addExtraIndent(String... messages) {
        String res = "";
        for (String s : messages) {
            res += EXTRA_INDENT;
            res += s;
//            res += '\n';
        }
        return res;
    }

    private static String addDividers(String message) {
        // assertion is that individual lines of messages already have been indented and have line breaks
        return DIVIDER_LINE + '\n' + message + DIVIDER_LINE;
    }

    // rename this
    private static String formatMultiLineMessages(String firstLine, String ... moreIndentedLines) {
        return addDividers(indent(firstLine, addExtraIndent(moreIndentedLines)));
    }

    private static String formatSingleLineMsg(String firstLine) {
        return addDividers(indent(firstLine));
    }

    /**
     * For testing purposes
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        System.out.println(ui.getExitMessage());
        printExitMsg();

        String er = "halkf";
        printException(er);
        System.out.println(ui.formatException(er));
    }
    
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
