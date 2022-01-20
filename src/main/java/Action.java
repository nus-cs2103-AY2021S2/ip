public enum Action
{
    EXIT("bye"),
    LIST("list"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DONE("done"),
    DELETE("delete"),;

    private String command;

    Action(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return command;
    }
}