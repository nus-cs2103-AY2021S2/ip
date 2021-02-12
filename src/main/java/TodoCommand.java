public class TodoCommand extends AddCommand {

    TodoCommand(String input, String[] parts, TaskList tasks) {
        super(input, parts, tasks);
    }

    public String getString() {
        StringBuilder str = new StringBuilder();
        for (int i = 1; i < parts.length; i++) {
            str.append(" ");
            str.append(parts[i]);
        }
        String taskString = str.toString();
        Todo todo = new Todo(taskString);
        tasks.add(todo);
        return todo.toString();
    }
}
