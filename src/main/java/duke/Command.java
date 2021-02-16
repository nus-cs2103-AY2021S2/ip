package duke;

public enum Command {
    TODO, HELLO, DEADLINE, EVENT, LIST, FIND, COMPLETE, REMOVE, BYE;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
