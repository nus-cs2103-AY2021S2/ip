import javax.xml.crypto.Data;

public abstract class Command {
//    String s;
//    public Command(String st) {
//        this.s = st;
//    }
    public abstract void execute(TaskList tasks, String input, DataHandler dataHandler);
    public abstract boolean isExit();
}
