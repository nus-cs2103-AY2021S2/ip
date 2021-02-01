package checklst.parser;

import checklst.task.Event;
import checklst.exception.ChecklstException;
import checklst.storage.Storage;
import checklst.task.Deadline;
import checklst.task.Task;
import checklst.task.TaskList;
import checklst.task.Todo;
import checklst.ui.Ui;

/**  
 * The Parser class makes sense of inputs and runs the respective follow up methods.
 */
public class Parser {
    
    /**
     * Processes input commands and calls the respective command methods.
     * @param input Input string.
     * @param ui Ui instance for output.
     * @param taskList TaskList instance for manipulating tasks.
     * @param storage Storage instance for saving of commands.
     */
    public void parse(String[] input, Ui ui, TaskList taskList, Storage storage) {
        storage.addCommand(input);
        try {
            switch (input[0]) {
                case "list":
                    ui.sendOutput(taskList.toString());
                    break;
                case "done":
                    int doneIndex = Integer.parseInt(input[1]);
                    ui.sendOutput("Nice! I've marked this task as done!\n\t" + taskList.completeTask(doneIndex));
                    break;
                case "delete":
                    int deleteIndex = Integer.parseInt(input[1]);
                    ui.sendOutput("Alright! I've deleted this task!\n\t" + taskList.deleteTask(deleteIndex));
                    break;
                case "todo":
                    Task newTodo = Todo.makeTodo(input[1]);
                    ui.sendOutput(taskList.add(newTodo));
                    break;
                case "event":
                    Task newEvent = Event.makeEvent(input[1]);
                    ui.sendOutput(taskList.add(newEvent));
                    break;
                case "deadline":
                    Task newDeadline = Deadline.makeDeadline(input[1]);
                    ui.sendOutput(taskList.add(newDeadline));
                    break;
                case "save":
                    storage.saveToFile();
                    ui.sendOutput("History sucessfully saved!");
                    break;
                default:
                    throw new ChecklstException("Sorry I didn't understand that command!!");
            }
        } catch (ChecklstException e) {
            storage.removeLastCommand(); // Remove invalid commands
            ui.sendOutput(e.getMessage());
        }
    }

    /**
     * Processes input commands from the saved history and calls the respective command methods
     * but without ui output or storage.
     * @param input Input string.
     * @param storage Storage instance for saving of commands.
     */
    public void parseHistoryCommand(String[] input, TaskList taskList) {
        try {
            switch (input[0]) {
                case "done":
                    int doneIndex = Integer.parseInt(input[1]);
                    taskList.completeTask(doneIndex);
                    break;
                case "delete":
                    int deleteIndex = Integer.parseInt(input[1]);
                    taskList.deleteTask(deleteIndex);
                    break;
                case "todo":
                    Task newTodo = Todo.makeTodo(input[1]);
                    taskList.add(newTodo);
                    break;
                case "event":
                    Task newEvent = Event.makeEvent(input[1]);
                    taskList.add(newEvent);
                    break;
                case "deadline":
                    Task newDeadline = Deadline.makeDeadline(input[1]);
                    taskList.add(newDeadline);
                    break;
            }
        } catch (ChecklstException e) {
            // Nothing because all exceptions should be handled on initial run.
        }
        
    }

}
