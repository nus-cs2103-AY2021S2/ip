import java.io.IOException;

public class Parser {
    private final TaskList taskList;

    public Parser(TaskList taskListToWriteInto) {
        this.taskList = taskListToWriteInto;
    }

    // for every new command to support, need to add to cases here and if statements in parse method
    // could simplify it somehow --> can tasktype enums each have error messages associated with them
    // todo definitely need to simplify, too much duplication?
    // but how else do you want to detect the first word if not for the space?
    private static void handleOnlyFirstArgGiven(String command)
            throws MissingArgumentException, UnsupportedCommandException {
        String errMsg = "";

        switch (command) {
        case "todo":
            errMsg = "Please include a description for your todo.";
            break;
        case "event":
            errMsg = "Please include a description and an /at argument for your event.";
            break;
        case "deadline":
            errMsg = "Please include a description and a /by argument for your deadline.";
            break;
        case "done":
            errMsg = "Please include the list item number of the task to mark done.";
            break;
        case "delete":
            errMsg = "Please include the list item number of the task to delete.";
            break;
        default:
            throw new UnsupportedCommandException();
        }

        throw new MissingArgumentException(errMsg);
    }

    // for missing second/third arguments
    // replace String taskType with Enum later
    private static String determineErrMsg(String taskType, int positionMissing) {
        String errMsg = "";

        switch (taskType) {
        case "todo":
            errMsg = "Missing argument " + positionMissing + ". Please include a todo description.";
            break;
        case "event":
            if (positionMissing == 2) {
                errMsg = "Missing argument " + positionMissing + ". Please include an event description.";
            } else if (positionMissing == 3) {
                errMsg = "Missing an /at argument. Please include an event timing.";
            }
            break;
        case "deadline":
            if (positionMissing == 2) {
                errMsg = "Missing argument " + positionMissing + ". Please include a description of the deadline.";
            } else if (positionMissing == 3) {
                errMsg = "Missing a /by argument. Please include a deadline.";
            }
            break;
        default:
            errMsg = "Missing arguments for this task type.";
            break;
        }
        return errMsg;
    }


    private void parseNextArgs(String firstWord, String userInput, int firstSpaceIndex)
            throws MissingArgumentException, UnsupportedCommandException, InvalidArgumentException {
        // some variables declared upfront
        // todo declare these variables in if blocks below for readability
        String desc;
        String thirdArg;
        int secondCmdIndex = 0;

        try {
            if (firstWord.equals("done")) {

                desc = userInput.substring(firstSpaceIndex + 1).trim();
                int secondArg = Integer.parseInt(desc);

                taskList.markDone(secondArg);

            } else if (firstWord.equals("todo")) {

                desc = userInput.substring(firstSpaceIndex + 1).trim();
                taskList.addTask(new Todo(desc));

            } else if (firstWord.equals("deadline")) {

                secondCmdIndex = userInput.indexOf("/by"); // assuming valid
                int byIndex = secondCmdIndex; // for readability
                desc = userInput.substring(firstSpaceIndex + 1, byIndex - 1).trim();
                thirdArg = userInput.substring(byIndex + 3).trim();

                taskList.addTask(new Deadline(desc, thirdArg));

            } else if (firstWord.equals("event")) {

                secondCmdIndex = userInput.indexOf("/at"); // assuming valid
                int atIndex = secondCmdIndex; // for readability
                desc = userInput.substring(firstSpaceIndex + 1, atIndex - 1).trim();
                thirdArg = userInput.substring(atIndex + 3).trim();

                taskList.addTask(new Event(desc, thirdArg));
            } else if (firstWord.equals("delete")) {
                desc = userInput.substring(firstSpaceIndex + 1).trim();
                int secondArg = Integer.parseInt(desc);

                // todo checkIfValidListIndex();
                taskList.deleteTask(secondArg);
            } else if (firstWord.equals("find")) {

                desc = userInput.substring(firstSpaceIndex + 1).trim();
                taskList.findTasks(desc);

            } else {
                throw new UnsupportedCommandException();
            }
        } catch (StringIndexOutOfBoundsException e) {
            if (secondCmdIndex == -1) {
                throw new MissingArgumentException(determineErrMsg(firstWord, 3), e);
            } else if (firstSpaceIndex + 1 > secondCmdIndex - 1) {
                throw new MissingArgumentException(determineErrMsg(firstWord, 2), e);
            }
        }
    }

    // this check could be done using an enum property?
    // static?
    private static void checkIfTooManyArgs(String command) throws InvalidArgumentException {
        switch (command) {
        case "bye":
        case "list":
            throw new InvalidArgumentException("Too many arguments provided. Did you mean '" + command + "'?");
        default:
            break;
        }
    }

    private void checkInvalidOnEmptyList(String command) throws InvalidArgumentException {
        if (taskList.isEmpty() && (command.equals("done") || command.equals("delete"))) {
            throw new InvalidArgumentException("This command cannot be done on an empty task list.");
        }
    }

    // return value represents if scanner needs to remain open
    private boolean checkAndHandleIfOneArgIsValid(String command)
            throws MissingArgumentException, UnsupportedCommandException {

        if (command.equals("bye")) {
            Ui.printExitMsg();
            return false;
        } else if (command.equals("list")) {
            Ui.printTaskList(taskList);
            return true;
        }

        // print correct error message when insufficient arguments
        handleOnlyFirstArgGiven(command);
        return true;
    }

    // should exceptions be all handled in parser or elsewhere? how do you test if the correct exception is thrown
    // make enums for supported commands?

    /**
     * Main driver for parsing any user input
     * @param userInput User input from terminal
     * @return returns whether to keep scanner open
     */
    public boolean parseInputLine(String userInput)  {

        // SETTING UP THE VARIABLES NEEDED FOR ERROR CHECKING / PARSING
        int firstSpaceIndex = userInput.indexOf(" "); // todo can consider using split(" ", 2)?
        String firstWord;

        if (firstSpaceIndex == -1) {
            firstWord = userInput.trim();
        } else {
            firstWord = userInput.substring(0, firstSpaceIndex);
        }


        // ERROR CHECKING AND PROCESSING DIFFERENT COMMANDS
        try {
            // check for commands that are invalid on an empty task list
            checkInvalidOnEmptyList(firstWord);

            // if there's only one arg, this function handles whether the one arg is valid
            // and responds accordingly, or whether it's invalid and prints out the correct
            // error messages
            if (firstSpaceIndex == -1) {
                return checkAndHandleIfOneArgIsValid(firstWord);
            }

            // check if more than one arg provided for commands that require only one
            checkIfTooManyArgs(firstWord);

            // just enough args
            parseNextArgs(firstWord, userInput, firstSpaceIndex);

            // UPON SUCCESSFUL COMMAND EXECUTION
            // save tasksFile
            // todo still saveTasksList in parser?
            taskList.saveTasksList();

        } catch (UnsupportedCommandException | InvalidArgumentException | MissingArgumentException e) {
            // is it better to detect unsupported first command earlier?
            // currently being detected at the end of many if blocks
            // maybe should save list of supported commands in another file (String[]{todo, deadline, event}
            Ui.printException(e.getMessage());
            return true;
        } catch (IOException e) {
            // todo see if you can offer better help
            Ui.print(new String[]{"Oops, error occurred in saving the file.", e.getMessage()});
            return true;
        } catch (Exception e) {
            String errMsg = "didn't expect this exception " + e;
            Ui.printException(errMsg);
            return true;
        }

        return true;
    }
}
