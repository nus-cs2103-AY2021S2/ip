public class DoneCmd extends Command {
    private final String cmdArgs;

    public DoneCmd(String cmdArgs) {
        this.cmdArgs = cmdArgs;
    }

    @Override
    public String execute(TaskList lst) {
        int idx = Integer.parseInt(cmdArgs) - 1;
        if (idx < 0 || idx >= lst.size()) {
            throw new DukeException(String.format("Item no. %d cannot be found in list", idx + 1));
        }

        Task t = lst.get(idx);
        t.markDone();

        return "Nice! I've marked this task as done:\n"
                + String.format("%s\n", t.toString());
    }
}
