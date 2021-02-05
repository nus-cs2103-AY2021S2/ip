package duke.task;

public enum taskState {
    NOTDONE {
        @Override
        public String toString() {
            return " ";
        }
    }, DONE {
        @Override
        public String toString() {
            return "x";
        }
    }
}
