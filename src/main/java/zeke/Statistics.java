package zeke;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;


public class Statistics {

    /**
     * Returns deadlines that are overdue and not completed yet.
     *
     * @param list list of tasks.
     * @return list of overdue deadlines.
     */
    public String getOverdueDeadlines(ArrayList<Task> list) {
        String message = "";
        ArrayList<Task> overdueDeadlines = new ArrayList<>();
        for (Task task : list) {
            if (task.getType() == 'D' && !task.getDoneStatus()) {
                Deadline deadline = (Deadline) task;
                if (deadline.getLocalDate().isBefore(LocalDate.now())) {
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

    /**
     * Returns deadlines due or events happening in a week from now.
     *
     * @param list list of tasks.
     * @return list of deadlines and events due soon.
     */
    public String getTasksDueSoon(ArrayList<Task> list) {
        String message = "";
        ArrayList<Task> tasksDueSoon = new ArrayList<>();
        for (Task task : list) {
            if (task.getType() == 'D') {
                Deadline deadline = (Deadline) task;
                int numberOfDaysApart = (int) LocalDate.now().until(deadline.getLocalDate(), ChronoUnit.DAYS);
                if (numberOfDaysApart <= 7 && numberOfDaysApart >= 0) {
                    tasksDueSoon.add(task);
                }
            } else if (task.getType() == 'E') {
                Event event = (Event) task;
                int numberOfDaysApart = (int) LocalDate.now().until(event.getLocalDate(), ChronoUnit.DAYS);
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
