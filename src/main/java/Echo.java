
public class Echo implements Command {
    private boolean isExitCommand = false;
    private String echoString;

    /**
     * Constructor for Echo command object
     * @param inputString user input to be echoed
     */
    public Echo(String inputString) {
        this.echoString = inputString;
    }

    @Override
    public String runCommand() {
        return this.echoString;
    }

    @Override
    public boolean isExitCommand() {
        return this.isExitCommand;
    }
}
