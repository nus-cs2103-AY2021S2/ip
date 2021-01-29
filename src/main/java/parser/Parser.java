package parser;

import commands.*;
import exceptions.SnomException;

public class Parser {

    public static Command parse(String commandStr) throws SnomException {
        CommandEnum commandEnum = CommandEnum.getCommand(commandStr);
        switch(commandEnum){
            case LIST:
                return new ListCommand(CommandEnum.LIST);
            case FINISH:
                return new FinishCommand(CommandEnum.FINISH);
            case DELETE:
                return new DeleteCommand(CommandEnum.DELETE);
            case BYE:
                return new ExitCommand(CommandEnum.BYE);
            case TODO:
                return new AddCommand(CommandEnum.TODO);
            case DEADLINE:
                return new AddCommand(CommandEnum.DEADLINE);
            case EVENT:
                return new AddCommand(CommandEnum.EVENT);
            case FIND:
                return new FindCommand(CommandEnum.FIND);
            default:
                throw new SnomException("OOPS!!! I'm sorry, but I don't know what '\" + input + \"' means :-(\"");
        }
    }
}
