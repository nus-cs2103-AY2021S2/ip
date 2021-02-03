public class DeadlineCommand extends Command {
    public static final boolean IS_EXIT = false;
    protected String input;

    public DeadlineCommand(String input) {
        super(IS_EXIT);
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (input.length() <= 9 || !input.contains("/by")) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        String[] strArr = input.split("/by ");
        String description = strArr[0].substring(9).trim();
        String by = strArr[1];
        System.out.println("Got it. I've added this task:");
        Deadline d = new Deadline(description, by);
        tasks.addTask(d);
        System.out.println(" " + d.toString());
        storage.addNewDataToFile("D", "0", d.getDescription(), d.getBy());
        if(tasks.getSize() == 1) {
            System.out.printf("Now you have %d task in the list.%n", tasks.getSize());
        } else {
            System.out.printf("Now you have %d tasks in the list.%n", tasks.getSize());
        }
    }
}
