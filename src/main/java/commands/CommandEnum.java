package commands;

public enum CommandEnum {
    LIST,
    FINISH,
    DELETE,
    BYE,
    TODO,
    DEADLINE,
    EVENT,
    FIND,
    NONE;

    public static CommandEnum getCommand(String command){
        for(CommandEnum cEnum: CommandEnum.values()){
            if(command.equalsIgnoreCase(String.valueOf(cEnum))){
                return cEnum;
            }
        }
        return CommandEnum.NONE;
    }
}
