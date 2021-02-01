package main.java;

public class ToDoCmd extends Command {

    private String cmd;

    public ToDoCmd (String cmd) {
        this.cmd = cmd;
    }

    @Override
    public void execute(TaskList lst, Ui ui, Storage storage) throws DuckieException {
        String[] cmdArr = cmd.trim().split(" ");
        //System.out.println(line);
        ui.customLine();
        ToDo tempT = new ToDo(cmdArr[1]);
        lst.addTask(tempT);
        System.out.println("ok! i've added this task:");
        System.out.println(tempT.toString());

        if (lst.getListSize() == 0) {
            System.out.println("there are no tasks in your list!");
        } else if (lst.getListSize() == 1) {
            System.out.println("you have " + lst.getListSize() + " task in your list! keep working!");
        } else {
            System.out.println("you have " + lst.getListSize() + " tasks in your list! keep working!");
        }
        //System.out.println(line);
        ui.customLine();
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
