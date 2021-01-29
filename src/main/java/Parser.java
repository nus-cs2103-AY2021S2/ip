import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

public class Parser {
    public static Command parse(String line) throws ParseException, InvalidCommandException {
        Command c = null;
        String singleTokens[] = {"bye", "list"};
        String[] tokens = splitTokenIntoTwo(line, " ", singleTokens);
        switch (tokens[0]) {
        case "bye":
            //Leave as null
            break;
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
        case "delete":
            c = new DeleteCommand(Integer.parseInt(tokens[1]));
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
