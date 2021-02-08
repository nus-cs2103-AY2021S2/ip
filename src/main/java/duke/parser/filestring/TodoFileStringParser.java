package duke.parser.filestring;

import duke.exceptions.DukeExceptionIllegalArgument;
import duke.tasks.Todo;

/**
 * Parser class to read and write Todo to file.
 */
public class TodoFileStringParser extends TaskFileStringParser {

    /**
     * Generates file string for Todo.
     *
     * @param todo Todo
     * @return string representation of todo to be written to file
     */
    public String toFileString(Todo todo) {
        String description = todo.getDescription();
        String done = todo.getDone() ? "1" : "0";
        return String.format("todo\t%s\t%s", description, done);
    }

    /**
     * Reads file string for Todo.
     *
     * @param todoFileString string representation of Todo
     * @return Todo
     * @throws DukeExceptionIllegalArgument When Todo parsing fails
     */
    public static Todo fromFileString(String todoFileString) throws DukeExceptionIllegalArgument {
        String[] data = todoFileString.split("\t");
        assert data.length == 4;
        assert data[0].equals("todo");
        String description = data[1];
        boolean done = data[2].equals("1");
        return new Todo(description, done);
    }
}
