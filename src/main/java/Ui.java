public class Ui {
    private String response;

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
     * @return a response
     */
    public void reply() {
        System.out.println(response);
    }
    /**
     * Method to print a String
     * @return a String object in the console
     */
    public void print(String statement) {
        response = statement;
        System.out.println(response);
    }
}
