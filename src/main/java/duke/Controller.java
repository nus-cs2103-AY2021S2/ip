package duke;

import duke.commands.CommandType;
import duke.exceptions.DukeEmptyListException;
import duke.exceptions.DukeUnknownArgumentsException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents the Controller used to control the logic of the Duke program. Controller contains
 * the TaskList, Storage, and Ui.
 */
public class Controller {
    private final static String END_COMMAND = "bye";
    private final TaskList taskList;
    private final Storage storage;
    private final Ui ui;

    /**
     * Construct a Controller class. Defaulted with UI used to get user inputs and show outputs,
     * Storage used to get savefile from data directory, and tasklist from the save file or a new
     * tasklist.
     */
    public Controller() {
        this.ui = new Ui();
        storage = Storage.getInstance();
        taskList = new TaskList(storage, ui);
    }

    /**
     * Starts the program and accepting user inputs.
     */
    public void run() {
        ui.printStartMsg();
        String input = ui.getUserCommand();

        while(!input.equals(END_COMMAND)) {
            ui.printDivider();
            handleInput(input);
            ui.printDivider();
            input = ui.getUserCommand();
        }

        ui.printByeMsg();
    }

    /**
     * Handle inputs from user.
     * @param input User inputs.
     */
    private void handleInput(String input) {
        CommandType command = Parser.parseCommand(input);
        executeCommand(input, command);
    }

    /**
     * Execute User inputs based on the commandType.
     * @param input user input used based on commandType.
     * @param command commandType used to differentiate how input is used.
     */
    private void executeCommand(String input, CommandType command) {
        try {
            switch (command) {
            case DONE:
                doneTask(input);
                break;
            case LIST:
                printList();
                break;
            case DELETE:
                taskList.deleteTask(input);
                break;
            case ADD:
                addTask(input);
                break;
            }
           taskList.updateSave(storage);
        } catch (DukeUnknownArgumentsException e) {
            ui.printErrorMsg(e);
        } catch (NumberFormatException e) {
            ui.printErrorMsg(e);
        } catch (IndexOutOfBoundsException e) {
            ui.printErrorMsg(e, taskList);
        } catch (DukeEmptyListException e) {
            ui.printErrorMsg(e);
        }
    }

    /**
     * Mark specified task based on input as done.
     * @param input input used to get index to be marked as done.
     */
    private void doneTask(String input) {
        taskList.done(input);
    }

    /**
     * Add task based on input to the TaskList.
     * @param input input used to get specific information of the task to be added.
     * @throws DukeUnknownArgumentsException if the input contains an unknown command.
     */
    private void addTask(String input) throws DukeUnknownArgumentsException {
        taskList.add(input);
    }

    /**
     * Print the string representation of the TaskList.
     */
    private void printList() {
        taskList.print();
    }

}
