package tasks;

import exceptions.SnomException;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList(ArrayList<Task> list){
        this.list = list;
    }

    public ArrayList<Task> getList(){
        return this.list;
    }

    /**
     * Returns the size of the task list
     *
     * @return size of task list
     */
    public int getSize(){
        return list.size();
    }

    /**
     * Returns a single task [todo, deadline, event] with the given index
     *
     * @param i index
     * @return  task
     */
    public Task getTask(int i){
        return list.get(i);
    }

    /**
     * Returns a new task list with task description containing the given keyword
     *
     * @param keyword keyword to be searched
     * @return        new task list
     */
    public ArrayList<Task> findTask(String keyword){
        ArrayList<Task> newList = new ArrayList<>();
        for(Task task: list){
            if(task.getDescription().contains(keyword)){
                newList.add(task);
            }
        }
        return newList;
    }

    /**
     * Adds the given task to task list, can be a todo, deadline or event task.
     * Then prints out respective messages.
     *
     * @param task either tasks.Todo, tasks.Deadline, tasks.Event
     */
    public void addTask(Task task){
        list.add(task);
    }


    /**
     * Set the task status by the given task numbers as finished.
     * Then prints out the complete messages.
     *
     * @param taskNums       task number list that needs to mark as finish
     * @return               a list of finished tasks
     * @throws SnomException If the task number is not available in the task list.
     */
    public Task[] finishTask(int[] taskNums) throws SnomException{
        Task[] finishedTasks = new Task[taskNums.length];
        for(int i = 0; i < taskNums.length; i++){
            int taskNo = taskNums[i] - 1;
            try{
                Task task = list.get(taskNo);
                task.setStatus(true);
                finishedTasks[i] = task;
            }catch(IndexOutOfBoundsException e){
                throw new SnomException("Oops! You have entered a task number: "
                        + taskNums[i] + " which is invalid! Please try again!");
            }
        }
        return finishedTasks;
    }

    /**
     * Removes the given task numbers from the task list.
     * Then prints out the deleted messages.
     *
     * @param  taskNums      task number list that needs to be removed
     * @return               a list of deleted tasks
     * @throws SnomException If the task number is not available in the task list.
     */
    public Task[] deleteTask(int[] taskNums) throws SnomException{
        Task[] deletedTasks = new Task[taskNums.length];
        for(int i = 0; i < taskNums.length; i++){
            int taskNo = taskNums[i] - 1 - i;
            try{
                Task task = list.get(taskNo);
                list.remove(task);
                deletedTasks[i] = task;
            }catch(IndexOutOfBoundsException e){
                throw new SnomException("Oops! You have entered a task number: "
                        + taskNums[i] + " which is invalid! Please try again!");
            }
        }
        return deletedTasks;
    }
}
