package com.jetbrains;

public class DukeInvalidCommandException extends Exception {
    public String message = "Oh no! I don't know what that means. ):";
    DukeInvalidCommandException() {
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String toString() {
        return message;
    }
}
