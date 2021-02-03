package duke;

import duke.commands.BasicCommandType;
import duke.exceptions.DukeEmptyListException;
import duke.exceptions.DukeUnknownArgumentsException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Message;
import duke.ui.Ui;

/**
 * Represents the Controller used to control the logic of the Duke program. Controller contains
 * the TaskList, Storage, and Ui.
 */
public class Controller {
    private static final String END_COMMAND = "bye";
    private final TaskList tasks;
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
        tasks = new TaskList(storage, ui);
    }

    /**
     * Starts the program and accepting user inputs.
     */
    public String run(String input) {
        //ui.printStartMsg();
        //String input = ui.getUserCommand();

        /*
        while (!input.equals(END_COMMAND)) {
            ui.printDivider();
            handleInput(input);
            ui.printDivider();
            input = ui.getUserCommand();
        }
         */
        if (input.equals("bye")) {
            return Message.getByeMsg();
        }

        return handleInput(input);
        //ui.printByeMsg();
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
            //ui.printErrorMsg(e);
        } catch (NumberFormatException e) {
            return Message.getErrorMsg(e);
            //ui.printErrorMsg(e);
        } catch (IndexOutOfBoundsException e) {
            return Message.getErrorMsg(e, tasks);
            //ui.printErrorMsg(e, tasks);
        }
    }

    /**
     * Mark specified task based on input as done.
     * @param input input used to get index to be marked as done.
     */
    private String doneTask(String input) {
        return tasks.done(input);
    }

    /**
     * Add task based on input to the TaskList.
     * @param input input used to get specific information of the task to be added.
     * @throws DukeUnknownArgumentsException if the input contains an unknown command.
     */
    private String specificTask(String input) throws DukeUnknownArgumentsException {
        return tasks.run(input);
    }

    /**
     * Print the string representation of the TaskList.
     */
    private String printList() {
        return tasks.print();
    }

}
