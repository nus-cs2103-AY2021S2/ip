import java.util.ArrayList;

public class JobList {
    ArrayList<Task> list;
    int lengthOfLine;

    JobList(int length) {
         this.list = new ArrayList<>();
         this.lengthOfLine = length;
    }

    void addJob(Task t) {
        this.list.add(t);
    }

    Task getJob(int index) {
        return this.list.get(index);
    }

    int getSize() {
        return this.list.size();
    }

    void replaceJob(int index, Task newTask) {
        this.list.remove(index);
        this.list.add(index, newTask);
    }

    @Override
    public String toString() {
        StringBuilder resultStr = new StringBuilder();
        for (int i = 0; i < this.list.size(); i++) {
            resultStr.append(StringParser.newLiner((i + 1) + "." + list.get(i).toString(), lengthOfLine));
        }
        return resultStr.toString();
    }

}
