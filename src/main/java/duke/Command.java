package duke;

public class Command {

    protected static String input;
    protected static boolean isExit;

    public Command (String input, TaskList taskList){
        this.input = input;
        this.isExit = false;
    }

    public TaskList execute(TaskList tasklist, UI ui, DataStorage storage) throws DukeException {
        if(this.input.equals("bye")){
            Command ec = new ExitCommand();
            ec.execute(tasklist,ui,storage);
        }
        return tasklist;

    }

    protected static boolean isExit(){
        return isExit;
    }






}
