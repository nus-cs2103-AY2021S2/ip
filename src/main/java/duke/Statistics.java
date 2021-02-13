package duke;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;


public class Statistics {

    public String getOverdueDeadlines(ArrayList<Task> list) {
        String message = "";
        ArrayList<Task> overdueDeadlines = new ArrayList<>();
        for (Task task : list) {
            if (task.getType() == 'D' && !task.getDoneStatus()) {
                Deadline deadline = (Deadline) task;
                if (deadline.getFormattedDate().isBefore(LocalDate.now())) {
                    overdueDeadlines.add(task);
                }
            }
        }
        int num = 1;
        for (Task task : overdueDeadlines) {
            message += String.format("%d. %s", num, task) + "\n";
            num++;
        }
        return message;
    }

    public String getTasksDueSoon(ArrayList<Task> list) {
        String message = "";
        ArrayList<Task> tasksDueSoon = new ArrayList<>();
        for (Task task : list) {
            if (task.getType() == 'D') {
                Deadline deadline = (Deadline) task;
                int numberOfDaysApart = (int) LocalDate.now().until(deadline.getFormattedDate(), ChronoUnit.DAYS);
                if (numberOfDaysApart <= 7 && numberOfDaysApart >= 0) {
                    tasksDueSoon.add(task);
                }
            } else if (task.getType() == 'E') {
                Event event = (Event) task;
                int numberOfDaysApart = (int) LocalDate.now().until(event.getFormattedDate(), ChronoUnit.DAYS);
                if (numberOfDaysApart <= 7 && numberOfDaysApart >= 0) {
                    tasksDueSoon.add(task);
                }
            }
        }
        int num = 1;
        for (Task task : tasksDueSoon) {
            message += String.format("%d. %s", num, task) + "\n";
            num++;
        }
        return message;
    }


}
