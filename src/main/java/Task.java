public class Task {

    private final int id;
    private final String description;
    private final boolean isDone;

    public Task(String description, int id) {
        this.description = description;
        this.id = id;
        this.isDone = false;
    }

    private Task(String description, int id, boolean isDone) {
        this.description = description;
        this.id = id;
        this.isDone = isDone;
    }

    public Task markDone() {
        return new Task(description, id, true);
    }

    public int getID() {
        return id;
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {

        String checkBox;
        if (isDone) {
            checkBox = "[X] " ;
        } else {
            checkBox = "[] " ;
        }

        return id + ". " + checkBox + description;
    }
}
