package parser;

import commands.*;
import exceptions.SnomException;

import java.util.Arrays;

public class Parser {

    /**
     * Returns a command child class based on the given string of command.
     *
     * @param userInput     string of user input
     * @return               Command child classes
     * @throws SnomException if command is unknown
     */
    public static Command parse(String userInput) throws SnomException {
        String commandStr = parseCommandStr(userInput);
        String commandContent = parseCommandContent(userInput);
        CommandEnum commandEnum = CommandEnum.getCommand(commandStr);
        switch(commandEnum){
        case LIST:
            return new ListCommand(CommandEnum.LIST, commandContent);
        case FINISH:
            return new FinishCommand(CommandEnum.FINISH, commandContent);
        case DELETE:
            return new DeleteCommand(CommandEnum.DELETE, commandContent);
        case BYE:
            return new ExitCommand(CommandEnum.BYE, commandContent);
        case TODO:
            return new AddCommand(CommandEnum.TODO, commandContent);
        case DEADLINE:
            return new AddCommand(CommandEnum.DEADLINE, commandContent);
        case EVENT:
            return new AddCommand(CommandEnum.EVENT, commandContent);
        default:
            throw new SnomException("OOPS!!! I'm sorry, but I don't know what \'" + commandStr + "\' means :-(");
        }
    }

    /**
     * Returns the command string from user input
     *
     * @param userInput user input
     * @return          command as string
     */
    public static String parseCommandStr(String userInput){
        return userInput.split(" ")[0];
    }

    /**
     * Returns the content of the command from user input
     *
     * @param userInput user input
     * @return          content of the command
     */
    public static String parseCommandContent(String userInput) {
        String[] splittedInputs = userInput.split(" ", 2);
        if(splittedInputs.length >= 2){
            return splittedInputs[1];
        }else{
            return "";
        }
    }

    /**
     * Returns an array of task numbers to be finished/deleted
     *
     * @param taskNumString  string containing all the task numbers
     * @return               array of task numbers
     * @throws SnomException if no numbers was given
     */
    public static int[] parseTaskNumbers(String taskNumString) throws SnomException {
        int[] taskNumbers = Arrays.stream(taskNumString.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        if(taskNumbers.length >= 1){
            return taskNumbers;
        }else{
            throw new SnomException("Oops! Please at least give one task number");
        }
    }
}
