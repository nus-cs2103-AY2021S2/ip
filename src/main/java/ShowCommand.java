public class ShowCommand extends Command {

    public ShowCommand(String info) {
        super(info);
    }

    @Override
    public void execute(TaskList tasks, Ui ui,
                        Storage storage, Statistics stat) throws DukeException {
        switch (info) {
        case "todo":
            ui.showStatistic("There are " + stat.showStat("todo") + " todo in the list.");
            break;
        case "event":
            ui.showStatistic("There are " + stat.showStat("event") + " events in the list.");
            break;
        case "deadline":
            ui.showStatistic("There are " + stat.showStat("deadline") + " deadlines in the list.");
            break;
        case "done":
            ui.showStatistic("You have done " + stat.showStat("done") + " tasks in the list.");
            break;
        case "undone":
            ui.showStatistic("You have not done " + stat.showStat("undone") + " tasks in the list.");
            break;
        case "delete":
            ui.showStatistic("You have deleted " + stat.showStat("delete") + " tasks in this session.");
            break;
        case "size":
            ui.showStatistic("There are " + tasks.size + " tasks in the list.");
            break;
        default:
            throw new DukeException("OOPS! The description for show cannot be empty");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
