package duke.commands;

import duke.exceptions.DukeExceptionIllegalArgument;
import duke.parser.Parser;
import duke.storage.FileLoader;
import duke.tasks.DateTask;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Find command.
 *
 * Filters tasks according to supplied date range and displays list to user.
 */
public class DukeCommandFind extends DukeCommand {

    protected Optional<LocalDateTime> from;
    protected Optional<LocalDateTime> to;

    public DukeCommandFind(String input) throws DukeExceptionIllegalArgument {
        if (input.startsWith("/from ")) {
            input = input.substring(6);
            if (input.contains(" /to ")) {
                String[] dates = input.split(" /to ");
                from = Optional.of(Parser.parseDate(dates[0].strip()));
                to = Optional.of(Parser.parseDate(dates[1].strip()));
            } else {
                from = Optional.of(Parser.parseDate(input));
                to = Optional.empty();
            }
        } else if (input.startsWith("/to ")) {
            input = input.substring(4);
            if (input.contains(" /from ")) {
                String[] dates = input.split(" /from ");
                to = Optional.of(Parser.parseDate(dates[0].strip()));
                from = Optional.of(Parser.parseDate(dates[1].strip()));
            } else {
                to = Optional.of(Parser.parseDate(input));
                from = Optional.empty();
            }
        } else {
            throw new DukeExceptionIllegalArgument(
                    "Argument must be valid dates following the "
                    + "\n'/from' and/or '/to' options.");
        }
        if (from.isPresent() && to.isPresent() && from.get().isAfter(to.get())) {
            throw new DukeExceptionIllegalArgument(
                    "'/from' date must be before '/to' date.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, FileLoader loader) throws DukeExceptionIllegalArgument{
        ArrayList<DateTask> view = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i);
            if (!(task instanceof DateTask)) {
                continue;
            }

            DateTask datetask = (DateTask) task;
            System.out.println(datetask.getDatetime());
            if (from.isPresent() && from.get().isAfter(datetask.getDatetime())) {
                continue;
            }
            if (to.isPresent() && to.get().isBefore(datetask.getDatetime())) {
                continue;
            }
            view.add(datetask);
        }

        // Print list in sorted order of datetime
        // IntelliJ's coding recommendations reduced it to this, pretty impressive
        view.sort(Comparator.comparing(DateTask::getDatetime));
        List<String> lines = new ArrayList<>();
        lines.add("Here are the tasks requested in sorted order,");
        if (from.isPresent()) {
            lines.add(" from:  " + Parser.formatDateFull(from.get()));
        }
        if (to.isPresent()) {
            lines.add(" till:  " + Parser.formatDateFull(to.get()));
        }
        lines.add("");
        for (DateTask t : view) {
            String s = " - " + t.toString();
            lines.add(s);
        }
        ui.showMessage(lines);
    }
}
