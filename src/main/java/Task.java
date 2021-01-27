import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Task {
    private String name;
    private boolean done;
    private String type;
    private String preposition;
    private LocalDate date;

    public Task(String type, String name) {
        this.name = name;
        this.done = false;
        this.type = type;
        this.date = null;
    }
    
    public Task(String type, String name, String date, String preposition) {
        this.name = name;
        this.done = false;
        this.type = type;
        this.date = LocalDate.parse(date);
        this.preposition = preposition;
    }

    public String getType() {
        return type;
    }

    public boolean getDone() {
        return done;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public void setDone() {
        this.done = true;    
    }

    @Override
    public String toString() {
        if (type.equals("E") || type.equals("D")) {
            return String.format("[%s][%s] %s (%s: %s)",
                    type,
                    done ? "X" : " ",
                    name,
                    preposition,
                    date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        } else {
            return String.format("[%s][%s] %s",
                    type,
                    done ? "X" : " ",
                    name);
        }
    }

    public String toSaveFormat() {
        String line = type + " | " + (done ? "1" : "0") + " | " + name;
        if (type.equals("E") || type.equals("D")) {
            line += " | " + date + " | " + preposition;
        }
        return line;
    }
}
