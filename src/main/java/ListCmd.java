package main.java;

public class ListCmd extends Command {
    private String cmd;

    public ListCmd (String cmd) {
        this.cmd = cmd;
    }

    @Override
    public void execute(TaskList lst, Ui ui, Storage storage) throws DuckieException {
        //System.out.println(line);
        ui.customLine();

        if (lst.getListSize() == 0) {
            System.out.println("there are no tasks in your list!");
        } else if (lst.getListSize() == 1) {
            System.out.println("get to work! this is the task in your list: ");
        } else {
            System.out.println("get to work! these are the tasks in your list: ");
        }
        for (int i = 0; i < lst.getListSize(); i++) {
            System.out.println(i + 1 + "." + lst.getTask(i).toString());
        }
        //System.out.println(line);
        ui.customLine();
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
