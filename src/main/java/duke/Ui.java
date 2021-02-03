package duke;

public class Ui {
    private final String[] HELLO = {
            System.lineSeparator() + " _____ ____  ____  _  __ _____ ____ "
                    + System.lineSeparator()
                    + "/__ __Y  _ \\/ ___\\/ |/ //  __//  __\\"
                    + System.lineSeparator()
                    + "  / \\ | / \\||    \\|   / |  \\  |  \\/|"
                    + System.lineSeparator()
                    + "  | | | |-||\\___ ||   \\ |  /_ |    /"
                    + System.lineSeparator()
                    + "  \\_/ \\_/ \\|\\____/\\_|\\_\\\\____\\\\_/\\_\\", "Sup sup! I'm Tasker :)"
    };
    private final String[] GOODBYE = {
            System.lineSeparator() + " _____ ____  ____  ____  ____ ___  _ _____" + System.lineSeparator()
                    + "/  __//  _ \\/  _ \\/  _ \\/  _ \\\\  \\///  __/" + System.lineSeparator()
                    + "| |  _| / \\|| / \\|| | \\|| | // \\  / |  \\  " + System.lineSeparator()
                    + "| |_//| \\_/|| \\_/|| |_/|| |_\\\\ / /  |  /_ " + System.lineSeparator()
                    + "\\____\\\\____/\\____/\\____/\\____//_/   \\____\\", "Till next time :)"
    };
    private final String[] READY = {
            System.lineSeparator() + "Tasker is ready :)"
    };
    private final String[] LOADING_ERROR = {
            System.lineSeparator() + "Something went wrong :("
    };
    private final String LINE_PARTITION = "_______________________________" + "___________________________________"
                    + "_______________________________________________________"
                    + "_____________________________________________________________" + System.lineSeparator();

    /**
     * Returns a String of generated message which will be printed later on.
     * @param messages a String array that contains the message body.
     * @return the generated message for the user.
     */
    public String generateMessage(String[] messages) {
        StringBuilder output = new StringBuilder(LINE_PARTITION);
        for (String message : messages) {
            output.append(message).append(System.lineSeparator());
        }
        output.append(LINE_PARTITION);
        return output.toString();
    }

    public void showLoadingError() {
        System.out.println(generateMessage(LOADING_ERROR));
    }
    public void greetUser() {
        System.out.println(generateMessage(HELLO));
    }
    public void indicateReady() {
        System.out.println(generateMessage(READY));
    }
    public void sayGoodbye() {
        System.out.println(generateMessage(GOODBYE));
    }
}
