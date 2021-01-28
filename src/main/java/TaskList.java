import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void setList(ArrayList<Task> prevTasks) {
        tasks = prevTasks;
    }

    public static ArrayList<Task> getList() {
        //prob with this is can change value as pass by ref
        return tasks;
    }

    public static int getListSize() {
        return tasks.size();
    }

    public static void addTask(Task task) {
        tasks.add(task);
    }

    public static String printList() {
        return Ui.printList(tasks);
    }

    public static Task doneTask(int number) {
        if (tasks.size()==0) {
            throw new IndexOutOfBoundsException(Ui.lineGetter()
                    + "  No tasks to complete!\n" + Ui.lineGetter());
        } else if (number < 0 || number >= tasks.size()) {
            throw new IndexOutOfBoundsException(Ui.lineGetter()
                    + " Enter 'done' followed by a number between "
                    + "1 and " + tasks.size() + "\n" + Ui.lineGetter());
        }
        tasks.get(number).setDone();
        return tasks.get(number);
    }

    public static Task deleteTask(int number) {
        if (tasks.size() == 0) {
            throw new IndexOutOfBoundsException(Ui.lineGetter()
                    + "  No tasks to delete!\n" + Ui.lineGetter());
        } else if (number < 0 || number >= tasks.size()) {
            throw new IndexOutOfBoundsException(Ui.lineGetter()
                    + " Enter 'delete' followed by a number between "
                    + "1 and " + tasks.size() + "\n" + Ui.lineGetter());
        }
        return tasks.remove(number);
    }
}
