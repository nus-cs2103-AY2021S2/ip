public class DukeBot {
    private String name;

    public DukeBot(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Hello! I'm " + this.name + "\n" + "What can I do for you?\n";
    }
}
