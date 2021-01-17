import java.util.List;

public class DoneCmd implements Command {
    private final List<Task> lst;

    public DoneCmd(List<Task> lst) {
        this.lst = lst;
    }

    @Override
    public String process(String cmdArgs) {
        int idx = Integer.parseInt(cmdArgs);
        Task t = lst.get(idx - 1);
        t.markDone();

        String resp = "Nice! I've marked this task as done:\n";
        resp += String.format("[%s] %s\n", t.getStatusIcon(), t.getContent());
        return resp;
    }
}
