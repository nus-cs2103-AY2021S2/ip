import java.util.List;

public class DeleteCmd implements Command {
    private final List<Task> lst;

    public DeleteCmd(List<Task> lst) {
        this.lst = lst;
    }

    @Override
    public String process(String cmdArgs) {
        int idx = Integer.parseInt(cmdArgs) - 1;
        if (idx < 0 || idx >= lst.size()) {
            throw new DukeException(String.format("S/N %d cannot be found in list", idx + 1));
        }

        Task t = this.lst.remove(idx);
        String resp = "Noted. I've removed this task:\n";
        resp += String.format("\t%s\n", t.toString());
        resp += String.format("Now you have %d tasks in the list\n", lst.size());
        return resp;
    }
}
