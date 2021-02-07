package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import main.java.command.AddDeadlineCommand;
import main.java.command.AddEventCommand;
import main.java.command.AddTodoCommand;
import main.java.command.Command;
import main.java.command.DeleteCommand;
import main.java.command.DoneCommand;
import main.java.command.ExitCommand;
import main.java.command.FindCommand;
import main.java.command.ListCommand;
import main.java.command.WrongCommand;
import main.java.exceptions.IllegalInputFormatException;

/**
 * A Parser class that is used to parse user input as Commands
 */
public class Parser {

    /**
     * Creates a parser instance
     */
    public Parser() {

    }

    /**
     * Parse a particular command string and create a command to be returned
     * @param commandStr user input string command
     * @return parsed command
     * @throws Exception parsing exceptions including wrong input, index out of bounds due to wrong input etc.
     */
    public Command parse(String commandStr) {
        Command command = null;
        try {
            if (commandStr == null) {
                command = new ExitCommand();
            } else if (commandStr.toLowerCase().equals("bye")) {
                command = new ExitCommand();
            } else if (commandStr.trim().toLowerCase().equals("list")) {
                command = new ListCommand();
            } else if (commandStr.toLowerCase().split(" ")[0].equals("done")) {
                int doneIndex = Integer.parseInt(commandStr.split(" ")[1]) - 1;
                command = new DoneCommand(doneIndex);
            } else if (commandStr.toLowerCase().split(" ")[0].equals("delete")) {
                List<Integer> deleteList = new ArrayList<>();
                try {
                    String[] deleteArr = commandStr.substring(7).split(" ");
                    for (String indexStr : deleteArr) {
                        deleteList.add(Integer.parseInt(indexStr) - 1);
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                command = new DeleteCommand(deleteList);
            } else if (commandStr.toLowerCase().split(" ")[0].equals("todo")) {
                String todoName = commandStr.substring(5);
                if (todoName.trim().length() == 0) {
                    throw new IllegalInputFormatException();
                }
                command = new AddTodoCommand(todoName);
            } else if (commandStr.toLowerCase().split(" ")[0].equals("deadline")) {
                String[] arr = commandStr.split(" /");
                String name = arr[0].substring(9);
                String[] arr2 = arr[1].split(" ", 2);
                try {
                    LocalDate timeDate = LocalDate.parse(arr2[1]);
                    command = new AddDeadlineCommand(name, arr2[0], timeDate);
                } catch (DateTimeParseException e) {
                    command = new AddDeadlineCommand(name, arr2[0], arr2[1]);
                }
            } else if (commandStr.toLowerCase().split(" ")[0].equals("event")) {
                String[] arr = commandStr.split(" /");
                String name = arr[0].substring(6);
                String[] arr2 = arr[1].split(" ", 2);
                try {
                    LocalDate timeDate = LocalDate.parse(arr2[1]);
                    command = new AddEventCommand(name, arr2[0], timeDate);
                } catch (DateTimeParseException e) {
                    command = new AddEventCommand(name, arr2[0], arr2[1]);
                }
            } else if (commandStr.toLowerCase().split(" ")[0].equals("find")) {
                String keyword = commandStr.substring(5);
                if (keyword.trim().length() == 0) {
                    throw new IllegalInputFormatException();
                }
                command = new FindCommand(keyword);
            } else {
                command = new WrongCommand();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return command;
    }
}
