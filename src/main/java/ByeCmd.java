public class ByeCmd implements Command {
    private static final String byeMsg = "Bye. Hope to see you again soon!\n";

    @Override
    public String process(String cmdArgs) {
        return byeMsg;
    }
}
