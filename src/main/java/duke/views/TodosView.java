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
     * @param todoToRender Optional Todo to be rendered
     * @return String which renders out how the information carried by the Todo
     */
    private static String renderTodoLine(Optional<? extends Todo> todoToRender) {
        return todoToRender.map(Todo::getMessage).orElse("Empty Todo");
    }

    /**
     * Takes in a matching TodosList and returns a rendered view of the todos with a specified
     * header
     *
     * @param matchingTodosList List of Optional Todos that matches the keywords to be rendered
     * @return String containing tasks that match the input keywords
     */
    public static String renderMatchedTodosList(List<Optional<? extends Todo>> matchingTodosList) {
        return padStringWithSpacing(String.format("Here are the matching tasks in your list:\n%s",
                IntStream.range(0, matchingTodosList.size())
                        .mapToObj(idx -> String.format("%d.%s", idx + 1,
                                renderTodoLine(matchingTodosList.get(idx))))
                        .collect(Collectors.joining("\n"))));
    }

    /**
     * Turns the todosList into a stream of messages from Todos and output them with a new line in
     * between each Todo
     * @param todosList List of optional todos passed in from TodosController
     * @return String containing rendered view of listed Todos
     */
    public static String renderListOfTodos(List<Optional<? extends Todo>> todosList) {
        return padStringWithSpacing(String.format("Here are the tasks in your list:\n%s",
                IntStream.range(0, todosList.size()).mapToObj(
                    idx -> String.format("%d.%s", idx + 1, renderTodoLine(todosList.get(idx))))
                        .collect(Collectors.joining("\n"))));
    }

    /**
     * Returns "Got it, I've added this task:", followed by the message contained in the new todo
     * @param newTodo Optional Todo object containing a new Todo to be printed
     * @param listSize Integer list size taken to return the number of tasks user currently has
     * @return String showing todo that got added along with todos list size
     */
    public static String renderAddTodoReply(Optional<? extends Todo> newTodo, int listSize) {
        return padStringWithSpacing(String.format(
                "Got it! I've added this task:\n%s\nNow you have %d tasks in the list.",
                renderTodoLine(newTodo), listSize));
    }

    /**
     * Returns  "Noted. I've removed this task:", followed by message contained in new Todo
     * @param deletedTodo Optional Todo object containing the Todo to be deleted
     * @param listSize Integer list size taken to return number of tasks user currently has
     * @return String containing reply to deleting a Todo
     */
    public static String renderDeleteTodoReply(Optional<? extends Todo> deletedTodo, int listSize) {
        return padStringWithSpacing(String.format(
                "Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.",
                renderTodoLine(deletedTodo), listSize));
    }

    /**
     * Adds text indicating todo is marked as done and renders the String to show the Todo
     * @param newTodo Optional Todo to be marked as Done
     * @return String containing reply to marking a todo as done
     */
    public static String renderMarkTodoAsDoneReply(Optional<? extends Todo> newTodo) {
        return padStringWithSpacing(
                String.format("Nice! I've marked this task as done:\n%s", renderTodoLine(newTodo)));
    }

    /**
     * Private method padStringWithSpacing adds divider around the text passed in to be printed
     * @param input String to be padded with divider put around it
     * @return input String padded with dividers and spacing
     */
    private static String padStringWithSpacing(String input) {
        return String.format("\n%s\n%s\n%s\n%n", DIVIDER, input, DIVIDER);
    }
}
