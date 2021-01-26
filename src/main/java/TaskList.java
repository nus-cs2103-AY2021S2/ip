import java.util.ArrayList;

class TaskList {
    private ArrayList<Task> list;
    private boolean isDone;

    TaskList() {
        list = new ArrayList<>();
        isDone = false;
    }

    protected ArrayList<Task> getTasks() {
        return this.list;
    }

    protected void addTask(Task content) {
        list.add(content);
    }

    protected void addTask(String[] tasks) {
        String type = tasks[0].strip();
        String done = tasks[1].strip();
        String desc = tasks[2].strip();
        Task task;
        task = new ToDo(desc);
        if (type.equals("T")) {
            task = new ToDo(desc);
            this.addTask(task);
        } else if (type.equals("D")) {
            String byDate = tasks[3];
            task = new Deadline(desc,byDate);
            this.addTask(task);
        } else if (type.equals("E")) {
            String atDate = tasks[3];
            task = new Event(desc,atDate);
            this.addTask(task);
        } else {
            System.out.println("File has invalid entries");
        }
        if (done.equals("1")) {
            task.setDone();
        }
    }

    protected void setTaskDone(int count) throws IllegalArgumentException {
        if (count > list.size() || count <= 0) {
            throw new IllegalArgumentException("Error: Task number out of range.");
        } else {
            list.get(count-1).setDone();
        }
    }

    protected Task deleteTask(int count) throws IllegalArgumentException {
        if (count > list.size() || count <= 0) {
            throw new IllegalArgumentException("Error: Task number out of range.");
        } else {
            Task removedTask = list.remove(count-1);
            return removedTask;
        }
    }

    protected void setExited() {
        this.isDone = true;
    }

    protected boolean hasExited() {
        return this.isDone;
    }

}
