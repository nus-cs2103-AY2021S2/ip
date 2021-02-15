package com.tanboonji.duke.command;

/**
 * The HelpCommand class contains information to execute the "help" command.
 */
public class HelpCommand extends Command {

    /** String input to execute this command */
    public static final String COMMAND = "help";
    /** Command list for help message */
    public static final String COMMAND_LIST = "Commands available:\n"
            + "- list\n"
            + "- find [keyword]\n"
            + "- todo [description]\n"
            + "- deadline [description] /by [deadline]\n"
            + "- event [description] /at [datetime]\n"
            + "- done [task number]\n"
            + "- delete [task number]\n"
            + "- alias [command] [alias]\n"
            + "- listalias\n"
            + "- deletealias [alias]\n"
            + "- help\n"
            + "- bye\n";

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
