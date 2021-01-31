public class Todo extends Task {

    /**
     * Factory method to make Todo and handle input.
     * @param name - Name of Todo.
     * @return - Todo Object.
     */
    public static Todo makeTodo(String name) {
        return new Todo(name);
    }
    
    protected Todo(String name) {
        super(name);
    }

    protected Todo(String name, boolean completed) {
        super(name, completed);
    }

    @Override
    public Task complete() {
        return new Todo(this.name, true);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
