public class Task {
    enum Type { TODOS, DEADLINES, EVENTS }

    protected String description;
    protected boolean isDone;
    protected Type type;
//        protected String date;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(Type type, String description) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

//        public Task(Type type, String description, String date) {
//            this.description = description;
//            this.isDone = false;
//            this.type = type;
//            this.date = date;
//        }

    public String getStatusIcon() {
//            return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
        return (isDone ? "X" : " "); //return tick or X symbols
    }

//        public String getTypeIcon () {
//            return (type == Type.TODOS ? "T" : type == Type.DEADLINES ? "D" : "E");
//        }

    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
