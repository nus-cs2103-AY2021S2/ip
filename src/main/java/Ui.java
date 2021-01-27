public class Ui {
    // formatting for print messages
    private static final String lines = "    ______________________________________________";
    private static final String indent = "      ";
    public static final String taskIndent = "  "; // an extra indent for listing tasks, is used outside this class

    public static void intro() {
        String logo = " ______\n"
                + "/______\\ Kiwi's\n"
                + "|______|     Inn\n"
                + "####################";

        // intro message
        System.out.println(logo);
        print(new String[]{"Welcome, traveller. I'm Kiwi.", "What would you like to do today?"});

    }

    public static void printTaskList(TaskList taskList) {
        System.out.println(lines);

        System.out.println(indent + "Your tasks:");

        for (int i = 0; i < taskList.size(); i++) {
            System.out.print(taskIndent);
            System.out.println(indent + (i + 1) + "." + taskList.get(i));
        }

        System.out.println();
        System.out.println(indent + "You have " + taskList.size() + " tasks. ");

        System.out.println(lines);
    }

    // helper method to format chat bot responses
    // prints all strings in messages array in a separate indented line
    // maybe can think of splitting strings that are too long into different lines
    public static void print(String[] messages) {
        System.out.println(lines);

        for (int i = 0; i < messages.length; i++) {
            System.out.println(indent + messages[i]);
        }

        System.out.println(lines);
    }

    // print formatted single-line message
    public static void print(String message) {
        System.out.println(lines);

        System.out.println(indent + message);

        System.out.println(lines);
    }


    // format your exception printing here
    public static void printException(String errMsg) {
        print(new String[]{"Oops, Kiwi couldn't process that.", taskIndent + errMsg});
    }

    public static void printExitMsg() {
        print("Bye. See you again soon!");
    }
}
