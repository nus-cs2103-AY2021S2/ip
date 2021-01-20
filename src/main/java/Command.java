interface Command {
    public boolean shouldExit();
    public String getResponse();
    public TaskList execute(TaskList taskList);
}
