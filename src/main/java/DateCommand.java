public class DateCommand extends Command {

    public DateCommand(String info) {
        super(info);
    }

    @Override
    public void execute(TaskList tasks, Ui ui,
                        Storage storage, Statistics stat) throws DukeException {
        String time;

        if (info.equals("")) {
            throw new DukeException("OOPS!!! The description cannot be empty.");
        } else {
            time = Parser.parseTime(info);
            ui.showDate(time, tasks.searchDateTask(time));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
