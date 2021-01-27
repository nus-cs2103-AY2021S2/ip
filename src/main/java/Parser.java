import java.util.Arrays;

public class Parser {
    private TaskList tasks;

    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Processes a command given by user.
     * @param userInput Command from user
     */
    protected void processCommand(String userInput) {
        if ("list".equals(userInput)) {
            printList();
        } else if (userInput.startsWith("done ")) {
            try {
                setAsDone(userInput);
            } catch (DukeException ex) {
                System.out.println(ex);
            }
        } else if (userInput.startsWith("delete ")) {
            try {
                deleteFromList(userInput);
            } catch (DukeException ex) {
                System.out.println(ex);
            }
        } else {
            try {
                addTaskToList(userInput, false);
            } catch (DukeException ex) {
                System.out.println(ex);
            }
        }
    }

    /**
     * Sets a task as done and prints out the task marked as done.
     * @param userInput Takes in command from user in the format: done &lt;indexOfItem&gt;
     * @throws DukeException Throws error if the indexOfItem given is invalid
     */
    protected void setAsDone(String userInput) throws DukeException {
        try {
            int indexToMark = Integer.parseInt(userInput.substring(5));
            Task ts = tasks.getTask(indexToMark);
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
    protected void deleteFromList(String userInput) throws DukeException {
        try {
            int indexToDelete = Integer.parseInt(userInput.substring(7));
            Task ts = tasks.getTask(indexToDelete);
            tasks.removeTask(indexToDelete);
            System.out.println("Okay! I've removed this task: ");
            System.out.println("  " + ts);
            System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
            System.out.println("-----------------------------------------------------");
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            throw new ArgumentException(4);
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
    protected void addTaskToList(String userInput, boolean isDone) throws DukeException {
        if (userInput.startsWith("todo ")) {
            String[] splits = userInput.split("todo ");
            if (splits.length == 2) {
                Todo addedTask = new Todo(Arrays.asList(splits).get(1), isDone);
                tasks.addTask(addedTask);
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
                    tasks.addTask(addedTask);
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
                tasks.addTask(addedTask);
                System.out.println("Got it, I've added this task to the list: ");
                System.out.println("  " + addedTask);
            } else {
                throw new ArgumentException(3);
            }
        } else {
            throw new KeywordException();
        }
        System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
        System.out.println("-----------------------------------------------------");
    }

    /**
     * Prints out the list of all events.
     */
    protected void printList() {
        System.out.println("Here are the tasks in your list:");
        System.out.println(tasks.toString());
        System.out.println("-----------------------------------------------------");
    }



}
