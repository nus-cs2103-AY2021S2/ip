package duke;

import java.util.ListIterator;

import java.nio.file.Paths;

import command.Command;
import command.CommandDetails;
import command.DukeCommand;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import utility.Parser;
import utility.Storage;
import utility.TaskList;
import utility.Ui;

/**
 * Represents the highest level code responsible for Duke's operations.
 */
public class Duke {

    private TaskList taskList;
    private Storage storage;

    Duke() {
        try {
            this.storage = new Storage(Paths.get("data", "duke.txt"), Paths.get("data"));
            this.taskList = new TaskList(storage.readFromFile());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
            Command command = dukeCommand.getCommand();
            CommandDetails cmdDetails = dukeCommand.getDetails();

            if (command == Command.BYE) {
                return Ui.showExitUi();

            } else if (command == Command.DELETE) {
                int index = cmdDetails.getIndexForListDoneOrDelete();
                assert index >= 0;
                if (index >= taskList.getSize()) {
                    throw new DukeException("No such task in the list");
                }

                Task removedTask = taskList.delete(index);
                storage.writeToFile(taskList);

                assert removedTask != null : "removed task from TaskList is a null";
                return Ui.showSuccessfulDelete(taskList.getSize(), removedTask);

            } else if (command == Command.LIST) {
                return Ui.showList(taskList);

            } else if (command == Command.DONE) {
                int index = cmdDetails.getIndexForListDoneOrDelete();
                assert index >= 0;
                if (index >= taskList.getSize()) {
                    throw new DukeException("No such task in the list");
                }

                assert taskList.get(index) != null : "Task to be completed is a null";
                taskList.markAsDone(index);
                storage.writeToFile(taskList);
                return Ui.showSuccessfulDone(taskList.get(index));


            } else if (command == Command.INVALID) {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-()");

            } else if (command == Command.FIND) {

                ListIterator<Task> taskIter = taskList.getTasks().listIterator();
                String keyword = cmdDetails.getFindKeyword();
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

            } else if (command == Command.TAG) {
                ListIterator<Task> taskIter = taskList.getTasks().listIterator();
                Tag tagAction = cmdDetails.getTagAction();
                String taskDescription = cmdDetails.getTaskDescription();
                String tag = cmdDetails.getTag();
                Task relevantTask = null;

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

            } else if (command == Command.TODO) {
                Todo newTodo = new Todo(cmdDetails.getTaskDescription());
                taskList.add(newTodo);
                storage.writeToFile(taskList);
                return Ui.showSuccessfulAdd(taskList.getSize(), newTodo);

            } else if (command == Command.DEADLINE) {
                Deadline newDeadline = new Deadline(cmdDetails.getTaskDescription(), cmdDetails.getDeadline());
                taskList.add(newDeadline);
                storage.writeToFile(taskList);
                return Ui.showSuccessfulAdd(taskList.getSize(), newDeadline);

            } else if (command == Command.EVENT) {
                Event newEvent = new Event(cmdDetails.getTaskDescription(), cmdDetails.getDeadline(),
                        cmdDetails.getEventTime());
                taskList.add(newEvent);
                storage.writeToFile(taskList);
                return Ui.showSuccessfulAdd(taskList.getSize(), newEvent);

            } else {
                throw new AssertionError("Duke has encountered an input in Duke.java that it does not know how to "
                        + "handle");
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

    public int getNumOfTasks() {
        return taskList.getSize();
    }
}
