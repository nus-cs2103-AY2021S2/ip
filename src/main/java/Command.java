import java.io.IOException;

public abstract class Command {
    public abstract String action();

    public void writeToFile() {
        try {
            FileAccessor.writeToFile(TaskList.getList());
        } catch (IOException e) {
            assert false : Ui.informUnableSave();
        }
    }
}