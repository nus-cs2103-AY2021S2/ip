package data.task;

public interface ITask {

    /**
     *
     * @return data.task.ITask object with a true isDone status
     */
    ITask markDone();

    /**
     *
     * @return the status of the data.task.ITask object
     */
    boolean isDone();

    String getContentString();

    boolean isSameTime(String date);

}
