package snom.ui;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import snom.common.core.Messages;
import snom.common.exceptions.SnomException;
import snom.common.util.SnomioUtil;
import snom.model.task.Task;
import snom.model.task.TaskList;

/**
 * Deals with interactions with the user.
 * Snomio simply compile both BufferedReader and BufferedWriter
 * for easier I/O usages. For eg. read commands, contents, numbers
 * from user's input.
 *
 * Solution below adapted from https://github.com/Kattis/kattio/blob/master/Kattio.java
 */
public class Snomio extends PrintWriter {
    private BufferedReader reader;
    private StringTokenizer tokenizer;

    /**
     * Constructs a {@code Snomio}
     */
    public Snomio() {
        super(new BufferedOutputStream(System.out));
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Returns the welcome message of Snom on startup.
     *
     * @return welcome message
     */
    public String getWelcomeMsg() {
        return Messages.MESSAGE_WELCOME;
    }

    /**
     * Returns a message containing the entire {@code TaskList}.
     *
     * @return               string of entire task List
     * @throws SnomException if there is content after the command or there isn't any task in the task list
     */
    public String getTaskList(TaskList taskList) throws SnomException {
        if (taskList.isEmpty()) {
            throw new SnomException(Messages.MESSAGE_EMPTY_TASK_LIST);
        }

        String message = Messages.MESSAGE_TASK_LIST;
        for (int i = 0; i < taskList.size(); i++) {
            message += (i + 1) + ". " + taskList.get(i).toString() + "\n";
        }

        return message;
    }

    /**
     * Returns out the list from searching the keyword.
     *
     * @return               string of matching task list
     * @throws SnomException if there is content after the command or there isn't any task in the task list
     */
    public String getMatchingTaskList(TaskList taskList) throws SnomException {
        if (taskList.isEmpty()) {
            throw new SnomException(Messages.MESSAGE_NO_MATCHING_TASK);
        }

        String message = Messages.MESSAGE_MATCHING_TASK_LIST;
        for (int i = 0; i < taskList.size(); i++) {
            message += (i + 1) + ". " + taskList.get(i).toString() + "\n";
        }

        return message;
    }

    /**
     * Returns the {@code Task} added into the {@code TaskList} and size of current taskList.
     *
     * @param task     task added
     * @param listSize task list size
     * @return         task added into taskList
     */
    public String getTaskAdded(Task task, int listSize) {
        return String.format(Messages.MESSAGE_TASK_ADDED, task.toString(), listSize);
    }

    /**
     * Returns the list of recent finished {@code Task}.
     *
     * @param finishedTasks list of finished {@code Task}
     * @return              recent finished {@code Task}
     */
    public String getFinishedTasks(Task[] finishedTasks) {
        String message = Messages.MESSAGE_TASK_FINISHED;
        for (Task task: finishedTasks) {
            message += "\t" + task.toString() + "\n";
        }
        return message;
    }

    /**
     * Returns the list of recently deleted {@code Task}.
     *
     * @param deletedTasks list of deleted {@code Task}
     * @return             recent deleted {@code Task}
     */
    public String getDeletedTasks(Task[] deletedTasks) {
        String message = Messages.MESSAGE_TASK_DELETED;
        for (Task task: deletedTasks) {
            message += "\t" + task.toString() + "\n";
        }
        return message;
    }

    /**
     * Returns exit message.
     *
     * @return String exit message
     */
    public String getExitMessage() {
        return Messages.MESSAGE_EXIT;
    }

    /**
     * Returns the first word from next input line.
     * If there are already words/tokens in the tokenizer, it will return the next first token instead.
     *
     * @return  the first word that extracted from the tokenizer
     */
    public String readWord() {
        String input = null;
        String token;

        while (SnomioUtil.hasNoTokens(tokenizer)) {
            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            tokenizer = new StringTokenizer(input);
        }
        token = tokenizer.nextToken();

        return token;
    }

    /**
     * Returns remaining words/tokens in the tokenizer as a string.
     *
     *  @return the whole line of input or the rest of the sentence from the previous read line
     */
    public String readRemainingLine() {
        String line = "";

        if (SnomioUtil.hasNoTokens(tokenizer)) {
            try {
                line = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            line += tokenizer.nextToken("");
        }

        return line;
    }

    /**
     * Returns the first integer input.
     *
     * @return  single integer input
     */
    public int readInt() throws SnomException {
        String nextWord = readWord();
        if (SnomioUtil.isValidInteger(nextWord)) {
            return Integer.parseInt(nextWord);
        } else {
            throw new SnomException(Messages.ERROR_INVALID_INT_INPUT);
        }
    }

    /**
     * Returns first double input.
     *
     * @return  single double input
     */
    public double readDouble() throws SnomException {
        String nextWord = readWord();
        if (SnomioUtil.isValidDouble(nextWord)) {
            return Double.parseDouble(nextWord);
        } else {
            throw new SnomException(Messages.ERROR_INVALID_INT_INPUT);
        }
    }
}
