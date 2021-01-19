public class Duke {

    private boolean isRunning;

    public Duke() {
        isRunning = true;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public String getResponse(String input) {
        String command = InputHandler.parse(input);

        if (command.equals("bye")) {
            isRunning = false;
            return "Bye. Hope to see you again soon!";
        } else {
            return command;
        }
    }

    public void displayWelcomeMessage() {
        System.out.println("    Hello! I'm Duke\n    What can I do for you?");
    }
}
