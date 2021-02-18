package jeff;

public abstract class Command {
    String mainInfo;
    String supplementaryInfo;

    Command(String main, String supp) {
        mainInfo = main;
        supplementaryInfo = supp;
    }

    Command(String main) {
        mainInfo = main;
    }

    Command() {
    }

    public abstract String execute(TaskList tasks, Storage storage) throws JeffException;
}
