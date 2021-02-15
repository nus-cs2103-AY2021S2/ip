interface IDuke {

    /**
     * Prints greeting message.
     */
    public void greeting();

    /**
     * Prints bye message.
     */
    public void bye();

    /**
     *
     * @param input User input
     * @return IDuke object
     */
    IDuke processInput(String input);

    /**
     *
     * @param task a task
     * @return new IDuke object with the adding task
     */
    IDuke addTask(ITask task);

    /**
     * Prints the task list
     */
    void display();

    /**
     *
     * @param id task index
     * @return a specific ITask object
     */
    ITask getTask(int id);

    /**
     *
     * @return number of tasks in the list
     */
    int numOfTasks();

}
