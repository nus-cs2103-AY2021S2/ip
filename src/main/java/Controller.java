import exceptions.DukeEmptyListException;
import exceptions.DukeUnknownArgumentsException;

import java.util.Scanner;

public class Controller {
    private final static String END_COMMAND = "bye";
    private final TaskList taskList;
    private final Storage storage;
    private final Ui ui;

    public Controller() {
        storage = Storage.getInstance();
        this.ui = new Ui();
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
        CommandType command = Parser.parseCommand(input);
        executeCommand(input, command);
    }

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

    private void doneTask(String input) {
        taskList.done(input);
    }

    private void addTask(String input) throws DukeUnknownArgumentsException {
        taskList.add(input);
    }


    private void printList() {
        taskList.print();
    }

}
