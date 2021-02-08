import java.nio.file.Path;
import java.time.format.DateTimeParseException;
import java.util.Date;

import duke.GuiUi;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.ExpensesList;
import duke.exceptions.DukeException;
import duke.exceptions.ParseException;
import duke.expenses.Expense;
import duke.tasks.Task;
import javafx.scene.image.Image;

// potential exceptions to catch:
// 1) deleting a non-existent task
// 2) marking a non-existent task as done
// 3) marking an already done task done again

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private ExpensesList expenses;

    /**
     * Creates a Duke object
     */
    public Duke() throws DukeException {
        try {
            storage = new Storage();
            tasks = new TaskList(storage.loadTasksData());
            expenses = new ExpensesList(storage.loadExpensesData());
        } catch (DukeException e) {
            tasks = new TaskList();
            expenses = new ExpensesList();
        }
    }

    /**
     * Get responses from Tasker depending on the user's input
     *
     * @param input user input
     * @return Tasker's response
     */
    public String getResponse(String input) {
        assert(input != null);
        try {
            int indexOfWhitespaceAfterCommand = input.indexOf(' ');
            input.substring(indexOfWhitespaceAfterCommand);
        } catch (StringIndexOutOfBoundsException e) {
            try {
                Parser.parseCommand(input);
            } catch (IllegalArgumentException exception) {
                return GuiUi.displayGenericError();
            }
            Parser.Command command = Parser.parseCommand(input);
            switch (command) {
            case LIST:
                return GuiUi.displayListOfTasks(this.tasks);
            case LIST_E:
                return GuiUi.displayListOfExpenses(this.expenses);
            case BYE:
                return GuiUi.displayExitMsg();
            default:
                return GuiUi.displayGenericError();
            }
        }
        try {
            int indexOfSpaceAfterCommand = input.indexOf(' ');
            //get the command word
            String commandWord = input.substring(0, indexOfSpaceAfterCommand);
            //get the rest of the input
            String inputAfterCommand = input.substring(indexOfSpaceAfterCommand + 1);
            Parser.Command command = Parser.parseCommand(commandWord);
            switch (command) {
            case SPEND:
                try {
                    Expense expenseToBeAdded = Parser.parseExpenseDescription(command, inputAfterCommand);
                    expenses.add(expenseToBeAdded);
                    storage.saveExpensesData(expenses.getExpenses());
                    return GuiUi.displayAddExpenseSuccess(expenses, expenseToBeAdded);
                } catch (DukeException e) {
                    return GuiUi.displayGenericError();
                } catch (DateTimeParseException e) {
                    return GuiUi.displaySpendParseError();
                } catch (ParseException e) {
                    return GuiUi.insertMsgIntoChatBox(e.getErrMsg());
                }
            case DONE:
                int indexToMark = Integer.parseInt(inputAfterCommand);
                try {
                    String taskMarkedDone = GuiUi.displayMarkDoneSuccess(tasks.mark(indexToMark));
                    storage.saveTasksData(tasks.getTasks());
                    return taskMarkedDone;
                } catch (DukeException e) {
                    return GuiUi.displayFailToSaveError();
                } catch (IndexOutOfBoundsException e) {
                    return GuiUi.displayIndexOutOfBoundsError(tasks);
                }
            case DELETE:
                int indexToDelete = Integer.parseInt(inputAfterCommand);
                try {
                    Task taskDeleted = tasks.delete(indexToDelete);
                    storage.saveTasksData(tasks.getTasks());
                    return GuiUi.displayDeleteSuccess(tasks, taskDeleted);
                } catch (DukeException e) {
                    return GuiUi.displayFailToSaveError();
                } catch (IndexOutOfBoundsException e) {
                    return GuiUi.displayIndexOutOfBoundsError(tasks);
                }
            case FIND:
                TaskList matchingTasks = tasks.find(inputAfterCommand);
                return GuiUi.showMatchingResult(matchingTasks);
            default:
                try {
                    Task taskToBeAdded = Parser.parseDescription(command, inputAfterCommand);
                    tasks.add(taskToBeAdded);
                    storage.saveTasksData(tasks.getTasks());
                    return GuiUi.displayAddSuccess(tasks, taskToBeAdded);
                } catch (DukeException e) {
                    return GuiUi.displayFailToSaveError();
                } catch (ParseException e) {
                    return GuiUi.insertMsgIntoChatBox(e.getErrMsg());
                } catch (DateTimeParseException e) {
                    return GuiUi.displayParseError();
                }
            }
        } catch (IllegalArgumentException e) {
            return GuiUi.displayGenericError();
        }
    }
}


