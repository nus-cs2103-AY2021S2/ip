package commands;

public enum CommandEnum {
    LIST,
    FINISH,
    DELETE,
    BYE,
    TODO,
    DEADLINE,
    EVENT,
    NONE;

    /**
     * Returns enum based of given string of command.
     *
     * @param command string of command
     * @return        enum of command
     */
    public static CommandEnum getCommand(String command){
        for(CommandEnum cEnum: CommandEnum.values()){
            if(command.equalsIgnoreCase(String.valueOf(cEnum))){
                return cEnum;
            }
        }
        return CommandEnum.NONE;
    }
}
