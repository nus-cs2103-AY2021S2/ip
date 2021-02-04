public class ParserOutput {
    private boolean bye;
    private Task task;
    private int index;
    private int action;

    private ParserOutput(boolean bye, Task task, int action, int index) {
        this.bye = bye;
        this.task = task;
        this.action = action;
        this.index = index;
    }

    /**
     * Factory for creating ParserOutput to indicate bye command.
     * @return ParserOutput with bye set to true
     */
    public static ParserOutput byeOutput() {
        return new ParserOutput(true, null, 0, 0);
    }

    /**
     * Factory for creating ParserOutput to indicate remove command.
     * @param index index of task to be removed
     * @return ParserOutput with action set to 1, and index set to given index
     */
    public static ParserOutput removeOutput(int index) {
        return new ParserOutput(false, null, 1, index);
    }

    /**
     * Factory for creating ParserOutput to indicate remove command.
     * @param index index of task to be set to done
     * @return ParserOutput with action set to 2, and index set to given index
     */
    public static ParserOutput doneOutput(int index) {
        return new ParserOutput(false, null, 2, index);
    }

    /**
     * Factory for creating ParserOutput to indicate add command.
     * @param task task to be added
     * @return ParserOutput with action set to 3, and contains task to be added
     */
    public static ParserOutput addOutput(Task task) {
        return new ParserOutput(false, task, 3, 0);
    }

    private static ParserOutput setOutput(Task task, int index) {
        return new ParserOutput(false, task, 4, index);
    }

    /**
     * Factory for creating ParserOutput to indicate list command.
     * @return ParserOutput with action set to 5
     */
    public static ParserOutput listOutput() {
        return new ParserOutput(false, null, 5, 0);
    }

    /**
     * Returns if bye is true.
     * @return value of bye
     */
    public boolean isBye() {
        return bye;
    }

    /**
     * Getter for index.
     * @return index
     */
    public int getIndex() {
        return index;
    }

    /**
     * Getter for action number.
     * @return action
     */
    public int getAction() {
        return action;
    }

    /**
     * Getter for task.
     * @return task
     */
    public Task getTask() {
        return task;
    }
}
