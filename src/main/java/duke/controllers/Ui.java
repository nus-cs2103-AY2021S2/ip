package duke.controllers;

import java.util.Arrays;
import java.util.Optional;

import duke.exceptions.DukeBlankTaskException;
import duke.exceptions.DukeCommandNotFoundException;
import duke.exceptions.DukeTaskIndexOutOfRangeException;
import duke.models.*;
import duke.views.Greeting;
import duke.views.TodosView;

public class Ui {
    private final Storage storage;
    private TaskList taskList;

    public Ui(Storage storage) {
        this.storage = storage;
        this.taskList = new TaskList(storage.retrieveLocalDatabase());
    }

    /**
     * AppController starts to listen to commands from the user and performs actions as required by
     * user's commands
     */
    public String respondToInput(String input) {
        // list has structure [command, ... command related args]
        Parser command = new Parser(Arrays.asList((input.split(" "))));

        // try catch statement wraps around getCommand which may throw an error if
        // command input does not adhere to command format
        try {
            // @formatter:off
            switch (command.getCommand()) {
            case LIST:
                // list all the current todos
                return taskList.listTodos();
            case EVENT:
                // add a new event to the model
                try {
                    Pair<TaskList, Optional<? extends Todo>> addEventReturn = taskList.addEvent(command.getCommandArgs());
                    taskList = addEventReturn.getFirst();
                    Optional<? extends Todo> newEvent = addEventReturn.getSecond();
                    return TodosView.renderAddTodoReply(newEvent, taskList.todosSize());
                } catch (Exception e) {
                    return Greeting.printErrorMessage(e);
                }
            case TODO:
                // add a new todo to the model
                try {
                    Pair<TaskList, Optional<? extends Todo>> addTodoReturn  = taskList.addTodo(command.getCommandArgs());
                    taskList = addTodoReturn.getFirst();
                    Optional<? extends Todo> newTodo = addTodoReturn.getSecond();
                    return TodosView.renderAddTodoReply(newTodo, taskList.todosSize());
                } catch (DukeBlankTaskException e) {
                    return Greeting.printErrorMessage(e);
                }
            case DEADLINE:
                // amend a current todo's deadline
                try {
                    Pair<TaskList, Optional<? extends Todo>> addDeadlineReturn = taskList.addDeadline(command.getCommandArgs());
                    taskList = addDeadlineReturn.getFirst();
                    Optional<? extends Todo> newDeadline = addDeadlineReturn.getSecond();
                    return TodosView.renderAddTodoReply(newDeadline, taskList.todosSize());
                } catch (Exception e) {
                    return Greeting.printErrorMessage(e);
                }
            case DONE:
                // mark a todo as done
                try {
                    Pair<TaskList, Optional<? extends Todo>> markAsDoneReturn = taskList.markAsDone(command.getCommandArgs());
                    taskList = markAsDoneReturn.getFirst();
                    Optional<? extends Todo> todoMarkedAsDone = markAsDoneReturn.getSecond();
                    return TodosView.renderMarkTodoAsDoneReply(todoMarkedAsDone);
                } catch (DukeTaskIndexOutOfRangeException e) {
                    return Greeting.printErrorMessage(e);
                }
            case DELETE:
                // delete a todo from the list
                try {
                    Pair<TaskList, Optional<? extends Todo>> deleteTodoReturn = taskList.deleteTodo(command.getCommandArgs());
                    taskList = deleteTodoReturn.getFirst();
                    Optional<? extends Todo> deletedTodo = deleteTodoReturn.getSecond();
                    return TodosView.renderDeleteTodoReply(deletedTodo, taskList.todosSize());
                } catch (Exception e) {
                    return Greeting.printErrorMessage(e);
                }
            case FIND:
                // find a todo with the relevant keyword
                return taskList.findByKeyword(command.getCommandArgs());
            case BYE:

                // save current todosController tasks to local db before exit
                storage.saveTasksToLocalDatabase(taskList.getTodos());

                // return bye greeting
                return Greeting.bye();
            case UNKNOWN:
                // Fallthrough
            default:
                // print command printed in if not recognised
                try {
                    throw new DukeCommandNotFoundException(
                            "Sorry, the command you are trying to use is not found, please try again!");
                } catch (DukeCommandNotFoundException e) {
                    return Greeting.printErrorMessage(e);
                }
            }
        } catch (DukeCommandNotFoundException e) {
            // catch error from Command.getCommand()
            return Greeting.printErrorMessage(e);
        } catch (Exception e) {
            // program not supposed to end up here
            e.printStackTrace();
            return "Error, unexpected catch statement executed";
        }
    }

}
