package com.tanboonji.duke.command;

/**
 * The HelpCommand class contains information to execute the "help" command.
 */
public class HelpCommand extends Command {

    /** String input to execute this command */
    public static final String COMMAND = "help";
    /** Command list for help message */
    public static final String COMMAND_LIST = "\tCommands available:\n"
            + "\t\t- list\n"
            + "\t\t- find [keyword]\n"
            + "\t\t- todo [description]\n"
            + "\t\t- deadline [description] /by [deadline]\n"
            + "\t\t- event [description] /at [datetime]\n"
            + "\t\t- done [task number]\n"
            + "\t\t- delete [task number]\n"
            + "\t\t- help\n"
            + "\t\t- bye\n";

    /**
     * Default class constructor.
     */
    public HelpCommand() {
    }

    @Override
    public boolean shouldSaveData() {
        return false;
    }

    @Override
    public boolean shouldExitDuke() {
        return false;
    }

    @Override
    public String execute() {
        return COMMAND_LIST;
    }
}
