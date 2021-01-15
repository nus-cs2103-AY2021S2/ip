import java.util.ArrayList;

public class JobList {
    ArrayList<Task> list;

    JobList() {
         this.list = new ArrayList<>();
    }

    void addJob(Task t) {
        this.list.add(t);
    }

    String formatList() {
        StringBuilder resultStr = new StringBuilder();
        for (int i = 0; i < this.list.size(); i++) {
            resultStr.append(StringParser.newLiner((i + 1) + ". " + list.get(i).getDescription(), 60));
        }
        return resultStr.toString();
    }

}
