package duke.controllers;

import java.util.Arrays;
import java.util.Optional;

import duke.exceptions.DukeBlankTaskException;
import duke.exceptions.DukeCommandNotFoundException;
import duke.exceptions.DukeTaskIndexOutOfRangeException;
import duke.models.Pair;
import duke.models.Storage;
import duke.models.Todo;
import duke.models.Parser;
import duke.views.Greeting;
import duke.views.TodosView;

public class Ui {
    /** Storage to deal with database functions */
    private final Storage storage;
    /** TaskList containing tasks and logic */
    private TaskList taskList;

    /**
     * Initialises Storage and TaskList
     * @param storage indicating intialised Storage
     */
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
                return TodosView.formatListOfTodosToString(taskList.getTodos());
            case EVENT:
                // add a new todo to the tasklist
                try {
                    // get pair return
                    Pair<TaskList, Optional<? extends Todo>> addEventReturn =
                            taskList.addEvent(command.getCommandArgs());
                    // assign updated tasklist
                    taskList = addEventReturn.getFirst();
                    // get new event added
                    Optional<? extends Todo> newEvent = addEventReturn.getSecond();

                    // return string reply to adding of new event
                    return TodosView.addTodoReply(newEvent, taskList.todosSize());
                } catch (Exception e) {
                    return Greeting.printErrorMessage(e);
                }
            case TODO:
                // add a new todo to the tasklist
                try {
                    // get pair return from tasklist add todo
                    Pair<TaskList, Optional<? extends Todo>> addTodoReturn =
                            taskList.addTodo(command.getCommandArgs());
                    // update with new tasklist
                    taskList = addTodoReturn.getFirst();
                    // get new todo from tasklist return
                    Optional<? extends Todo> newTodo = addTodoReturn.getSecond();

                    // return rendered reply for todo
                    return TodosView.addTodoReply(newTodo, taskList.todosSize());
                } catch (DukeBlankTaskException e) {
                    return Greeting.printErrorMessage(e);
                }
            case DEADLINE:
                // add a deadline to tasklist
                try {
                    // create new deadline with params from task list
                    Pair<TaskList, Optional<? extends Todo>> addDeadlineReturn =
                            taskList.addDeadline(command.getCommandArgs());

                    // get updated tasklist and replace instance in class
                    taskList = addDeadlineReturn.getFirst();

                    // get new deadline to be printed
                    Optional<? extends Todo> newDeadline = addDeadlineReturn.getSecond();

                    // return rendered String of deadline
                    return TodosView.addTodoReply(newDeadline, taskList.todosSize());
                } catch (Exception e) {
                    return Greeting.printErrorMessage(e);
                }
            case DONE:
                // mark a todo as done
                try {
                    // get mark as done reply from tasklist
                    Pair<TaskList, Optional<? extends Todo>> markAsDoneReturn =
                            taskList.markAsDone(command.getCommandArgs());

                    // replace taskList with new one from tasklist return
                    taskList = markAsDoneReturn.getFirst();

                    // get todoMarkedAsDone to be printed
                    Optional<? extends Todo> todoMarkedAsDone = markAsDoneReturn.getSecond();

                    // reply with the String output
                    return TodosView.markTodoAsDoneReply(todoMarkedAsDone);
                } catch (DukeTaskIndexOutOfRangeException e) {
                    return Greeting.printErrorMessage(e);
                }
            case DELETE:
                // delete a todo from the list
                try {
                    // get responsee from tasklist for delete
                    Pair<TaskList, Optional<? extends Todo>> deleteTodoReturn =
                            taskList.deleteTodo(command.getCommandArgs());

                    // set new taskList as updated one
                    taskList = deleteTodoReturn.getFirst();

                    // get deleted todo
                    Optional<? extends Todo> deletedTodo = deleteTodoReturn.getSecond();

                    // return reply of deleted todo
                    return TodosView.deleteTodoReply(deletedTodo, taskList.todosSize());
                } catch (Exception e) {
                    return Greeting.printErrorMessage(e);
                }
            case FIND:
                // find a todo with the relevant keyword
                return TodosView.formatMatchedTodosToString(taskList.findByKeyword(command.getCommandArgs()));
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
