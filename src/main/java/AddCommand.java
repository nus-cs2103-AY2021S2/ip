public class AddCommand extends Command {
    public AddCommand(String[] commandSplit) {
        super(commandSplit);
    }

    @Override
    void execute(TaskList list) throws DukeException {
        String keyword = commandSplit[0];
        if (keyword.equals("deadline")) {
            addDeadline(list);
        } else if (keyword.equals("todo")) {
            addToDo(list);
        } else {
            addEvent(list);
        }
    }

    private void addDeadline(TaskList list) throws DukeException {
        String[] userInputSplit = this.commandSplit;
        //Index of /by keyword
        int byIndex = 0;
        for (int i = 0; i < userInputSplit.length; i++) {
            if (userInputSplit[i].equals("/by")) {
                byIndex = i;
            }
        }
        if (byIndex == 0) {
            throw new DukeException("Missing /by keyword for new deadline.");
        } else if (byIndex == 1) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        if (byIndex == userInputSplit.length - 1) {
            throw new DukeException("Missing date of the deadline.");
        }
        String task = Helper.join(userInputSplit, 1, byIndex - 1);
        String date = Helper.join(userInputSplit, byIndex + 1, userInputSplit.length - 1);
        list.add(new Deadline(task, date));
    }

    private void addToDo(TaskList list) throws DukeException {
        String[] userInputSplit = this.commandSplit;
        if (userInputSplit.length <= 1) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        String task = Helper.join(userInputSplit, 1, userInputSplit.length - 1);
        list.add(new ToDo(task));
    }

    private void addEvent(TaskList list) throws DukeException {
        String[] userInputSplit = this.commandSplit;
        //Index of /at keyword
        int atIndex = 0;
        for (int i = 0; i < userInputSplit.length; i++) {
            if (userInputSplit[i].equals("/at")) {
                atIndex = i;
            }
        }
        if (atIndex == 0) {
            throw new DukeException("Missing /at keyword for new Event.");
        } else if (atIndex == 1) {
            throw new DukeException("☹ OOPS!!! The description of an Event cannot be empty.");
        }
        if (atIndex == userInputSplit.length - 1) {
            throw new DukeException("Missing date of the Event.");
        }
        String task = Helper.join(userInputSplit, 1, atIndex - 1);
        String date = Helper.join(userInputSplit, atIndex + 1, userInputSplit.length - 1);
        list.add(new Event(task, date));
    }
}
