import java.time.LocalDate;
import java.util.ArrayList;

public class DukeList {
    private ArrayList<Task> list;

    public DukeList() {
        this.list = new ArrayList<>();
    }

    public void add(Task item) {
        list.add(item);
    }

    public void done(int x) {
        list.get(x).markAsDone();
    }

    public int noOfTasks() {
        return list.size();
    }

    /**
     *
     * @param x Task number to be removed
     * deletes the stated task
     */

    public void delete(int x) {
        list.remove(x);
    }

    /**
     * DukeList getter
     * @param x Task number x
     * @return Task x
     */
    public Task get(int x) {
        return list.get(x);
    }

    /**
     * Prints all task on a given day
     * @param day yyyy-mm-dd format
     */
    public void showTaskOnDay(LocalDate day) {
        int counter = 1;
        for (Task curr : list) {
            if (curr instanceof Deadlines) {
                if (((Deadlines) curr).getBy().equals(day)) {
                    System.out.println(counter + "." +curr);
                    counter++;
                }
            } else if (curr instanceof Events) {
                if (((Events) curr).getDuration().equals(day)) {
                    System.out.println(counter + "." + curr);
                    counter++;
                }
            }
        }
    }

    /**
     * Prints DukeList object
     */
    public void printAll() {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            System.out.println((i + 1) + "." + list.get(i));
        }
    }
}
