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

    public void reply() {
        System.out.println(response);
    }

    public void replace(String statement) {
        response = statement;
        System.out.println(response);
    }
}
