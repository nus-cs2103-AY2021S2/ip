package duke.commands;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import duke.DukeException;
import duke.ParserUtils;
import duke.Storage;
import duke.TaskList;
import duke.models.Deadline;
import duke.models.Event;
import duke.models.Task;
import duke.models.Todo;
import duke.ui.Ui;

/**
 * Handles the find command of the user to search tasks based on keyword.
 * Format of command: "find [keyword]".
 */
public class FindCommand implements Command {
    public static final String TODO_KEYWORD = "todo";
    public static final String EVENT_KEYWORD = "event";
    public static final String DEADLINE_KEYWORD = "deadline";

    private final String keyword;

    protected FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Creates a new instance of Find Command
     *
     * @param argString string with argument
     * @return instance of Find Command
     * @throws DukeException
     */
    public static FindCommand buildInstance(String argString) throws DukeException {
        String[] cmdArgs = ParserUtils.getCommandArgs(argString, "I'm sorry, but find needs a keyword specified.");

        assert (cmdArgs[0].equals("find"));

        String keyword = cmdArgs[1];
        return new FindCommand(keyword);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() <= 0) {
            ui.printIndentOutput("The current list is empty.");
        } else {
            printList(tasks, ui);
        }
    }

    private void printList(TaskList tasks, Ui ui) {
        // Split by space
        List<String> keywordSplit = Arrays.stream(keyword.split(" "))
            .map(x -> x.toLowerCase()).collect(Collectors.toList());
        List<FindTaskWrapper> filteredList = IntStream.range(0, tasks.size())
            .mapToObj(x -> new FindTaskWrapper(x, tasks.getTask(x), getSimilarityScore(tasks.getTask(x), keywordSplit)))
            .filter(x -> x.getSimilarityScore() != 0)
            .sorted()
            .collect(Collectors.toList());

        if (filteredList.size() <= 0) {
            ui.printIndentOutput("No tasks found with the given keyword.");
        } else {
            ui.printIndentOutput("Here are the matching tasks in your list:");
            IntStream.range(0, filteredList.size()).forEach(x -> {
                ui.printIndentOutput((x + 1) + ". " + filteredList.get(x).getTask());
            });
        }
    }

    private static double getSimilarityScore(Task task, List<String> keywords) {
        double score = 0.0;

        score += searchByTaskName(task, keywords);
        score += searchByType(task, keywords);

        return score;
    }

    private static double searchByTaskName(Task task, List<String> keywords) {
        double score = 0.0;

        for (String keyword: keywords) {
            boolean match = task.getTaskName().toLowerCase().matches(".*\\b" + keyword + "\\b.*");
            if (match) {
                score += (keyword.length() * keyword.length());
            } else if (task.getTaskName().toLowerCase().contains(keyword)) {
                score += keyword.length();
            }
        }

        return score;
    }

    private static double searchByType(Task task, List<String> keywords) {
        double score = 0.0;

        if (keywords.contains(TODO_KEYWORD) && task instanceof Todo) {
            score += Math.sqrt(TODO_KEYWORD.length() * TODO_KEYWORD.length());
        }

        if (keywords.contains(EVENT_KEYWORD) && task instanceof Event) {
            score += Math.sqrt(EVENT_KEYWORD.length() * EVENT_KEYWORD.length());
        }

        if (keywords.contains(DEADLINE_KEYWORD) && task instanceof Deadline) {
            score += Math.sqrt(DEADLINE_KEYWORD.length() * DEADLINE_KEYWORD.length());
        }

        return score;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    public static class FindTaskWrapper implements Comparable<FindTaskWrapper> {
        private final int originalIndex;
        private final Task task;
        private final double similarityScore;

        /**
         * Creates a new task wrapper with original index
         *
         * @param originalIndex original index of task
         * @param task          task
         */
        public FindTaskWrapper(int originalIndex, Task task, double similarityScore) {
            this.originalIndex = originalIndex;
            this.task = task;
            this.similarityScore = similarityScore;
        }

        public Task getTask() {
            return task;
        }

        public int getOriginalIndex() {
            return originalIndex;
        }

        public double getSimilarityScore() {
            return similarityScore;
        }

        /**
         * Sort by decreasing similarity score by default (ensures that natural order
         * of the list is kept).
         */
        @Override
        public int compareTo(FindTaskWrapper findTaskWrapper) {
            if (similarityScore < findTaskWrapper.similarityScore) {
                return 1;
            } else if (similarityScore > findTaskWrapper.similarityScore) {
                return -1;
            }
            return originalIndex < findTaskWrapper.originalIndex ? 1 : -1;
        }
    }
}
