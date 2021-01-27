import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    // use collection for holding all tasks
//    private static final ArrayList<Task> taskList = new ArrayList<>();
    private static TaskList taskList;

    // formatting for print messages
    private static final String lines = "    ______________________________________________";
    private static final String indent = "      ";
    private static final String taskIndent = "  "; // just an extra indent for listing tasks

    /**
     * Mark specified task done
     *
     * @param i off-by-one index of a task in array list
     */
    private static void markDone(int i) throws InvalidArgumentException {
        if (i < 1 || i > taskList.size()) {
            throw new InvalidArgumentException(invalidNumErrMsg(i, 1, taskList.size()));
        }

        taskList.get(i - 1).markAsDone();

        print(new String[]{"Good work! I've marked this task done:",
                taskIndent + taskList.get(i - 1)});
    }

    private static void printTaskList() {
        System.out.println(lines);

        System.out.println(indent + "Your tasks:");

        for (int i = 0; i < taskList.size(); i++) {
            System.out.print(taskIndent);
            System.out.println(indent + (i + 1) + "." + taskList.get(i));
        }

        System.out.println();
        System.out.println(indent + "You have " + taskList.size() + " tasks. ");

        System.out.println(lines);
    }

    // helper method to format chat bot responses
    // prints all strings in messages array in a separate indented line
    // maybe can think of splitting strings that are too long into different lines
    private static void print(String[] messages) {
        System.out.println(lines);

        for (int i = 0; i < messages.length; i++) {
            System.out.println(indent + messages[i]);
        }

        System.out.println(lines);
    }

    // print formatted single-line message
    private static void print(String message) {
        System.out.println(lines);

        System.out.println(indent + message);

        System.out.println(lines);
    }

    private static void addTask(Task t) {
        taskList.add(t);

        String[] messages = {
                "Success. I've added this task:",
                taskIndent + t // standardize this indent,
        };

        print(messages);
    }

    private static String invalidNumErrMsg(int i, int min, int max) {
        String errMsg = "Invalid list index given: " + i
                + ". Number needs to be between " + min + " and " + max + " (inclusive). ";
        return errMsg;
    }

    private static void deleteTask(int i) throws InvalidArgumentException {
        if (i < 1 || i > taskList.size()) {
            throw new InvalidArgumentException(invalidNumErrMsg(i, 1, taskList.size()));
        }

        print(new String[]{"Got you. I've deleted this task:",
                taskIndent + taskList.get(i - 1)});

        taskList.remove(i - 1);
    }

    // for every new command to support, need to add to cases here and if statements in parse method
    // could simplify it somehow
    // todo definitely need to simplify, too much duplication?
    // but how else do you want to detect the first word if not for the space?
    private static void handleOnlyFirstArgGiven(String command)
            throws MissingArgumentException, UnsupportedCommandException {
        String errMsg;

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

    private static void parseNextArgs(String firstWord, String userInput, int firstSpaceIndex)
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

                markDone(secondArg);

            } else if (firstWord.equals("todo")) {

                desc = userInput.substring(firstSpaceIndex + 1).trim();
                addTask(new Todo(desc));

            } else if (firstWord.equals("deadline")) {

                secondCmdIndex = userInput.indexOf("/by"); // assuming valid
                int byIndex = secondCmdIndex; // for readability
                desc = userInput.substring(firstSpaceIndex + 1, byIndex - 1).trim();
                thirdArg = userInput.substring(byIndex + 3).trim();

                addTask(new Deadline(desc, thirdArg));

            } else if (firstWord.equals("event")) {

                secondCmdIndex = userInput.indexOf("/at"); // assuming valid
                int atIndex = secondCmdIndex; // for readability
                desc = userInput.substring(firstSpaceIndex + 1, atIndex - 1).trim();
                thirdArg = userInput.substring(atIndex + 3).trim();

                addTask(new Event(desc, thirdArg));
            } else if (firstWord.equals("delete")) {
                desc = userInput.substring(firstSpaceIndex + 1).trim();
                int secondArg = Integer.parseInt(desc);

                deleteTask(secondArg);
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
    private static void checkIfTooManyArgs(String command) throws InvalidArgumentException {
        switch (command) {
        case "bye":
        case "list":
            throw new InvalidArgumentException("Too many arguments provided. Did you mean '" + command + "'?");
        default:
            break;
        }
    }

    private static void checkInvalidOnEmptyList(String command) throws InvalidArgumentException {
        if (taskList.isEmpty() && (command.equals("done") || command.equals("delete"))) {
            throw new InvalidArgumentException("This command cannot be done on an empty task list.");
        }
    }


    private static boolean checkAndHandleIfOneArgIsValid(String command)
            throws MissingArgumentException, UnsupportedCommandException {
        // check if only one argument needed
        if (command.equals("bye")) {
            printExitMsg();
            return false;
        } else if (command.equals("list")) {
            printTaskList();
            return true;
        }

        // print correct error message when insufficient arguments
        handleOnlyFirstArgGiven(command); // try catch block not necessary, because of throws
        return true;
    }


    // format your exception printing here
    private static void printException(String errMsg) {
        print(new String[]{"Oops, Kiwi couldn't process that.", taskIndent + errMsg});
    }

    // parse done, todos, deadline, event, delete commands
    // make enums for supported commands?
    // return whether to keep scanner open
    private static boolean parseInputLine(String userInput)  {

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
            taskList.saveTasksList();
            
        } catch (UnsupportedCommandException | InvalidArgumentException | MissingArgumentException e) {
            // is it better to detect unsupported first command earlier?
            // currently being detected at the end of many if blocks 
            // maybe should save list of supported commands in another file (String[]{todo, deadline, event}
            printException(e.getMessage());
            return true;
        } catch (IOException e) {
            // todo see if you can offer better help
            print(new String[]{"Oops, error occurred in saving the file.", e.getMessage()});
            return true;
        } catch (Exception e) {
            String errMsg = "didn't expect this exception " + e;
            printException(errMsg);
            return true;
        }

        return true;
    }

    public static void printExitMsg() {
        print("Bye. See you again soon!");
    }

    public static void intro() {
        String logo = " ______\n"
                        + "/______\\ Kiwi's\n"
                        + "|______|     Inn\n"
                        + "####################";

        // intro message
        System.out.println(logo);
        print(new String[]{"Welcome, traveller. I'm Kiwi.", "What would you like to do today?"});

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // random testing things
        // should be rearranged elsewhere
        try {
            taskList = TaskList.setupTaskList();
        } catch (IOException e) {
            print(new String[]{"Something went wrong in loading the task file and parsing",
                    e.getMessage()});
        }

        intro();

        // variables to reuse
        String userInput;
        boolean remainOpen = true;

        while (remainOpen) {
            userInput = sc.nextLine().trim();
            remainOpen = parseInputLine(userInput);
        }

        sc.close();
    }
}
