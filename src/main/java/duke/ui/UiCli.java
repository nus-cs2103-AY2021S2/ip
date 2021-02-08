package duke.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import duke.exceptions.DukeException;
import duke.exceptions.DukeExceptionIllegalArgument;

public class UiCli extends Ui {

    private final BufferedReader in;

    public UiCli() {
        in = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public String getUserInput(String inputPrompt, String inputHint) throws DukeExceptionIllegalArgument {
        try {
            System.out.print(inputPrompt);
            return in.readLine();
        } catch (IOException e) {
            throw new DukeExceptionIllegalArgument("Failed to read input.");
        }
    }

    @Override
    public String getUserInputSecondary(String inputPrompt, String inputHint) throws DukeExceptionIllegalArgument {
        return getUserInput(inputPrompt, inputHint);
    }

    @Override
    public boolean getUserConfirmation(String confirmationPrompt) throws DukeException {
        String reply = getUserInput("Confirm deletion of all tasks (y/[n])? ", "");
        return reply.equalsIgnoreCase("y");
    }

    /**
     * Send exception for pretty printing.
     *
     * Exception message can be composed of multiple lines.
     *
     * @param e Exception.
     */
    @Override
    public void printError(DukeException e) {
        String message = String.valueOf(e).strip();
        if (message.contains("\n")) {
            int index = message.indexOf('\n');
            String firstLine = message.substring(0, index);
            String[] restLines = message.substring(index + 1).split("\n");
            List<String> lines = Arrays.stream(restLines).map(s -> "  " + s).collect(Collectors.toList());
            showResponse("☹ OOPS!!! " + firstLine, lines);
        } else {
            showResponse("☹ OOPS!!! " + message);
        }
    }

    /**
     * Prints user input as callback
     *
     * @param callbackText string
     */
    @Override
    public void printCallback(String callbackText) {
        System.out.print(callbackText);
    }

    /**
     * Prints user input as callback
     *
     * @param responseText string
     */
    @Override
    public void printResponse(String responseText) {
        printCallback(responseText);
    }
}
