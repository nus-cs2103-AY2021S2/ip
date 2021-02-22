package myDuke;

/**
 * Represents a ToDo Task
 */
class ToDo extends Task {

    ToDo(String s, boolean b) {
        super(s, b);
    }

    @Override
    ToDo setAsDone() {
        return new ToDo(this.info, true);
    }

    @Override
    ToDo setAsUndone() {
        return new ToDo(this.info, false);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}