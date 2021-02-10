package duke;

import java.util.Scanner;

/**
 * The Parser class reads lines and extracts
 * commands from read lines in the command-line.
 *
 * @author  Justin Gnoh
 * @version 1.0
 * @since   2021-02-06
 */
public class Parser {
    private String read;

    private Scanner scan = new Scanner(System.in);

    /**
     * This method reads a line on the command prompt and
     * removes any extra space at the end.
     */
    public void readLine() {
        this.read = scan.nextLine().trim();
    }

    /**
     * This method extracts the command from the input
     * line that was read.
     *
     * @param input This receives a String input
     * @return String This returns a command in string format
     */
    public String getCommand(String input) {
        String command = input.split(" ")[0];
        return command;
    }

    public String getArguments(String input) {
        String arguments = input.split(" ", 2)[1];
        return arguments;
    }

    public int getDeleteIndex(String input) {
        int i = Integer.parseInt(input.split(" ")[1]) - 1;
        return i;
    }

    public String getRead() {
        return this.read;
    }
}
