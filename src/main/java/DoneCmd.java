import java.util.List;

public class DoneCmd implements Command {
    private final List<Task> lst;

    public DoneCmd(List<Task> lst) {
        this.lst = lst;
    }

    @Override
    public String process(String cmdArgs) {
        int idx = Integer.parseInt(cmdArgs) - 1;
        if (idx < 0 || idx >= lst.size()) {
            throw new DukeException(String.format("Item no. %d cannot be found in list", idx + 1));
        }

        Task t = lst.get(idx);
        t.markDone();

        String resp = "Nice! I've marked this task as done:\n";
        resp += String.format("%s\n", t.toString());
        return resp;
    }
}
