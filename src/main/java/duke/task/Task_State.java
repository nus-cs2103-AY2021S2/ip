package duke.task;

public enum Task_State {
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
