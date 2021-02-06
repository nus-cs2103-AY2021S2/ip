package duke.views;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import duke.models.Todo;

public class TodosView {
    /** Denotes the divider to use to go between lines */
    private static final String DIVIDER = "---";

    /**
     * Explicit definition of the default constructor to create a new Todos View renderer
     */
    public TodosView() {}

    /**
     * Takes in an Optional Todo and renders it out into a String
     *
     * @param todoToRender Optional Todo to be rendered
     * @return String which renders out how the information carried by the Todo
     */
    private String renderTodoLine(Optional<? extends Todo> todoToRender) {
        return todoToRender.map(Todo::getMessage).orElse("Empty Todo");
    }

    /**
     * Takes in a matching TodosList and returns a rendered view of the todos with a specified
     * header
     *
     * @param matchingTodosList List of Optional Todos that matches the keywords to be rendered
     */
    public void matchListTodos(List<Optional<? extends Todo>> matchingTodosList) {
        printWithSpacing(String.format("Here are the matching tasks in your list:\n%s",
                IntStream.range(0, matchingTodosList.size())
                        .mapToObj(idx -> String.format("%d.%s", idx + 1,
                                renderTodoLine(matchingTodosList.get(idx))))
                        .collect(Collectors.joining("\n"))));
    }

    /**
     * Turns the todosList into a stream of messages from Todos and output them with a new line in
     * between each Todo
     *
     * @param todosList List of optional todos passed in from TodosController
     */
    public void listTodos(List<Optional<? extends Todo>> todosList) {
        printWithSpacing(String.format("Here are the tasks in your list:\n%s",
                IntStream.range(0, todosList.size()).mapToObj(
                    idx -> String.format("%d.%s", idx + 1, renderTodoLine(todosList.get(idx))))
                        .collect(Collectors.joining("\n"))));
    }

    /**
     * Prints 'Got it, I've added this task:', followed by the message contained in the new todo
     *
     * @param newTodo Optional Todo object containing a new Todo to be printed
     * @param listSize Integer list size taken to return the number of tasks user currently has
     */
    public void added(Optional<? extends Todo> newTodo, int listSize) {
        printWithSpacing(String.format(
                "Got it! I've added this task:\n%s\nNow you have %d tasks in the list.",
                renderTodoLine(newTodo), listSize));
    }

    /**
     * Prints 'Noted. I've removed this task:', followed by message contained in new Todo
     *
     * @param deletedTodo Optional Todo object containing the Todo to be deleted
     * @param listSize Integer list size taken to return number of tasks user currently has
     */
    public void deleted(Optional<? extends Todo> deletedTodo, int listSize) {
        printWithSpacing(String.format(
                "Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.",
                renderTodoLine(deletedTodo), listSize));
    }

    /**
     * Adds text indicating todo is marked as done and renders the String to show the Todo
     *
     * @param newTodo Optional Todo to be marked as Done
     */
    public void markAsDone(Optional<? extends Todo> newTodo) {
        printWithSpacing(
                String.format("Nice! I've marked this task as done:\n%s", renderTodoLine(newTodo)));
    }

    /**
     * Private method printWithSpacing adds divider around the text passed in to be printed
     *
     * @param text String to be printed with divider put around it
     */
    private void printWithSpacing(String text) {
        System.out.printf("\n%s\n%s\n%s\n%n", DIVIDER, text, DIVIDER);
    }
}
