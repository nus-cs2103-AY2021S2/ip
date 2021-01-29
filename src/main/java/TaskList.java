import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void setList(ArrayList<Task> prevTasks) {
        tasks = prevTasks;
    }

    public static ArrayList<Task> getList() {//prob with this is can change value as pass by ref
        return tasks;
    }

    public static int listSize() {
        return tasks.size();
    }

    public static void addTask(Task task) {
        tasks.add(task);
    }

    public static String printList() {
        return Ui.printList(tasks);
    }

    public static Task doneTask(int num) {
        if(tasks.size()==0) {
            throw new IndexOutOfBoundsException(Ui.lineGetter() +
                    "  No tasks to complete!\n" + Ui.lineGetter());
        } else if (num < 0 || num >= tasks.size()) {
            throw new IndexOutOfBoundsException(Ui.lineGetter() +
                    " Enter 'done' followed by a number between " +
                    "1 and " + tasks.size() + "\n" + Ui.lineGetter());
        }
        tasks.get(num).doneTask();
        return tasks.get(num);
    }

    public static Task deleteTask(int num) {
        if(tasks.size()==0) {
            throw new IndexOutOfBoundsException(Ui.lineGetter() +
                    "  No tasks to delete!\n" + Ui.lineGetter());
        } else if (num < 0 || num >= tasks.size()) {
            throw new IndexOutOfBoundsException(Ui.lineGetter() +
                    " Enter 'delete' followed by a number between " +
                    "1 and " + tasks.size() + "\n" + Ui.lineGetter());
        }
        return tasks.remove(num);
    }

    //try use hmap? so like each time we add a  task, we are mapping each word of the task to the idx of occurence in
    //the list,then in the harddrive can have a section for hmap and like each word and the idxes separated by a comma.
    //can be done, may be good in long term, but is painful to code, may take time, which i dont got too much of
    public static String findTask(String keyword) {
        keyword = keyword.trim();
        String[] split = keyword.split(" ");
        //String result = "";
        if (split.length > 1 || keyword.equals("")) {
            throw new IllegalArgumentException(Ui.lineGetter()
                    + " Please enter 'find' followed by only 1 word to search\n"
                    + Ui.lineGetter());
        }
        //System.out.println("sdfsf|" + keyword + "|");
        String result = "";
        //int count = 0;
        for (int i = 0; i < tasks.size(); i++) {
            String task = tasks.get(i).toString();
            String[] split1 = task.split(" ");
            for (int j = 0; j < split1.length; j++) {
                if (keyword.equals(split1[j])) {
                    int count = i + 1;
                    result = result + " " + count + ". " + task + "\n";
                }
            }
        }
        if (result.equals("")) {
            return Ui.lineGetter() +  " Sorry, there are no matching tasks :( \n"
                    + Ui.lineGetter();
        } else {
            return Ui.lineGetter() + " Here are your matching tasks: \n"
                    + result + Ui.lineGetter();
        }
    }
}
