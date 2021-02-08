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
    ConsoleUI ui;
    boolean isBye;
    DateValidator validator;

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

    public boolean getIsBye() {
        return this.isBye;
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
     * @return
     */
    public Tuple2 todoCase(int taskIterator, Task[] tasks, String input) {
        try {
            String[] taskCommands = input.split("/", 2);
            String[] firstHalf = taskCommands[0].split(" ", 2);
            // create todoTask
            if (firstHalf.length == 1) {
                throw new Parser.MissingTodoDescriptorException("------------------------------------\n"
                        + ":( OOPS!!! The description of a todo cannot be empty\n"
                        + "------------------------------------");
            } else {
                tasks[taskIterator] = new Task(firstHalf[1], false);
            }
            taskIterator = taskIterator + 1;
        } catch (Parser.MissingTodoDescriptorException e) {
            System.out.println(e.getMessage());
        }
        return new Tuple2(taskIterator,
                ui.addTaskMessage(tasks[taskIterator - 1].toFormattedString(), taskIterator));
    }

    /**
     * Output case when user inputs "event".
     *
     * @param taskIterator Number of tasks user has, used by Duke.
     * @param tasks        Array of available user tasks.
     * @param input        Entirety of user input.
     * @return
     */
    public Tuple2 eventCase(int taskIterator, Task[] tasks, String input) {
        String[] taskCommands = input.split("/", 2);
        String[] firstHalf = taskCommands[0].split(" ", 2);
        String[] secondHalf = taskCommands[1].split(" ", 2);
        try {
            if (validator.isValid(secondHalf[1])) {
                System.out.println("validator ok");
                Date date = new SimpleDateFormat("d/MM/yyyy HHmm").parse(secondHalf[1]);
                tasks[taskIterator] = new EventTask(firstHalf[1], false, secondHalf[1].trim(), date);
            } else {
                System.out.println("Invalid date format for timed Task");
            }
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        taskIterator = taskIterator + 1;
        return new Tuple2(taskIterator,
                ui.addTaskMessage(tasks[taskIterator - 1].toFormattedString(), taskIterator));
    }

    /**
     * Output case when user inputs "deadline".
     *
     * @param taskIterator Number of tasks user has, used by Duke.
     * @param tasks        Array of available user tasks.
     * @param input        Entirety of user input.
     * @return
     */
    public Tuple2 deadlineCase(int taskIterator, Task[] tasks, String input) {
        String[] taskCommands = input.split("/", 2);
        String[] firstHalf = taskCommands[0].split(" ", 2);
        String[] secondHalf = taskCommands[1].split(" ", 2);
        try {
            if (validator.isValid(secondHalf[1])) {
                Date date = new SimpleDateFormat("d/MM/yyyy HHmm").parse(secondHalf[1]);
                tasks[taskIterator] = new DeadlineTask(firstHalf[1], false, secondHalf[1].trim(), date);
            } else {
                System.out.println("Invalid date format for timed Task");
            }
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        taskIterator = taskIterator + 1;
        return new Tuple2(taskIterator,
                ui.addTaskMessage(tasks[taskIterator - 1].toFormattedString(), taskIterator));
    }

    /**
     * Output case when user inputs "delete".
     *
     * @param taskIterator      Number of tasks user has, used by Duke.
     * @param tasks             Array of available user tasks.
     * @param deletedTaskNumber The task number that is now deleted.
     * @return
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
     * @return
     */
    public Tuple2 findCase(int taskIterator, Task[] tasks, String toFind) {
        String output = "Here are the matching tasks in your list:\n";
        for (Task t : tasks) {
            if (t != null) {
                if (t.getTaskName().contains(toFind)) {
                    output = output + t.toFormattedString() + "\n";
                }
            }
        }
        return new Tuple2(taskIterator, output);
    }

    /**
     * Parses lines of user input and outputs corresponding command.
     *
     * @param tasks        List of tasks from file.
     * @param taskIterator Integer to count number of tasks at a time.
     * @return New number of tasks after any addition or deletion.
     */
    public Tuple2<Integer, String> parseInput(String input, Task[] tasks, int taskIterator) {
        String[] inputArr = input.split(" ", 2);
        String command = inputArr[0];
        Tuple2 results = new Tuple2();
        results.setInteger(taskIterator);

        try {
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
                throw new UnknownInputParamException("------------------------------------\n"
                        + ":( OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                        + "------------------------------------");
            }
        } catch (UnknownInputParamException e) {
            System.out.println(e.getMessage());
        }

        return results;
    }
}
