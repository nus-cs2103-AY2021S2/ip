public class CommandDecorator implements ICommand {

    private ICommand decoratedCommand;

    public CommandDecorator(ICommand decoratedCommand) {
        this.decoratedCommand = decoratedCommand;
    }

    public void execute(String[] parameters){
        System.out.println("-".repeat(40));
        decoratedCommand.execute(parameters);
        System.out.println("-".repeat(40));
    }
}
