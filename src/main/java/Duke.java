import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> collection = new ArrayList<Task>();
        Boolean exit = false;
        String input;

        // Welcomes user
        greeting();

        // Receive action
        do {
            try {
                input = Duke.ask(sc);

                if (input.toLowerCase().startsWith("todo") || input.toLowerCase().startsWith("deadline") || input.toLowerCase().startsWith("event"))
                    add(collection, input);
                else if (input.toLowerCase().startsWith("done"))
                    done(collection, input);
                else if (input.toLowerCase().equals("list"))
                    list(collection);
                else if (input.toLowerCase().equals("bye"))
                    exit = exit();
                else
                    invalid();
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

    public static String[] parseInput(String input) {
        String[] inputArr = input.split(" ");
        String taskType = inputArr[0];
        String taskDesc = "";
        String taskArg = "";

        // Get description
        int i;
        for (i = 1; i < inputArr.length; i++) {
            if (inputArr[i].toLowerCase().equals("/by") || inputArr[i].toLowerCase().equals("/at")) {
                break;
            } else {
                if (!taskDesc.equals(""))
                    taskDesc += " ";
                taskDesc += inputArr[i];
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
        Duke.say("Is there anything I can do for you today?");
    }

    public static boolean exit() {
        Duke.say("Alright, take care. I hope to see you again soon!");
        return true;
    }

    public static void list(ArrayList<Task> collection) {
        Duke.say("You got a total of " + collection.size() + " task(s).");
        for (int i = 0; i < collection.size(); i++) {
            System.out.println(String.format("\t%d. %s", i + 1, collection.get(i)));
        }
    }

    public static void add(ArrayList<Task> collection, String input) {
        // Parse input
        String[] parsedInputArr = Duke.parseInput(input);

        // Add to collection
        if (parsedInputArr[0].toLowerCase().equals("todo"))
            collection.add(new Todo(parsedInputArr[1]));
        else if (parsedInputArr[0].toLowerCase().equals("deadline"))
            collection.add(new Deadline(parsedInputArr[1], parsedInputArr[2]));
        else if (parsedInputArr[0].toLowerCase().equals("event"))
            collection.add(new Event(parsedInputArr[1], parsedInputArr[2]));
        Duke.say("Got it, I have added the task \"" + parsedInputArr[1] + "\" to your collection.");
    }

    public static void done(ArrayList<Task> collection, String input) throws DukeException {
        try {
            int itemIdx = Integer.parseInt(input.split(" ")[1]) - 1;
            collection.get(itemIdx).markAsDone();
            Duke.say("Task \"" + collection.get(itemIdx).getDescription() + "\" is marked as done.");
        } catch (NumberFormatException e) {
            throw new DukeException("I need a task number...");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("I don't think there is such a task...");
        }
    }

    public static void invalid() throws DukeException {
        throw new DukeException("I'm not trained with these commands yet...");
    }

}
