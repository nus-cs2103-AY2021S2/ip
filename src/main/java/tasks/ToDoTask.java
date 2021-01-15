package tasks;

public class ToDoTask extends Task{
    private String type;

    public ToDoTask(String description) {
        super(description);
        this.type = "[T]";
    }

    @Override
    public void getStatusAndTask() {
        System.out.println("      " + this.type + "[" + this.getStatusIcon() + "] " + this.description);
    }

    @Override
    public String toString() {
        return "       " + this.type + super.toString().trim();
    }
}
