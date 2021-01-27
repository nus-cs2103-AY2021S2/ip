public class HelpCommand extends Command {

    public static final String COMMAND = "help";
    private final String ERROR_MESSAGE = "☹ Sorry, please enter a valid command.\n";
    private final String COMMAND_LIST = "\tCommands available:\n\t\t" +
            "- list\n\t\t- done [task number]\n\t\t- todo [description]\n\t\t" +
            "- deadline [description] /by [deadline]\n\t\t- event [description] /at [datetime]\n\t\t- help\n\t\t" +
            "- delete [task number]\n\t\t- bye";
    private boolean showError;

    public HelpCommand(boolean showError) {
        this.showError = showError;
    }

    @Override
    public boolean shouldSave() {
        return false;
    }

    @Override
    public String execute() {
        if (showError) {
            return ERROR_MESSAGE + COMMAND_LIST;
        } else {
            return COMMAND_LIST;
        }
    }
}
