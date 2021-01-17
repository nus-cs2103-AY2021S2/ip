import java.util.List;

public class ListCmd implements Command {
    private final List<String> lst;

    public ListCmd(List<String> lst) {
        this.lst = lst;
    }

    @Override
    public String process(String cmdArgs) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lst.size(); i++) {
            sb.append(String.format("%d. %s\n", i + 1, lst.get(i)));
        }
        return sb.toString();
    }
}
