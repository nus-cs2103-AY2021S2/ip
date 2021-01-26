public class Parser {

    public static Command parse(String commandStr){
        CommandEnum commandEnum = CommandEnum.getCommand(commandStr);
        switch(commandEnum){
            case LIST:
                return new ListCommand(CommandEnum.LIST);
            case FINISH:
                return new FinishCommand(CommandEnum.FINISH);
            case DELETE:
                return new DeleteCommand(CommandEnum.DELETE);
            case BYE:
                return new ExitCommand(CommandEnum.BYE);
            case TODO:
                return new AddCommand(CommandEnum.TODO);
            case DEADLINE:
                return new AddCommand(CommandEnum.DEADLINE);
            case EVENT:
                return new AddCommand(CommandEnum.EVENT);
            default:
        }
        return null;
    }
}
