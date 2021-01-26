class CommandBorder implements ICommand {
    private ICommand decoratedCommand;

    CommandBorder(ICommand decoratedCommand) {
        this.decoratedCommand = decoratedCommand;
    }

    public void execute(String parameters){
        System.out.println("-".repeat(40));
        decoratedCommand.execute(parameters);
        System.out.println("-".repeat(40));
    }
}
