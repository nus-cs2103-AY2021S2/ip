package duke.commands;

public enum SpecificCommandType {
    TODO("todo", 2), DEADLINE("deadline", 9), EVENT( "event", 6), FIND("find", 5);

    private String name;
    private int addCommandPostfix;

    SpecificCommandType(String name, int addCommandPostfix) {
        this.name = name;
        this.addCommandPostfix = addCommandPostfix;
    }

    public String getName() {
        return name;
    }

    public int getPostfix() {
        return addCommandPostfix;
    }
}
