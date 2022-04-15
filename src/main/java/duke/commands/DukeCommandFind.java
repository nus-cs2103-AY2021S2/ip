package duke.commands;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import duke.exceptions.DukeExceptionIllegalArgument;
import duke.parser.DatetimeParser;
import duke.parser.UserInputTokenSet;
import duke.responses.Response;
import duke.storage.FileLoader;
import duke.tasks.DateTask;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Find command.
 *
 * Filters tasks according to supplied date range and displays list to user.
 */
public class DukeCommandFind extends DukeCommand {

    protected Optional<LocalDateTime> from;
    protected Optional<LocalDateTime> to;
    protected String searchTerms;

    /**
     * Constructor to record date range and search query.
     *
     * @param tokenSet user input tokens
     * @throws DukeExceptionIllegalArgument When dates cannot be parsed
     */
    public DukeCommandFind(UserInputTokenSet tokenSet) throws DukeExceptionIllegalArgument {
        from = Optional.empty(); // datetime lower bound
        to = Optional.empty(); // datetime upper bound
        searchTerms = tokenSet.get("/text"); // keyword search
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

    /**
     * Returns Response listing tasks from tasklist according to search query, writes to file and displays success
     *
     * @param tasks tasklist
     * @param loader storage
     * @return Response
     */
    @Override
    public Response execute(TaskList tasks, FileLoader loader) {
        List<Integer> validTaskIndices = getTaskIndicesMatchingDateCriteria(tasks);

        /* Prepare response */
        List<String> lines = new ArrayList<>();
        if (validTaskIndices.isEmpty()) {
            lines.add("No tasks matching query / date range");
            addToResponseSearchParameters(lines);
            return Response.createResponseOk(lines.toArray(new String[0]));
        }

        /* Prepare non-empty response */
        lines.add("Found " + validTaskIndices.size() + " task(s) matching query / date range:");
        addToResponseSearchParameters(lines);
        addToResponseFoundTasks(lines, validTaskIndices, tasks);
        return Response.createResponseOk(lines.toArray(new String[0]));
    }

    private List<Integer> getTaskIndicesMatchingDateCriteria(TaskList tasks) {
        boolean datedSearch = (from.isPresent() || to.isPresent());
        ArrayList<Integer> validTaskIndices = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTaskUnsafe(i);

            /* If date enabled, must sort by DateTasks only. */
            if (datedSearch && isNotWithinDateRange(task)) {
                continue;
            }
            if (!task.getDescription().contains(searchTerms)) {
                continue;
            }
            validTaskIndices.add(i);
        }
        return validTaskIndices;
    }

    private boolean isNotWithinDateRange(Task task) {
        /* Not a date in the first place */
        if (!(task instanceof DateTask)) {
            return true;
        }
        /* Checks if within date range */
        DateTask datetask = (DateTask) task;
        if (from.isPresent() && from.get().isAfter(datetask.getDatetime())) {
            return true;
        }
        return to.isPresent() && to.get().isBefore(datetask.getDatetime());
    }

    private void addToResponseSearchParameters(List<String> lines) {
        from.ifPresent(localDateTime -> lines.add(" from:  " + DatetimeParser.formatDateFull(localDateTime)));
        to.ifPresent(localDateTime -> lines.add(" till:  " + DatetimeParser.formatDateFull(localDateTime)));
        if (!searchTerms.isBlank()) {
            lines.add(" query: '" + searchTerms + "'");
        }

        /* Print empty response line if params not present */
        if (from.isEmpty() && to.isEmpty() && searchTerms.isBlank()) {
            lines.add("no query / date range provided");
        }
    }

    private void addToResponseFoundTasks(List<String> lines, List<Integer> validTaskIndices, TaskList tasks) {
        lines.add(""); // blank line
        for (int taskIndex : validTaskIndices) {
            String s = tasks.getLeftPadding(taskIndex + 1) + (taskIndex + 1) + ". "
                    + tasks.getTaskUnsafe(taskIndex).toString();
            lines.add(s);
        }
    }
}
