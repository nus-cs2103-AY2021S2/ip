public class ToDo extends Command {

    public ToDo(String commandDescription) {
        super(commandDescription);
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
