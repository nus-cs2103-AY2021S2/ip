public class Task {
    private boolean done;
    private String name;

    Task(String name) {
        this.name = name;
        this.done = false;
    }

    void markDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        String check = done ? "X" : " ";
        return String.format("[%s] %s", check, name);
    }

    public String toStorageString() {
        if (done) {
            return "Y|" + this.name + "|";
        } else {
            return "N|" + this.name + "|";
        }
    }

    public String getName() {
        return this.name;
    }
}
