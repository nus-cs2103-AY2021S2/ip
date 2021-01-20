import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public enum CommandsEnum {
        TODO, DEADLINE, EVENT,
        DONE, DELETE, LIST,
        BYE, USAGE
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> collection = new ArrayList<Task>();
        boolean exit = false;

        // Welcomes user
        greeting();
        // Receive action
        do {
            try {
                String input = Duke.ask(sc);
                // Check if input is an empty line
                if (input.equals(""))
                    continue;

                // Process input
                CommandsEnum command = CommandsEnum.valueOf(input.split(" ")[0].toUpperCase());
                switch (command) {
                    case TODO:
                    case DEADLINE:
                    case EVENT:
                        add(collection, input);
                        break;
                    case DONE:
                        done(collection, input);
                        break;
                    case DELETE:
                        delete(collection, input);
                        break;
                    case LIST:
                        list(collection);
                        break;
                    case BYE:
                        exit = bye();
                        break;
                    case USAGE:
                        usage();
                }
            } catch (IllegalArgumentException e) {
                invalid(); // Invalid enum value (i.e. no such command)
            } catch (DukeException e) {
                Duke.say("Oh no... " + e.getMessage());
            }
        } while (!exit || sc.hasNextLine());
    }

    public static void say(String message, Boolean newLine) {
        System.out.print(">> " + message);
        if (newLine)
            System.out.print("\n");
    }

    public static void say(String message) {
        Duke.say(message, true);
    }

    public static String ask(Scanner sc) {
        System.out.print("<< ");
        return sc.nextLine();
    }

    public static String[] parseInput(String input) throws DukeException {
        String[] inputArr = input.split(" ");
        String taskType = inputArr[0].toLowerCase();
        String taskDesc = "";
        String taskArg = "";

        // Get description
        int i;
        for (i = 1; i < inputArr.length; i++) {
            if (taskType.equals("todo") || (!inputArr[i].toLowerCase().equals("/by") && !inputArr[i].toLowerCase().equals("/at"))) {
                if (!taskDesc.equals(""))
                    taskDesc += " ";
                taskDesc += inputArr[i];
            } else {
                // Ensure no misuse of arguments
                if (!inputArr[i].toLowerCase().equals("/by") && taskType.equals("deadline"))
                    throw new DukeException("You're confusing me with parameters from other commands...");
                else if (!inputArr[i].toLowerCase().equals("/at") && taskType.equals("event"))
                    throw new DukeException("You're confusing me with parameters from other commands...");

                break;
            }
        }

        // Get argument
        for (i = i + 1; i < inputArr.length; i++) {
            if (!taskArg.equals(""))
                taskArg += " ";
            taskArg += inputArr[i];
        }

        return new String[]{taskType, taskDesc, taskArg};
    }

    public static void greeting() {
        System.out.println("____________________________________________________________");
        System.out.println(" ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|");
        System.out.println("____________________________________________________________");
        Duke.say("Hey, hello there! I'm Duke, your personal chat bot.");
        Duke.say("To know more about what I can do, type 'usage'.");
        Duke.say("Sooooo... Is there anything I can do for you today?");
    }

    public static boolean bye() {
        Duke.say("Alright, take care. I hope to see you again soon!");
        return true;
    }

    public static void list(ArrayList<Task> collection) {
        Duke.say("You got a total of " + collection.size() + " task(s).");
        for (int i = 0; i < collection.size(); i++) {
            Duke.say(String.format("\t%d. %s", i + 1, collection.get(i)));
        }
    }

    public static void add(ArrayList<Task> collection, String input) throws DukeException {
        // Parse input
        String[] parsedInputArr = Duke.parseInput(input);

        // Ensure task description and argument cannot be empty
        if (parsedInputArr[1].equals("")) {
            throw new DukeException("I need a description of your task...");
        } else if (parsedInputArr[2].equals("")) {
            if (parsedInputArr[0].equals("deadline"))
                throw new DukeException("I need to know when your task ends...");
            if (parsedInputArr[0].equals("event"))
                throw new DukeException("I need to know the time period of your event...");
        }

        // Add to collection
        if (parsedInputArr[0].equals("todo"))
            collection.add(new Todo(parsedInputArr[1]));
        else if (parsedInputArr[0].equals("deadline"))
            collection.add(new Deadline(parsedInputArr[1], parsedInputArr[2]));
        else if (parsedInputArr[0].equals("event"))
            collection.add(new Event(parsedInputArr[1], parsedInputArr[2]));
        Duke.say("Got it, I have added the task '" + parsedInputArr[1] + "' to your collection.");
    }

    public static void done(ArrayList<Task> collection, String input) throws DukeException {
        try {
            int itemIdx = Integer.parseInt(input.split(" ")[1]) - 1;
            boolean status = collection.get(itemIdx).markAsDone();
            if (!status)
                throw new IllegalArgumentException();
            Duke.say("Task '" + collection.get(itemIdx).getDescription() + "' is marked as done.");
        } catch (NumberFormatException e) {
            throw new DukeException("I need a task number...");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("I don't think there is such a task...");
        } catch (IllegalArgumentException e) {
            throw new DukeException("Task had already been marked as done...");
        }
    }

    public static void delete(ArrayList<Task> collection, String input) throws DukeException {
        try {
            int itemIdx = Integer.parseInt(input.split(" ")[1]) - 1;
            Task task = collection.remove(itemIdx);
            Duke.say("Task '" + task.getDescription() + "' has been deleted.");
        } catch (NumberFormatException e) {
            throw new DukeException("I need a task number...");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("I don't think there is such a task...");
        }
    }

    public static void invalid() {
        Duke.say("I'm not trained with these commands yet...");
    }

    public static void usage() {
        Duke.say("Hey! These are commands available:");
        Duke.say("\t1. todo <task_description>");
        Duke.say("\t2. deadline <task_description> /by <date_time>");
        Duke.say("\t3. event <task_description> /at <date_time>");
        Duke.say("\t4. done <task_number>");
        Duke.say("\t5. delete <task_number>");
        Duke.say("\t6. list");
        Duke.say("\t7. bye");
        Duke.say("\t8. usage");
    }
}
