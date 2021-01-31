package duke;

class DefaultCommand implements ICommand{
    @Override
    public void execute(String parameters) {
        System.out.println("Error: Unknown command word. Please try again.");
    }
}
