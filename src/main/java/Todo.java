public class Todo extends Task{
    public Todo(String des) {
        super(des);
    }

    /**
     * makes a todo with the string given
     * @param task input string containing task description
     * @return a Todo
     * @throws DukeException
     */

    public static Todo makeTodo(String task) throws DukeException {
        if (task.equals("")) {
            throw new DukeException("☹ OOPS!!!The description of a todo cannot be empty.\n");
        } else {
            return new Todo(task);
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
