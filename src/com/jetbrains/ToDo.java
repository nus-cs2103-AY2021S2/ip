package com.jetbrains;

public class ToDo extends Task {
    ToDo(String input) {
        this.task = input.trim().substring(5);
        this.isDone = false;
    }
    @Override
    public String toString() {
        return "TODO" + super.toString();
    }
}
