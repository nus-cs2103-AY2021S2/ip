public enum TaskTypes {
    TODO("todo"), DEADLINE("deadline"), EVENT("event");

    private final String type;

    TaskTypes(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}