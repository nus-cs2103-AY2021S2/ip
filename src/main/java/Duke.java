import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static String lines = "    ______________________________________________";
    private static String indent = "      ";
    private static String taskIndent = "  "; // just an extra indent for listing tasks

    /**
     * Mark specified task done
     * @param i off-by-one index of a task in array list
     */
    private static void markDone(int i) {
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

    private static void handleOnlyFirstArgGiven(String oneArg)
            throws MissingArgumentException, UnsupportedCommandException {
        String errMsg = "";

        switch (oneArg){
        case "done":
            errMsg = "Please include the list item number of the task to mark done.";
            break;
        case "todo":
            errMsg = "Please include a description for your todo.";
            break;
        case "event":
            errMsg = "Please include a description and an /at argument for your event.";
            break;
        case "deadline":
            errMsg = "Please include a description and a /by argument for your deadline.";
            break;
        default:
            throw new UnsupportedCommandException();
        }

        throw new MissingArgumentException(errMsg);
//        print(new String[]{errMsg});
    }


    // for missing second/third arguments
    // replace String taskType with Enum later
    private static String determineErrMsg(String taskType, int positionMissing) {
        String errMsg = "";

        switch (taskType){
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
    private static void parseCommand(String userInput) throws MissingArgumentException, UnsupportedCommandException {
        int firstSpaceIndex = userInput.indexOf(" "); // todo can consider using split(" ", 2)?

        // only one word was provided

        if (firstSpaceIndex == -1) {
            handleOnlyFirstArgGiven(userInput); // seems like try catch block not necessary, because of throws
            return;
        }

        String firstWord = userInput.substring(0, firstSpaceIndex); // don't include the space

        // declare variables here to help with exception checking
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
                    parseCommand(userInput.trim());
                } catch (MissingArgumentException | UnsupportedCommandException e) {
                    print(new String[]{e.toString()});
                }
            }
        }
    }
}
