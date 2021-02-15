package com.tanboonji.duke.command;

/**
 * The ListAliasCommand class contains information to execute the "list" command.
 */
public class ListAliasCommand extends Command {

    /** String input to execute this command */
    public static final String COMMAND = "listalias";

    /**
     * Default class constructor.
     */
    public ListAliasCommand() {
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
        StringBuilder builder = new StringBuilder();
        builder.append("Here are the list of your alias:\n");
        builder.append(aliasMap);
        return builder.toString().trim();
    }
}
