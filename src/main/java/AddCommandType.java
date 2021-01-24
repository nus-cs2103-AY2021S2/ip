public enum AddCommandType {
    TODO("todo", 2), DEADLINE("deadline", 9), EVENT( "event", 6);

    private String name;
    private int addCommandPostfix;

    AddCommandType(String name, int addCommandPostfix) {
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
