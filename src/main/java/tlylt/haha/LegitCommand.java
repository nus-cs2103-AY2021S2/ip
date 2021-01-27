package tlylt.haha;

public enum LegitCommand {
    DEADLINE("deadline"),
    EVENT("event"),
    TODO("todo"),
    LIST("list"),
    DONE("done"),
    DELETE("delete"),
    BYE("bye");
    private final String rep;
    private String detail = "";

    LegitCommand(String rep) {
        this.rep = rep;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

    public String getRep() {
        return rep;
    }

}
