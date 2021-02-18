package com.tanboonji.jhin.command;

/**
 * The HelpCommand class contains information to execute the "help" command.
 */
public class HelpCommand extends Command {

    /** Command list for help message */
    public static final String COMMAND_LIST = "Commands available:\n"
            + "[list, ls]\n"
            + "todo <description>\n"
            + "deadline <description> /by <date> <time>\n"
            + "event <description> /at <date> <time>\n"
            + "done <task number>\n"
            + "[delete, rm] <task number>\n"
            + "[find, search] <keyword>\n"
            + "[listalias, lsalias]\n"
            + "alias <command> <alias>\n"
            + "[deletealias, rmalias] <alias>\n"
            + "help\n"
            + "[bye, exit]\n";

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
    public boolean shouldExitJhin() {
        return false;
    }

    @Override
    public String execute() {
        return COMMAND_LIST;
    }
}
