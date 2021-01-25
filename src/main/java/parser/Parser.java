package parser;

import Exceptions.*;
import command.*;
import storage.Storage;

public class Parser {
    public static Command parseTask(String task) throws DukeException {
        String[] line = task.split(" ", 2); // split type of task from description
        String type = line[0]; // type of task
        if (task.isBlank()) {
            throw new EmptyLineException(" ");
        }
        if (line.length != 2) {
            line = new String[]{type, ""};
        }
        switch (type) {
            case "list":
                return new showListCommand("list");
            case "done":
                return new DoneCommand(line[1]);
            case "delete":
                return new DeleteCommand(line[1]);
            case "todo":
                return new AddToDoCommand(line[1]);
            case "deadline":
                return new AddDeadlineCommand(line[1]);
            case "event":
                return new AddEventCommand(line[1]);
            case "bye":
                return new ByeCommand("bye");
            default:
                throw new IncorrectTypeException("");
        }
    }
}
