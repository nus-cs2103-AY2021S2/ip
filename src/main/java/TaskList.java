import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> lst = new ArrayList<>();

    TaskList() {
    }

    public void add(Parser p) throws EmptyDescription {
        String typeOfTask = p.getTypeOfTask();
        String description = p.getDescription();
        if (description.equals("")) {
            throw new EmptyDescription(typeOfTask);
        } else {
            String time = p.getTime();
            Task newTask;
            if (typeOfTask.equals("todo")) {
                newTask = new Todo(description);
            } else if (typeOfTask.equals("deadline")) {
                newTask = new Deadline(description, time);
            } else {
                newTask = new Event(description, time);
            }
            lst.add(newTask);
            String instructions = Response.ADD.toString() + newTask + "\n" + this.status();
            Duke.output(instructions);
        }
    }

    public void delete(Parser p) {
        int i = Integer.parseInt(p.getDescription());
        String instructions = Response.DELETE.toString() + lst.get(i - 1) + "\n" + this.status();
        lst.remove(i - 1);
        Duke.output(instructions);
    }

    public void markAsDone(Parser p) {
        int i = Integer.parseInt(p.getDescription());
        lst.set(i - 1, lst.get(i - 1).setDone());
        Duke.output(Response.DONE.toString() + lst.get(i - 1) + "\n");
    }

    public void list() {
        String msg = "";
        for(int i = 0; i < lst.size(); i++) {
            msg += (i + 1) + "." + lst.get(i) + "\n";
        }
        Duke.output(Response.LIST.toString() + msg);
    }

    public  String status() {
        return "Now you have " + lst.size() + " tasks in the list.\n";
    }
}
