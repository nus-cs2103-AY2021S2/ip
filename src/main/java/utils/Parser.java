package utils;

import enums.Commands;

import exceptions.DukeException;
import exceptions.InvalidOptionException;
import exceptions.UnrecognisedCommandException;
import handlers.TaskHandler;
import tasks.Task;

import java.util.ArrayList;
import java.util.Locale;

public class Parser {

    public static void handleInput(String s, ArrayList<Task> taskList) throws DukeException {
        String input = s.trim();
        Commands command;

        if (input.equals("")) {
            return;
        }

        String[] inputArr = input.split(" ", 2);

        try {
            command = Commands.valueOf(inputArr[0].toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            throw new UnrecognisedCommandException(inputArr[0].toUpperCase(Locale.ROOT));
        }

        switch (command) {
        case LIST:
            TaskHandler.listTasks(taskList);
            break;
        case DONE:
            try {
                TaskHandler.doneTask(inputArr[1], taskList);
                break;
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new InvalidOptionException(command.name());
            }
        case DELETE:
            try {
                TaskHandler.deleteTask(inputArr[1], taskList);
                break;
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new InvalidOptionException(command.name());
            }
        case BYE:
            Formatter.printlnWithIndentation("Bye. Hope to see you again soon!");
            System.exit(0);
            break;
        case TODO:
            try {
                TaskHandler.addTask(command, inputArr[1], taskList);
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidOptionException(command.name());
            }
            break;
        case DEADLINE:
            try {
                TaskHandler.addTask(command, inputArr[1], taskList);
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidOptionException(command.name());
            }
            break;
        case EVENT:
            try {
                TaskHandler.addTask(command, inputArr[1], taskList);
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidOptionException(command.name());
            }
            break;
        default:
            Formatter.printBetweenLines("Invalid command, please try again!");
        }
    }
}
