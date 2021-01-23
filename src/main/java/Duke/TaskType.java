package Duke;

public enum TaskType {
    TODO("T"), DEADLINE("D"), EVENT("E");

    private final String type;

    TaskType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
