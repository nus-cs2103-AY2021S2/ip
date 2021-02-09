package duke;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Parses user commands and triggers corresponding effects.
 */
public class CommandParser {
    private static final int SPLIT_LIMIT = 2;
    private static final String DONE_REGEX_MATCH = "^(do(ne)?|finish(ed)?|completed?)( \\d+)+$";
    private static final String DELETE_REGEX_MATCH = "^(delete|remove)( \\d+)+$";
    private static final String TASK_REGEX_MATCH = "^(todo|deadline|event)( .+)?$";
    private static final String FIND_REGEX_MATCH = "^(find|search) \\d+$";

    private TaskList tasks;
    private Ui ui;

    public CommandParser(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Parses a user-input command and triggers the relevant effects.
     * @param userInput String representation of the command to be parsed.
     * @return String response to the command.
     * @throws DukeException If an Exception occurs due to a malformed command.
     */
    public String parseCommand(String userInput) throws DukeException {
        String reply;

        if (userInput.toLowerCase().equals("list")) {
            // display list
            reply = ui.showAllTasks(tasks);

        } else if (userInput.toLowerCase().matches(DONE_REGEX_MATCH)) {
            // finish a task
            TaskList doneTasks = new TaskList();

            String[] bits = userInput.split(" ");

            for (int i = 1; i < bits.length; i++) {
                int idx = Integer.parseInt(bits[i]);
                if (idx < 1 || idx > tasks.size()) {
                    throw new DukeException("Oops! Invalid task number included.");
                }

                Task finishedTask = tasks.get(idx);

                if (!finishedTask.isDone()) {
                    finishedTask.markAsDone();
                    doneTasks.add(finishedTask);
                }
            }

            reply = ui.showDoneTasks(doneTasks);

        } else if (userInput.toLowerCase().matches(DELETE_REGEX_MATCH)) {
            // manually remove task
            ArrayList<Integer> tasksToRemove = new ArrayList<>();
            TaskList removedTasks = new TaskList();

            String[] bits = userInput.split(" ");

            for (int i = 1; i < bits.length; i++) {
                int idx = Integer.parseInt(bits[i]);
                if (idx < 1 || idx > tasks.size()) {
                    throw new DukeException("Oops! Invalid task number included.");
                }
                tasksToRemove.add(idx);
            }

            Collections.sort(tasksToRemove, Collections.reverseOrder());

            for (int i = 0; i < tasksToRemove.size(); i++) {
                int idx = tasksToRemove.get(i);
                Task removedTask = tasks.remove(idx);
                removedTasks.add(removedTask);
            }

            reply = ui.showRemovedTasks(removedTasks, tasks.size());

        } else if (userInput.toLowerCase().matches(TASK_REGEX_MATCH)) {
            // add task to list
            Task newTask = TaskParser.parseTask(userInput);
            tasks.add(newTask);
            reply = ui.showAddedTask(newTask, tasks.size());

        } else if (userInput.toLowerCase().matches(FIND_REGEX_MATCH)) {
            // find task in list
            String[] bits = userInput.split(" ", SPLIT_LIMIT);
            if (bits.length == 1) {
                throw new DukeException("Oops! Usage: find [search pattern]");
            }

            TaskList matchingTasks = tasks.find(bits[1]);
            reply = ui.showFoundTasks(matchingTasks);

        } else {
            throw new DukeException("I don't understand that command!");
        }
        return reply;
    }
}