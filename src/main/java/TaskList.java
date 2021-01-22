import java.util.ArrayList;

public class TaskList {
    public static final String INVALID_DATETIME_FORMAT = "Invalid format for date and time." + "\n" +
            "Your date (and time) should have format yyyy-mm-dd (HH-MM)" +
            "For example: 2019-10-15 or 2019-10-15 1800";

    private ArrayList<Task> list;
    private Parser parser;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
        this.parser = new Parser();
    }

    public ArrayList<Task> getList() {
        return list;
    }

    public String finishTask(int index){
        try{
          Task task = list.get(index - 1);
          task.markAsDone();
          return "Nice! I've marked this task as done:\n  " + task.toString();
        } catch (IndexOutOfBoundsException e){
            throw new InvalidIndex("Done", list.size());
        }
    }

    public String deleteTask(int index){
        try{
            Task task = list.remove(index - 1);
            return "Noted. I've removed this task: \n" +
                    task.toString() + "\n" +
                    "Now you have " + list.size() + " tasks in the list.";
        } catch (IndexOutOfBoundsException e){
            throw new InvalidIndex("Delete", list.size());
        }
    }

    public String addTask(String command) throws NoSuchCommandException, EmptyTaskException, InvalidTask{
        if (command.equalsIgnoreCase(Command.TODO.getAction()) ||
                command.equalsIgnoreCase(Command.DEADLINE.getAction()) ||
                command.equalsIgnoreCase(Command.EVENT.getAction())){
            throw new EmptyTaskException(command);
        } else if (command.toLowerCase().startsWith(Command.TODO.getAction() + " ")){
            Todo task = parser.parseTodo(command);
            list.add(task);
            return "Got it. I've added this task: \n" +
                    "  " + task +"\n" +
                    "Now you have " + list.size() + " tasks in the list.";
        } else if (command.toLowerCase().startsWith(Command.DEADLINE.getAction() + " ")){
            Deadline task = parser.parseDeadline(command);
            if (task == null){
                return INVALID_DATETIME_FORMAT;
            } else {
                list.add(task);
                return "Got it. I've added this task: \n" +
                        "  " + task +"\n" +
                        "Now you have " + list.size() + " tasks in the list.";
            }
        } else if (command.toLowerCase().startsWith(Command.EVENT.getAction() + " ")){
            Event task = parser.parseEvent(command);
            if (task == null){
                return INVALID_DATETIME_FORMAT;
            } else {
                list.add(task);
                return "Got it. I've added this task: \n" +
                        "  " + task +"\n" +
                        "Now you have " + list.size() + " tasks in the list.";
            }
        } else {
            throw new NoSuchCommandException();
        }
    }
}
