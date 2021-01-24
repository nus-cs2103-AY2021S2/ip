import java.util.ArrayList;

public class StorageEncoder {

    public static final String NEW_LINE = "\n";

    public static String encodeTasks(ArrayList<Task> tasks) {
        String res = "";
        for (Task task : tasks) {
            res = res.concat(task.data() + NEW_LINE);
        }
        return res;
    }
}
