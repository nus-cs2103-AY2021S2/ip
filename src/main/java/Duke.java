public class Duke {

    private boolean isRunning;
    private TaskList taskList;

    public Duke() {
        isRunning = true;
        taskList = new TaskList();
    }

    public void init() {
        displayWelcomeMessage();
    }

    public boolean isRunning() {
        return isRunning;
    }

    public String getResponse(String input) {
        Command command;
        try {
            command = InputHandler.parse(input);
            isRunning = !command.shouldExit();
            taskList = command.execute(taskList);
            String output = command.getResponse();
            return output;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public void displayWelcomeMessage() {
        System.out.println("    Hello! I'm Duke\n    What can I do for you?");
    }
}
