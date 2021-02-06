package duke.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import duke.exceptions.DukeBlankTaskException;
import duke.exceptions.DukeCommandNotFoundException;
import duke.exceptions.DukeTaskIndexOutOfRangeException;
import duke.models.Parser;
import duke.models.Storage;
import duke.models.Todo;
import duke.views.Greeting;

public class Ui {
    private final Storage storage;

    public Ui(Storage storage) {
        this.storage = storage;
    }

    /**
     * AppController starts to listen to commands from the user and performs actions as required by
     * user's commands
     */
    public void start() {
        // initialise greeting view
        Greeting greeting = new Greeting();

        // greet user
        greeting.greet();

        // initialise scanner
        Scanner sc = new Scanner(System.in);

        // get existing list of Todos if any
        List<Optional<? extends Todo>> existingTodosList = storage.retrieveLocalDatabase();

        // init TodosController
        TaskList todosController = new TaskList(existingTodosList);

        // loop will be broken only by 'bye'
        while (true) {
            // list has structure [command, ... command related args]
            Parser command = new Parser(Arrays.asList((sc.nextLine().split(" "))));

            // try catch statement wraps around getCommand which may throw an error if
            // command input does not adhere to command format
            try {
                //@formatter:off
                switch (command.getCommand()) {
                case LIST:
                    // list all the current todos
                    todosController.listTodos();
                    break;
                case EVENT:
                    // add a new event to the model
                    try {
                        todosController = todosController.addEvent(command.getCommandArgs());
                    } catch (Exception e) {
                        greeting.printErrorMessage(e);
                    }
                    break;
                case TODO:
                    // add a new todo to the model
                    try {
                        todosController = todosController.addTodo(command.getCommandArgs());
                    } catch (DukeBlankTaskException e) {
                        greeting.printErrorMessage(e);
                    }
                    break;
                case DEADLINE:
                    // amend a current todo's deadline
                    try {
                        todosController = todosController.addDeadline(command.getCommandArgs());
                    } catch (Exception e) {
                        greeting.printErrorMessage(e);
                    }
                    break;
                case DONE:
                    // mark a todo as done
                    try {
                        todosController = todosController.markAsDone(command.getCommandArgs());
                    } catch (DukeTaskIndexOutOfRangeException e) {
                        greeting.printErrorMessage(e);
                    }
                    break;
                case DELETE:
                    // delete a todo from the list
                    try {
                        todosController = todosController.deleteTodo(command.getCommandArgs());
                    } catch (Exception e) {
                        greeting.printErrorMessage(e);
                    }
                    break;
                case FIND:
                    // find a todo with the relevant keyword
                    todosController.findByKeyword(command.getCommandArgs());
                    break;
                case BYE:
                    // break out of main function
                    greeting.bye();

                    // save current todosController tasks to local db before exit
                    storage.saveTasksToLocalDatabase(todosController.getTodos());

                    // close scanner preventing mem leak
                    sc.close();

                    // return is called here to end start()
                    // thereby, stopping the instance of duke.Duke
                    return;
                case UNKNOWN:
                    // Fallthrough
                default:
                    // print command printed in if not recognised
                    try {
                        throw new DukeCommandNotFoundException(
                                "Sorry, the command you are trying to use is not found, please try again!");
                    } catch (DukeCommandNotFoundException e) {
                        greeting.printErrorMessage(e);
                    }
                    break;
                }
            } catch (DukeCommandNotFoundException e) {
                // catch error from Command.getCommand()
                greeting.printErrorMessage(e);
            } catch (Exception e) {
                // program not supposed to end up here
                e.printStackTrace();
            }
        }

    }

}
