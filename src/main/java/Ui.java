import java.time.format.DateTimeFormatter;

public class Ui {
    public final static DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern(
        "eee, dd MMM yyyy HH:mm a");

    public void print (String... messages) {
        for (String message: messages) {
            System.out.println("    " + message);
        }
    }

    public void brace () {
        print("__________________________________________________"
                + "__________________________________________________");
    }

    public void storageReading (String username) {
        print("Reading tasks from userdata for User: " + username);
    }

    public void greet () {
        print("I am", 
                " ____        _        ", 
                "|  _ \\ _   _| | _____ ",
                "| | | | | | | |/ / _ \\",
                "| |_| | |_| |   <  __/",
                "|____/ \\__,_|_|\\_\\___|",
                "at your service.");
    }

    public void addedTask (TaskList tasks) {
        print("Added task:");
        print(tasks.get(tasks.size() - 1).taskInformation(Ui.outputFormat));
        print("You now have " + tasks.size() + " tasks.");
    }

    public void removedTask (TaskList tasks, Task task) {
        print("Removed task:", task.taskInformation(Ui.outputFormat), "You now have " 
            + tasks.size() + " tasks.");
    }

    public void confuzzled () {
        print("WHaT dO YoU MeAN ?! .___.");
    }

    public void byebye () {
        print("Byebye, come back again soon! :D");
    }
}