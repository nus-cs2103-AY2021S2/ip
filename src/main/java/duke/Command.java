package duke;

public enum Command {
    TODO, DEADLINE, EVENT, LIST, FIND, COMPLETE, REMOVE, BYE;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
