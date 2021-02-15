package checklst.parser;

import checklst.exception.ChecklstException;
import checklst.task.Deadline;
import checklst.task.Event;
import checklst.task.Task;
import checklst.task.TaskList;
import checklst.task.Todo;

/**
 * The Parser class makes sense of inputs and runs the respective follow up methods.
 */
public class Parser {

    /**
     * Processes input commands and calls the respective command methods.
     * @param input Input string.
     * @param taskList TaskList instance for manipulating tasks.
     * @return Response String.
     * @throws ChecklstException Exceptions from called tasks.
     */
    public String parse(String[] input, TaskList taskList) throws ChecklstException {
        String output = "";
        switch (input[0]) {
        case "list":
            output = taskList.toString();
            break;
        case "done":
            assert input.length >= 1 : "No Done Input";
            int doneIndex = Integer.parseInt(input[1]);
            output = "Nice! I've marked this task as done!\n" + taskList.completeTask(doneIndex);
            break;
        case "delete":
            assert input.length >= 1 : "No Delete Input";
            int deleteIndex = Integer.parseInt(input[1]);
            output = "Alright! I've deleted this task!\n" + taskList.deleteTask(deleteIndex);
            break;
        case "todo":
            assert input.length >= 1 : "No Todo Input";
            Task newTodo = Todo.makeTodo(input[1]);
            output = taskList.add(newTodo);
            break;
        case "event":
            assert input.length >= 1 : "No Event Input";
            Task newEvent = Event.makeEvent(input[1]);
            output = taskList.add(newEvent);
            break;
        case "deadline":
            assert input.length >= 1 : "No Deadline Input";
            Task newDeadline = Deadline.makeDeadline(input[1]);
            output = taskList.add(newDeadline);
            break;
        case "find":
            assert input.length >= 1 : "No Find Input";
            output = "Here are the matching tasks in your list!\n" + taskList.findTask(input[1]);
            break;
        case "sort":
            output = taskList.sort();
            break;
        default:
            throw new ChecklstException("Sorry I didn't understand that command!!");
        }

        return output;
    }

}
