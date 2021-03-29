package duke;

public class Ui {
    private String response;

    /**
     * Constructor method
     */
    public Ui() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        response = "Hello from\n" + logo + "\n" + "What can I do for you?";
    }
    /**
     * Constructor method.
     * @param statement command given
     */
    public Ui(String statement) {
        response = statement;
    }

    /**
     * Method to get create a response
     */
    public void reply() {
        System.out.println(response);
    }

    /**
     * Method to print a String
     */
    public void print(String statement) {
        response = statement;
        System.out.println(response);
    }
}
