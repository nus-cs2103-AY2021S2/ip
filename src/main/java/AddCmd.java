import java.util.List;

public class AddCmd implements Command {
    private final List<Task> lst;

    public AddCmd(List<Task> lst) {
        this.lst = lst;
    }

    @Override
    public String process(String input) {
        this.lst.add(new Task(input));
        return String.format("added: %s\n", input);
    }
}
