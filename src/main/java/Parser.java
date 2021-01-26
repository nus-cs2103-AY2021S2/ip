import java.util.List;
import java.util.ArrayList;
public class Parser {
    private static final String splitString = "p@nt3k";

    public static List<Task> parseResult(List<String> listOfString) {
        List<Task> lst = new ArrayList<>();
        for (String str : listOfString) {
            String[] previousVersionTasks = str.split(splitString);
            String job = previousVersionTasks[0];
            String jobName = previousVersionTasks[1];
            Boolean done = Boolean.parseBoolean(previousVersionTasks[2]);
            if (job.equals("ToDo")) {
                ToDo task = new ToDo(jobName, done);
                lst.add(task);
            } else if (job.equals("Deadline")) {
                String jobTime = previousVersionTasks[3];
                Deadline task = new Deadline(jobName, done, jobTime);
                lst.add(task);
            } else if (job.equals("Event")) {
                String jobTime = previousVersionTasks[3];
                Event task = new Event(jobName, done, jobTime);
                lst.add(task);
            }
        }
        return lst;
    }

    public static String convertTasksToString(TaskManager taskManager) {
        List<String> lst = new ArrayList<>();
        for (int i=0; i < taskManager.size(); i++) {
            Task task = taskManager.get(i);
            StringBuilder taskInStringForm = new StringBuilder();
            if (task instanceof ToDo) {
                taskInStringForm.append("ToDo");
                taskInStringForm.append(splitString);
                taskInStringForm.append(task.getJob());
                taskInStringForm.append(splitString);
                taskInStringForm.append(task.getDone());
            } else if (task instanceof Deadline) {
                taskInStringForm.append("Deadline");
                taskInStringForm.append(splitString);
                taskInStringForm.append(task.getJob());
                taskInStringForm.append(splitString);
                taskInStringForm.append(task.getDone());
                taskInStringForm.append(splitString);
                taskInStringForm.append(((Deadline)task).getTime());
            } else if (task instanceof Event) {
                taskInStringForm.append("Event");
                taskInStringForm.append(splitString);
                taskInStringForm.append(task.getJob());
                taskInStringForm.append(splitString);
                taskInStringForm.append(task.getDone());
                taskInStringForm.append(splitString);
                taskInStringForm.append(((Event)task).getTime());
            }
            lst.add(taskInStringForm.toString());
        }

        StringBuilder combinedStringTasks = new StringBuilder();
        for (String str: lst) {
            combinedStringTasks.append(str);
            combinedStringTasks.append("\n");
        }
        return combinedStringTasks.toString();
    }
}
