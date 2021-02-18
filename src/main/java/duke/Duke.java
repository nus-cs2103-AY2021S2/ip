package duke;

import java.util.ListIterator;

import java.nio.file.Paths;

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

                if (index >= taskList.getTasks().size()) {
                    throw new DukeException("No such task in the list");
                }

                Task removedTask = taskList.delete(Integer.parseInt(dukeCommand.getDetails()) - 1);
                storage.writeToFile(taskList);
                return Ui.showSuccessfulDelete(taskList.getTasks().size(), removedTask);

            } else if (dukeCommand.getCommand() == Command.LIST) {
                return Ui.showList(taskList);

            } else if (dukeCommand.getCommand() == Command.DONE) {
                Integer index = Integer.parseInt(dukeCommand.getDetails()) - 1;

                if (index >= taskList.getTasks().size()) {
                    throw new DukeException("No such task in the list");
                }

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

                    if (curr.getDescription().contains(keyword)) {
                        matchedTasks.add(curr);
                    }
                }

                return Ui.showMatchedTasks(matchedTasks);


            } else {
                Task newTask = Parser.parseRemainder(dukeCommand.getCommand(), dukeCommand.getDetails());

                taskList.add(newTask);
                storage.writeToFile(taskList);
                return Ui.showSuccessfulAdd(taskList.getTasks().size(), newTask);
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
