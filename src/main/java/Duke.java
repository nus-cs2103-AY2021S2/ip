import java.util.Scanner;

import java.util.Arrays;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Duke {
    private static TaskList tasks;

    public static void main(String[] args) {
        tasks = new TaskList();
        try {
            Duke.loadData();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Duke: Hello I'm Duke, what can I do for you?");
        System.out.println("-----------------------------------------------------");
        System.out.println("Enter an input ('bye' to quit): ");
        String userInput = sc.nextLine();
        System.out.println("User Input: " + userInput);
        while (!"bye".equals(userInput)) {
            Duke.processCommand(userInput);
            Duke.writeTaskList();
            System.out.println("Enter an input ('bye' to quit): ");
            userInput = sc.nextLine();
            System.out.println("User Input: " + userInput);
        }
        System.out.println("Duke: Bye, hope to see you again! :)");
        System.out.println("-----------------------------------------------------");
    }

    public static void loadData() throws FileNotFoundException {
        File dataFolder = new File("data");
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }
        File dukeFile = new File("data/duke.txt");
        if (dukeFile.exists()) {
            Scanner s = new Scanner(dukeFile); // create a Scanner using the File as the source
            while (s.hasNext()) {
                Duke.readTaskList(s.nextLine());
            }
            Duke.printList();
        }
    }

    public static void readTaskList(String taskData) {
        String[] splits = taskData.split(" \\| ");
        if (splits[0].equals("T")) {
            Todo addedTask = new Todo(Arrays.asList(splits).get(2), Arrays.asList(splits).get(1).equals("1"));
            tasks.addTask(addedTask);
        } else if (splits[0].equals("D")) {
            try {
                Deadline addedTask = new Deadline(Arrays.asList(splits).get(2), Arrays.asList(splits).get(1).equals("1"), Arrays.asList(splits).get(3));
                tasks.addTask(addedTask);
            } catch (DukeException e) {
                System.out.println(e);
            }
        } else if (splits[0].equals("E")) {
            try {
                Event addedTask = new Event(Arrays.asList(splits).get(2), Arrays.asList(splits).get(1).equals("1"), Arrays.asList(splits).get(3));
                tasks.addTask(addedTask);
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }


    public static void writeNewFile(String filePath, String userInput) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(userInput);
        fw.close();
    }

    public static void writeTaskList() {
        String userInput = tasks.joinToTxt();
        String filePath = "data/duke.txt";
        try {
            writeNewFile(filePath, userInput);
        } catch (IOException e) {
            System.out.println("ERROR");
        }
    }

    /**
     * Prints out the list of all events.
     */
    public static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i=1; i<=Duke.tasks.getSize(); i++) {
            System.out.println("  " + i + ". " + Duke.tasks.getTask(i));
        }
        System.out.println("-----------------------------------------------------");
    }

    /**
     * Sets a task as done and prints out the task marked as done.
     * @param userInput Takes in command from user in the format: done &lt;indexOfItem&gt;
     * @throws DukeException Throws error if the indexOfItem given is invalid
     */
    public static void setAsDone(String userInput) throws DukeException {
        try {
            int indexToMark = Integer.parseInt(userInput.substring(5));
            Task ts = Duke.tasks.getTask(indexToMark);
            ts.markAsDone();
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println("  " + ts);
            System.out.println("-----------------------------------------------------");
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            throw new ArgumentException(4);
        }
    }

    /**
     * Deletes a task in the list and prints out the task deleted with the remaining number of tasks in the list.
     * @param userInput Takes in command from user in the format: delete &lt;indexOfItem&gt;
     * @throws DukeException Throws error if the indexOfItem given is invalid
     */
    public static void deleteFromList(String userInput) throws DukeException {
        try {
            int indexToDelete = Integer.parseInt(userInput.substring(7));
            Task ts = Duke.tasks.getTask(indexToDelete);
            Duke.tasks.removeTask(indexToDelete);
            System.out.println("Okay! I've removed this task: ");
            System.out.println("  " + ts);
            System.out.println("Now you have " + Duke.tasks.getSize() + " tasks in the list.");
            System.out.println("-----------------------------------------------------");
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            throw new ArgumentException(4);
        }
    }

    /**
     * Processes a command given by user.
     * @param userInput Command from user
     */
    public static void processCommand(String userInput) {
        if ("list".equals(userInput)) {
            Duke.printList();
        } else if (userInput.startsWith("done ")) {
            try {
                Duke.setAsDone(userInput);
            } catch (DukeException ex) {
                System.out.println(ex);
            }
        } else if (userInput.startsWith("delete ")) {
            try {
                Duke.deleteFromList(userInput);
            } catch (DukeException ex) {
                System.out.println(ex);
            }
        } else {
            try {
                Duke.addTask(userInput, false);
            } catch (DukeException ex) {
                System.out.println(ex);
            }
        }
    }

    /**
     * Adds a task into the list and prints out the task added with the number of tasks in the list.
     * @param userInput Takes in command from user in the given formats for each task type:
     *                  <p> todo task: todo &lt;task_description&gt; </p>
     *                  <p> deadline task: deadline &lt;task_description&gt; /by &lt;date&gt; </p>
     *                  <p> event task: event &lt;event_description&gt; /at &lt;date&gt; </p>
     * @param isDone True if task to be added is done, else false.
     * @throws DukeException Throws error if the keyword or format is wrong.
     */
    public static void addTask(String userInput, boolean isDone) throws DukeException {
        if (userInput.startsWith("todo ")) {
            String[] splits = userInput.split("todo ");
            if (splits.length == 2) {
                Todo addedTask = new Todo(Arrays.asList(splits).get(1), isDone);
                Duke.tasks.addTask(addedTask);
                System.out.println("Got it, I've added this task to the list: ");
                System.out.println("  " + addedTask);
            } else {
                throw new ArgumentException(1);
            }
        } else if (userInput.startsWith("deadline ")) {
            String[] splits = userInput.split("deadline |/by ");
            if ((splits.length == 3) && !(splits[1].equals("")) && !(splits[2].equals(""))) {
                try {
                    Deadline addedTask = new Deadline(Arrays.asList(splits).get(1), isDone, Arrays.asList(splits).get(2));
                    Duke.tasks.addTask(addedTask);
                    System.out.println("Got it, I've added this task to the list: ");
                    System.out.println("  " + addedTask);
                } catch (DukeException ex) {
                    System.out.println(ex);
                }
            } else {
                throw new ArgumentException(2);
            }
        } else if (userInput.startsWith("event ")) {
            String[] splits = userInput.split("event | /at ");
            if ((splits.length == 3) && !(splits[1].equals("")) && !(splits[2].equals(""))) {
                Event addedTask = new Event(Arrays.asList(splits).get(1), isDone, Arrays.asList(splits).get(2));
                Duke.tasks.addTask(addedTask);
                System.out.println("Got it, I've added this task to the list: ");
                System.out.println("  " + addedTask);
            } else {
                throw new ArgumentException(3);
            }
        } else {
            throw new KeywordException();
        }
        System.out.println("Now you have " + Duke.tasks.getSize() + " tasks in the list.");
        System.out.println("-----------------------------------------------------");
    }
}
