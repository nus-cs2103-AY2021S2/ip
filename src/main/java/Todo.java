public class Todo extends Task{
    public Todo(String des) {
        super(des);
    }

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
