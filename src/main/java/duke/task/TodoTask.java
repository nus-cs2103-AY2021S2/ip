package duke.task;

/**
 * This class handles the TodoTask.
 */
public class TodoTask extends Task {
    /**
     * Constructor of todoTask
     *
     * @param task
     */
    public TodoTask(String task) {
        super(task);
        divideCommand = task.split(" ");
    }

    public String getName(){
        if(getSizeOfCommand() == 2){
            return divideCommand[1];
        }else{
            return divideCommand[1] + " " + divideCommand[2];
        }
    }

    /**
     * String representation of todoTask
     *
     * @return
     */
    @Override
    public String toString() {
        if (this.isDone()) {
            return "[T][X] " + getName();
        } else {
            return "[T][-] " + getName();
        }
    }
}
