package com.jetbrains;

import java.lang.Throwable;
public class Deadline extends Task {
    String by;

    Deadline(String input) throws DukeIncompleteCommandException {
        input = input.substring(8).trim();

        if (input.equals("")) {
            throw new DukeIncompleteCommandException();
        }

        String[] inputs = input.split("/by");

        if (!input.contains("/by") || inputs.length < 2) {
            throw new DukeIncompleteCommandException("Oh no! Please enter an due date. :P");
        }
        this.task = inputs[0];
        this.isDone = false;
        this.by = inputs[1].trim();
    }

    @Override
    public String toString() {
        return String.format("DDLN%s (by: %s)" ,
                super.toString(), by);
    }
}
