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
     */
    public String parse(String[] input, TaskList taskList) {
        String output = "";
        try {
            switch (input[0]) {
            case "list":
                output = taskList.toString();
                break;
            case "done":
                assert input.length > 0 : "No Done Input";
                int doneIndex = Integer.parseInt(input[1]);
                output = "Nice! I've marked this task as done!\n" + taskList.completeTask(doneIndex);
                break;
            case "delete":
                assert input.length > 0 : "No Delete Input";
                int deleteIndex = Integer.parseInt(input[1]);
                output = "Alright! I've deleted this task!\n" + taskList.deleteTask(deleteIndex);
                break;
            case "todo":
                assert input.length > 0 : "No Todo Input";
                Task newTodo = Todo.makeTodo(input[1]);
                output = taskList.add(newTodo);
                break;
            case "event":
                assert input.length > 0 : "No Event Input";
                Task newEvent = Event.makeEvent(input[1]);
                output = taskList.add(newEvent);
                break;
            case "deadline":
                assert input.length > 0 : "No Deadline Input";
                Task newDeadline = Deadline.makeDeadline(input[1]);
                output = taskList.add(newDeadline);
                break;
            case "find":
                assert input.length > 0 : "No Find Input";
                output = "Here are the matching tasks in your list!\n" + taskList.findTask(input[1]);
                break;
            default:
                throw new ChecklstException("Sorry I didn't understand that command!!");
            }
        } catch (ChecklstException e) {
            output = e.getMessage();
        }

        return output;
    }

    /**
     * Processes history commands and run the relevant Task parser.
     * @param input Input string containing a Task.
     * @param taskList TaskList to add Tasks to.
     * @throws ChecklstException Exception if strings are corrupted.
     */
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
