public class Task {
    protected String description;
    protected boolean isDone;


    public Task(String description) {
        this.description = description;
        this.isDone = false; //task not done is represented by a X
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols.
    }

    public String getDescription() {

        return this.description;
    }

    public void markAsDone() {
        this.isDone = true; //a task that is done is represented by a tick
    }

    public String getType() {
        return "[ ]";
    }

    public Task taskParser(String record) {
        if (record.contains("[T]")) {
            if (record.contains("\u2713")) {
                String[] description = record.split("\u2713 ");
                String content = description[1];
                Todo myTodo = new Todo(content);
                myTodo.markAsDone();
                return myTodo;
            }
        }
        return new Todo("xxx");
    }


    @Override
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }

}
