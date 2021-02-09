package vergil.types;

import vergil.components.Storage;
import vergil.components.TaskList;

import java.time.LocalDateTime;

public class Command {
    private CommandType type;
    private String desc;
    private LocalDateTime time;
    private int listNumber;

    public Command(CommandType type, String desc, LocalDateTime time, int listNumber) {
        assert type != null : "Command type cannot be null.";
        assert desc != null : "Description cannot be null.";
        assert time != null : "Date and time cannot be null.";

        this.type = type;
        this.desc = desc;
        this.time = time;
        this.listNumber = listNumber;
    }

    public CommandType getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public int getListNumber() {
        return listNumber;
    }

    public String execute(TaskList taskList, Storage storage) throws VergilException {
        switch (getType()) {
        case BYE:
            return "Bye. See you soon!";

        case LIST:
            return taskList.toString();

        case DONE:
            taskList.complete(getListNumber());
            storage.save(taskList);
            return "Success!";

        case DELETE:
            taskList.delete(getListNumber());
            storage.save(taskList);
            return "Success!";

        case TODO:
            taskList.add(new Todo(getDesc()));
            storage.save(taskList);
            return "Success!";

        case DEADLINE:
            taskList.add(new Deadline(getDesc(), getTime()));
            storage.save(taskList);
            return "Success!";

        case EVENT:
            taskList.add(new Event(getDesc(), getTime()));
            storage.save(taskList);
            return "Success!";

        case FIND:
            TaskList resultsList = taskList.find(getDesc());

            if (resultsList.getLength() > 0) {
                return resultsList.toString();
            } else {
                return "Oops! No tasks match any of the given keywords.";
            }
        }

        return "This line should not be read by the user.";
    }
}
