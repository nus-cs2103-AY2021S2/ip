package duke;

import duke.commands.BasicCommandType;
import duke.exceptions.*;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Message;

/**
 * Represents the Controller used to control the logic of the duke.gui.Duke program. Controller contains
 * the TaskList, Storage, and Ui.
 */
public class Controller {
    private static final String END_COMMAND = "bye";
    private TaskList tasks;
    private Storage storage;

    /**
     * Initialises the new Controller for the logic of the Duke application.
     * @return Error messages if there are issues present, else returns the starting message.
     */
    public String initialise() {
        try {
            storage = Storage.getInstance();
            tasks = new TaskList(storage);
        } catch (DukeCorruptedStorageException e) {
            tasks = new TaskList();
            return Message.getErrorMsg(e);
        } catch (DukeCreateFileException e) {
            return Message.getErrorMsg(e);
        } catch (DukeCreateDirectoryException e) {
            return Message.getErrorMsg(e);
        }
        return Message.getStartMsg();
    }

    /**
     * Starts the program and accepting user inputs.
     */
    public String run(String input) {
        if (input.equals("bye")) {
            return Message.getByeMsg();
        }

        return handleInput(input);
    }

    /**
     * Handles inputs from user.
     * @param input User inputs.
     */
    public String handleInput(String input) {
        BasicCommandType command = Parser.parseCommand(input);
        return executeCommand(input, command);
    }

    /**
     * Executes User inputs based on the commandType.
     * @param input user input used based on commandType.
     * @param command commandType used to differentiate how input is used.
     */
    private String executeCommand(String input, BasicCommandType command) {
        try {
            String output;
            switch (command) {
            case DONE:
                output = doneTask(input);
                break;
            case LIST:
                output = printList();
                break;
            case DELETE:
                output = tasks.deleteTask(input);
                break;
            case ADD:
                output = specificTask(input);
                break;
            default:
                throw new DukeUnknownArgumentsException();
            }
            tasks.updateSave(storage);
            return output;
        } catch (DukeUnknownArgumentsException | DukeEmptyListException e) {
            return Message.getErrorMsg(e);
        } catch (DukeSaveFileException e) {
            return Message.getErrorMsg(e);
        } catch (NumberFormatException e) {
            return Message.getErrorMsg(e);
        } catch (IndexOutOfBoundsException e) {
            return Message.getErrorMsg(e, tasks);
        }
    }

    /**
     * Marks specified task based on input as done.
     * @param input input used to get index to be marked as done.
     */
    private String doneTask(String input) {
        return tasks.done(input);
    }

    /**
     * Adds task based on input to the TaskList.
     * @param input input used to get specific information of the task to be added.
     * @throws DukeUnknownArgumentsException if the input contains an unknown command.
     */
    private String specificTask(String input) throws DukeUnknownArgumentsException {
        return tasks.run(input);
    }

    /**
     * Prints the string representation of the TaskList.
     */
    private String printList() {
        return tasks.print();
    }

}
