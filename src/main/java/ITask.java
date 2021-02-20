public interface ITask {

    /**
     *
     * @return ITask object with a true isDone status
     */
    ITask markDone();

    /**
     *
     * @return the status of the ITask object
     */
    boolean isDone();


}
