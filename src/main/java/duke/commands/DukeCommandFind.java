package duke.commands;

import duke.exceptions.DukeExceptionIllegalArgument;
import duke.parser.DatetimeParser;
import duke.parser.UserInputTokenSet;
import duke.storage.FileLoader;
import duke.tasks.DateTask;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Find command.
 *
 * Filters tasks according to supplied date range and displays list to user.
 */
public class DukeCommandFind extends DukeCommand {

    protected Optional<LocalDateTime> from;
    protected Optional<LocalDateTime> to;
    protected String search;

    public DukeCommandFind(UserInputTokenSet tokenSet) throws DukeExceptionIllegalArgument {
        from = Optional.empty(); // datetime lower bound
        to = Optional.empty(); // datetime upper bound
        search = tokenSet.get("/text"); // keyword search
        if (tokenSet.contains("from")) {
            from = Optional.of(DatetimeParser.parseDate(tokenSet.get("from")));
        }
        if (tokenSet.contains("to")) {
            to = Optional.of(DatetimeParser.parseDate(tokenSet.get("to")));
        }
        if (from.isPresent() && to.isPresent() && from.get().isAfter(to.get())) {
            throw new DukeExceptionIllegalArgument(
                    "'/from' date must be before '/to' date.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, FileLoader loader) throws DukeExceptionIllegalArgument{
        boolean datedSearch = (from.isPresent() || to.isPresent());
        ArrayList<Task> view = new ArrayList<>();
        ArrayList<Integer> viewIndex = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i);

            if (datedSearch) {
                if (!(task instanceof DateTask)) {
                    continue;
                }
                DateTask datetask = (DateTask) task;
                if (from.isPresent() && from.get().isAfter(datetask.getDatetime())) {
                    continue;
                }
                if (to.isPresent() && to.get().isBefore(datetask.getDatetime())) {
                    continue;
                }
            }
            if (!task.getDescription().contains(search)) {
                continue;
            }
            // If date enabled, must sort by DateTasks only.

            view.add(task);
            viewIndex.add(i+1);
        }

        // Empty task view list
        if (view.isEmpty()) {
            List<String> lines = new ArrayList<>();
            lines.add("No tasks matching search term / date range");
            if (from.isPresent()) {
                lines.add(" from:  " + DatetimeParser.formatDateFull(from.get()));
            }
            if (to.isPresent()) {
                lines.add(" till:  " + DatetimeParser.formatDateFull(to.get()));
            }
            if (!search.isBlank()) {
                lines.add(" query: '" + search + "'");
            }
            ui.showMessage(lines);
            return;
        }

        // Sorting disabled to allow list indexing... Might re-enable as option
        // Print list in sorted order of datetime
        // IntelliJ's coding recommendations reduced it to this, pretty impressive
        // if (datedSearch) {
        //     view.sort(Comparator.comparing(t -> ((DateTask) t).getDatetime()));
        // } else {
        //    view.sort(Comparator.comparing(Task::getDescription));
        // }

        // Get output
        List<String> lines = new ArrayList<>();
        lines.add("Found " + view.size() + " task(s) matching search term / date range");
        if (from.isPresent()) {
            lines.add(" from:  " + DatetimeParser.formatDateFull(from.get()));
        }
        if (to.isPresent()) {
            lines.add(" till:  " + DatetimeParser.formatDateFull(to.get()));
        }
        if (!search.isBlank()) {
            lines.add(" query: '" + search + "'");
        }
        lines.add("");
        for (int i = 0; i < view.size(); i++) {
            String s = "" + viewIndex.get(i) + ". " + view.get(i).toString();
            lines.add(s);
        }
        ui.showMessage(lines);
    }
}
