package duke.ui;

import duke.commands.CommandResult;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Represents a text UI that uses the standard input and outputs.
 */
public class TextUi implements Ui {
    private static final String COMMAND_PROMPT = "Type Command> ";

    private final Scanner in;
    private final PrintStream out;

    /**
     * Constructs a text UI that uses the standard input and outputs.
     */
    public TextUi() {
        in = new Scanner(System.in);
        out = System.out;
    }

    /**
     * Prompts the user then reads a line of user input and returns it as a string. Ignores blank input.
     *
     * @return Non-blank user input.
     */
    @Override
    public String readCommand() {
        String line;
        do {
            out.print(COMMAND_PROMPT);
            line = in.nextLine();
        } while (isInvalid(line));
        return line;
    }

    /**
     * Prints the feedback of the given command result if it has any.
     *
     * @param result The result to display.
     */
    @Override
    public void showCommandResult(CommandResult result) {
        if (result.hasFeedback()) {
            out.println(result.getFeedback());
        }
    }

    /**
     * Prints the given error message.
     *
     * @param errMsg The error message to display.
     */
    @Override
    public void showError(String errMsg) {
        out.println(errMsg);
    }

    /**
     * Prints a farewell message to the user.
     */
    @Override
    public void showFarewell() {
        out.println("Goodbye, cruel world!");
    }

    /**
     * Prints a greeting message to the user.
     */
    @Override
    public void showGreeting() {
        final String logo = "███      ███                             ███                      \n"
                + " ███    ███   ██                         ███                      \n"
                + "  ███ ███        ███ ███    █████        ███     ███     ███ ███  \n"
                + "    ███      ███  ███  ███ ███  ███      ███   ██   ███   ███  ███\n"
                + "    ███      ███  ███  ███ ██   ███      ███  █████████   ███  ███\n"
                + "    ███      ███  ███  ███ ████ ███ ██   ███  ██          ███  ███\n"
                + "    ███      ███ ████  ███     ███   █████      █████    ████  ███\n"
                + "                             ████                                 \n";
        out.println("Hi there. I am");
        out.println(logo);
        out.println("How may I help you?");
    }

    /**
     * Prints the given message to the user.
     *
     * @param msg The message to display.
     */
    @Override
    public void showMessage(String msg) {
        out.println(msg);
    }

    private boolean isInvalid(String input) {
        return input.isBlank();
    }
}
