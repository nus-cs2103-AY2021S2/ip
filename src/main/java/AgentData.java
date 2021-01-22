import java.util.List;
import java.util.ArrayList;

public class AgentData implements java.io.Serializable {
    private final List<Task> data;

    public AgentData(List<Task> data) {
        this.data = data;
    }

    public List<Task> getData() {
        return data;
    }

    public int count() {
        return data.size();
    }
}
