package duke;

import java.util.ListIterator;

import java.nio.file.Paths;

import task.Task;
import utility.Parser;
import utility.Storage;
import utility.TaskList;
import utility.Ui;

/**
 * Represents the highest level code responsible for Duke's operations.
 */
public class Duke {

    private TaskList taskList;
    private final Storage storage;

    Duke() {
        this.taskList = new TaskList();
        this.storage = new Storage(Paths.get("data", "duke.txt"), Paths.get("data"));
    }

    /**
     * Makes changes to the program's state and returns Duke's response to the user's input.
     * @param input the String representing the user's input
     * @return a String representing the program's response
     */
    public String run(String input) {
        try {
            taskList = new TaskList(storage.readFromFile());
            DukeCommand dukeCommand = Parser.parseCommand(input);

            if (dukeCommand.getCommand() == Command.BYE) {
                return Ui.showExitUi();

            } else if (dukeCommand.getCommand() == Command.DELETE) {
                Integer index = Integer.parseInt(dukeCommand.getDetails()) - 1;

                assert index >= 0;
                if (index >= taskList.getSize()) {
                    throw new DukeException("No such task in the list");
                }

                Task removedTask = taskList.delete(index);
                storage.writeToFile(taskList);

                assert removedTask != null : "removed task from TaskList is a null";
                return Ui.showSuccessfulDelete(taskList.getSize(), removedTask);

            } else if (dukeCommand.getCommand() == Command.LIST) {
                return Ui.showList(taskList);

            } else if (dukeCommand.getCommand() == Command.DONE) {
                Integer index = Integer.parseInt(dukeCommand.getDetails()) - 1;

                assert index >= 0;
                if (index >= taskList.getSize()) {
                    throw new DukeException("No such task in the list");
                }

                assert taskList.get(index) != null : "Task to be completed is a null";
                taskList.markAsDone(index);
                storage.writeToFile(taskList);
                return Ui.showSuccessfulDone(taskList.get(index));


            } else if (dukeCommand.getCommand() == Command.INVALID) {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-()");

            } else if (dukeCommand.getCommand() == Command.FIND) {
                if (dukeCommand.getDetails().length() == 0) {
                    throw new DukeException("OOPS!!! Search keyword cannot be empty");
                }

                ListIterator<Task> taskIter = taskList.getTasks().listIterator();
                String keyword = dukeCommand.getDetails();
                TaskList matchedTasks = new TaskList();

                while (taskIter.hasNext()) {
                    Task curr = taskIter.next();
                    assert curr != null : "The list of tasks contains a null";

                    if (curr.getDescription().contains(keyword)) {
                        matchedTasks.add(curr);
                    }
                }

                if (matchedTasks.getSize() == 0) {
                    return Ui.showNoMatchedTasks();
                }

                assert matchedTasks.getSize() > 0;
                return Ui.showMatchedTasks(matchedTasks);

            } else if (dukeCommand.getCommand() == Command.TAG) {
                if (dukeCommand.getDetails().length() == 0) {
                    throw new DukeException("OOPS!!! tag keyword cannot be empty");
                }
                String[] tagParams = dukeCommand.getDetails().split(" ", 3);
                String tagMode = tagParams[0];
                String taskDescription = tagParams[1];
                String tag = tagParams[2];
                Tag tagAction;
                Task relevantTask = null;

                if (tagMode.equals("add")) {
                    tagAction = Tag.ADD;
                } else if (tagMode.equals("delete")) {
                    tagAction = Tag.DELETE;
                } else {
                    throw new DukeException("OOPS!!! Duke does not understand what you want to do with tag");
                }

                ListIterator<Task> taskIter = taskList.getTasks().listIterator();

                while (taskIter.hasNext()) {
                    Task curr = taskIter.next();
                    assert curr != null : "The list of tasks contains a null";

                    if (curr.getDescription().equals(taskDescription)) {
                        curr.handleTag(tagAction, tag);
                        taskIter.set(curr);
                        relevantTask = curr;
                        break;
                    }
                }

                boolean hasNotFoundRelevantTask = relevantTask == null;
                if (hasNotFoundRelevantTask) {
                    throw new DukeException("OOPS!!! Cannot find the relevant task!");
                }
                storage.writeToFile(taskList);
                return Ui.showTagHandling(tagAction, tag, relevantTask);

            } else {
                Task newTask = Parser.parseRemainder(dukeCommand.getCommand(), dukeCommand.getDetails());

                taskList.add(newTask);
                storage.writeToFile(taskList);
                return Ui.showSuccessfulAdd(taskList.getSize(), newTask);
            }

        } catch (DukeException exp) {
            return Ui.showDukeException(exp);
        } catch (Exception err) {
            return Ui.showException(err);
        }
    }

    public String getResponse(String input) {
        return run(input);
    }
}
