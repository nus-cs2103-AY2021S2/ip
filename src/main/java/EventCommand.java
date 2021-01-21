public class EventCommand extends AddCommand {
    public EventCommand(String content) {
        super(new EventTask(content.split("/at")[0].trim(), content.split("/at")[1].trim()));
    }
}
