package controllers;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import exceptions.DukeBlankDetailsException;
import exceptions.DukeBlankTaskException;
import exceptions.DukeDateTimeParseException;
import exceptions.DukeTaskIndexOutOfRangeException;

import models.Deadline;
import models.Event;
import models.Todo;

import views.TodosView;

public class TaskList {
    /** todosList contains the state of the todos */
    private final List<Optional<? extends Todo>> todos;
    /** TodosView initialised to render view of Todos */
    private final TodosView todosView = new TodosView();

    /**
     * Constructor of TodosController without any arguments initialises a new TodosView and an empty
     * list of Optional Todo objects
     */
    public TaskList() {
        this.todos = new ArrayList<>();
    }

    /**
     * Constructor of TodosController which takes in an existing List of Optional Todos
     * 
     * @param todosList is an existing List of Optional Todos
     */
    public TaskList(List<Optional<? extends Todo>> todosList) {
        this.todos = todosList;
    }

    /**
     * Passes list of todos in the TodosController to TodosView to render the view of the list of
     * Todos
     */
    public void listTodos() {
        this.todosView.listTodos(this.todos);
    }

    /**
     * Getter for todosList attr in TodosController
     * 
     * @return List<Optional<? extends Todo>> list of all todos contained in TodosController
     */
    public List<Optional<? extends Todo>> getTodos() {
        return this.todos;
    }

    /**
     * Takes in a list of keywords and prints todos with messages that contains any of the keywords
     * passed in
     * 
     * @param keywordList String list of keywords to be matched
     */
    public void findByKeyword(List<String> keywordList) {
        todosView.matchListTodos(this.todos.stream().filter(optTodo -> {
            // check if todo message contains keyword
            return optTodo.map(Todo::getMessage).map(message -> {
                // split message by space as delimiter
                List<String> messagesSplitByWhitespace = Arrays.asList(message.split(" "));
                // if any part of split message is contained in keywordList, return true
                return messagesSplitByWhitespace.stream().anyMatch(keywordList::contains);
            }).orElse(false);
        }).collect(Collectors.toList()));
    }

    /**
     * Adds a new Todo to the todosList and returns new TodosController containing that list
     * 
     * @param newTodoList contains the new Todo that must not be an empty array
     * @throws DukeBlankTaskException when user types in 'todo' but has nothing afterwards
     * @return TodosController with todosList containing the new Todo added
     */
    public TaskList addTodo(List<String> newTodoList) throws DukeBlankTaskException {
        if (newTodoList.size() == 0) {
            throw new DukeBlankTaskException("The Todo you are trying to add cannot be blank!");
        }
        Optional<? extends Todo> newTodoObject =
                Optional.of(new Todo(String.join(" ", newTodoList)));
        try {
            this.todosView.added(newTodoObject, this.todos.size() + 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new TaskList(Stream.concat(this.todos.stream(), Stream.of(newTodoObject))
                .collect(Collectors.toList()));
    }

    /**
     * Deletes a todo from the list of the todos controller
     * 
     * @param deleteTodoArgs is a list of size 1, containing the index of the todo to delete
     * @return TodosController with the todo with index passed in deleted from the list
     * @throws DukeBlankTaskException when user specifies the delete command without providing an
     *         index of the todo to delete
     * @throws DukeTaskIndexOutOfRangeException when user specifies an index that is out of the
     *         range of the list size of todos in the controller
     */
    public TaskList deleteTodo(List<String> deleteTodoArgs)
            throws DukeBlankTaskException, DukeTaskIndexOutOfRangeException {
        // check if args is empty
        if (deleteTodoArgs.size() == 0) {
            throw new DukeBlankTaskException(
                    "Please input an index for the Todo you want to delete!");
        }

        // get index of todo to delete
        int idxDelete = Integer.parseInt(deleteTodoArgs.get(0)) - 1;
        if (idxDelete >= this.todos.size()) {
            throw new DukeTaskIndexOutOfRangeException("The index you input has an index that "
                            + "is beyond the range of the number of tasks you "
                            + "currently have. Please try again.");
        }

        // render deleted view
        todosView.deleted(this.todos.get(idxDelete), this.todos.size() - 1);

        // remove from stream
        return new TaskList(
                IntStream.range(0, this.todos.size()).filter(idx -> idx != idxDelete)
                        .mapToObj(this.todos::get).collect(Collectors.toList()));
    }

    /**
     * Takes in the list containing details about the new deadline and returns a new TodosController
     * with the new Deadline added
     * 
     * @param newDeadlineList takes in list of arguments provided to the command for processing into
     *        a Deadline object
     * @return new TodosController with the new Deadline object added into it
     * @throws DukeBlankTaskException Exception is thrown when user does not add in any details
     *         after typing the 'deadline' command
     * @throws DukeBlankDetailsException Exception is thrown when user tries to define an deadline,
     *         without adding /by <details> for the event
     * @throws DukeDateTimeParseException Exception is thrown when date time passed into CLI is of
     *         the wrong format
     */
    public TaskList addDeadline(List<String> newDeadlineList)
            throws DukeBlankTaskException, DukeBlankDetailsException, DukeDateTimeParseException {
        if (newDeadlineList.size() == 0) {
            throw new DukeBlankTaskException("The Deadline you are trying to add cannot be blank!");
        }

        ArrayList<String> messages = new ArrayList<>();
        ArrayList<String> deadlines = new ArrayList<>();

        // iterate through list to find where escape character is
        // once found, everything after is part of the deadline
        newDeadlineList.stream().forEach(substring -> {
            if (substring.contains("/")) {
                deadlines.add(substring);
            } else if (deadlines.size() == 0) {
                messages.add(substring);
            } else {
                deadlines.add(substring);
            }
        });

        // if no message, throw exception
        if (messages.size() == 0) {
            throw new DukeBlankTaskException("Please define a task message for your Deadline");
        }

        // if no deadline input or /by without any deadline, throw exception
        if (deadlines.size() <= 1) {
            // @formatter:off
            String exceptionMessage = "Please add a /by followed by the deadline time and date in DD/MM/YYYY "
                    + "HHMM to specify a time and date for the Deadline task. If there is no time for "
                    + "this deadline perhaps consider creating a todo instead.";
            throw new DukeBlankDetailsException(exceptionMessage);
        }

        // Create new Deadline object, slicing deadline array from index 1 since we
        // added the '/by' which shouldn't be in the actual Deadline object
        // creating a new deadline might throw an exception if the date time is in the
        // wrong format
        Optional<Deadline> newDeadline;
        try {
            newDeadline = Optional.of(new Deadline(String.join(" ", messages),
                    String.join(" ", deadlines.subList(1, deadlines.size()))));
        } catch (DateTimeParseException e) {
            throw new DukeDateTimeParseException(
                    "Please format your date after /by to be DD/MM/YYYY HHMM");
        }

        // render added view
        this.todosView.added(newDeadline, this.todos.size() + 1);

        // return new controller
        return new TaskList(Stream.concat(this.todos.stream(), Stream.of(newDeadline))
                .collect(Collectors.toList()));
    }

    /**
     * Takes in the list containing details about the new deadline and returns a new TodosController
     * with the new Event added
     * 
     * @param newEventList takes in list of arguments provided to the command for processing into a
     *        Event object
     * @return new TodosController with the new Event object added into it
     * @throws DukeBlankTaskException Exception is thrown when user does not add in any details
     *         after typing the 'event' command
     * @throws DukeBlankDetailsException Exception is thrown when user tries to define an event,
     *         without adding /at <details> for the event
     * @throws DukeDateTimeParseException Exception is thrown when date time passed into CLI is of
     *         the wrong format
     */
    public TaskList addEvent(List<String> newEventList)
            throws DukeBlankDetailsException, DukeBlankTaskException, DukeDateTimeParseException {
        // if list is empty, throw error
        if (newEventList.size() == 0) {
            throw new DukeBlankTaskException("The Event you are trying to add cannot be blank!");
        }

        ArrayList<String> messages = new ArrayList<>();
        ArrayList<String> eventTimes = new ArrayList<>();

        // iterate through list to find where escape character is
        // once found, everything after is part of the deadline
        newEventList.forEach(substring -> {
            if (substring.contains("/")) {
                eventTimes.add(substring);
            } else if (eventTimes.size() == 0) {
                messages.add(substring);
            } else {
                eventTimes.add(substring);
            }
        });

        // if no message, throw exception
        if (messages.size() == 0) {
            throw new DukeBlankTaskException("Please define a task message for your Event");
        }

        // if no deadline input or /by without any deadline, throw exception
        if (eventTimes.size() <= 1) {
            // @formatter:off
            String exceptionMessage = "Please add a /by followed by the event time and date in DD/MM/YYYY "
                            + "HHMM to specify a time and date for the Event task. If there is no time for "
                            + "this event perhaps consider creating a todo instead.";
            throw new DukeBlankDetailsException(exceptionMessage);
        }

        // Create new Event object, slicing eventTime array from index 1 since we
        // added the '/at' which shouldn't be in the actual Event object
        // Creating an event might throw an exception if the date is in the wrong format
        Optional<Event> newEvent;
        try {
            newEvent = Optional.of(new Event(String.join(" ", messages),
                    String.join(" ", eventTimes.subList(1, eventTimes.size()))));
        } catch (DateTimeParseException e) {
            throw new DukeDateTimeParseException(
                    "Please format your date after /at to be DD/MM/YYYY HHMM");
        }

        try {
            this.todosView.added(newEvent, this.todos.size() + 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // return new controller
        return new TaskList(Stream.concat(this.todos.stream(), Stream.of(newEvent))
                .collect(Collectors.toList()));
    }

    /**
     * Asserts that index of todo passed in to be marked as done is lesser than length of todosList,
     * else there would be an arrayoutofboundsexception thrown
     * 
     * @param doneArgs should be a List<String> of size 1 containing one argument that is the ID of
     *        which todo to mark as done and uses a 1-based indexing of the todos
     * @return TodosController containing Todo that's now updated as done
     * @throws DukeTaskIndexOutOfRangeException Exception is thrown when the user specifies a task
     *         index that is out of range.
     */
    public TaskList markAsDone(List<String> doneArgs) throws DukeTaskIndexOutOfRangeException {
        int idxIsDone = Integer.parseInt(doneArgs.get(0)) - 1;
        if (idxIsDone >= this.todos.size()) {
            throw new DukeTaskIndexOutOfRangeException("The index you input has an index that is "
                            + "beyond the range of the number of tasks you currently have. "
                            + "Please try again.");
        }
        return new TaskList(IntStream.range(0, this.todos.size()).mapToObj(idx -> {
            if (idx == idxIsDone) {
                Optional<? extends Todo> doneTodo =
                        this.todos.get(idx).map(Todo::markAsDone);
                try {
                    this.todosView.markAsDone(doneTodo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return doneTodo;
            } else {
                return this.todos.get(idx);
            }
        }).collect(Collectors.toList()));
    }

}
