import java.util.List;

public class ListCmd implements Command {
    private final List<Task> lst;

    public ListCmd(List<Task> lst) {
        this.lst = lst;
    }

    @Override
    public String process(String cmdArgs) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lst.size(); i++) {
            Task task = lst.get(i);
            sb.append(String.format("%d. %s\n", i + 1, task.toString()));
        }
        return sb.toString();
    }
}
