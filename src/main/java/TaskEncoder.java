import java.util.List;
import java.util.ArrayList;

public class TaskEncoder {

    static StringBuilder encodedTask = new StringBuilder();

    public static List<String> encodeTask(List<ITask> taskList){
        List<String> encodedTasks = new ArrayList<>();
        taskList.forEach(x -> encodedTasks.add(x.getContentString()));
        return encodedTasks;
    }


}
