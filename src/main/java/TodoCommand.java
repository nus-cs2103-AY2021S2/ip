public class TodoCommand extends AddCommand {
    public TodoCommand(String content) {
        super(new TodoTask(content));
    }
}
