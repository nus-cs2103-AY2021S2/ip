/**
 * Class which deals with the UI interactions with the user.
 */
class Ui {
    static final String FORMAT_LINE = "....................................................";

    /**
     * Greets the user
     */
    public void greetUser() {
        System.out.println(FORMAT_LINE + "\nHey, " +
                "I am Duke.\nHow can I help you?\n" +
                FORMAT_LINE);
    }

    /**
     * Bids farewell to the user
     */
    public void farewellUser() {
        System.out.println(FORMAT_LINE +
                "\nGoodbye and see you soon!\n" +
                FORMAT_LINE);
    }

    /**
     * Echoes the command input by the user for better UI clairty
     */
    public void echoCommand(String input) {
        System.out.println("\n>>> " + input);
    }

}