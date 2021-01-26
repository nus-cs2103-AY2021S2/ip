import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public enum Commands {
        USAGE, LIST,
        TODO, DEADLINE, EVENT,
        DONE, DELETE, SAVE, BYE
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        
        // Attempt to load tasks file
        ArrayList<Task> collection;
        try {
            collection = load("data/duke.txt");
        } catch (IOException e) {
            collection = new ArrayList<Task>();
        }

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
                Commands command = Commands.valueOf(input.split(" ")[0].toUpperCase());
                switch (command) {
                    case USAGE:
                        usage();
                        break;
                    case LIST:
                        list(collection);
                        break;
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
                    case SAVE:
                        save(collection);
                        break;
                    case BYE:
                        exit = bye(sc);
                }
            } catch (IllegalArgumentException e) {
                Duke.say("Oh no... I'm not trained with these commands yet...");
            } catch (DateTimeParseException e) {
                Duke.say("Oh no... Please specify a proper date... (Format: YYYY-MM-DD)");
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

    public static void usage() {
        Duke.say("Hey! These are the commands available:");
        Duke.say("\t- usage");
        Duke.say("\t- list");
        Duke.say("\t- todo <task_description>");
        Duke.say("\t- deadline <task_description> /by <date_time>");
        Duke.say("\t- event <task_description> /at <date_time>");
        Duke.say("\t- done <task_number>");
        Duke.say("\t- delete <task_number>");
        Duke.say("\t- save");
        Duke.say("\t- bye");
    }

    public static void list(ArrayList<Task> collection) {
        Duke.say("You got a total of " + collection.size() + " task(s).");
        for (int i = 0; i < collection.size(); i++) {
            Duke.say(String.format("\t%d. %s", i + 1, collection.get(i)));
        }
    }

    public static void add(ArrayList<Task> collection, String input) throws DukeException, DateTimeParseException {
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
            collection.add(new Deadline(parsedInputArr[1], LocalDate.parse(parsedInputArr[2])));
        else if (parsedInputArr[0].equals("event"))
            collection.add(new Event(parsedInputArr[1], LocalDate.parse(parsedInputArr[2])));
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

    public static ArrayList<Task> load(String dataPath) throws IOException {
        ArrayList<Task> collection = new ArrayList<Task>();
        Path dirPath = Paths.get(System.getProperty("user.dir"), "data");
        Path filePath = Paths.get(dirPath.toString(), "duke.txt");

        ArrayList<String> lines = (ArrayList<String>)Files.readAllLines(filePath);
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i).replace('|', ' ');
            String[] splitLine = line.split("[ ]+");
            System.out.println(line);

            Task task;
            if (splitLine[0].equals("T"))
                task = new Todo(splitLine[2]);
            else if (splitLine[0].equals("D")) {
                task = new Deadline(splitLine[2], LocalDate.parse(splitLine[3]));
            }
            else
                task = new Event(splitLine[2], LocalDate.parse(splitLine[3]));

            if (splitLine[1].equals("1"))
                task.markAsDone();

            collection.add(task);
        }

        return collection;
    }

    public static void save(ArrayList<Task> collection) throws DukeException {
        try {
            Path dirPath = Paths.get(System.getProperty("user.dir"), "data");
            Files.createDirectories(dirPath); // force create directories

            Path filePath = Paths.get(dirPath.toString(), "duke.txt");
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < collection.size(); i++) {
                Task task = collection.get(i);

                // Task type
                if (task instanceof Todo)
                    sb.append('T');
                else if (task instanceof Deadline)
                    sb.append('D');
                else if (task instanceof Event)
                    sb.append('E');

                // Status
                sb.append(" | ");
                if (task.getStatusIcon().equals("*"))
                    sb.append("1");
                else
                    sb.append("0");

                // Description
                sb.append(" | ");
                sb.append(task.getDescription());

                // Args
                if (task instanceof Deadline) {
                    sb.append(" | ");
                    sb.append(((Deadline)task).getBy());
                } else if (task instanceof Event) {
                    sb.append(" | ");
                    sb.append(((Event)task).getAt());
                }
                sb.append('\n');
            }
            Files.write(filePath, sb.toString().getBytes());
        } catch (IOException e) {
            throw new DukeException("I don't think there is such a file...");
        }
    }

    public static boolean bye(Scanner sc) {
        Duke.say("Are you sure? (Y/N)");
        String confirmation = Duke.ask(sc);
        if (confirmation.toLowerCase().equals("y") || confirmation.toLowerCase().equals("yes")) {
            Duke.say("Alright, take care. I hope to see you again soon!");
            return true;
        }
        Duke.say("Hmm... alright I'll stay.");
        return false;
    }
}
