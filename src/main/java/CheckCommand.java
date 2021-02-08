import java.time.LocalDate;

public class CheckCommand implements Command{
    private LocalDate date;

    public CheckCommand(LocalDate date) {
        this.date = date;
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList filteredList = new TaskList();

        for (int i = 1; i <= tasks.size(); i++) {
            Task currTask = tasks.get(i);
            if (this.date.equals(currTask.getDate())) {
                filteredList.add(currTask);
            }
        }

        ui.printMessage(filteredList.toString());
    }
    
}
