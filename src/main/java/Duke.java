import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String bound = "____________________________________________________________";
        String topBound = "____________________________________________________________\n";
        String bottomBound = "\n____________________________________________________________\n";
        Scanner sc = new Scanner(System.in);
        DukeList list = new DukeList();
        String path = "data/duke.txt";
        File dir = new File("data");
        File f = new File(path);
        try {
            if (!dir.exists()) {
                dir.mkdirs();
            } else { //read the file and make the  duke obj
                readFromFile(path, list);
            }
            if (!f.exists()) f.createNewFile();
        } catch (IOException e) {
            System.out.println("Something went wrong!");
            sc.close();
        }

        System.out.println(topBound + logo + "\nHello! I'm Duke\nWhat can I do for you today?" + bottomBound);

         inputLoop: while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] commandArray = input.split(" ", 2); //extracts the command word from the rest of the input
            String commandWord = commandArray[0];

            switch (commandWord) {
            case "bye":
                try {
                    list.writeToFile(path);
                    System.out.println(topBound + "Good bye! Stay calm and keep coding o7." + bottomBound);
                    sc.close();

                } catch (IOException e) {
                    System.out.println("Error in saving list!");
                }
                break inputLoop;
            case "list":
                System.out.println(bound);
                System.out.println("Here are the tasks in your list:");
                list.printAll();
                System.out.println(topBound);
                break;
            case "done":
                try {
                    int x = Integer.parseInt(commandArray[1]) - 1;
                    list.done(x);
                    System.out.println(topBound + "Good job! I've marked this task as done:\n" + "  " + list.get(x)
                            + bottomBound);
                } catch (IndexOutOfBoundsException e) {
                    // Task number x does not exist
                    System.out.println(topBound + "Uh oh! Task does not exist and can not be completed!" + bottomBound);
                } catch (NumberFormatException e) {
                    // parameter for done is not an Integer
                    System.out.println(topBound + "Uh oh! done must be followed by an Integer ie. done 1.\nTry again!"
                            + bottomBound);
                }
                break;
            case "reset":
                System.out.println(bound);
                list.deleteAll();
                System.out.println("All tasks deleted!");
                System.out.println(topBound);
                break;
            case "delete":
                try {
                    int x = Integer.parseInt(commandArray[1]) - 1;
                    Task curr = list.get(x);
                    list.delete(x);
                    System.out.println(topBound + "Got it! I've removed this task:\n" + " " + curr + "\n" + "You have "
                            + list.noOfTasks() + " tasks left in the list." + bottomBound);
                } catch (IndexOutOfBoundsException e) {
                    // task number x does not exist
                    System.out.println(topBound + "Uh Oh! Task does not exist and cannot be deleted!" + bottomBound);
                } catch (NumberFormatException e) {
                    // parameter for delete is not an Integer
                    System.out.println(topBound
                            + "Uh Oh! delete must be followed by an Integer, ie. delete 1.\nTry again!" + bottomBound);
                }
                break;
            case "todo":
                try {
                    String[] parametersArray = commandArray[1].split("/");
                    String taskName = parametersArray[0].stripTrailing();
                    if (taskName.equals("")) {
                        throw new DukeException("todo");
                    }
                    ToDos curr = new ToDos(taskName);
                    list.add(curr);
                    System.out.println(topBound + "Got it! I've added this task:\n" + "  " + curr
                            + "\nNow you have " + list.noOfTasks() + " tasks in the list." + bottomBound);
                } catch (DukeException e) {
                    // command came without a description
                    System.out.println(topBound + e.getMessage() + bottomBound);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(topBound
                            + "Uh Oh! todo must include a name of the task.\nTry again!" + bottomBound);
                }
                break;
            case "deadline":
                try {
                    String[] parametersArray = commandArray[1].split("/");
                    String taskName = parametersArray[0].stripTrailing();
                    if (taskName.equals("")) {
                        throw new DukeException("deadline");
                    }
                    LocalDate d = LocalDate.parse(parametersArray[1].replaceFirst("by", "")
                            .stripLeading());
                    Deadlines curr = new Deadlines(taskName, d);
                    list.add(curr);
                    System.out.println(topBound + "Got it! I've added this task:\n" + "  " + curr
                            + "\nNow you have " + list.noOfTasks() + " tasks in the list." + bottomBound);
                } catch (DukeException e) {
                    // command came without a description
                    System.out.println(topBound + e.getMessage() + bottomBound);
                } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
                    // command came without proper date time format
                    System.out.println(topBound
                            + "Uh Oh! event must include '/at YYYY-MM-DD'.\nTry again!" + bottomBound);
                }
                break;
            case "event":
                try {
                    String[] parametersArray = commandArray[1].split("/");
                    String taskName = parametersArray[0].stripTrailing();
                    if (taskName.equals("")) {
                        throw new DukeException("event");
                    }
                    LocalDate d = LocalDate.parse(parametersArray[1].replaceFirst("at", "")
                            .stripLeading());
                    Events curr = new Events(taskName, d);
                    list.add(curr);
                    System.out.println(topBound + "Got it! I've added this task:\n" + "  " + curr
                            + "\nNow you have " + list.noOfTasks() + " tasks in the list." + bottomBound);
                } catch (DukeException e) {
                    // command came without a description
                    System.out.println(topBound + e.getMessage() + bottomBound);
                } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
                    // command came without proper date time format
                    System.out.println(topBound
                            + "Uh Oh! event must include '/at YYYY-MM-DD'.\nTry again!" + bottomBound);
                }
                break;
            case "show":
                try {
                    LocalDate day = LocalDate.parse(commandArray[1].replaceFirst("show", "").stripLeading());
                    System.out.println(topBound + "Here are your tasks on " +
                            day.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":");
                    list.showTaskOnDay(day);
                    System.out.println(bound);
                } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
                    System.out.println(topBound
                            + "Uh Oh! show must be followed by a date in YYYY-MM-DD format.\nTry again!" + bottomBound);
                }
                break;
            default:
                System.out.println(topBound + "Uh Oh! I'm sorry, but I don't know what that means." + bottomBound);
                break;
            }
        }
    }
    public static void readFromFile(String filePath, DukeList list) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            String curr = s.nextLine();
            String[] currArray = curr.split("\\|");
            
            if (currArray[0].equals("T")) {
                ToDos currTask;
                if (currArray[1].equals("0")) {
                    currTask = new ToDos(currArray[2]);
                } else {
                    currTask = new ToDos(currArray[2], true);
                }
                list.add(currTask);
            } else if (currArray[0].equals("D")) {
                Deadlines currTask;
                if (currArray[1].equals("0")) {
                    currTask = new Deadlines(currArray[2], LocalDate.parse(currArray[3]));
                } else {
                    currTask = new Deadlines(currArray[2], LocalDate.parse(currArray[3]), true);
                }
                list.add(currTask);
            } else if (currArray[0].equals("E")) {
                Events currTask;
                if (currArray[1].equals("0")) {
                    currTask = new Events(currArray[2], LocalDate.parse(currArray[3]));
                } else {
                    currTask = new Events(currArray[2], LocalDate.parse(currArray[3]), true);
                }
                list.add(currTask);
            }
        }
    }
}
