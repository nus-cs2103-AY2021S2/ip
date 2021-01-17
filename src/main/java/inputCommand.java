public class inputCommand {
    String command;
    enum predefinedCommand {
        list,
        bye
    }

    public inputCommand(){
        this.command = "";
    }

    public inputCommand(String in){
        this.command = in;
    }

    public String getCommand(){
        return this.command;
    }

    public String print(lists inputList){
        try{
            predefinedCommand switchVal = predefinedCommand.valueOf(this.command);
            switch(switchVal){
                case bye:
                    return "Bye. Hope to see you again soon!";
                case list:
                    String initStr = "";
                    for(int i = 0; i < inputList.getDukeList().size(); i++){
                        initStr += ((i + 1) + ". " + inputList.getDukeList().get(i) + "\n");
                    }
                    return initStr;
            }
            return "";

        }catch(IllegalArgumentException ex){
            inputList.addCommandMutable(this);
            return "added: " + this.command;
        }
    }
}
