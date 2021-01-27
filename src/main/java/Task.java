import java.time.LocalDate;

abstract class Task {
    protected final String taskName;
    protected boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public void completeTask() {
        this.isDone = true;
    }

    public static String printDate(LocalDate date) {
        String month = date.getMonth().toString().substring(0,3);
        String[] splitDate = date.toString().split("-");
        String year = splitDate[0];
        String day = splitDate[2];
        return month.substring(0,1) + month.substring(1,3).toLowerCase() +
                " " + day + " " + year;
    }

    public boolean isComplete() {
        return this.isDone;
    }
}