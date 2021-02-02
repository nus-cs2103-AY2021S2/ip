public class ListCommand extends Command {

    public static final String COMMAND = "list";

    public ListCommand() {
    }

    @Override
    public boolean shouldSave() {
        return false;
    }

    @Override
    public String execute() {
        StringBuilder builder = new StringBuilder();
        builder.append("Here are the tasks in your list:\n");
        int numbering = 1;
        for (Task task: taskList.getList()) {
            builder.append("\t")
                    .append(numbering++)
                    .append(".")
                    .append(task)
                    .append("\n");
        }

        if (numbering == 1) {
            builder.append("\tYou currently have 0 tasks.");
        }
        return builder.toString().trim();
    }
}
