//public interface Command {
//    String execute(TaskList taskLst);
//}

public abstract class Command {
    public abstract String execute(TaskList taskLst);

    public boolean isExit() {
        return false;
    }
}
