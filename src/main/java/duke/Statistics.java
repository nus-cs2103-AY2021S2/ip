package duke;

import java.time.LocalDate;
import java.util.ArrayList;

public class Statistics {

    public String getOverdueDeadlines(ArrayList<Task> list) {
        String message = "";
        ArrayList<Task> overdueDeadlines = new ArrayList<>();
        for (Task task : list) {
            if (task.getType() == 'D') {
                Deadline deadline = (Deadline) task;
                if (deadline.getFormattedDate().compareTo(LocalDate.now()) < 0) {
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
                int numberOfDaysDifference = deadline.getFormattedDate().compareTo(LocalDate.now());
                if (numberOfDaysDifference <= 7 && numberOfDaysDifference >= 0) {
                    tasksDueSoon.add(task);
                }
            } else if (task.getType() == 'E') {
                Event event = (Event) task;
                int numberOfDaysDifference = event.getFormattedDate().compareTo(LocalDate.now());
                if (numberOfDaysDifference <= 7 && numberOfDaysDifference >= 0) {
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
