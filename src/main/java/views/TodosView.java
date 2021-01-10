package views;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import models.Todo;

public class TodosView {
    String spacing = "---";

    public TodosView() {
    }

    /**
     * Turns the todosList into a stream of messages from Todos and output them with a new line in
     * between each Todo
     * 
     * @param todosList List of optional todos passed in from TodosController
     */
    public void listTodos(List<Optional<Todo>> todosList) {
        printWithSpacing(IntStream.range(0, todosList.size())
                .mapToObj(idx -> String.format("%d. %s", idx + 1,
                        todosList.get(idx).map(todo -> todo.getMessage()).orElse("Empty Todo")))
                .collect(Collectors.joining("\n")));
    }

    /**
     * Prints 'added:', followed by the message contained in the new todo
     * 
     * @param newTodo Optional Todo object containing a new Todo to be printed
     * @throws Exception if the newTodo object is empty
     */
    public void added(Optional<Todo> newTodo) throws Exception {
        printWithSpacing(String.format("added: %s", newTodo.map(todo -> todo.getMessage())
                .orElseThrow(() -> new Exception("The Todo object supplied is empty"))));
    }

    /**
     * Private method printWithSpacing adds spacing around the text passed in to be printed
     * 
     * @param text String to be printed with spacing put around it
     */
    private void printWithSpacing(String text) {
        System.out.println(String.format("\n%s\n%s\n%s\n", spacing, text, spacing));
    }
}
