public class Ui {
    /**
     * Prints the logo and welcome messages.
     */
    void showWelcome() {
        String logo = "          ____                               \n" +
                "        ,'  , `.                                   \n" +
                "     ,-+-,.' _ |                                   \n" +
                "  ,-+-. ;   , ||                                   \n" +
                " ,--.'|'   |  ;|                                   \n" +
                "|   |  ,', |  ':  ,--.--.        .--,    ,--.--.   \n" +
                "|   | /  | |  || /       \\     /_ ./|   /       \\  \n" +
                "'   | :  | :  |,.--.  .-. | , ' , ' :  .--.  .-. | \n" +
                ";   . |  ; |--'  \\__\\/: . ./___/ \\: |   \\__\\/: . . \n" +
                "|   : |  | ,     ,\" .--.; | .  \\  ' |   ,\" .--.; | \n" +
                "|   : '  |/     /  /  ,.  |  \\  ;   :  /  /  ,.  | \n" +
                ";   | |`-'     ;  :   .'   \\  \\  \\  ; ;  :   .'   \\\n" +
                "|   ;/         |  ,     .-./   :  \\  \\|  ,     .-./\n" +
                "'---'           `--`---'        \\  ' ; `--`---'    \n" +
                "                                 `--` ";

        System.out.println(logo);

        System.out.println("    ____________________________________");
        System.out.println("    Hello, I'm Maya! \n    What can I do for you?");
        System.out.println("    ____________________________________");
    }

    /**
     * Prints the line separator.
     */
    void showLine() {
        System.out.println("    ____________________________________");
    }

    /**
     * Prints the exit message.
     */
    void showBye() {
        System.out.println("    Bye. Hope to see you again soon!");
    }

    /**
     * Prints the error message.
     */
    void showError(String err) {
        System.out.println("    " + err);
    }
}
