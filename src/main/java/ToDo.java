import java.time.LocalDateTime;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description, "T");
    }

    public ToDo(String description, LocalDateTime timeCreated) {
        super(description, timeCreated, "T");
    }
}

