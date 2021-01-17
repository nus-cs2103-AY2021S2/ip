/**
 * This class is to store task's information
 *
 * @author Sharptail
 */
public class Task {
    private String description;
    private boolean status;

    public Task(String description){
        this.description = description;
        this.status = false;
    }

    public String getDescription(){
        return this.description;
    }

    public boolean getStatus(){
        return status;
    }

    public String getStatusSymbol(){
        return status ? "\u2713" : " ";
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setStatus(boolean status){
        this.status = status;
    }

    @Override
    public String toString(){
        return "[" + this.getStatusSymbol() + "]" + this.description;
    }
}
