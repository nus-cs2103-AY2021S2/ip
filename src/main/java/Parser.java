import java.time.LocalDate;

/**
 * Parser processes commands.
 */
public class Parser {

    /** List of tasks */
    private TaskList tasks;

    /**
     * Initializes a newly created Parser object with a given list of tasks.
     *
     * @param tasks List of tasks to process commands on.
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Processes a command given by user.
     * <p> Commands recognised: </p>
     * <p> 1. List out all tasks in list: list </p>
     * <p> 2. Set task in list as done: done &lt;position_of_task_in_list&gt; </p>
     * <p> 3. Delete task in list: delete &lt;position_of_task_in_list&gt; </p>
     * <p> 4. Add todo-task: todo &lt;task_description&gt; </p>
     * <p> 5. Add deadline-task: deadline &lt;task_description&gt; /by &lt;date&gt; </p>
     * <p> 6. Add event-task: event &lt;event_description&gt; /at &lt;date&gt; </p>
     * <p> 7. Find task using keyword: find &lt;keyword&gt; </p>
     * <p> 8. Remind tasks due soon: remind (optional: &lt;coming_within_this_number_of_days&gt;) </p>
     * <p> 9. Exit Duke: bye </p>
     *
     * @param userInput Command from user.
     * @return String representing the output of processing command.
     */
    protected String processCommand(String userInput) {
        if ("list".equals(userInput)) {
            return printList();
        } else if (userInput.startsWith("done ")) {
            return setAsDone(userInput);
        } else if (userInput.startsWith("delete ")) {
            return deleteFromList(userInput);
        } else if (userInput.startsWith("find ")) {
            return findFromList(userInput);
        } else if (userInput.startsWith("remind")) {
            return remindTasks(userInput);
        } else {
            return addTaskToList(userInput, false);
        }
    }

    /**
     * Sets a task as done and return the task marked as done.
     *
     * @param userInput Takes in command from user in the format: done &lt;indexOfItem&gt;
     * @return String representing the output of processing command.
     */
    protected String setAsDone(String userInput) {
        String textToReturn = "";
        try {
            int indexToMark = Integer.parseInt(userInput.substring(5));
            Task ts = tasks.getTask(indexToMark);
            ts.markAsDone();
            textToReturn += "Nice! I've marked this task as done: \n";
            textToReturn += "  " + ts;
            return textToReturn;
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            return new ArgumentException(4).toString();
        }
    }

    /**
     * Deletes a task in the list and returns the task deleted with the remaining number of tasks in the list.
     *
     * @param userInput Takes in command from user in the format: delete &lt;indexOfItem&gt;
     * @return String representing the output of processing command.
     */
    private String deleteFromList(String userInput) {
        String textToReturn = "";
        try {
            int indexToDelete = Integer.parseInt(userInput.substring(7));
            Task ts = tasks.getTask(indexToDelete);
            tasks.removeTask(indexToDelete);
            textToReturn += "Okay! I've removed this task: \n";
            textToReturn += "  " + ts;
            textToReturn += "\nNow you have " + tasks.getSize() + " tasks in the list.";
            return textToReturn;
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            return new ArgumentException(4).toString();
        }
    }

    /**
     * Finds all tasks in the list that matches the keyword given by user.
     *
     * @param userInput Takes in command from user in the format: find &lt;keyword&gt;
     * @return String representing the output of processing command.
     */
    private String findFromList(String userInput) {
        TaskList tasksMatchingKeyword = new TaskList();
        String keyword = userInput.substring(5);
        for (int i = 1; i <= tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            if (task.getDescription().contains(keyword)) {
                tasksMatchingKeyword.addTask(task);
            }
        }
        return printList(tasksMatchingKeyword);
    }

    /**
     * Reminds user of all deadlines/events in the list that is due soon.
     * With no input of number of days, default is 1 week.
     *
     * @param userInput Takes in command from user in the format:
     *                  remind (optional: &lt;coming_within_this_number_of_days&gt;)
     * @return String representing the output of processing command.
     */
    private String remindTasks(String userInput) {
        TaskComparator compareTasks = new TaskComparator();
        TaskList tasksToRemind = new TaskList();
        String trimmedUserInput = userInput.trim();
        int daysToGet = 7;
        if (!trimmedUserInput.equals("remind")) {
            try {
                daysToGet = Integer.parseInt(userInput.substring(7));
            } catch (NumberFormatException ex) {
                return "Invalid input: Please try again with a valid integer or leave it blank! :)";
            }
        }
        LocalDate fromDate = LocalDate.now();
        LocalDate toDate = LocalDate.now().plusDays(daysToGet);
        for (int i = 1; i <= tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            if (!task.isDone() && !compareTasks.isTodo(task)) {
                LocalDate date = compareTasks.getTaskDate(task);
                if (!date.isBefore(fromDate) && !date.isAfter(toDate)) {
                    tasksToRemind.addTask(task);
                }
            }
        }
        return printList(tasksToRemind);
    }

    /**
     * Adds a task into the list and returns the task added with the number of tasks in the list.
     * Calls the respective functions to add different tasks into the list.
     * Else returns an error string describing the error.
     *
     * @param userInput Takes in command from user in the given formats for each task type:
     *                  <p> todo task: todo &lt;task_description&gt; </p>
     *                  <p> deadline task: deadline &lt;task_description&gt; /by &lt;date&gt; </p>
     *                  <p> event task: event &lt;event_description&gt; /at &lt;date&gt; </p>
     * @param isDone True if task to be added is done, else false.
     * @return String representing the output of processing command.
     */
    protected String addTaskToList(String userInput, boolean isDone) {
        String textToReturn = "";
        if (userInput.startsWith("todo ")) {
            textToReturn += addTodo(userInput, isDone);
        } else if (userInput.startsWith("deadline ")) {
            textToReturn += addDeadline(userInput, isDone);
        } else if (userInput.startsWith("event ")) {
            textToReturn += addEvent(userInput, isDone);
        } else {
            textToReturn += new KeywordException().toString();
        }
        textToReturn += "\nYou currently have " + tasks.getSize() + " tasks in the list.";
        return textToReturn;
    }

    /**
     * Adds a Todo-task into the list and returns the confirmation of adding the task.
     * Else returns an error string describing the error.
     *
     * @param userInput Takes in command from user in the given format:
     *                  <p> todo task: todo &lt;task_description&gt; </p>
     * @param isDone True if task to be added is done, else false.
     * @return String representing the output of processing command.
     */
    private String addTodo(String userInput, boolean isDone) {
        String[] splits = userInput.split("todo ");
        String textToReturn = "";
        if (splits.length != 2) {
            return new ArgumentException(1).toString();
        }
        String description = splits[1];
        Todo addedTask = new Todo(description, isDone);
        tasks.addTask(addedTask);
        textToReturn += "Got it, I've added this task to the list: \n";
        textToReturn += "  " + addedTask;
        return textToReturn;
    }

    /**
     * Adds a Deadline-task into the list and returns the confirmation of adding the task.
     * Else returns an error string describing the error.
     *
     * @param userInput Takes in command from user in the given format:
     *                  <p> deadline task: deadline &lt;task_description&gt; /by &lt;date&gt; </p>
     * @param isDone True if task to be added is done, else false.
     * @return String representing the output of processing command.
     */
    private String addDeadline(String userInput, boolean isDone) {
        String[] splits = userInput.split("deadline |/by ");
        String textToReturn = "";
        if ((splits.length != 3) || (splits[1].equals("")) || (splits[2].equals(""))) {
            return new ArgumentException(2).toString();
        }
        String description = splits[1];
        String date = splits[2];
        try {
            Deadline addedTask = new Deadline(description, isDone, date);
            tasks.addTask(addedTask);
            textToReturn += "Got it, I've added this task to the list: \n";
            textToReturn += "  " + addedTask;
        } catch (DukeException ex) {
            textToReturn = ex.toString();
        }
        return textToReturn;
    }

    /**
     * Adds a Event-task into the list and returns the confirmation of adding the task.
     * Else returns an error string describing the error.
     *
     * @param userInput Takes in command from user in the given format:
     *                  <p> event task: event &lt;event_description&gt; /at &lt;date&gt; </p>
     * @param isDone True if task to be added is done, else false.
     * @return String representing the output of processing command.
     */
    private String addEvent(String userInput, boolean isDone) {
        String[] splits = userInput.split("event | /at ");
        String textToReturn = "";
        if ((splits.length != 3) || (splits[1].equals("")) || (splits[2].equals(""))) {
            return new ArgumentException(3).toString();
        }
        String description = splits[1];
        String date = splits[2];
        try {
            Event addedTask = new Event(description, isDone, date);
            tasks.addTask(addedTask);
            textToReturn += "Got it, I've added this task to the list: \n";
            textToReturn += "  " + addedTask;
        } catch (DukeException ex) {
            textToReturn = ex.toString();
        }
        return textToReturn;
    }

    /**
     * Returns the representation of the list of all events.
     *
     * @return String representing the list of all events.
     */
    protected String printList() {
        String listRepresentation = "Here are the tasks in your list:\n";
        listRepresentation += tasks.toString();
        return listRepresentation;
    }

    /**
     * Returns the list of all requested events.
     *
     * @return String representing the list of all matching events.
     */
    protected String printList(TaskList tasks) {
        String listRepresentation = "Here are the tasks you requested for:\n";
        listRepresentation += tasks.toString();
        return listRepresentation;
    }

}
