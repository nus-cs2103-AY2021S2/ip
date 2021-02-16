/**
 * Handles the todo command, where user adds a Todo object to the list.
 */
public class TodoCommand extends AddCommand {

    TodoCommand(String input, String[] parts, TaskList tasks) {
        super(input, parts, tasks);
    }

    /**
     * Returns a string representation of the Todo object just added.
     *
     * @return A string which represents the Todo just added.
     */
    public String getString() {
        StringBuilder str = new StringBuilder();
        for (int i = 1; i < parts.length; i++) {
            str.append(" ");
            str.append(parts[i]);
        }
        String taskString = str.toString();
        Todo todo = new Todo(taskString);
        tasks.add(todo);
        return Ui.showAddText() + todo.toString() + tasks.getSizeString();
    }


}
