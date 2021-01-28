package vergil.types;

import java.time.LocalDateTime;

public class Command {
    private CommandType type;
    private String desc;
    private LocalDateTime time;
    private int listNumber;

    public Command(CommandType type, String desc, LocalDateTime time, int listNumber) {
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
}
