package Duke.Helper;

import Duke.Exception.*;
import Duke.Task.*;
import Duke.Command.*;
import Duke.Constant.*;
import java.util.ArrayList;

public class TaskList {
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
          return Constants.MARK_DONE_TASK + task.toString();
        } catch (IndexOutOfBoundsException e){
            throw new InvalidIndex("Done", list.size());
        }
    }

    public String deleteTask(int index){
        try{
            Task task = list.remove(index - 1);
            return Constants.DELETE_TASK_SUCCESS +
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
            return Constants.ADD_TASK_SUCCESS +
                    "  " + task +"\n" +
                    "Now you have " + list.size() + " tasks in the list.";
        } else if (command.toLowerCase().startsWith(Command.DEADLINE.getAction() + " ")){
            Deadline task = parser.parseDeadline(command);
            if (task == null){
                return Constants.INVALID_DATETIME_FORMAT;
            } else {
                list.add(task);
                return Constants.ADD_TASK_SUCCESS +
                        "  " + task +"\n" +
                        "Now you have " + list.size() + " tasks in the list.";
            }
        } else if (command.toLowerCase().startsWith(Command.EVENT.getAction() + " ")){
            Event task = parser.parseEvent(command);
            if (task == null){
                return Constants.INVALID_DATETIME_FORMAT;
            } else {
                list.add(task);
                return Constants.ADD_TASK_SUCCESS +
                        "  " + task +"\n" +
                        "Now you have " + list.size() + " tasks in the list.";
            }
        } else {
            throw new NoSuchCommandException();
        }
    }
}
