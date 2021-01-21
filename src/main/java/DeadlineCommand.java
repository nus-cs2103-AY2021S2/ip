public class DeadlineCommand extends AddCommand {
    public DeadlineCommand(String content) {
        super(new DeadlineTask(content.split("/by")[0].trim(), content.split("/by")[1].trim()));
    }
}
