package duke.command;

import java.time.LocalDate;

import duke.Storage;
import duke.TaskManager;
import duke.exception.DukeException;

public class EventCommand extends Command {
    private String name;
    private LocalDate date;

    //variables for recurring events
    private int instances = 1;
    private int interval = 0;
    private boolean isYearRecurrence = false;
    private boolean isMonthRecurrence = false;
    private boolean isDayRecurrence = false;

    /**
     *  EventCommand constructor.
     *
     *  @param name Name of Event Task.
     *  @param date Date of Event Task.
     */
    public EventCommand(String name, LocalDate date) {
        assert name.length() > 0 : "Empty Name";
        this.name = name;
        this.date = date;
    }

    /**
     *  EventCommand constructor that support recurring events.
     *
     *  @param name Name of Event Task.
     *  @param date Date of Event Task.
     *  @param i Number of recurrence of Event Task.
     *  @param interval Interval frequency of Event Task.
     *  @param type Interval type. (valid: 'y', 'm', 'd')
     */
    public EventCommand(String name, LocalDate date, int i, int interval, String type) {
        assert name.length() > 0 : "Empty Name";
        this.name = name;
        this.date = date;
        this.instances = i;
        this.interval = interval;
        switch (type) {
        case "y":
            this.isYearRecurrence = true;
            break;
        case "m":
            this.isMonthRecurrence = true;
            break;
        case "d":
            this.isDayRecurrence = true;
            break;
        }
    }

    private LocalDate incrementDate(LocalDate date) {
        if (isYearRecurrence) {
            return date.plusYears(interval);
        } else if (isMonthRecurrence) {
            return date.plusMonths(interval);
        } else if (isDayRecurrence) {
            return date.plusDays(interval);
        } else {
            return date;
        }
    }

    /**
     *  Executes EventCommand.
     *
     *  @param tm TaskManager Object from Duke.
     *  @param st Storage Object from Duke.
     *  @return Command response.
     *  @throws DukeException If any error arises from execution.
     */
    public String execute(TaskManager tm, Storage st) throws DukeException {
        LocalDate currDate = date;
        for (int i = 0; i < instances; i++) {
            tm.addEventTask(name, date);
            currDate = incrementDate(currDate);
        }
        st.save(tm);
        String res = String.format("added%s: %s\n Now you have %d task(s)",
                instances > 1 ? " recurring events" : "",
                name,
                tm.getSize());
        return res;
    }
}
