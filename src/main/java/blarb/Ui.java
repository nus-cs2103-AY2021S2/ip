package blarb;

import java.util.Scanner;

/**
 * {@code Ui} is the user interface.
 */
class Ui {
    private final Scanner sc;

    /**
     * Initializes a new User Interface.
     */
    Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Reads in the user input.
     *
     * @return String interpretation of the user input.
     */
    public String read() {
        return sc.nextLine();
    }

    /**
     * Checks if the UI is available.
     *
     * @return The availability of the UI.
     */
    public boolean isAvailable() {
        return sc.hasNextLine();
    }

    /**
     * Prints the logo of the chatbot.
     */
    public void rollCredits() {
        String logo = " ____            _      ____   ____\n" +
                "|    \\ |        / \\    |    \\ |    \\\n" +
                "|____/ |       /___\\   |____/ |____/\n" +
                "|    \\ |      /     \\  |  \\   |    \\\n" +
                "|____/ |____ /       \\ |   \\_ |____/\n";
        System.out.println("You are now in the presence of\n" + logo);
    }

    /**
     * Bids goodbye to the user.
     */
    public void adios() {
        System.out.println("Hasta la vida, baby.");
    }

    /**
     * Prints output in response format.
     *
     * @param output Output string
     */
    public void blurt(String output) {
        String response = "%s\n\n> ";
        System.out.printf(response, output);
    }

    /**
     * Prints an error message generated by exceptions not caused by user input.
     *
     * @param warn The warning message to be displayed.
     */
    public void warn(String warn) {
        String response = "Error! %s\n\n";
        System.out.printf(response, warn);
    }
}
