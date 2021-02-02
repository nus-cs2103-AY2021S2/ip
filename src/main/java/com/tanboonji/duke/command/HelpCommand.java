package com.tanboonji.duke.command;

public class HelpCommand extends Command {

    public static final String COMMAND = "help";
    public static final String ERROR_MESSAGE = "☹ Sorry, please enter a valid command.\n";
    public static final String COMMAND_LIST = "\tCommands available:\n"
            + "\t\t- list\n"
            + "\t\t- find [keyword]"
            + "\t\t- todo [description]\n"
            + "\t\t- deadline [description] /by [deadline]\n"
            + "\t\t- event [description] /at [datetime]\n"
            + "\t\t- done [task number]\n"
            + "\t\t- delete [task number]\n"
            + "\t\t- help\n"
            + "\t\t- bye\n";

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
