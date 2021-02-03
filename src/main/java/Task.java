public class Task {
    private String description;
    Task(String description) {
        this.description = description;
    }
    @Override
    public String toString() {
        return description;
    }
}
