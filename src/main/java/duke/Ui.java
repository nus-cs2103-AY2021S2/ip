package duke;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Ui class that handles interactions with user.
 */
public class Ui {
    private static final String INDENT = "  ";
    private static final String TEXT_INDENT = INDENT + "|" + " ";
    private static final String RES_BOX_TOP = INDENT + "_______________________________________________\n";
    private static final String RES_BOX_BOTTOM = INDENT + "|____________________________________________\n"
            + INDENT + "                                              \\|\n";
    private static Scanner sc = new Scanner(System.in);
    private final Scanner in;
    private final PrintStream out;

    /**
     * Default constructor.
     */
    public Ui() {
        this.in = new Scanner(System.in);
        this.out = System.out;
    }


    private boolean hasCommand() {
        return in.hasNext();
    }

    /**
     * Reads input by line.
     * @return String of input.
     */
    public String readCommand() {
        assert hasCommand();
        return in.nextLine();
    }

    /**
     * Closes system input.
     */
    public void close() {
        in.close();
    }

    /**
     * Displays the default intro message.
     */
    public void displayIntro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String response = "Hello from\n"
                + logo + "\n"
                + "What can I do for you?\n";
        respond(response);
    }

    /**
     * Formats and prints the string response in a chat response form.
     * @param response response from Duke.
     */
    public void respond(String response) {
        String indentedResponse = Arrays.stream(response.split("\n"))
                .map(line -> TEXT_INDENT + line)
                .collect(Collectors.joining("\n")) + "\n";
        String output = RES_BOX_TOP
                + indentedResponse
                + RES_BOX_BOTTOM;

        System.out.println(output);
    }
}
