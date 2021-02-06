package checklst.parser;

import checklst.exception.ChecklstException;
import checklst.task.Deadline;
import checklst.task.Event;
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
     * @return Response String. 
     */
    public String parse(String[] input, Ui ui, TaskList taskList) {
        String output = "";
        try {
            switch (input[0]) {
            case "list":
                output = ui.sendOutput(taskList.toString());
                break;
            case "done":
                int doneIndex = Integer.parseInt(input[1]);
                output = ui.sendOutput("Nice! I've marked this task as done!\n\t" + taskList.completeTask(doneIndex));
                break;
            case "delete":
                int deleteIndex = Integer.parseInt(input[1]);
                output = ui.sendOutput("Alright! I've deleted this task!\n\t" + taskList.deleteTask(deleteIndex));
                break;
            case "todo":
                Task newTodo = Todo.makeTodo(input[1]);
                output = ui.sendOutput(taskList.add(newTodo));
                break;
            case "event":
                Task newEvent = Event.makeEvent(input[1]);
                output = ui.sendOutput(taskList.add(newEvent));
                break;
            case "deadline":
                Task newDeadline = Deadline.makeDeadline(input[1]);
                output = ui.sendOutput(taskList.add(newDeadline));
                break;
            case "find":
                output = ui.sendOutput("Here are the matching tasks in your list!\n\t" + taskList.findTask(input[1]));
                break;
            default:
                throw new ChecklstException("Sorry I didn't understand that command!!");
            }
        } catch (ChecklstException e) {
            output = ui.sendOutput(e.getMessage());
        }

        return output;
    }

    public void parseHistory(String input, TaskList taskList) throws ChecklstException {
        if (input.charAt(0) == 'T') {
            taskList.add(Todo.parseTodo(input));
        } else if (input.charAt(0) == 'D') {
            taskList.add(Deadline.parseDeadline(input));
        } else if (input.charAt(0) == 'E') {
            taskList.add(Event.parseEvent(input));
        } else {
            throw new ChecklstException("History corrupted");
        }
    }

}
