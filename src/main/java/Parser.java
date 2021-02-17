import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    //make sense of commands
    //outputs command object

    public static final Pattern
            DONE_COMMAND_FORMAT = Pattern.compile("done\\s\\d+");
    public static final Pattern
            DELETE_COMMAND_FORMAT = Pattern.compile("delete\\s+\\d+");
    public static final Pattern
            FIND_COMMAND_FORMAT = Pattern.compile("find(\\s([A-Za-z])+)+");

    public static final Pattern TODO_COMMAND_FORMAT =
            Pattern.compile("todo(\\s([A-Za-z])+)+");
    public static final Pattern DEADLINES_COMMAND_FORMAT =
            Pattern.compile("deadline(\\s([A-Za-z])+)"
                    + "+\\s/by\\s\\d\\d\\d\\d-\\d\\d-\\d\\d");
    public static final Pattern EVENTS_COMMAND_FORMAT =
            Pattern.compile("event(\\s([A-Za-z])+)"
                    + "+\\s/at\\s\\d\\d\\d\\d-\\d\\d" +
                    "-\\d\\d\\s\\d\\d\\d\\d-\\d\\d\\d\\d");


    public Parser() {
    }

    public static Command parse(String fullCommand)
            throws DukeException.TaskFormatError {
                //this.fullCommand = fullCommand;
                String ret = "";
                //sort out fullCommand type
                Command retCommand;
                if (fullCommand.equals("bye")) {
                    //create byeCommand
                    return new ByeCommand();
                } else if (fullCommand.equals("list")) {
                    //create listCommand
                    return new ListCommand();
                } else if (fullCommand.equals("sort")) {
                    return new SortCommand();
                } else {
                    final Matcher doneMatcher =
                            DONE_COMMAND_FORMAT.matcher(fullCommand);
                    final Matcher deleteMatcher =
                            DELETE_COMMAND_FORMAT.matcher(fullCommand);
                    final Matcher todoMatcher =
                            TODO_COMMAND_FORMAT.matcher(fullCommand);
                    final Matcher deadlinesMatcher =
                            DEADLINES_COMMAND_FORMAT.matcher(fullCommand);
                    final Matcher eventsMatcher =
                            EVENTS_COMMAND_FORMAT.matcher(fullCommand);
                    final Matcher findMatcher =
                            FIND_COMMAND_FORMAT.matcher(fullCommand);

                    if (doneMatcher.matches()) {
                        //create done command
                        return new DoneCommand(fullCommand);
                    } else if (deleteMatcher.matches()) {
                        //create delete command
                        return new DeleteCommand(fullCommand);
                    } else if (todoMatcher.matches()) {
                        //create todo command
                        return new TodoCommand(fullCommand);
                    } else if (deadlinesMatcher.matches()) {
                        //create deadline command
                        return new DeadlinesCommand(fullCommand);
                    } else if (eventsMatcher.matches()) {
                        //create events command
                        return new EventsCommand(fullCommand);
                    } else if (findMatcher.matches()) {
                        //create match command
                        return new FindCommand(fullCommand);
                    } else {
                        //***NO COMMAND MATCH (THROW ERROR)
                        throw new DukeException.TaskFormatError("Task is of incorrect format");
                    }


                }

    }

}
