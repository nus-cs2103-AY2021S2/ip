import java.time.format.DateTimeParseException;

public class AddCommand extends Command {
    private final String type;
    public AddCommand(String type, String taskDescription) {
        super(taskDescription);
        this.type = type;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        try {
            Task task;
            if (type.equals("deadline")) {
                storage.append(taskList.addTask("deadline", taskDescription));
            } else if (type.equals("event")) {
                storage.append(taskList.addTask("event", taskDescription));
            } else if (type.equals("todo")) {
                storage.append(taskList.addTask("todo", taskDescription));
            }
        } catch (DateTimeParseException e) {
            System.out.println("     The date time format is wrong. It supposed to be yyyy-MM-dd or yyyy/MM/dd");
        } catch (Exception e) {
            System.out.println("     " + e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }
}
