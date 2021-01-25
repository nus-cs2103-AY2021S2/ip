package main.java.duke;

import main.java.duke.command.*;
import main.java.duke.task.Deadline;
import main.java.duke.task.Event;
import main.java.duke.task.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class Parser {

    private String command;
    private Optional<String> secondSpecifier;
    private Optional<String> thirdSpecifier;

    public Parser()  {
    }

    public Command parse(String input) throws DukeException {
        String[] args = input.split(" ");
        this.command = args[0];

        if (this.command.equals("")) {
            throw new DukeException("Please enter a proper command!");
        }

        if (args.length >= 2) {
            this.secondSpecifier = Optional.ofNullable(args[1]);
        } else {
            this.secondSpecifier = Optional.empty();
        }

        if (args.length == 4) {
            this.thirdSpecifier = Optional.ofNullable(args[3]);
        } else {
            this.thirdSpecifier = Optional.empty();
        }

        if (this.command.equals("todo") && this.secondSpecifier.isEmpty()) {
            throw new DukeException("There\'s no task name specified!");
        }

        if (this.command.equals("event") || this.command.equals("deadline")) {
            if (this.secondSpecifier.isEmpty() || this.thirdSpecifier.isEmpty()) {
                throw new DukeException("There\'s no task name or date specified!");
            }

            try {
                LocalDate.parse(this.thirdSpecifier.get());
            } catch (DateTimeParseException dtEx) {
                throw new DukeException("\"Your date/time must be in the yyyy-mm-dd format. Please try again!");
            }
        }

        switch (this.command) {
            case "list":
                if (this.secondSpecifier.isEmpty()) {
                    return new ShowTaskCommand();
                } else if (this.secondSpecifier.get().equals("today")) {
                    return new ShowTaskCommand(LocalDate.now());
                } else if (this.secondSpecifier.get().equals("tomorrow")) {
                    return new ShowTaskCommand(LocalDate.now().plus(1, ChronoUnit.DAYS));
                } else {
                    return new ShowTaskCommand(this.secondSpecifier.get());
                }
            case "todo":
                Todo todo = new Todo(this.secondSpecifier.get());
                return new AddTaskCommand(todo);
            case "deadline":
                Deadline deadline = new Deadline(this.secondSpecifier.get(), LocalDate.parse(this.thirdSpecifier.get()));
                return new AddTaskCommand(deadline);
            case "event":
                Event event = new Event(this.secondSpecifier.get(), LocalDate.parse(this.thirdSpecifier.get()));
                return new AddTaskCommand(event);
            case "done":
                return new DoneTaskCommand(Integer.parseInt(this.secondSpecifier.orElse("-1")));
            case "delete":
                return new DeleteTaskCommand(Integer.parseInt(this.secondSpecifier.orElse("-1")));
            case "bye":
                return new ByeCommand();
            default:
                throw new DukeException("There\'s no such command! Try todo?");
        }
    }
}
