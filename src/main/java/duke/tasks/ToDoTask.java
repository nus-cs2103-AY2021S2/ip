package duke.tasks;

public class ToDoTask extends Task{

    public ToDoTask(String description) {
        super(description, "[T]");
    }

    @Override
    public void getStatusAndTask() {
        System.out.println("      " + this.type + this.getStatus() + this.description);
    }

    @Override
    public String toString() {
        return "       " + this.type + super.toString().trim();
    }
}
