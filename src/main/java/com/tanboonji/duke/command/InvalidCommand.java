package com.tanboonji.duke.command;

public class InvalidCommand extends Command {

    String errorMessage;

    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean shouldExit() {
        return false;
    }

    @Override
    public boolean shouldSave() {
        return false;
    }

    @Override
    public String execute() {
        return errorMessage;
    }
}
