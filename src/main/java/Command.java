import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Command {
    private String command;
    private String description;

    public Command(String command, String description) {
        this.command = command;
        this.description = description;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        String[] descriptionSplit;
        switch (command) {
        case "bye":
        case "list":
        case "hello":
            ui.showCommandReply(command, tasks);
            break;
        case "done":
            try {
                tasks.getTask(Integer.parseInt(description) - 1).markAsDone();
                ui.showDoneReply(Integer.parseInt(description) - 1, tasks);
                storage.updateFile(tasks);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showErrorReply("error_done_empty");
            } catch (NumberFormatException e) {
                ui.showErrorReply("error_done_no_meaning");
            } catch (IndexOutOfBoundsException e) {
                ui.showErrorReply("error_done_non_existed_task");
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
        case "delete":
            try {
                tasks.getTask(Integer.parseInt(description) - 1);
                ui.showDeleteReply(Integer.parseInt(description) - 1, tasks);
                tasks.removeTask(Integer.parseInt(description) - 1);
                storage.updateFile(tasks);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showErrorReply("error_delete_empty");
            } catch (NumberFormatException e) {
                ui.showErrorReply("error_delete_no_meaning");
            } catch (NullPointerException e) {
                ui.showErrorReply("error_delete_non_existed_task");
            } catch (IndexOutOfBoundsException e) {
                ui.showErrorReply("error_delete_non_existed_task");
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
        case "date":
            try {
                tasks.getTask(Integer.parseInt(description) - 1);
                ui.showDateReply(Integer.parseInt(description) - 1, tasks);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showErrorReply("error_date_empty");
            } catch (NumberFormatException e) {
                ui.showErrorReply("error_date_no_meaning");
            } catch (NullPointerException e) {
                ui.showErrorReply("error_date_non_existed_task");
            } catch (IndexOutOfBoundsException e) {
                ui.showErrorReply("error_date_non_existed_task");
            }
            break;


        case "todo":
            try {
                String test = description;
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showErrorReply("error_todo_empty");
                break;
            }
            tasks.addTask(new Todo(description));
            storage.updateFile(tasks);
            ui.showCommandReply(description, tasks);
            break;

        case "deadline":
            try {
                String test = description;
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showErrorReply("error_deadline_empty");
                break;
            }
            descriptionSplit = description.split("/by");
            try {
                tasks.addTask(new Deadline(descriptionSplit[0].trim(), descriptionSplit[1].trim()));
                storage.updateFile(tasks);
                ui.showCommandReply(command, tasks);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showErrorReply("error_deadline_by");
            } catch (DateTimeParseException e) {
                ui.showErrorReply("error_deadline_by");
            }

            break;
        case "event":
            try {
                String test = description;
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showErrorReply("error_event_empty");
                break;
            }
            descriptionSplit = description.split("/at");
            try {
                tasks.addTask(new Event(descriptionSplit[0].trim(), descriptionSplit[1].trim()));
                storage.updateFile(tasks);
                ui.showCommandReply(command, tasks);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showErrorReply("error_event_at");
            } catch (DateTimeParseException e) {
                ui.showErrorReply("error_event_at");
            }

            break;
        default:
            ui.showErrorReply("error_no_meaning");
        }
    }

}
