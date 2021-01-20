public class Echo implements Command {
    private final String input;

    public Echo(String input) {
        this.input = input;
    }

    @Override
    public String execute() {
        return input;
    }
}
