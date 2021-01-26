import java.io.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class Duke {
    public static void main(String[] args) throws IOException {

        //Level 7
        File file = new File("src/main/data/duke.txt");
        if (file.createNewFile()) {
            System.out.println("Hard Disk created.");
        } else {
            System.out.println("Hard Disk loaded.");
        }

        // Initialise list
        List<Task> tasks = new ArrayList<>();
        int numOfItems = getFileLinesCount(file);
        loadHardDrive(file,tasks);
        String[] command = {"list", "bye", "todo", "deadline", "event"};

        // Level 1 - 6.
        System.out.println("Hello, I am Duke, your personal Assistant. How may I help you today?:");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine().trim();

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
        saveHardDrive(file, tasks);
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
     * Loads hard drive from the file
     * @param file Input file (hard drive)
     * @param tasks current list of tasks
     * @throws FileNotFoundException throws file not found exception if file is not found at target location.
     */
    public static void loadHardDrive(File file, List<Task> tasks) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()){
            String[] entry = sc.nextLine().split(" / ");
            switch (entry[0]){
                case "T":
                    tasks.add(new Todo(entry[2], Boolean.parseBoolean(entry[1])));
                    break;
                case "E":
                    tasks.add(new Event(entry[2], entry[3], Boolean.parseBoolean(entry[1])));
                    break;
                case "D":
                    tasks.add(new Deadline(entry[2], entry[3], Boolean.parseBoolean(entry[1])));
                    break;
            }
        }
        sc.close();
    }

    /**
     * Saves the information from the current list of tasks to the hard drive (duke.txt)
     * @param file target file
     * @param tasks current list of tasks
     * @throws IOException throw IOException when there is an error with the file.
     */
    public static void saveHardDrive(File file, List<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(file);
        for (Task t: tasks) {
            if (t instanceof Todo) {
                fw.write(String.format("T / %s / %s%n", t.getIsDone(), t.getDescription()));
            } else if (t instanceof Event) {
                fw.write(String.format("E / %s / %s / %s%n", t.getIsDone(), t.getDescription(), ((Event) t).getTimeslot()));
            } else if (t instanceof Deadline) {
                fw.write(String.format("D / %s / %s / %s%n", t.getIsDone(), t.getDescription(), ((Deadline) t).getDeadline()));
            }
        }
        fw.close();
    }

    /**
     * Returns the number of lines in the file.
     * @param file input file
     * @return returns an int of the number of lines in the text file
     * @throws IOException throw IOException when there is an error with the file.
     */
    public static int getFileLinesCount(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        int lines = 0;
        while (reader.readLine() != null) {lines++;};
        reader.close();
        return lines;
    }


     /** Method to check if a certain string is of date format.
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