package tlylt.haha;

public enum LegitCommand {
    DEADLINE("deadline"),
    EVENT("event"),
    TODO("todo"),
    LIST("list"),
    DONE("done"),
    DELETE("delete"),
    BYE("bye"),
    FIND("find");
    
    private final String rep;
    private String detail = "";

    LegitCommand(String rep) {
        this.rep = rep;
    }

    void setDetail(String detail) {
        this.detail = detail;
    }

    String getDetail() {
        return detail;
    }

    String getRep() {
        return rep;
    }

}
