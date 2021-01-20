import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static String lines = "    ______________________________________________";
    private static String indent = "      ";
    private static String taskIndent = "  "; // just an extra indent for listing tasks

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
    // e.g. delete and done shouldn't be inputted when list is empty, whether or not numArgs is correct
    // but how else do you want to detect the first word if not for the space?
    private static void handleOnlyFirstArgGiven(String oneArg)
            throws MissingArgumentException, UnsupportedCommandException {
        String errMsg = "";

        switch (oneArg) {
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

    // parse done, todos, deadline or event commands
    // make enums for supported commands?
    private static void parseMultiWordCommand(String userInput) throws MissingArgumentException,
            UnsupportedCommandException, InvalidArgumentException {

        int firstSpaceIndex = userInput.indexOf(" "); // todo can consider using split(" ", 2)?
        String firstWord;

        if (firstSpaceIndex == -1) {
            // why is this needed - detect commands that are invalid before detecting missing arguments
            firstWord = userInput.trim();
        } else {
            firstWord = userInput.substring(0, firstSpaceIndex);
        }

        // check for invalid commands on an empty task list
        if (taskList.isEmpty() && (firstWord.equals("done") || firstWord.equals("delete"))) {
            throw new InvalidArgumentException("Invalid command. The task list is empty.");
        }

        // only one word was provided
        if (firstSpaceIndex == -1) {
            handleOnlyFirstArgGiven(userInput); // seems like try catch block not necessary, because of throws
            return;
        }

        // some variables declared upfront
        // todo declare these variables in if blocks below for readability
        String desc = "";
        String thirdArg = "";
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
        } catch (UnsupportedCommandException e) {
            throw e;
        } catch (InvalidArgumentException e) {
            throw e;
        } catch (Exception e) {
            String errMsg = "don't know " + e;
            print(new String[]{errMsg});
        }

        // catch exceptions where substring end is wrong i.e. extra arguments not found?
    }

    public static void main(String[] args) {
        String logo =
                " ______\n"
                        + "/______\\ Kiwi's\n"
                        + "|______|     Inn\n"
                        + "####################";

        Scanner sc = new Scanner(System.in);

        // intro message
        System.out.println(logo);
        print(new String[]{"Welcome, traveller. I'm Kiwi.", "What would you like to do today?"});

        // variables to reuse
        String userInput;
        String[] toPrint = new String[1];
        String bye = "bye";

        while (true) {
            userInput = sc.nextLine().trim();
            if (userInput.equals(bye)) {
                toPrint[0] = "Bye. See you again soon!";
                print(toPrint);
                sc.close();
                break;
            } else if (userInput.equals("list")) {
                printTaskList();
            } else {
                // assumed to be a valid command and have space
                try {
                    parseMultiWordCommand(userInput.trim());
                } catch (MissingArgumentException | UnsupportedCommandException | InvalidArgumentException e) {
                    print(new String[]{e.toString()});
                }
            }
        }
    }
}
