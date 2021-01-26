import javax.swing.text.DateFormatter;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello, I am Duke, your personal Assistant. How may I help you today?:");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine().trim();

        // Initialise list
        List<Task> tasks = new ArrayList<>();
        int numOfItems = 0;
        String[] command = {"list", "bye", "todo", "deadline", "event"};

        // User input
        while (!input.equals("bye")) {
            String[] inputArr = input.split(" ");
            try {
                switch(inputArr[0]) {
                    case "list":
                        System.out.println("Here are the tasks on your list:");
                        for (int i = 1; i <= numOfItems; i++) {
                            String output = String.format("%s. %s", i, tasks.get(i - 1).toString());
                            System.out.println(output);
                        }
                        break;
                    case "done":
                        doneCommand(tasks, inputArr);
                        break;
                    case "todo":
                        toDoCommand(inputArr, tasks, numOfItems, input);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(tasks.get(numOfItems).toString());
                        numOfItems++;
                        System.out.println("Now you have " + numOfItems + " tasks in the list.");
                        break;
                    case "event":
                        eventCommand(inputArr, tasks, numOfItems, input);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(tasks.get(numOfItems).toString());
                        numOfItems++;
                        System.out.println("Now you have " + numOfItems + " tasks in the list.");
                        break;
                    case "deadline":
                        deadlineCommand(inputArr, tasks, input);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(tasks.get(numOfItems).toString());
                        numOfItems++;
                        System.out.println("Now you have " + numOfItems + " tasks in the list.");
                        break;
                    case "delete":
                        System.out.println("Noted. I've removed this task: ");
                        System.out.println(tasks.get(Integer.parseInt(inputArr[1]) - 1));
                        deleteCommand(inputArr, tasks);
                        numOfItems--;
                        System.out.println("Now you have " + numOfItems + " tasks in the list.");
                        break;
                    default:
                        throw new DukeWrongInputException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeMissingInputException e) {
                System.out.println(e.toString());
            } catch (DukeWrongInputException e) {
                System.out.println(e.toString());
            }
            input = sc.nextLine().trim();
        }
        byeCommand();
        sc.close();
    }

    /**
     * Command method when the user types in "bye".
     */
    public static void byeCommand(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Command method when the user types in "done X"
     * @param tasks List of tasks.
     * @param inputArr Command input.
     */
    public static void doneCommand(List<Task>tasks, String[] inputArr ){
        System.out.println("Nice! I've marked this task as done:");
        Task doneTask = tasks.get(Integer.parseInt(inputArr[1]) - 1);
        doneTask.markAsDone();
        System.out.println(tasks.get(Integer.parseInt(inputArr[1]) - 1).toString());
    }

    /**
     * Command method when the user types in "todo".
     * @param inputArr Command input in array form.
     * @param tasks List of tasks.
     * @param numOfItems Number of items in the list.
     * @param input Original input
     * @throws DukeMissingInputException
     */
    public static void toDoCommand(String[] inputArr, List<Task> tasks, int numOfItems, String input)
            throws DukeMissingInputException {
        String description = "";
        if (input.equals("todo")) {
            throw new DukeMissingInputException("OOPS!!! The description of a todo cannot be empty.");
        } else {
            for (int i = 1; i < inputArr.length; i++) {
                description += (inputArr[i] + " ");
            }
        }
        description = description.trim();
        tasks.add(new Todo(description)) ;
        tasks.get(numOfItems).toString();
    }

    /**
     * Command method when the user types in "deadline".
     * @param inputArr Command input in array form.
     * @param tasks List of tasks.
     * @param input Original input.
     * @throws DukeMissingInputException Throws Missing Input Exception when missing input.
     */
    public static void deadlineCommand(String[] inputArr, List<Task> tasks, String input)
            throws DukeMissingInputException, DukeWrongInputException {
        String description = "";
        String deadline = "";
        boolean foundBy = false;
        if (input.equals("deadline")) {
            throw new DukeMissingInputException("OOPS!!! The description of a deadline cannot be empty.");
        } else {
            for (int i = 1; i < inputArr.length; i++) {
                if (inputArr[i].equals("/by")) {
                    foundBy = true;
                    continue;
                }
                if (foundBy) {
                    deadline += (inputArr[i] + " ");
                } else {
                    description += (inputArr[i] + " ");
                }
            }
        }
        deadline = deadline.trim();
        if (isDate(deadline)){
            LocalDate dateDeadline = LocalDate.parse(deadline, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            dateDeadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            tasks.add(new Deadline(description, dateDeadline));
        } else {
            throw new DukeWrongInputException("OOPS!!! Please enter your deadline in the format: deadline /by yyyy-mm-dd :-(");
        }
    }

    /**
     * Command method when the user types in "event".
     * @param inputArr Command input in array form.
     * @param tasks List of tasks.
     * @param numOfItems Number of items in the list.
     * @param input Original input.
     * @throws DukeMissingInputException
     */
    public static void eventCommand(String[] inputArr, List<Task> tasks, int numOfItems, String input)
            throws DukeMissingInputException {
        String description = "";
        String time = "";
        boolean foundAt = false;
        if (input.equals("event")) {
            throw new DukeMissingInputException("OOPS!!! The description of an event cannot be empty.");
        } else {
            for (int i = 1; i < inputArr.length; i++) {
                if (inputArr[i].equals("/at")){
                    foundAt = true;
                    continue;
                }
                if (foundAt) {
                    time += (inputArr[i] + " ");
                } else {
                    description += (inputArr[i] + " ");
                }
            }
        }
        time = time.trim();
        tasks.add(new Event(description, time));
    }

    /**
     * Command method when the user types in "event".
     * @param inputArr Command input in array form.
     * @param tasks List of tasks.
     */
    public static void deleteCommand(String[] inputArr, List<Task> tasks){
        tasks.remove(Integer.parseInt(inputArr[1]) - 1);
    }

    /**
     * Method to check if a certain string is of date format.
     * @param str - input string to be checked if it is in the format of a string
     * @return boolean value telling us whether the string is a date or just simple text.
     */
    public static boolean isDate(String str) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-mm-DD");
        try {
            LocalDate.parse(str, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}