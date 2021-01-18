public class DukeTask {
    protected String name;
    protected boolean isDone;
    protected TaskType type;

    enum TaskType {
        TODO("[T]"),
        DEADLINE("[D]"),
        EVENT("[E]"),
        EMPTY("[ ]");

        private final String type;

        TaskType(String code){
            this.type = code;
        }
        @Override
        public String toString(){
            return this.type;
        }
    }

    public DukeTask(String name) {
        this(name, false, TaskType.EMPTY);
    }

    public DukeTask(String name, TaskType type) {
        this(name, false, type);
    }

    public DukeTask(String name, boolean isDone, TaskType type) {
        this.name = name;
        this.isDone = isDone;
        this.type = type;
    }

    public DukeTask markDone() {
        return new DukeTask(this.name, true, this.type);
    }

    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    @Override
    public String toString() {
        return this.type + this.getStatusIcon() + " " + this.name;
    }
}
