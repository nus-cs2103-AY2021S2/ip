class PrintCommand implements ICommand{
    @Override
    public void execute(String parameters) {
        System.out.println(parameters);
    }
}
