package jeff;

import jeff.command.Command;
import jeff.command.CommandBye;
import jeff.command.CommandDeadline;
import jeff.command.CommandDelete;
import jeff.command.CommandDone;
import jeff.command.CommandEvent;
import jeff.command.CommandFind;
import jeff.command.CommandList;
import jeff.command.CommandReschedule;
import jeff.command.CommandToDo;

/**
 * Parses a single user input line and executes the input.
 */
public class Parser {

    /**
     * Parses the message passed in by user into a Command object.
     *
     * @param message Message passed in by user.
     * @return Command object representing the message.
     * @throws JeffException If an error is encountered.
     */
    public static Command parse(String message) throws JeffException {
        String[] messageSplit = message.split(",", 2);
        String[] messageFrontSplit = messageSplit[0].trim().split(" ", 2);
        String messageCmd = messageFrontSplit[0].toLowerCase();
        CommandTypes cmd;
        try {
            cmd = CommandTypes.valueOf(messageCmd);
        } catch (IllegalArgumentException e) {
            throw new JeffException("I can't understand the message");
        }
        switch (cmd) {
        case list:
            return new CommandList();
        case todo:
            if (messageFrontSplit.length < 2) {
                throw new JeffException("please provide a description for todo");
            }
            return new CommandToDo(messageFrontSplit[1]);
        case deadline:
            if (messageFrontSplit.length < 2) {
                throw new JeffException("please provide a description for deadline");
            }
            if (messageSplit.length < 2) {
                throw new JeffException("please provide a date and time for deadline");
            }
            return new CommandDeadline(messageFrontSplit[1], messageSplit[1]);
        case event:
            if (messageFrontSplit.length < 2) {
                throw new JeffException("please provide a description for event");
            }
            if (messageSplit.length < 2) {
                throw new JeffException("please provide a date and time for event");
            }
            return new CommandEvent(messageFrontSplit[1], messageSplit[1]);
        case done:
            if (messageFrontSplit.length < 2) {
                throw new JeffException("wrong number of arguments for done");
            }
            return new CommandDone(messageFrontSplit[1]);

        case delete:
            if (messageFrontSplit.length < 2) {
                throw new JeffException("wrong number of arguments for delete");
            }
            return new CommandDelete(messageFrontSplit[1]);
        case bye:
            return new CommandBye();
        case find:
            if (messageFrontSplit.length < 2) {
                throw new JeffException("enter keyword(s) to find task(s) by");
            }
            return new CommandFind(messageFrontSplit[1]);
        case reschedule:
            if (messageFrontSplit.length < 2) {
                throw new JeffException("please provide index of deadline/event to reschedule");
            }
            if (messageSplit.length < 2) {
                throw new JeffException("please provide a date and time for deadline/event to be rescheduled");
            }
            return new CommandReschedule(messageFrontSplit[1], messageSplit[1]);
        default:
            assert false : "Code should not reach this point of execution.";
            throw new JeffException("something went wrong");
        }
    }
}
