
public class EchoCommand implements Command {
    private boolean isExitCommand = false;
    private String echoString;

    /**
     * Constructor for Echo command object
     * @param inputString user input to be echoed
     */
    public EchoCommand(String inputString) {
        this.echoString = inputString;
    }

    @Override
    public TaskList runCommand(TaskList taskList) {
        return taskList;
    }

    @Override
    public boolean isExitCommand() {
        return this.isExitCommand;
    }

    @Override
    public String getResponse() {
        return this.echoString;
    }
}
