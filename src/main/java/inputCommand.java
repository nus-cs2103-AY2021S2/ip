public class inputCommand {
    String command;

    public inputCommand(){
        this.command = "";
    }

    public inputCommand(String in){
        this.command = in;
    }

    public String getInput(){
        return this.command;
    }
}
