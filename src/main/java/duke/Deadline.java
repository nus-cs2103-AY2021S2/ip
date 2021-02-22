package duke;

/** Represents Deadline inherited from Command,
 * attached with command description and due date
 *
 * @author Chia Jia-Xi, Kymie
 * @version 0.1
 * @since 2021-02-22
 */

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends duke.Command {

    public LocalDate dueDate;
    private String formattedDate;

    /**
     * Constructor for Command child class Deadline
     *
     * @param commandDescription every command has a description attached
     * @param dueDate date when command is due
     */
    public Deadline(String commandDescription, LocalDate dueDate) {
        super(commandDescription);
        this.isDone = false;
        this.dueDate = dueDate;

        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMM yyyy");
        this.formattedDate = this.dueDate.format(format);
    }

    public LocalDate getTime() {
        return this.dueDate;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " | by: " + formattedDate;
    }
}
