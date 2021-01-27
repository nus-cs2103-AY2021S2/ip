public class InvalidCommand extends Command {
    private String feedbackMsg;

    public InvalidCommand(String feedbackMsg) {
        this.feedbackMsg = feedbackMsg;
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        return new CommandResult(feedbackMsg);
    }
}
