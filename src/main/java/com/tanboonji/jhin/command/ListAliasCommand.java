package com.tanboonji.jhin.command;

/**
 * The ListAliasCommand class contains information to execute the "list" command.
 */
public class ListAliasCommand extends Command {

    private static final String SUCCESS_MESSAGE_FORMAT = "Here are the your aliases:\n"
            + "%s";
    private static final String SUCCESS_EMPTY_ALIAS_MESSAGE = "You currently have 0 aliases.";

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
    public boolean shouldExitJhin() {
        return false;
    }

    @Override
    public String execute() {
        if (aliasMap.getSize() == 0) {
            return SUCCESS_EMPTY_ALIAS_MESSAGE;
        }

        return String.format(SUCCESS_MESSAGE_FORMAT, aliasMap);
    }
}
