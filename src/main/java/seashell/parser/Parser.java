package seashell.parser;

import seashell.command.AddCommand;
import seashell.command.ClearCommand;
import seashell.command.Command;
import seashell.command.DeleteCommand;
import seashell.command.DoneCommand;
import seashell.command.ExitCommand;
import seashell.command.FindCommand;
import seashell.command.HelpCommand;
import seashell.command.ListCommand;
import seashell.exception.SeashellException;
import seashell.task.TaskType;

public class Parser {

    /**
     * Takes the user input as a string and parses it into a command object that represents the command, so that
     * Seashell can execute it
     * @param input from user
     * @return Command object representing user input
     * @throws SeashellException if command is not recognised
     */
    public static Command parse(String input) throws SeashellException {
        assert !input.isEmpty();
        if (input.stripTrailing().equals("bye")) {
            return new ExitCommand();
        } else if (input.stripTrailing().equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("done ")) {
            return parseDone(input);
        } else if (input.startsWith("delete ")) {
            return parseDelete(input);
        } else if (input.startsWith("todo")) {
            return parseAddTodo(input);
        } else if (input.startsWith("deadline")) {
            return parseAddDeadline(input);
        } else if (input.startsWith("event")) {
            return parseAddEvent(input);
        } else if (input.stripTrailing().equals("help")) {
            return new HelpCommand();
        } else if (input.stripTrailing().equals("clear")) {
            return new ClearCommand();
        } else if (input.startsWith("find ")) {
            return parseAddFind(input);
        } else {
            throw new SeashellException("OOPS!!! Command is not recognised!");
        }
    }

    private static Command parseDone(String input) {
        String num = input.substring(5);
        int index = Integer.parseInt(num);
        System.out.println(index);
        return new DoneCommand(index);
    }

    private static Command parseDelete(String input) {
        String num = input.substring(7);
        int index = Integer.parseInt(num);
        return new DeleteCommand(index);
    }

    private static Command parseAddTodo(String input) throws SeashellException {
        String taskName = input.substring(4).stripLeading();
        if (taskName.equals("")) {
            throw new SeashellException("OOPS!!! The description of a task cannot be empty.");
        } else {
            return new AddCommand(TaskType.TODO, taskName);
        }
    }

    private static Command parseAddDeadline(String input) throws SeashellException {
        String taskName = input.substring(8).stripLeading();
        if (taskName.equals("")) {
            throw new SeashellException("OOPS!!! The description of a task cannot be empty.");
        } else if (!input.contains("/by")) {
            throw new SeashellException("OOPS!!! The syntax of adding a deadline should be [name] /by [yyyy-mm-dd]");
        } else {
            taskName = input.substring(8, input.indexOf("/by") - 1).stripLeading();
            String by = input.substring(input.indexOf("/by") + 4);
            if (!DateValidator.isValid(by)) {
                throw new SeashellException("OOPS!!! The syntax of adding a deadline should be "
                        + "[name] /by [yyyy-mm-dd]");
            }
            return new AddCommand(TaskType.DEADLINE, taskName, by);
        }
    }

    private static Command parseAddEvent(String input) throws SeashellException {
        String taskName = input.substring(5).stripLeading();
        if (taskName.equals("")) {
            throw new SeashellException("OOPS!!! The description of a task cannot be empty.");
        } else if (!input.contains("/at")) {
            throw new SeashellException("OOPS!!! The syntax of adding an event should be [name] /at [yyyy-mm-dd]");
        } else {
            taskName = input.substring(5, input.indexOf("/at") - 1).stripLeading();
            String at = input.substring(input.indexOf("/at") + 4);
            if (!DateValidator.isValid(at)) {
                throw new SeashellException("OOPS!!! The syntax of adding an event should be [name] /by [yyyy-mm-dd]");
            }
            return new AddCommand(TaskType.EVENT, taskName, at);
        }
    }

    private static Command parseAddFind(String input) {
        String toFind = input.substring(5);
        return new FindCommand(toFind);
    }

}
