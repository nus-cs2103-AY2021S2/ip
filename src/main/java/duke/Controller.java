package duke;

import duke.commands.BasicCommandType;
import duke.exceptions.DukeEmptyListException;
import duke.exceptions.DukeUnknownArgumentsException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class Controller {
    private final static String END_COMMAND = "bye";
    private final TaskList taskList;
    private final Storage storage;
    private final Ui ui;

    public Controller() {
        this.ui = new Ui();
        storage = Storage.getInstance();
        taskList = new TaskList(storage, ui);
    }

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

    private void handleInput(String input) {
        BasicCommandType command = Parser.parseCommand(input);
        executeCommand(input, command);
    }

    private void executeCommand(String input, BasicCommandType command) {
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
                specificTask(input);
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

    private void doneTask(String input) {
        taskList.done(input);
    }

    private void specificTask(String input) throws DukeUnknownArgumentsException {
        taskList.run(input);
    }


    private void printList() {
        taskList.print();
    }

}
