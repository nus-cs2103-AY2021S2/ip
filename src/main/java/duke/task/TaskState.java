package duke.task;

public enum TaskState {
    NOTDONE {
        @Override
        public String toString() {
            return "\u2718";
        }
    }, DONE {
        @Override
        public String toString() {
            return "\u2713";
        }
    }
}
