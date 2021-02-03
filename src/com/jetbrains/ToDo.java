package com.jetbrains;

import java.lang.Throwable;
public class ToDo extends Task {
    ToDo(String input) throws DukeIncompleteCommandException {
        input = input.substring(4).trim();
        if (input.equals("")) {
            throw new DukeIncompleteCommandException();
        }
        this.task = input;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "TODO" + super.toString();
    }
}
