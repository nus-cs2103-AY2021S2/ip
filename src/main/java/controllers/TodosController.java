package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import java.util.stream.IntStream;

import exceptions.DukeBlankDetailsException;
import exceptions.DukeBlankTaskException;
import exceptions.DukeTaskIndexOutOfRangeException;
import models.Deadline;
import models.Event;
import models.Todo;

import views.TodosView;

public class TodosController {
    /** todosList contains the state of the todos */
    private List<Optional<? extends Todo>> todosList;
    /** TodosView initialised to render view of Todos */
    private TodosView todosView = new TodosView();

    /**
     * Constructor of TodosController without any arguments initialises a new
     * TodosView and an empty list of Optional Todo objects
     */
    public TodosController() {
        this.todosList = new ArrayList<>();
    }

    /**
     * Contructor of TodosController which takes in an existing List of Optional
     * Todos
     * 
     * @param todosList is an existing List of Optional Todos
     */
    public TodosController(List<Optional<? extends Todo>> todosList) {
        this.todosList = todosList;
    }

    /**
     * Passes list of todos in the TodosController to TodosView to render the view
     * of the list of Todos
     */
    public void listTodos() {
        this.todosView.listTodos(this.todosList);
    }

    /**
     * Adds a new Todo to the todosList and returns new TodosController containing
     * that list
     * 
     * @param newTodoList contains the new Todo that must not be an empty array
     * @throws DukeBlankTaskException when user types in 'todo' but has nothing
     *                                afterwards
     * @return TodosController with todosList containing the new Todo added
     */
    public TodosController addTodos(List<String> newTodoList) throws DukeBlankTaskException {
        if (newTodoList.size() == 0) {
            throw new DukeBlankTaskException("The Todo you are trying to add cannot be blank!");
        }
        Optional<? extends Todo> newTodoObject = Optional.ofNullable(new Todo(String.join(" ", newTodoList)));
        try {
            this.todosView.added(newTodoObject, this.todosList.size() + 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new TodosController(
                Stream.concat(this.todosList.stream(), Stream.of(newTodoObject)).collect(Collectors.toList()));
    }

    /**
     * Takes in the list containing details about the new deadline and returns a new
     * TodosController with the new Deadline added
     * 
     * @param newDeadlineList takes in list of arguments provided to the command for
     *                        processing into a Deadline object
     * @return new TodosController with the new Deadline object added into it
     * @throws DukeBlankTaskException    Exception is thrown when user does not add
     *                                   in any details after typing the 'deadline'
     *                                   command
     * @throws DukeBlankDetailsException Exception is thrown when user tries to
     *                                   define an deadline, without adding /by
     *                                   <details> for the event
     */
    public TodosController addDeadline(List<String> newDeadlineList)
            throws DukeBlankTaskException, DukeBlankDetailsException {
        if (newDeadlineList.size() == 0) {
            throw new DukeBlankTaskException("The Deadline you are trying to add cannot be blank!");
        }

        ArrayList<String> message = new ArrayList<>();
        ArrayList<String> deadline = new ArrayList<>();

        // iterate through list to find where escape character is
        // once found, everything after is part of the deadline
        newDeadlineList.stream().forEach(substring -> {
            if (substring.contains("/")) {
                deadline.add(substring);
            } else if (deadline.size() == 0) {
                message.add(substring);
            } else {
                deadline.add(substring);
            }
        });

        // if no message, throw exception
        if (message.size() == 0) {
            throw new DukeBlankTaskException("Please define a task message for your Deadline");
        }

        // if no deadline input or /by without any deadline, throw exception
        if (deadline.size() <= 1) {
            throw new DukeBlankDetailsException(
                    "Please add a /by followed by the deadline to specify a deadline for the Deadline task");
        }

        // Create new Deadline object, slicing deadline array from index 1 since we
        // added the '/by' which shouldn't be in the actual Deadline object
        Optional<Deadline> newDeadline = Optional.ofNullable(
                new Deadline(String.join(" ", message), String.join(" ", deadline.subList(1, deadline.size()))));
        try {
            this.todosView.added(newDeadline, this.todosList.size() + 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // return new controller
        return new TodosController(
                Stream.concat(this.todosList.stream(), Stream.of(newDeadline)).collect(Collectors.toList()));
    }

    /**
     * Takes in the list containing details about the new deadline and returns a new
     * TodosController with the new Event added
     * 
     * @param newEventList takes in list of arguments provided to the command for
     *                     processing into a Event object
     * @return new TodosController with the new Event object added into it
     * @throws DukeBlankTaskException    Exception is thrown when user does not add
     *                                   in any details after typing the 'event'
     *                                   command
     * @throws DukeBlankDetailsException Exception is thrown when user tries to
     *                                   define an event, without adding /at
     *                                   <details> for the event
     */
    public TodosController addEvent(List<String> newEventList)
            throws DukeBlankDetailsException, DukeBlankTaskException {
        // if list is empty, throw error
        if (newEventList.size() == 0) {
            throw new DukeBlankTaskException("The Event you are trying to add cannot be blank!");
        }

        ArrayList<String> message = new ArrayList<>();
        ArrayList<String> eventTime = new ArrayList<>();

        // iterate through list to find where escape character is
        // once found, everything after is part of the deadline
        newEventList.stream().forEach(substring -> {
            if (substring.contains("/")) {
                eventTime.add(substring);
            } else if (eventTime.size() == 0) {
                message.add(substring);
            } else {
                eventTime.add(substring);
            }
        });

        // if no message, throw exception
        if (message.size() == 0) {
            throw new DukeBlankTaskException("Please define a task message for your Event");
        }

        // if no event time is found or defined /at without anything else, throw
        // exception
        if (eventTime.size() <= 1) {
            throw new DukeBlankDetailsException("Please specify a time and date for an Event.");
        }

        // Create new Event object, slicing eventTime array from index 1 since we
        // added the '/at' which shouldn't be in the actual Event object
        Optional<Event> newEvent = Optional.ofNullable(
                new Event(String.join(" ", message), String.join(" ", eventTime.subList(1, eventTime.size()))));
        try {
            this.todosView.added(newEvent, this.todosList.size() + 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // return new controller
        return new TodosController(
                Stream.concat(this.todosList.stream(), Stream.of(newEvent)).collect(Collectors.toList()));
    }

    /**
     * Asserts that index of todo passed in to be marked as done is lesser than
     * length of todosList, else there would be an arrayoutofboundsexception thrown
     * 
     * @param doneArgs should be a List<String> of size 1 containing one argument
     *                 that is the ID of which todo to mark as done and uses a
     *                 1-based indexing of the todos
     * @return TodosController containing Todo that's now updated as done
     * @throws DukeTaskIndexOutOfRangeException Exception is thrown when the user
     *                                          specifies a task index that is out
     *                                          of range.
     */
    public TodosController markAsDone(List<String> doneArgs) throws DukeTaskIndexOutOfRangeException {
        int idxIsDone = Integer.parseInt(doneArgs.get(0)) - 1;
        if (idxIsDone >= this.todosList.size()) {
            throw new DukeTaskIndexOutOfRangeException(
                    "The index you input has an index that is beyond the range of the number of tasks you currently have. Please try again.");
        }
        return new TodosController(IntStream.range(0, this.todosList.size()).mapToObj(idx -> {
            if (idx == idxIsDone) {
                Optional<? extends Todo> doneTodo = this.todosList.get(idx).map(todo -> todo.markAsDone());
                try {
                    this.todosView.markAsDone(doneTodo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return doneTodo;
            } else {
                return this.todosList.get(idx);
            }
        }).collect(Collectors.toList()));
    }

}
