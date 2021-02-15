interface IDuke {
    public void greeting();
    public void bye();
    IDuke processInput(String input);
    IDuke addTask(ITask task);
    void display();
    ITask getTask(int id);
    int numOfTasks();

}
