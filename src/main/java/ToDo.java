public class ToDo extends Task {
    public ToDo(String taskInfo) {
        super(taskInfo);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String getData() {
        if (isDone == true) {
            return "T!@#1!@#" + taskInfo;
        } else {
            return "T!@#0!@#" + taskInfo;
        }
    }
}
