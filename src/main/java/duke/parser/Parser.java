package duke.parser;

import duke.datevalidator.DateValidator;
import duke.taskclass.DeadlineTask;
import duke.taskclass.EventTask;
import duke.taskclass.Task;
import duke.ui.ConsoleUI;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Parser for user input and spits output if input is valid.
 * If input invalid, spits error messages.
 */
public class Parser {
    private ConsoleUI ui;
    private boolean isBye;
    private DateValidator validator;

    /**
     * Constructor of the Parser.
     *
     * @param ui        Handler for user interface.
     * @param validator Valid date format for tasks.
     */
    public Parser(ConsoleUI ui, DateValidator validator) {
        this.ui = ui;
        this.isBye = false;
        this.validator = validator;
    }

    /**
     * Exception class for missing todoTask descriptor.
     */
    static class MissingTodoDescriptorException extends Exception {
        public MissingTodoDescriptorException(String errorMessage) {
            super(errorMessage);
        }
    }

    /**
     * Exception class for unknown input parameters.
     */
    static class UnknownInputParamException extends Exception {
        public UnknownInputParamException(String errorMessage) {
            super(errorMessage);
        }
    }

    /**
     * Output case when user inputs "bye".
     *
     * @param taskIterator Number of tasks user has, used by Duke.
     * @return Tuple object that contains the input parameters.
     */
    public Tuple2 byeCase(int taskIterator) {
        this.isBye = true;
        return new Tuple2(taskIterator, ui.bye());
    }

    /**
     * Output case when user inputs "list".
     *
     * @param taskIterator Number of tasks user has, used by Duke.
     * @param tasks        Array of available user tasks.
     * @return Tuple object that contains the input parameters.
     */
    public Tuple2 listCase(int taskIterator, Task[] tasks) {
        return new Tuple2(taskIterator, ui.list(tasks));
    }

    /**
     * Output case when user inputs "done".
     *
     * @param taskIterator        Number of tasks user has, used by Duke.
     * @param tasks               Array of available user tasks.
     * @param completedTaskNumber The task number that is now 'done'.
     * @return Tuple object that contains the input parameters.
     */
    public Tuple2 doneCase(int taskIterator, Task[] tasks, String completedTaskNumber) {
        int taskNum = Integer.parseInt(completedTaskNumber) - 1;
        tasks[taskNum].markDone();
        return new Tuple2(taskIterator, ui.markDone(tasks[taskNum].toFormattedString()));
    }

    /**
     * Output case when user inputs "_todo_".
     *
     * @param taskIterator Number of tasks user has, used by Duke.
     * @param tasks        Array of available user tasks.
     * @param input        Entirety of user input.
     * @return Message telling user task has been added.
     */
    public Tuple2 todoCase(int taskIterator, Task[] tasks, String input) {
        String[] taskCommands = input.split("/", 2);
        String[] firstHalf = taskCommands[0].split(" ", 2);
        try {
            if (firstHalf.length == 1) {
                throw new Parser.MissingTodoDescriptorException("------------------------------------\n"
                        + ":( OOPS!!! The description of a todo cannot be empty\n"
                        + "------------------------------------");
            }
        } catch (Parser.MissingTodoDescriptorException e) {
            System.out.println(e.getMessage());
        }

        Task testTask = new Task(firstHalf[1], false); // create todoTask
        return checkDuplicateTodoTask(taskIterator, tasks, testTask);
    }

    /**
     * Output case when user inputs "event".
     *
     * @param taskIterator Number of tasks user has, used by Duke.
     * @param tasks        Array of available user tasks.
     * @param input        Entirety of user input.
     * @return Message telling user task has been added.
     */
    public Tuple2 eventCase(int taskIterator, Task[] tasks, String input) {
        String[] taskCommands = input.split("/", 2);
        String[] firstHalf = taskCommands[0].split(" ", 2);
        String[] secondHalf = taskCommands[1].split(" ", 2);

        if (!validator.isValid(secondHalf[1])) {
            return new Tuple2(taskIterator, "Invalid date format for timed Task");
        }

        Date date = new Date();
        try {
            date = new SimpleDateFormat("d/MM/yyyy HHmm").parse(secondHalf[1]);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        EventTask testTask = new EventTask(firstHalf[1], false, secondHalf[1].trim(), date);
        return checkDuplicateEventTask(taskIterator, tasks, testTask);
    }

    /**
     * Output case when user inputs "deadline".
     *
     * @param taskIterator Number of tasks user has, used by Duke.
     * @param tasks        Array of available user tasks.
     * @param input        Entirety of user input.
     * @return Message telling user task has been added.
     */
    public Tuple2 deadlineCase(int taskIterator, Task[] tasks, String input) {
        String[] taskCommands = input.split("/", 2);
        String[] firstHalf = taskCommands[0].split(" ", 2);
        String[] secondHalf = taskCommands[1].split(" ", 2);

        if (!validator.isValid(secondHalf[1])) {
            return new Tuple2(taskIterator, "Invalid date format for timed Task");
        }

        Date date = new Date();
        try {
            date = new SimpleDateFormat("d/MM/yyyy HHmm").parse(secondHalf[1]);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        DeadlineTask testTask = new DeadlineTask(firstHalf[1], false, secondHalf[1].trim(), date);
        return checkDuplicateDeadlineTask(taskIterator, tasks, testTask);
    }

    /**
     * Checks if the task you want to add is a duplicate. Do not add if its a duplicate.
     *
     * @param taskIterator Number of tasks user has, used by Duke.
     * @param tasks        Array of available user tasks.
     * @param testTask     Added task.
     * @return Appropriate Tuple2 object depending on if input was duplicate or not.
     */
    public Tuple2 checkDuplicateTodoTask(int taskIterator, Task[] tasks, Task testTask) {
        assert testTask != null : "Task input must not be null.\n";

        boolean hasFoundDuplicateTask = false;
        for (Task otherTask : tasks) {
            if (otherTask == null) {
                continue;
            }

            if (testTask.equals(otherTask)) {
                hasFoundDuplicateTask = true;
                break;
            }
        }
        if (hasFoundDuplicateTask) {
            return new Tuple2(taskIterator, ui.formatBox("You are adding a duplicate task! Add task failed."));
        } else {
            tasks[taskIterator] = testTask;
            taskIterator = taskIterator + 1;
            return new Tuple2(taskIterator,
                    ui.addTaskMessage(testTask.toFormattedString(), taskIterator));
        }
    }

    /**
     * Checks if the task you want to add is a duplicate. Do not add if its a duplicate.
     *
     * @param taskIterator Number of tasks user has, used by Duke.
     * @param tasks        Array of available user tasks.
     * @param testTask     Added task.
     * @return Appropriate Tuple2 object depending on if input was duplicate or not.
     */
    public Tuple2 checkDuplicateEventTask(int taskIterator, Task[] tasks, EventTask testTask) {
        assert testTask != null : "Task input must not be null.\n";

        boolean hasFoundDuplicateTask = false;
        for (Task otherTask : tasks) {
            if (otherTask == null || !(otherTask instanceof EventTask)) {
                continue;
            }

            if (testTask.equals(otherTask)) {
                hasFoundDuplicateTask = true;
                break;
            }
        }
        if (hasFoundDuplicateTask) {
            return new Tuple2(taskIterator, ui.formatBox("You are adding a duplicate Event task! Add task failed."));
        } else {
            tasks[taskIterator] = testTask;
            taskIterator = taskIterator + 1;
            return new Tuple2(taskIterator,
                    ui.addTaskMessage(testTask.toFormattedString(), taskIterator));
        }
    }

    /**
     * Checks if the task you want to add is a duplicate. Do not add if its a duplicate.
     *
     * @param taskIterator Number of tasks user has, used by Duke.
     * @param tasks        Array of available user tasks.
     * @param testTask     Added task.
     * @return Appropriate Tuple2 object depending on if input was duplicate or not.
     */
    public Tuple2 checkDuplicateDeadlineTask(int taskIterator, Task[] tasks, DeadlineTask testTask) {
        assert testTask != null : "Task input must not be null.\n";

        boolean hasFoundDuplicateTask = false;
        for (Task otherTask : tasks) {
            if (otherTask == null || !(otherTask instanceof DeadlineTask)) {
                continue;
            }

            if (testTask.equals(otherTask)) {
                hasFoundDuplicateTask = true;
                break;
            }
        }
        if (hasFoundDuplicateTask) {
            return new Tuple2(taskIterator, ui.formatBox("You are adding a duplicate deadline task! Add task failed."));
        } else {
            tasks[taskIterator] = testTask;
            taskIterator = taskIterator + 1;
            return new Tuple2(taskIterator,
                    ui.addTaskMessage(testTask.toFormattedString(), taskIterator));
        }
    }

    /**
     * Output case when user inputs "delete".
     *
     * @param taskIterator      Number of tasks user has, used by Duke.
     * @param tasks             Array of available user tasks.
     * @param deletedTaskNumber The task number that is now deleted.
     * @return Message to user telling them task is deleted, and how many tasks are left.
     */
    public Tuple2 deleteCase(int taskIterator, Task[] tasks, String deletedTaskNumber) {
        int removeIndex = Integer.parseInt(deletedTaskNumber);
        taskIterator = taskIterator - 1; // reduce task count in list
        Task removedTask = tasks[removeIndex - 1];
        for (int i = removeIndex - 1; i < tasks.length - 1; i++) {
            tasks[i] = tasks[i + 1];
        }
        return new Tuple2(taskIterator, ui.deleteTaskMessage(removedTask.toFormattedString(), taskIterator));
    }

    /**
     * Output case when user inputs "find"
     *
     * @param taskIterator Number of tasks user has, used by Duke.
     * @param tasks        Array of available user tasks.
     * @param toFind       String to find within tasks.
     * @return String of found tasks.
     */
    public Tuple2 findCase(int taskIterator, Task[] tasks, String toFind) {
        String output = "Here are the matching tasks in your list:\n";
        for (Task t : tasks) {
            if (t == null) {
                continue;
            }
            if (t.getTaskName().contains(toFind)) {
                output = output + t.toFormattedString() + "\n";
            }
        }
        return new Tuple2(taskIterator, output);
    }

    /**
     * Output case when user inputs invalid inputs.
     * @param taskIterator Number of tasks user has, used by Duke.
     * @return Message to user telling them input is invalid.
     */
    public Tuple2 invalidCase(int taskIterator) {
        return new Tuple2(taskIterator, "You have entered an invalid input!\n"
                + "Check the user guide at https://ssagit.github.io/ip/");
    }

    /**
     * Parses lines of user input and outputs corresponding command.
     *
     * @param tasks        List of tasks from file.
     * @param taskIterator Integer to count number of tasks at a time.
     * @return New number of tasks after any addition or deletion.
     */
    public Tuple2<Integer, String> parseInput(String input, Task[] tasks, int taskIterator) {
        assert input != null : "input should not be null";

        String[] inputArr = input.split(" ", 2);
        String command = inputArr[0];
        Tuple2 results = new Tuple2();
        results.setInteger(taskIterator);

        switch (command) {
        case "bye":
            results = byeCase(taskIterator);
            break;
        case "list":
            results = listCase(taskIterator, tasks);
            break;
        case "done":
            results = doneCase(taskIterator, tasks, inputArr[1]);
            break;
        case "todo":
            results = todoCase(taskIterator, tasks, input);
            break;
        case "event":
            results = eventCase(taskIterator, tasks, input);
            break;
        case "deadline":
            results = deadlineCase(taskIterator, tasks, input);
            break;
        case "delete":
            results = deleteCase(taskIterator, tasks, inputArr[1]);
            break;
        case "find":
            results = findCase(taskIterator, tasks, inputArr[1]);
            break;
        default:
            results = invalidCase(taskIterator);
        }

        return results;
    }
}
