/*
 * Deal with interactions with the users.
 */
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

    public Ui(String statement) {
        response = statement;
    }

    /*
     * Respond with the current response.
     */
    public void reply() {
        System.out.println(response);
    }

    /*
     * Replace the current response with a new one.
     * Respond with the new response.
     *
     * @param statement The new response that will replace the current one.
     */
    public void replace(String statement) {
        response = statement;
        System.out.println(response);
    }
}
