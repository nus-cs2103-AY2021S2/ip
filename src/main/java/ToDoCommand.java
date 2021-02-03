public class ToDoCommand extends Command {
    public static final boolean IS_EXIT = false;
    protected String input;

    public ToDoCommand(String input) {
        super(IS_EXIT);
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if(input.length() <= 5) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty");
        }
        System.out.println("Got it. I've added this task:");
        String s = input.substring(5);
        ToDo td = new ToDo(s);
        tasks.addTask(td);
        System.out.println(" " + td.toString());
        storage.addNewDataToFile("T", "0", td.getDescription(), "");
        if(tasks.getSize() == 1) {
            System.out.printf("Now you have %d task in the list.%n", tasks.getSize());
        } else {
            System.out.printf("Now you have %d tasks in the list.%n", tasks.getSize());
        }
    }
}
