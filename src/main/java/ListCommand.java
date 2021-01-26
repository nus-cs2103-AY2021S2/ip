public class ListCommand extends Command {
    public ListCommand(String[] details) {
        super(details);
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (details.length == 1) {
            System.out.println("Here are all the tasks in your list\n" + taskList);
        } else {
            switch(details[1].replaceAll("\\s", "")) {
            case "-d":
                System.out.println("These are the deadlines in your list:\n"
                        + taskList.filterByTask("Deadline"));
            case "-e":
                System.out.println("These are the events in your list:\n"
                        + taskList.filterByTask("Event"));
            case "-t":
                System.out.println("These are the events in your list:\n"
                        + taskList.filterByTask("Event"));
            default:
                System.out.println("Bruh! Only use -d, -e, -t to get the specific type"
                        + "of task you want!");
            }
        }
    }

}
