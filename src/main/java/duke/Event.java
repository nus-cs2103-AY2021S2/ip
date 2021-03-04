package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/** Represents Event inherited from Command,
 * attached with command description and due date
 *
 * @author Chia Jia-Xi, Kymie
 * @version 0.1
 * @since 2021-02-22
 */
public class Event extends duke.Command {
    private final boolean isDone;
    private LocalDate eventDate;
    private String formattedDate;

    /**
     * Constructor for Command child class Event
     *
     * @param commandDescription every command has a description attached
     * @param eventDate date when event is held
     */
    public Event(String commandDescription, LocalDate eventDate) {
        super(commandDescription);
        this.isDone = false;
        this.eventDate = eventDate;

        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMM yyyy");
        this.formattedDate = this.eventDate.format(format);
    }

    public LocalDate getTime() {
        return this.eventDate;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " | at: " + formattedDate;
    }
}
