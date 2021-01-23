public class DeleteCommand extends Command {
    String command;

    DeleteCommand(String command) {
        this.command = command;
    }


    @Override
    void executeAndPrint(TaskList list, int length) throws DukeException {
        int index;
        index = Integer.parseInt(command.substring(7)) - 1;
        if (index < list.getSize() && index >= 0) {
            Task currTask = list.getJob(index);
            list.deleteJob(index);
            System.out.print("This task is deleted: \n"
                    + StringParser.newLiner(currTask.toString(), length)
                    + "Now you have " + list.getSize()
                    + (list.getSize() == 1 ? " task in the list\n" : " tasks in the list\n"));
        } else {
            System.out.print("No such task in the list\n");
        }
    }

    @Override
    boolean isExit() {
        return false;
    }
}
