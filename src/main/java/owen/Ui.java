package owen;

import java.util.Scanner;

/**
 * Terminal UI handling user input and output.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Creates new Terminal UI for handling user input and output.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Outputs a formatted response to standard output.
     *
     * @param response Raw unformatted response string.
     */
    public void outputResponse(String response) {
        Response formattedResponse = new DefaultResponse(response);
        System.out.println(formattedResponse.getFormattedResponse());
    }

    /**
     * Gets user input from standard input.
     *
     * @return User input string.
     */
    public String getInput() {
        return this.scanner.nextLine();
    }
}
