import java.util.ArrayList;
import java.util.List;

public class TaskDecoder {

    public static List<ITask> decodeTask(List<String> encodedTasks){
        List<ITask> decodedTasks = new ArrayList<>();
        encodedTasks.forEach(x -> decodedTasks.add(decode(x)));
        return decodedTasks;
    }

    static ITask decode(String encodedTask){
        String[] list = encodedTask.split("\\|");
        ITask task;
        switch (list[0]) {
            case "T":
                task = Todo.getTodo(list[2]);
                break;
            case "D":
                task = Deadline.getDeadline(list[2], list[3]);
                break;
            case "E":
                task = Event.getEvent(list[2], list[3]);
                break;
            default:
                throw new IllegalArgumentException("Unable to decode!");
        }

        if (list[1].equals("1")) {
            task = task.markDone();
        }

        return task;
    }


}
