public class DoneCommand extends Command {
    String command;

    DoneCommand(String command) {
        this.command = command;
    }


    @Override
    void executeAndPrint(TaskList list, int length) throws DukeException {
        int index;
        index = Integer.parseInt(command.substring(5)) - 1;
        if (index < list.getSize() && index >= 0) {
            Task currTask = list.getJob(index);
            currTask.markAsDone();
            list.replaceJob(index, currTask);
            System.out.print("This task is marked as done: \n"
                    + StringParser.newLiner(currTask.toString(), length));
        } else {
            System.out.print("No such task in the list\n");
        }
    }

    @Override
    boolean isExit() {
        return false;
    }
}
