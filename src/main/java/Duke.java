import java.util.ArrayList;
import java.util.List;

public class Duke {
    private static String OLDLOGO =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

    private static String LOGO =
            " ______      _  ________   ____   ____ _______ \n"
                    + "|  ____/ \\  | |/ /  ____| |  _ \\ / __ \\__   __|\n"
                    + "| |__ /  \\  | ' /| |__    | |_) | |  | | | |   \n"
                    + "|  __/ /\\ \\ |  < |  __|   |  _ <| |  | | | |\n"
                    + "| | / ____ \\| . \\| |____  | |_) | |__| | | |\n"
                    + "|_|/_/    \\_\\_|\\_\\______| |____/ \\____/  |_|\n";


    private static String EXITCOMMAND = "bye";
    private static String LISTCOMMAND = "list";
    private static String DONECOMMAND = "done";
    private static String TODOCOMMAND = "todo";
    private static String DEADLINECOMMAND = "deadline";
    private static String DEADLINESPLITREGEX = " /by ";
    private static String EVENTCOMMAND = "event";
    private static String EVENTSPLITREGEX = " /at ";
    private static String DELETECOMMAND = "delete";

    private static String saveFilePath = "/data/";
    private static String saveFileName = "savedHistory.txt";
    private static IO io;
    private static FileIO fileIO;

    public static void main(String[] args) {
        io = new IO();
        fileIO = new FileIO(saveFileName, saveFilePath);
        printHelloMessage();
        boolean continueProgram = true;
        List<Task> tasks = fileIO.tryReadTaskFile();
        while (continueProgram) {
            String reply = io.readLine();
            Command command;
            try {
                command = validateCommand(reply, tasks.size());
            } catch (CommandException e) {
                io.printBotMessage(e.getMessage());
                continue;
            }
            continueProgram = processCommand(tasks,command);
        }
        io.printBotMessage("Bye. Hope to see you again soon!");
    }

    public static void saveHistory(List<Task> task) {
        fileIO.writeTasksToFIle(task);
    }

    public static void printHelloMessage() {
        io.printBotMessage("Hello from\n" + LOGO + "What can I do for you?");
    }

    public static void printDoneMessage(Task task) {
        io.printBotMessage("Nice! I've marked this task as done:\n " + task.toString());
    }

    public static void printDeleteMessage(Task task, int count) {
        io.printBotMessage("Noted. I've removed this task:\n " + task.toString()+ "\nNow you have " + count + " tasks in the list.");
    }

    public static void printAddedTaskMessage(Task task, int count) {
        io.printBotMessage("Got it. I've added this task: \n  " + task.toString() + "\nNow you have " + count + " tasks in the list.");
    }

    //Process Command given by user
    public static boolean processCommand(List<Task> tasks, Command command) {
        switch(command.getCommand()) {
            case BYE:
                return false;
            case LIST:
                io.printTasks(tasks);
                break;
            case DONE:
                int doneIndex = Integer.parseInt(command.getDescription()) - 1;
                tasks.get(doneIndex).markComplete();
                printDoneMessage(tasks.get(doneIndex));
                saveHistory(tasks);
                break;
            case TODO:
                ToDos todoTask = new ToDos(command.getDescription());
                tasks.add(todoTask);
                printAddedTaskMessage(todoTask, tasks.size());
                saveHistory(tasks);
                break;
            case DEADLINE:
                String[] deadlineDetalis = command.getDescription().split(DEADLINESPLITREGEX);
                Deadlines deadlineTask = new Deadlines(deadlineDetalis[0], deadlineDetalis[1]);
                tasks.add(deadlineTask);
                printAddedTaskMessage(deadlineTask, tasks.size());
                saveHistory(tasks);
                break;
            case EVENT:
                String[] eventDetails = command.getDescription().split(EVENTSPLITREGEX);
                Events eventTask = new Events(eventDetails[0], eventDetails[1]);
                tasks.add(eventTask);
                printAddedTaskMessage(eventTask, tasks.size());
                saveHistory(tasks);
                break;
            case DELETE:
                int deleteIndex = Integer.parseInt(command.getDescription()) - 1;
                Task deletedTask = tasks.get(deleteIndex);
                tasks.remove(deleteIndex);
                printDeleteMessage(deletedTask, tasks.size());
                saveHistory(tasks);
                break;
        }

        return true;
    }

    //Validate User Input
    public static Command validateCommand(String command, int taskCount) throws CommandException {
        if (command.equals(EXITCOMMAND)) {
            return new Command(CommandType.BYE);
        } else if (command.equals(LISTCOMMAND)) {
            return new Command(CommandType.LIST);
        }

        if (command.equals(DONECOMMAND) || command.equals(DELETECOMMAND)) {
            throw new CommandException("☹ OOPS!!! You must indicate the index of the Tasks to be " + command + ".");
        }

        if (command.equals(TODOCOMMAND) || command.equals(DEADLINECOMMAND) || command.equals(EVENTCOMMAND)) {
            throw new CommandException("☹ OOPS!!! The description of a " + command + " cannot be empty.");
        }

        int firstSplit = command.indexOf(' ');
        if (firstSplit < 0) {
            throw new CommandException(" OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        String commandName = command.substring(0, firstSplit);
        String description = command.substring(firstSplit + 1);

        if (commandName.equals(DONECOMMAND) || commandName.equals(DELETECOMMAND) || commandName.equals(TODOCOMMAND) || commandName.equals(EVENTCOMMAND) || commandName.equals(DEADLINECOMMAND)) {
            if (description.isEmpty()) {
                throw new CommandException("☹ OOPS!!! The description of a " + commandName + " cannot be empty.");
            } else if (commandName.equals(DONECOMMAND) || commandName.equals(DELETECOMMAND)) {
                try {
                    int index = Integer.parseInt(description);
                    if (index > taskCount || index < 1) {
                        throw new CommandException("☹ OOPS!!! Task number out of range.");
                    }
                } catch (NumberFormatException e) {
                    throw new CommandException("☹ OOPS!!! Invalid Task Index Format.");
                }
                if (commandName.equals(DONECOMMAND)) {
                    return new Command(CommandType.DONE, description);

                } else if (commandName.equals(DELETECOMMAND)) {
                    return new Command(CommandType.DELETE, description);
                }
            } else if (commandName.equals(TODOCOMMAND)) {
                return new Command(CommandType.TODO, description);
            } else if (commandName.equals(DEADLINECOMMAND)) {
                if (!description.contains(DEADLINESPLITREGEX))
                    throw new CommandException("☹ OOPS!!! The description of a " + DEADLINECOMMAND + " must contain Date indicated by \"" + DEADLINESPLITREGEX + "\".");


                return new Command(CommandType.DEADLINE, description);
            } else if (commandName.equals(EVENTCOMMAND)) {
                if (!description.contains(EVENTSPLITREGEX))
                    throw new CommandException("☹ OOPS!!! The description of a " + EVENTCOMMAND + " must contain Date and Duration indicated by \"" + EVENTSPLITREGEX + "\".");

                return new Command(CommandType.EVENT, description);
            }
        }

        throw new CommandException(" OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
