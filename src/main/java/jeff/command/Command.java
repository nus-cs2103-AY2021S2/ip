package jeff.command;

import jeff.JeffException;
import jeff.Storage;
import jeff.TaskList;

public abstract class Command {
    private String mainInfo;
    private String supplementaryInfo;

    Command(String main, String supp) {
        mainInfo = main;
        supplementaryInfo = supp;
    }

    Command(String main) {
        mainInfo = main;
    }

    Command() {
    }

    public String getMainInfo() {
        return mainInfo;
    }
    public String getSupplementaryInfo() {
        return supplementaryInfo;
    }

    public abstract String execute(TaskList tasks, Storage storage) throws JeffException;
}
