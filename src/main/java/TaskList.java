import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TaskList {
    private List<Task> tasks;
    private int size;

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.size = 0;
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
        this.size = tasks.size();
    }

    public void listing() {
        for (int i = 1; i < tasks.size() + 1; i++) {
            Ui.print(Aligner.align(i + "." + tasks.get(i - 1).toString()));
        }
    }

    public List<Task> getList() {
        return this.tasks;
    }

    public String makeDone(int taskNo) {
        tasks.get(taskNo).markAsDone();
        return tasks.get(taskNo).toString();
    }

    public int size() {
        return this.size;
    }

    public String addByCommand(String fullCommand, String action) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        if (action.equals("todo")) {
            ToDo newToDo = new ToDo(fullCommand.substring(5));
            tasks.add(newToDo);
            this.size++;
            return newToDo.toString();
        } else if (action.equals("deadline")) {
            int indexOfDate = fullCommand.indexOf("/");
            String dateAndTime = fullCommand.substring(indexOfDate + 4);
            String date = dateAndTime.split(" ")[0];
            String time = dateAndTime.split(" ")[1];
            Deadline newDeadline = new Deadline(fullCommand.substring(9, indexOfDate), LocalDate.parse(date),
                    LocalTime.parse(time, formatter));
            tasks.add(newDeadline);
            this.size++;
            return newDeadline.toString();
        } else { //type == event
            int indexOfDate = fullCommand.indexOf("/");

            String dateAndTime = fullCommand.substring(indexOfDate + 4);
            String date = dateAndTime.split(" ")[0];
            String startTime = dateAndTime.split(" ")[1].split("-")[0];
            String endTime = dateAndTime.split(" ")[1].split("-")[1];
            Event newEvent = new Event(fullCommand.substring(6, indexOfDate), LocalDate.parse(date),
                    LocalTime.parse(startTime, formatter), LocalTime.parse(endTime, formatter));
            tasks.add(newEvent);
            this.size++;
            return newEvent.toString();
        }
    }

    public TaskList addByTask(Task newTask) {
        this.tasks.add(newTask);
        this.size++;
        return this;
    }

    public String remove(int taskNo) {
        Task removed = tasks.remove(taskNo);
        this.size--;
        return removed.toString();
    }

    public List<Task> print(LocalDate d) {
        List<Task> toPrint = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getDate() != null && t.getDate().equals(d)) {
                toPrint.add(t);
            }
        }
        return toPrint;
    }
}
