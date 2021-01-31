package duke.command;

import java.time.LocalDate;

import duke.Storage;
import duke.TaskManager;
import duke.exception.DukeException;

public class DeadlineCommand extends Command {
    private String name;
    private LocalDate date;

    /**
     *  DeadlineCommand constructor.
     *
     *  @param name Name of Deadline Task.
     *  @param date Date of Deadline Task.
     */
    public DeadlineCommand(String name, LocalDate date) {
        this.name = name;
        this.date = date;
    }

    /**
     *  Executes DeadlineCommand.
     *
     *  @param tm TaskManager Object from Duke.
     *  @param st Storage Object from Duke.
     *  @return Command response.
     *  @throws DukeException If any error arises from execution.
     */
    public String execute(TaskManager tm, Storage st) throws DukeException {
        tm.addDeadlineTask(name, date);
        st.save(tm);
        String res = String.format("added: %s\n Now you have %d task(s)",
                name,
                tm.getSize());
        return res;
    }
}
