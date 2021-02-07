import java.time.LocalDate;

public class AddCommand extends Command {

    AddCommand(String input, String[] parts, TaskList tasks) {
        super(input, parts, tasks);
    }
    @Override
    public String execute() throws InsufficientArgumentsException {
        if (parts.length == 1) {
            throw new InsufficientArgumentsException("Insufficient arguments provided");
        }
        StringBuilder str = new StringBuilder();
        if (input.equals("todo")) {
            for (int i = 1; i < parts.length; i++) {
                str.append(" ");
                str.append(parts[i]);
            }
            String taskString = str.toString();
            Todo todo = new Todo(taskString);
            tasks.add(todo);
            return todo.toString();
        } else {
            int slashIndex = 0;
            for (int i = 0; i < parts.length; i++) {
                if (parts[i].contains(Character.toString('/'))) {
                    slashIndex = i;
                }
            }
            for (int j = 1; j < slashIndex; j++) {
                str.append(" ");
                str.append(parts[j]);
            }
            String taskString = str.toString();
            StringBuilder byStringBuilder = new StringBuilder();
            for (int k = slashIndex + 1; k < parts.length; k++) {
                if (k != slashIndex + 1) {
                    byStringBuilder.append(" ");
                }
                byStringBuilder.append(parts[k]);
            }
            String dateString = byStringBuilder.toString();
            LocalDate date = LocalDate.parse(dateString);
            if (input.equals("deadline")) {
                Deadline deadline = new Deadline(taskString, date);
                tasks.add(deadline);
                return deadline.toString();
            } else {
                Event event = new Event(taskString, date);
                tasks.add(event);
                return event.toString();
            }
        }
    }
}
