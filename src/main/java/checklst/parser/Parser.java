package checklst.parser;

import java.util.Map;
import java.util.stream.Collectors;

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

    private final TaskList taskList;
    private final Map<String, MethodPair> methodMap = Map.of(
        "list", new MethodPair("Lists all Tasks", x -> list(x)),
        "done", new MethodPair("Completes a Task", x -> done(x)),
        "delete", new MethodPair("Deletes a Task", x -> delete(x)),
        "todo", new MethodPair("Creates a Todo", x -> todo(x)),
        "event", new MethodPair("Creates an Event", x -> event(x)),
        "deadline", new MethodPair("Creates a Deadline", x -> deadline(x)),
        "find", new MethodPair("Finds a Task", x -> find(x)),
        "sort", new MethodPair("Sorts Taks", x -> sort(x)),
        "help", new MethodPair("Get all Commands", x -> help(x))
    );

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    public String parse(String[] input) throws ChecklstException {
        return methodMap.get(input[0]).getMethod().apply(input);
    }

    private String list(String[] input) {
        return this.taskList.toString();
    }

    private String done(String[] input) throws ChecklstException {
        assert input.length >= 1 : "No Done Input";
        int doneIndex = Integer.parseInt(input[1]);
        return "Nice! I've marked this task as done!\n" + this.taskList.completeTask(doneIndex);
    }

    private String delete(String[] input) throws ChecklstException {
        assert input.length >= 1 : "No Delete Input";
        int deleteIndex = Integer.parseInt(input[1]);
        return "Alright! I've deleted this task!\n" + this.taskList.deleteTask(deleteIndex);
    }

    private String todo(String[] input) throws ChecklstException {
        assert input.length >= 1 : "No Todo Input";
        Task newTodo = Todo.makeTodo(input[1]);
        return this.taskList.add(newTodo);
    }

    private String event(String[] input) throws ChecklstException {
        assert input.length >= 1 : "No Event Input";
        Task newEvent = Event.makeEvent(input[1]);
        return this.taskList.add(newEvent);
    }

    private String deadline(String[] input) throws ChecklstException {
        assert input.length >= 1 : "No Deadline Input";
        Task newDeadline = Deadline.makeDeadline(input[1]);
        return this.taskList.add(newDeadline);
    }

    private String find(String[] input) throws ChecklstException {
        assert input.length >= 1 : "No Find Input";
        return "Here are the matching tasks in your list!\n" + this.taskList.findTask(input[1]);
    }

    private String sort(String[] input) {
        return this.taskList.sort();
    }

    private String help(String[] input) {
        return "These are the Commands Available:\n"
            + this.methodMap.entrySet().stream()
                .map(x -> x.getKey() + ": " + x.getValue().getDescription())
                .collect(Collectors.toList())
                .toString()
                .replace("[", "")
                .replace("]", "")
                .replace(", ", "\n");
    }

}
