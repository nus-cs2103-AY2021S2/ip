package duke;

import java.time.LocalDate;

/**
 * Handles all the logic behind each command
 */
public class CommandHandler {
    private static final String AUTO_SNOOZE_VALUE = "1";
    private Ui ui;
    private Parser parser;

    /**
     * This object is created to encapsulate a Ui and Parser object.
     *
     * @param ui Ui object
     * @param parser Parser object
     */
    public CommandHandler(Ui ui, Parser parser) {
        this.ui = ui;
        this.parser = parser;
    }

    /**
     * This method processes the input for a Todo.
     *
     * @param input This is the user input
     * @param taskList This is the existing taskList
     * @return This is the completion message
     */
    public String processTodo(String input, TaskList taskList) {
        try {
            String name = parser.getTodoName(input);
            Todo todo = new Todo(name);
            taskList.addTask(todo);
            return ui.printTask(todo, taskList.getSize());
        } catch (DukeException e) {
            return e.printMessage();
        }
    }

    /**
     * This method processes the input to mark a task as completed.
     *
     * @param input This is the user input
     * @param taskList This is the existing taskList
     * @return This is the completion message
     */
    public String processDone(String input, TaskList taskList) {
        try {
            int index = parser.getIndex(input);
            Task task = taskList.getSingleTask(index);
            task.markDone();

            return ui.printDone(task);
        } catch (DukeException e) {
            return e.printMessage();
        }
    }

    /**
     * This method processes the input for a Deadline.
     *
     * @param input This is the user input
     * @param taskList This is the existing taskList
     * @return This is the completion message
     */
    public String processDeadline(String input, TaskList taskList) {
        try {
            String name = parser.getEventOrDeadlineName(input);
            String by = parser.getDeadlineAttribute(input);
            LocalDate date = parser.stringToLocalDate(by);

            Deadline deadline = new Deadline(name, date);
            taskList.addTask(deadline);
            return ui.printTask(deadline, taskList.getSize());

        } catch (DukeException e) {
            return e.printMessage();
        }
    }

    /**
     * This method processes the input for an Event.
     *
     * @param input This is the user input
     * @param taskList This is the existing taskList
     * @return This is the completion message
     */
    public String processEvent(String input, TaskList taskList) {
        try {
            String name = parser.getEventOrDeadlineName(input);
            String at = parser.getEventAttribute(input);
            Event event = new Event(name, at);
            taskList.addTask(event);
            return ui.printTask(event, taskList.getSize());

        } catch (DukeException e) {
            return e.printMessage();
        }
    }

    /**
     * This method processes the input to delete a task.
     *
     * @param input This is the user input
     * @param taskList This is the existing taskList
     * @return This is the completion message
     */
    public String processDelete(String input, TaskList taskList) {
        try {
            int deleteIndex = parser.getIndex(input);
            Task deletedTask = taskList.getSingleTask(deleteIndex);
            taskList.deleteTask(deleteIndex);
            return ui.printDelete(deletedTask, taskList.getSize());
        } catch (DukeException e) {
            return e.printMessage();
        }
    }

    /**
     * This method processes the input to find a task.
     *
     * @param input This is the user input
     * @param taskList This is the existing taskList
     * @return This is the completion message
     */
    public String processFind(String input, TaskList taskList) {
        String arguments = parser.getArguments(input);
        TaskList output = taskList.matchTasks(arguments);
        return ui.printMatchingTask(output);
    }

    /**
     * This method processes the input to delete a task.
     *
     * @return This is the help message
     */
    public String processHelp() {
        return ui.getHelpMessage();
    }

    /**
     * This method processes the input to snooze a Deadline.
     *
     * @param input This is the user input
     * @param taskList This is the existing taskList
     * @return This is the completion message
     */
    public String processSnooze(String input, TaskList taskList) {
        try {
            int taskIndex = parser.getIndex(input);

            if (!(taskList.getSingleTask(taskIndex) instanceof Deadline)) {
                throw new DukeException("Task is not a Deadline! Snooze unsuccessful...");
            }

            String snoozeAttribute = parser.getSnoozeAttribute(input);

            if (snoozeAttribute.length() > 1) {
                String currentDeadlineName = taskList.getSingleTask(taskIndex).getName();

                LocalDate newDate = parser.stringToLocalDate(snoozeAttribute);
                Deadline newDeadline = new Deadline(currentDeadlineName, newDate);

                taskList.replaceTask(taskIndex, newDeadline);
                return ui.printDoneSnooze(parser.localDateToString(newDate));
            } else {
                Deadline oldDeadline = (Deadline) taskList.getSingleTask(taskIndex);
                LocalDate currDate = oldDeadline.getByAttribute();

                LocalDate newDate = currDate.plusDays(1);
                Deadline newDeadline = new Deadline(oldDeadline.getName(), newDate);
                taskList.replaceTask(taskIndex, newDeadline);

                return ui.printDoneSnooze(parser.localDateToString(newDate))
                        + "\n\n"
                        + "The deadline has been automatically snoozed by "
                        + AUTO_SNOOZE_VALUE
                        + " day";
            }
        } catch (DukeException e) {
            return e.printMessage();
        }
    }
}
