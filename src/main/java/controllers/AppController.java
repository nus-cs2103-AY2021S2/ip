package controllers;

import java.util.Arrays;
import java.util.Scanner;

import exceptions.DukeBlankTaskException;
import exceptions.DukeCommandNotFoundException;
import exceptions.DukeTaskIndexOutOfRangeException;
import models.Command;
import views.Greeting;

public class AppController {
    /**
     * AppController starts to listen to commands from the user and performs actions
     * as required by user's commands
     */
    public void start() {
        // initialise greeting view
        Greeting greeting = new Greeting();

        // greet user
        greeting.greet();

        // initialise scanner
        Scanner sc = new Scanner(System.in);

        // init TodosController
        TodosController todosController = new TodosController();

        // loop will be broken only by 'bye'
        while (true) {
            // list has structure [command, ... command related args]
            Command command = new Command(Arrays.asList((sc.nextLine().split(" "))));

            // try catch statement wraps around getCommand which may throw an error if
            // command input does not adhere to command format
            try {
                switch (command.getCommand()) {

                    case "list":
                        // list all the current todos
                        todosController.listTodos();
                        break;

                    case "event":
                        // add a new event to the model
                        try {
                            todosController = todosController.addEvent(command.getCommandArgs());

                        } catch (Exception e) {
                            greeting.printErrorMessage(e);
                        }
                        break;

                    case "todo":
                        // add a new todo to the model
                        try {
                            todosController = todosController.addTodos(command.getCommandArgs());

                        } catch (DukeBlankTaskException e) {
                            greeting.printErrorMessage(e);
                        }
                        break;

                    case "deadline":
                        // amend a current todo's deadline
                        try {
                            todosController = todosController.addDeadline(command.getCommandArgs());

                        } catch (Exception e) {
                            greeting.printErrorMessage(e);
                        }
                        break;

                    case "done":
                        // mark a todo as done
                        try {
                            todosController = todosController.markAsDone(command.getCommandArgs());

                        } catch (DukeTaskIndexOutOfRangeException e) {
                            greeting.printErrorMessage(e);
                        }
                        break;

                    case "delete":
                        // delete a todo from the list
                        try {
                            todosController = todosController.deleteTodo(command.getCommandArgs());

                        } catch (Exception e) {
                            greeting.printErrorMessage(e);
                        }
                        break;

                    case "bye":
                        // break out of main function
                        greeting.bye();

                        // close scanner preventing mem leak
                        sc.close();

                        // return to end function
                        return;

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

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
