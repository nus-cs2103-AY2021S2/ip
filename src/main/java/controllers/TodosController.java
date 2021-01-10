package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import models.Todo;

import views.TodosView;

public class TodosController {
    List<Optional<Todo>> todosList;
    TodosView todosView = new TodosView();

    /**
     * Constructor of TodosController without any arguments initialises a new TodosView and an empty
     * list of Optional Todo objects
     */
    public TodosController() {
        this.todosList = new ArrayList<>();
    }

    /**
     * Contructor of TodosController which takes in an existing List of Optional Todos
     * 
     * @param todosList is an existing List of Optional Todos
     */
    public TodosController(List<Optional<Todo>> todosList) {
        this.todosList = todosList;
    }

    /**
     * Passes list of todos in the TodosController to TodosView to render the view of the list of
     * Todos
     */
    public void listTodos() {
        todosView.listTodos(todosList);
    }

    /**
     * Adds a new Todo to the todosList and returns new TodosController containing that list
     * 
     * @param newTodo
     * @return TodosController with todosList containing the new Todo added
     */
    public TodosController addTodos(List<String> newTodoList) {
        Optional<Todo> newTodoObject = Optional.ofNullable(new Todo(String.join(" ", newTodoList)));
        try {
            todosView.added(newTodoObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new TodosController(Stream.concat(todosList.stream(), Stream.of(newTodoObject))
                .collect(Collectors.toList()));
    }
}
