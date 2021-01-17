import java.util.List;

public class AddCmd implements Command {
    private final List<String> lst;

    public AddCmd(List<String> lst) {
        this.lst = lst;
    }

    public String process(String input) {
        this.lst.add(input);
        return String.format("added: %s\n", input);
    }
}
