package tasks;

/**
 * This class is to store task's information
 *
 * @author Sharptail
 */
public class Task {
    private String description;
    private boolean isFinished;

    public Task(String description){
        this.description = description;
        this.isFinished = false;
    }

    public String getDescription(){
        return this.description;
    }

    public boolean getStatus(){
        return isFinished;
    }

    public String getStatusSymbol(){
        return isFinished ? "\u2713" : "\u2007";
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setStatus(boolean isFinished){
        this.isFinished = isFinished;
    }

    /**
     * Returns a string for saving purposes
     *
     * @return formatted string
     */
    public String toSaveString(){
        return (isFinished ? "true" : "false") + "," + this.description;
    }

    @Override
    public String toString(){
        return "[" + this.getStatusSymbol() + "]" + this.description;
    }
}
