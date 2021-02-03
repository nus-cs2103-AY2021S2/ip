package com.jetbrains;

import java.lang.Throwable;
public class Event extends Task {
    String at;

    Event(String input) throws DukeIncompleteCommandException {
        input = input.substring(5).trim();

        if (input.equals("")) {
            throw new DukeIncompleteCommandException();
        }

        String[] inputs = input.split("/at");

        if (!input.contains("/at") || inputs.length < 2) {
            throw new DukeIncompleteCommandException("Oh no! Please enter an event date. :P");
        }

        this.task = inputs[0];
        this.isDone = false;
        this.at = inputs[1].trim();
    }


    @Override
    public String toString() {
        return String.format("EVNT%s (at: %s)" ,
                super.toString(), at);
    }
}
