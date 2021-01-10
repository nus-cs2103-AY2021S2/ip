package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.IntStream;

import models.Deadline;
import models.Event;
import models.Todo;

import views.TodosView;

public class TodosController {
    List<Optional<? extends Todo>> todosList;
    TodosView todosView = new TodosView();

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
     * @param newTodo
     * @return TodosController with todosList containing the new Todo added
     */
    public TodosController addTodos(List<String> newTodoList) {
        Optional<? extends Todo> newTodoObject = Optional.ofNullable(new Todo(String.join(" ", newTodoList)));
        try {
            this.todosView.added(newTodoObject, this.todosList.size() + 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new TodosController(
                Stream.concat(this.todosList.stream(), Stream.of(newTodoObject)).collect(Collectors.toList()));
    }

    public TodosController addDeadline(List<String> newDeadlineList) {
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

        // Create new Deadline object
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

    public TodosController addEvent(List<String> newDeadlineList) {
        ArrayList<String> message = new ArrayList<>();
        ArrayList<String> eventTime = new ArrayList<>();

        // iterate through list to find where escape character is
        // once found, everything after is part of the deadline
        newDeadlineList.stream().forEach(substring -> {
            if (substring.contains("/")) {
                eventTime.add(substring);
            } else if (eventTime.size() == 0) {
                message.add(substring);
            } else {
                eventTime.add(substring);
            }
        });

        // Create new Deadline object
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
     */
    public TodosController markAsDone(List<String> doneArgs) {
        int idxIsDone = Integer.parseInt(doneArgs.get(0)) - 1;
        assert (idxIsDone < this.todosList.size());
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
