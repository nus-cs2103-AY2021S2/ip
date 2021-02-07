package duke;

import java.time.format.DateTimeParseException;

public class Parser {

    /**
     * Parses a given input and returns Strings as necessary based on the input.
     * The main driver of the Duke class.
     *
     * @param taskList The current TaskList.
     * @param input Input that is being read in.
     */
    public String parse(TaskList taskList, String input) {

        Ui ui = new Ui();

        String[] split = input.split("\\s+");

        switch (split[0]) {
        case "list":
            if (taskList.size() == 0) {
                return ui.emptyListMessage();
            } else {
                return ui.showList(taskList);
            }
        case "done":
            try {
                int done = Integer.parseInt(split[1]) - 1;
                taskList.get(done).setDone();
                return ui.setDone(taskList.get(done));
            } catch (Exception e) {
                return ui.errorMessage("invalidDone");
            }
        case "bye":
            return ui.byeBye();
        case "todo":
            try {
                taskList.add(new Task(input.substring(5)));
                return (ui.taskAdded(taskList.get(taskList.size() - 1)) + "\n"
                        + ui.showTaskListSize(taskList.size()));
            } catch (Exception e) {
                return ui.errorMessage("invalidTodo");
            }
        case "deadline":
            try {
                String[] splitagain = input.substring(9).split("/by");
                taskList.add(new Deadline(splitagain[0], splitagain[1].substring(1)));
                return (ui.taskAdded(taskList.get(taskList.size() - 1)) + "\n"
                        + ui.showTaskListSize(taskList.size()));
            } catch (DateTimeParseException de) {
                return ui.errorMessage("dateTimeError");
            } catch (Exception e) {
                return ui.errorMessage("invalidDeadline");
            }
        case "event":
            try {
                String[] splitagain2 = input.substring(6).split("/at");
                taskList.add(new Event(splitagain2[0], splitagain2[1].substring(1)));
                return (ui.taskAdded(taskList.get(taskList.size() - 1)) + "\n"
                        + ui.showTaskListSize(taskList.size()));
            } catch (DateTimeParseException de) {
                return ui.errorMessage("dateTimeError");
            } catch (Exception e) {
                return ui.errorMessage("invalidEvent");
            }
        case "delete":
            try {
                Task toDelete = taskList.get(Integer.parseInt(split[1]) - 1);
                taskList.remove(toDelete);
                return (ui.deleteTask(toDelete) + "\n"
                        + ui.showTaskListSize(taskList.size()));
            } catch (Exception e) {
                return ui.errorMessage("invalidDelete");
            }
        case "find":
            String toFind = input.substring(5);
            TaskList toReturn = new TaskList();
            for (Task t : taskList.getList()) {
                if (t.getTodo().contains(toFind)) {
                    toReturn.add(t);
                }
            }
            return ui.showSearchList(toReturn);
        case "tag":
            Task toTag = taskList.get(Integer.parseInt(split[1]) - 1);
            if (split.length < 3) {
                return ui.errorMessage("badTag");
            }
            String newTag = split[2];
            toTag.setTag(newTag);
            return ui.setTag(toTag);
        default:
            return ui.errorMessage("unknownInput");
        }
    }
}
