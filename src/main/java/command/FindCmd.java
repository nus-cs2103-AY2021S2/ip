package main.java.command;

import main.java.classes.*;

public class FindCmd extends Command {
    private String cmd;

    public FindCmd(String cmd) {
        this.cmd = cmd;
    }

    @Override
    public void execute(TaskList lst, Ui ui, Storage storage) throws DuckieException {
        String[] cmdArr = cmd.trim().split(" ");
        //System.out.println(line);
        ui.findMessage();

        int taskSize = lst.getListSize();
        for (int i = 1; i < taskSize + 1; i++) {
            if (lst.getTask(i - 1).getDescription().contains(cmdArr[1])) {
                String output = String.format("%s. %s", i, lst.getTask(i - 1).toString());
                System.out.println(output);
            }
            //System.out.println(line);
        }
        ui.customLine();
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
