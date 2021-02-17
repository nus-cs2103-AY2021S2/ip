package duke.commands;


import duke.exceptions.DukeException;
import duke.exceptions.HelpException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.ui.Ui;

/**
 * Displays the valid commands in the list
 */
public class HelpCommand extends Command{
    private final static String BYE_PROMPT = "bye - Saves the Task List into a .txt file and closes the program.\n\n";

    private final static String DEADLINE_PROMPT = "deadline - Adds a deadline task into the Task List.\n"
                                                + "Enter \"deadline TASK_NAME /by YYYY-MM-DD HH:MM\"\n"
                                                + "Example: deadline English Essay /by 2021-03-08 23:59\n\n";

    private final static String DELETE_PROMPT = "delete - Deletes the task at the specified position.\n"
                                              + "Enter \"delete INDEX_OF_TASK\"\n"
                                              + "Example: delete 1\n\n";

    private final static String DONE_PROMPT = "done - Marks the task at the specified position as completed.\n"
                                            + "Enter \"done INDEX_OF_TASK\"\n"
                                            + "Example: done 1\n\n";


    private final static String EVENT_PROMPT = "event - Adds an event task into the Task List.\n"
                                             + "Enter \"event TASK_NAME /at LOCATION_NAME YYYY-MM-DD HH:MM\"\n"
                                             + "Example: event John's Birthday /at MBS 2021-04-15 19:00\n\n";


    private final static String FIND_PROMPT = "find - Finds tasks in the Task List that contains the keyword\n"
                                            + "Enter \"find KEYWORD\"\n"
                                            + "Example: find meeting\n\n";

    private final static String HELP_PROMPT = "help - Returns a list of commands and their usage.\n"
                                            + "Enter \"help\" returns the full list of commands\n"
                                            + "Enter \"help COMMAND\" returns the specific instructions for the command\n"
                                            + "Example 1: help\n"
                                            + "Example 2: help event\n\n";


    private final static String LIST_PROMPT = "list - Returns the list of tasks in the Task List\n"
                                            + "Enter \"list\"\n\n";

    private final static String TODO_PROMPT = "todo - Adds a ToDo task into the Task List\n"
                                            + "Enter \"todo TASK_NAME\"\n"
                                            + "Example: todo read finish LOTR\n\n";

    private final static String HELP_MESSAGE_ALL = "Master, here is a list of all the commands: \n";

    private final static String HELP_MESSAGE_SPECIFIC = "Here is the information on the command, Master.\n";

    public HelpCommand(String description){

        super(description);
    }

    /**
     * Generates a list of all Commands.
     *
     * @param taskList contains the list of tasks
     * @param ui the UI object
     * @param storage where tasks in taskList will be stored when program ends
     * @return String containing details on all the commands and their usage
     * @throws DukeException
     */
    public String execute (TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String[] inputList = description.trim().split(" ", 2);

        //if user only enters "help"
        if (inputList.length == 1){
            return HELP_MESSAGE_ALL + BYE_PROMPT + DEADLINE_PROMPT
                    + DELETE_PROMPT + DONE_PROMPT + EVENT_PROMPT
                    + FIND_PROMPT   + HELP_PROMPT + LIST_PROMPT
                    + TODO_PROMPT;
        }

        //if user enter help with another command
        if (Parser.isBye(inputList[1])){
            return HELP_MESSAGE_SPECIFIC + BYE_PROMPT;
        }
        if (Parser.isDeadline(inputList[1])){
            return HELP_MESSAGE_SPECIFIC + DEADLINE_PROMPT;
        }

        if (Parser.isDelete(inputList[1])){
            return HELP_MESSAGE_SPECIFIC + DELETE_PROMPT;
        }
        if (Parser.isCompleted(inputList[1])){
            return HELP_MESSAGE_SPECIFIC + DONE_PROMPT;
        }
        if (Parser.isFind(inputList[1])){
            return HELP_MESSAGE_SPECIFIC + FIND_PROMPT;
        }
        if (Parser.isHelp(inputList[1])){
            return HELP_MESSAGE_SPECIFIC + HELP_PROMPT;
        }
        if (Parser.isList(inputList[1])){
            return HELP_MESSAGE_SPECIFIC + LIST_PROMPT;
        }
        if (Parser.isEvent(inputList[1])){
            return HELP_MESSAGE_SPECIFIC + EVENT_PROMPT;
        }
        if (Parser.isToDo(inputList[1])){
            return HELP_MESSAGE_SPECIFIC + TODO_PROMPT;
        }
        else {
            throw new HelpException();
        }
    }

    @Override
    public boolean isEndOfProgram() {
        return false;
    }
}
