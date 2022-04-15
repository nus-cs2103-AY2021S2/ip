package duke;

import java.time.format.DateTimeParseException;

public class Parser {

    private TaskList taskList;
    private Ui ui;
    private String[] split;
    private String input;

    /**
     * Parses a given input and returns Strings as necessary based on the input.
     * The main driver of the Duke class.
     *
     * @param t The current TaskList.
     * @param i Input that is being read in.
     */
    public String parse(TaskList t, String i) {

        ui = new Ui();
        taskList = t;
        split = i.split("\\s+");
        input = i;

        switch (split[0]) {
        case "list":
            return listCommand();
        case "done":
            return doneCommand();
        case "bye":
            return byeCommand();
        case "todo":
            return todoCommand();
        case "deadline":
            return deadlineCommand();
        case "event":
            return eventCommand();
        case "delete":
            return deleteCommand();
        case "find":
            return findCommand();
        case "tag":
            return tagCommand();
        default:
            return errorCommand();
        }
    }

    /**
     * Returns the result of parsing a list command.
     *
     * @return A reply to the user.
     */
    private String listCommand() {
        if (taskList.size() == 0) {
            return ui.emptyListMessage();
        } else {
            return ui.showList(taskList);
        }
    }

    /**
     * Returns the result of parsing a done command.
     *
     * @return A reply to the user.
     */
    private String doneCommand() {
        try {
            int done = Integer.parseInt(split[1]) - 1;
            if (taskList.get(done).getDone()) {
                return ui.errorMessage("alreadyDone");
            }
            taskList.get(done).setDone();
            return ui.setDone(taskList.get(done));
        } catch (Exception e) {
            return ui.errorMessage("invalidDone");
        }
    }

    /**
     * Returns the result of parsing a bye command.
     *
     * @return A reply to the user.
     */
    private String byeCommand() {
        return ui.byeBye();
    }

    /**
     * Returns the result of parsing a todo command.
     *
     * @return A reply to the user.
     */
    private String todoCommand() {
        try {
            String name = input.substring(5);
            if (name.trim().length() == 0) {
                return ui.errorMessage("invalidTodo");
            }
            if (name.contains("|")) {
                return ui.errorMessage("invalidBug");
            }
            taskList.add(new Task(input.substring(5)));
            return (ui.taskAdded(taskList.get(taskList.size() - 1)) + "\n"
                    + ui.showTaskListSize(taskList.size()));
        } catch (Exception e) {
            return ui.errorMessage("invalidTodo");
        }
    }

    /**
     * Returns the result of parsing a deadline command.
     *
     * @return A reply to the user.
     */
    private String deadlineCommand() {
        try {
            String[] splitagain2 = input.substring(9).split("/by");
            String name = splitagain2[0];
            if (name.trim().length() == 0) {
                return ui.errorMessage("invalidDeadline");
            }
            if (name.contains("|")) {
                return ui.errorMessage("invalidBug");
            }
            taskList.add(new Deadline(splitagain2[0], splitagain2[1].substring(1)));
            return (ui.taskAdded(taskList.get(taskList.size() - 1)) + "\n"
                    + ui.showTaskListSize(taskList.size()));
        } catch (DateTimeParseException de) {
            return ui.errorMessage("dateTimeError");
        } catch (Exception e) {
            return ui.errorMessage("invalidDeadline");
        }
    }

    /**
     * Returns the result of parsing a event command.
     *
     * @return A reply to the user.
     */
    private String eventCommand() {
        try {
            String[] splitagain2 = input.substring(6).split("/at");
            String name = splitagain2[0];
            if (name.trim().length() == 0) {
                return ui.errorMessage("invalidEvent");
            }
            if (name.contains("|")) {
                return ui.errorMessage("invalidBug");
            }
            taskList.add(new Event(splitagain2[0], splitagain2[1].substring(1)));
            return (ui.taskAdded(taskList.get(taskList.size() - 1)) + "\n"
                    + ui.showTaskListSize(taskList.size()));
        } catch (DateTimeParseException de) {
            return ui.errorMessage("dateTimeError");
        } catch (Exception e) {
            return ui.errorMessage("invalidEvent");
        }
    }

    /**
     * Returns the result of parsing a delete command.
     *
     * @return A reply to the user.
     */
    private String deleteCommand() {
        try {
            Task toDelete = taskList.get(Integer.parseInt(split[1]) - 1);
            taskList.remove(toDelete);
            return (ui.deleteTask(toDelete) + "\n"
                    + ui.showTaskListSize(taskList.size()));
        } catch (Exception e) {
            return ui.errorMessage("invalidDelete");
        }
    }

    /**
     * Returns the result of parsing a find command.
     *
     * @return A reply to the user.
     */
    private String findCommand() {
        try {
            String toFind = input.substring(5);
            if (toFind.trim().length() == 0) {
                return ui.errorMessage("badFind");
            }
            TaskList toReturn = new TaskList();
            for (Task t : taskList.getList()) {
                if (t.getTodo().contains(toFind)) {
                    toReturn.add(t);
                }
            }
            return ui.showSearchList(toReturn);
        } catch (Exception e) {
            return ui.errorMessage("invalidFind");
        }
    }

    /**
     * Returns the result of parsing a tag command.
     *
     * @return A reply to the user.
     */
    private String tagCommand() {
        try {
            int indexToTag = Integer.parseInt(split[1]) - 1;
            if (indexToTag >= taskList.size()) {
                return ui.errorMessage("invalidIndex");
            }
            Task toTag = taskList.get(Integer.parseInt(split[1]) - 1);
            if (split.length < 3) {
                return ui.errorMessage("badTag");
            }
            String newTag = split[2];
            toTag.setTag(newTag);
            return ui.setTag(toTag);
        } catch (Exception e) {
            return ui.errorMessage("badTag");
        }
    }

    /**
     * Returns the result of parsing a unknown input.
     *
     * @return A reply to the user.
     */
    private String errorCommand() {
        return ui.errorMessage("unknownInput");
    }

}
