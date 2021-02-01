package com.jetbrains;

public class Deadline extends Task {
    String dueDate;

    Deadline(String input) {
        String[] inputs = input.trim().split("/by ");
        this.task = inputs[0].substring(9);
        this.isDone = false;
        this.dueDate = inputs[1];
    }

    @Override
    public String toString() {
        return String.format("DDLN%s (by: %s)" ,
                super.toString(), dueDate);
    }
}
