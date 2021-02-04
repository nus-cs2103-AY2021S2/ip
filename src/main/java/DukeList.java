import java.time.LocalDate;
import java.util.ArrayList;

public class DukeList {
    private ArrayList<Task> list;
    private int size;
    private LocalDate date;

    public DukeList() {
        this.list = new ArrayList<>();
        this.size = 0;
    }
    public DukeList(ArrayList<Task> list) {
        this.list = list;
        size = list.size();
    }
    public void add(Task item) {
        list.add(item);
        size++;
    }

    public void done(int x) {
        list.get(x).markAsDone();
    }

    /**
     *
     * @param x Task number to be removed
     * deletes the stated task
     */

    public void delete(int x) {
        list.remove(x);
        size--;
    }

    public void deleteAll() {
        for (int i = size - 1; i >= 0; i--) {
            list.remove(i);
        }
        size = 0;
    }

    public int getSize() {
        return size;
    }

    /**
     * DukeList getter
     * @param x Task number x
     * @return Task x
     */
    public Task get(int x) {
        return list.get(x);
    }


    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }
}
