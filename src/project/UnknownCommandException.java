package main.java;

public class UnknownCommandException extends DukeException {

    UnknownCommandException(String s) {
        super("☹ OOPS!!! I'm sorry, but I don't know what '" + s + " means :-(");
    }
}
