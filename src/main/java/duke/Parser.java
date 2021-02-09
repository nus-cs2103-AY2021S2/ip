package duke;

import duke.command.*;
import duke.exception.InvalidCommandException;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

public class Parser {
    public static Command parse(String line) throws ParseException, InvalidCommandException {
        line = line.trim();
        Command c = null;
        String singleTokens[] = {"bye", "list", "exit", "ls"};
        String[] tokens = splitTokenIntoTwo(line, " ", singleTokens);
        switch (tokens[0].toLowerCase()) {
        case "exit":
            //Fall-through
        case "bye":
            //Leave as null
            break;
        case "ls":
            //Fall-through
        case "list":
            c = new ListCommand();
            break;
        case "done":
            c = new DoneCommand(Integer.parseInt(tokens[1]));
            break;
        case "todo":
            c = new AddCommand(new String[]{"T", tokens[1]});
            break;
        case "deadline":
            tokens = splitTokenIntoTwo(tokens[1], " /by ");
            c = new AddCommand(new String[]{"D", tokens[0], tokens[1]});
            break;
        case "event":
            tokens = splitTokenIntoTwo(tokens[1], " /at ");
            c = new AddCommand(new String[]{"E", tokens[0], tokens[1]});
            break;
        case "rm":
            //Fall-through
        case "remove":
            //Fall-through
        case "del":
            //Fall-through
        case "delete":
            c = new DeleteCommand(Integer.parseInt(tokens[1]));
            break;
        case "find":
            //Fall-through
        case "search":
            c = new SearchCommand(tokens[1]);
            break;
        default:
            throw new InvalidCommandException(tokens[0]);
        }
        return c;
    }
    private static String[] splitTokenIntoTwo(String parseTarget,String delimiter) throws ParseException {
        String[] tokens = parseTarget.split(delimiter,2);
        if (tokens.length < 2){
            throw new ParseException("Expected deliminter '"+ delimiter +"'", tokens[0].length());
        }
        return tokens;
    }

    private static String[] splitTokenIntoTwo(String parseTarget,String delimiter, String[] exception) throws ParseException{
        List<String> exceptionList = Arrays.asList(exception);
        String[] tokens = parseTarget.split(delimiter,2);
        if (!exceptionList.contains(tokens[0]) && tokens.length < 2){
            throw new ParseException("Expected deliminter '"+ delimiter +"'", tokens[0].length());
        }
        return tokens;
    }
}
