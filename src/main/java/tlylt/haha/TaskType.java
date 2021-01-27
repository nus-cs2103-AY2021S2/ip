package tlylt.haha;

public enum TaskType {
    DEADLINE("deadline"),
    EVENT("event"),
    TODO("todo");
    private final String rep;

    TaskType(String rep) {
        this.rep = rep;
    }

    String getRep() {
        return rep;
    }
}
