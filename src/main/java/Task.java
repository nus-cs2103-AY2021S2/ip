public class Task {
    private String description;

    public Task(String desc) {
        this.description = desc;
    }

    @Override
    public String toString() {
        return description;
    }
}
