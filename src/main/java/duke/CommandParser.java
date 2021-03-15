package duke;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Parses user commands and triggers corresponding effects.
 */
public class CommandParser {
    private static final int SPLIT_LIMIT = 2;
    private static final int MIN_SEGMENTS_SIZE = 2;
    private static final String DONE_REGEX_MATCH = "^(do(ne)?|finish(ed)?|completed?)( .*)?";
    private static final String DELETE_REGEX_MATCH = "^(delete|remove)( .*)?";
    private static final String TASK_REGEX_MATCH = "^(todo|deadline|event)( .*)?";
    private static final String FIND_REGEX_MATCH = "^(find|search)( .*)?";

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
            reply = ui.showAllTasks(tasks);

        } else if (userInput.toLowerCase().matches(DONE_REGEX_MATCH)) {
            String[] taskSegments = userInput.split(" ");
            if (taskSegments.length < MIN_SEGMENTS_SIZE) {
                throw new DukeException("Oops! Usage: done [task1, task2, ...]");
            }

            reply = parseDoneCommand(taskSegments);

        } else if (userInput.toLowerCase().matches(DELETE_REGEX_MATCH)) {
            String[] taskSegments = userInput.split(" ");
            if (taskSegments.length < MIN_SEGMENTS_SIZE) {
                throw new DukeException("Oops! Usage: delete [task1, task2, ...]");
            }

            reply = parseDeleteCommand(taskSegments);

        } else if (userInput.toLowerCase().matches(TASK_REGEX_MATCH)) {
            Task newTask = TaskParser.parseTask(userInput);
            tasks.add(newTask);
            reply = ui.showAddedTask(newTask, tasks.size());

        } else if (userInput.toLowerCase().matches(FIND_REGEX_MATCH)) {
            String[] taskSegments = userInput.split(" ", SPLIT_LIMIT);
            if (taskSegments.length < MIN_SEGMENTS_SIZE) {
                throw new DukeException("Oops! Usage: find [search pattern]");
            }

            TaskList matchingTasks = tasks.find(taskSegments[1]);
            reply = ui.showFoundTasks(matchingTasks);

        } else {
            throw new DukeException("I don't understand that command!");
        }
        return reply;
    }

    public String parseDoneCommand(String[] taskSegments) throws DukeException {
        ArrayList<Integer> tasksToDo = new ArrayList<>();
        TaskList doneTasks = new TaskList();

        // preprocess tasks to find invalid task numbers
        for (int i = 1; i < taskSegments.length; i++) {
            try {
                int idx = Integer.parseInt(taskSegments[i]);
                if (idx < 1 || idx > tasks.size()) {
                    throw new ArrayIndexOutOfBoundsException();
                }
                tasksToDo.add(idx);
            } catch (Exception e) {
                throw new DukeException("Oops! Invalid task number included.");
            }
        }

        // actually do the tasks
        for (int idx : tasksToDo) {
            Task finishedTask = tasks.get(idx);
            if (!finishedTask.isDone()) {
                finishedTask.markAsDone();
                doneTasks.add(finishedTask);
            }
        }

        String reply = ui.showDoneTasks(doneTasks);

        return reply;
    }

    private String parseDeleteCommand(String[] taskSegments) throws DukeException {
        ArrayList<Integer> tasksToRemove = new ArrayList<>();
        TaskList removedTasks = new TaskList();

        for (int i = 1; i < taskSegments.length; i++) {
            int idx = Integer.parseInt(taskSegments[i]);
            if (idx < 1 || idx > tasks.size()) {
                throw new DukeException("Oops! Invalid task number included.");
            }
            tasksToRemove.add(idx);
        }

        // reverse list so the tasks can be removed safely
        Collections.reverse(tasksToRemove);

        for (int idx : tasksToRemove) {
            Task removedTask = tasks.remove(idx);
            removedTasks.add(removedTask);
        }

        // reverse removedTasks so they display in order
        removedTasks.reverse();

        String reply = ui.showRemovedTasks(removedTasks, tasks.size());

        return reply;
    }
}