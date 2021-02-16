package com.nus.duke.command;

public class IncorrectCommand extends Command {

    private final String message;

    public IncorrectCommand(String message) {
        this.message = message;
    }

    @Override
    public String execute() {
        return this.message;
    }
}
