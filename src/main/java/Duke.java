import jdk.jfr.Event;

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

    private static IO io;

    public static void main(String[] args) {
        io = new IO();
        printHelloMessage();

        List<Task> Tasks = new ArrayList<Task>();
        while (true) {
            String reply = io.readLine();
            try {
                validateCommand(reply, Tasks.size());
            } catch (CommandException e) {
                io.printBotMessage(e.getMessage());
                continue;
            }
            if (reply.equals(EXITCOMMAND)) {
                break;
            } else if (reply.equals(LISTCOMMAND)) {
                io.printTasks(Tasks);
            } else if (reply.startsWith(DONECOMMAND)) {
                Integer index = Integer.parseInt(reply.substring(5));
                Tasks.get(index - 1).markCompleted();
                printDoneMessage(Tasks.get(index - 1));
            } else if (reply.startsWith(TODOCOMMAND)) {
                ToDos newTask = new ToDos(reply.substring(TODOCOMMAND.length() + 1));
                Tasks.add(newTask);
                printAddedTaskMessage(newTask, Tasks.size());
            } else if (reply.startsWith(DEADLINECOMMAND)) {
                String message = reply.substring(DEADLINECOMMAND.length() + 1);
                String[] messages = message.split(DEADLINESPLITREGEX);
                Deadlines newTask = new Deadlines(messages[0], messages[1]);
                Tasks.add(newTask);
                printAddedTaskMessage(newTask, Tasks.size());
            } else if (reply.startsWith(EVENTCOMMAND)) {
                String message = reply.substring(EVENTCOMMAND.length() + 1);
                String[] messages = message.split(EVENTSPLITREGEX);
                Events newTask = new Events(messages[0], messages[1]);
                Tasks.add(newTask);
                printAddedTaskMessage(newTask, Tasks.size());
            }
        }
        io.printBotMessage("Bye. Hope to see you again soon!");
    }

    public static void printHelloMessage() {
        io.printBotMessage("Hello from\n" + LOGO + "What can I do for you?");
    }

    public static void printDoneMessage(Task task) {
        io.printBotMessage("Nice! I've marked this task as done:\n " + task.toString());
    }

    public static void printAddedTaskMessage(Task task, int count) {
        io.printBotMessage("Got it. I've added this task: \n  " + task.toString() + "\nNow you have " + count + " tasks in the list.");
    }

    public static void validateCommand(String command, int taskCount) throws CommandException {
        if(command.equals(EXITCOMMAND)||command.equals(LISTCOMMAND))
            return;
        if(command.equals(DONECOMMAND)) {
            throw new CommandException("☹ OOPS!!! You must indicate the index of the Tasks to be done.");
        }

        if(command.equals(TODOCOMMAND)||command.equals(DEADLINECOMMAND)||command.equals(EVENTCOMMAND)) {
            throw new CommandException("☹ OOPS!!! The description of a "+command+" cannot be empty.");
        }

        int firstSplit = command.indexOf(' ');
        if(firstSplit<0) {
            throw new CommandException(" OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        String commandName = command.substring(0, firstSplit);
        String description= command.substring(firstSplit + 1);

        if(commandName.equals(DONECOMMAND)||commandName.equals(TODOCOMMAND)||commandName.equals(EVENTCOMMAND)||commandName.equals(DEADLINECOMMAND)) {
            if(description.isEmpty()) {
                throw new CommandException("☹ OOPS!!! The description of a "+commandName+" cannot be empty.");
            }else if (commandName.equals(DONECOMMAND)) {
                try {
                    int index = Integer.parseInt(description);
                    if(index > taskCount || index < 1) {
                        throw new CommandException("☹ OOPS!!! Task number out of range.");
                    }
                }catch (NumberFormatException e) {
                    throw new CommandException("☹ OOPS!!! Invalid Task Index Format.");
                }
            }else if (commandName.equals(DEADLINECOMMAND)) {
                if(!description.contains(DEADLINESPLITREGEX))
                    throw new CommandException("☹ OOPS!!! The description of a "+DEADLINECOMMAND+" must contain Date indicated by \""+DEADLINESPLITREGEX+"\".");
            } else if (commandName.equals(EVENTCOMMAND)) {
                if(!description.contains(EVENTSPLITREGEX))
                    throw new CommandException("☹ OOPS!!! The description of a "+EVENTCOMMAND+ " must contain Date and Duration indicated by \""+EVENTSPLITREGEX+"\".");
            }
        }else {
            throw new CommandException(" OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
