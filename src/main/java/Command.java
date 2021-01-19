public enum Command {
    LIST("list"), BYE("bye"),
    DONE("done"), DELETE("delete"),
    EVENT("event"), DEADLINE("deadline"), TODO("todo");

    private String action;
    Command(String action) {
        this.action = action;
    }

    public String getAction() {
        return this.action;
    }
}
