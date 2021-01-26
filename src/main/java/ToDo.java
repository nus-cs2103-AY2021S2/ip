public class ToDo extends Task{
    public ToDo(String name) {
        super(name);
    }
    public ToDo(String name, boolean done) {
        super(name);
        this.done = done;
    }
    @Override
    public void addTask(int count) throws DukeException{
        if(this.name.equals("todo")) {
            throw new DukeException("     ☹ OOPS!!! The description of a todo cannot be empty.");
        } else {
            super.addTask(count);
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
