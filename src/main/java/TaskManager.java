import java.util.ArrayList;

public class TaskManager {
    int listLength;
    ArrayList<Task> list;
    String path;

    public TaskManager(ArrayList<Task> list, int listLength, String path) {
        this.list = list;
        this.listLength = listLength;
        this.path = path;
    }

    //checks the type of instruction given based on the first word in the user input
    //throws exception if insufficient/wrong instruction given
    //increments listLength if a valid task and decrements if a delete instruction
    //adds a task to the list if task given and removes if a delete instruction
    //prints all tasks if input is list
    //completes a certain task if input is done
    void done(String[] info) throws DukeException {
        if (info.length == 1) {
            throw new DukeException("OOPS! Task completed is not specified");
        }
        if (Integer.parseInt(info[1]) > listLength || Integer.parseInt(info[1]) <= 0) {
            throw new DukeException("OOPS! There is no such specified task");
        }
        Task tobeDone = list.get(Integer.parseInt(info[1]) - 1);
        tobeDone.completed();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("    " + tobeDone);
    }

    void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0;  i < listLength; i++) {
            System.out.println( (i+1) + "."  + list.get(i));
        }
    }

    void delete(String[] info) throws DukeException {
        int taskIndex = Integer.parseInt(info[1]) - 1;
        if (taskIndex > listLength || taskIndex < 0) {
            throw new DukeException("OOPS!!! There is no task in that line to delete");
        }
        System.out.println(" Noted. I've removed this task:");
        System.out.println(list.remove(taskIndex));
        listLength--;
        System.out.println("Now you have " + listLength + " tasks in the list.");
    }

    void add(String[] info) throws DukeException {
        int length = info.length;
        Task task = null;
        if (info[0].equals("todo")) {
            if (length == 1) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty");
            }
            StringBuffer sb = new StringBuffer();
            for (int i = 1; i < info.length; i++) {
                sb.append(info[i]);
                if (i != info.length - 1) {
                    sb.append(" ");
                }
            }
            task = new ToDo(sb.toString(), "T");
        } else if (info[0].equals("event") || info[0].equals("deadline")) {
            if (length == 1) {
                if (info[0].equals("event")) {
                    throw new DukeException("OOPS! Specifics are needed for this event");
                } else {
                    throw new DukeException("OOPS! Specifics are needed for this deadline");
                }
            }
            StringBuffer description = new StringBuffer();
            StringBuffer dateAndTime = new StringBuffer();
            boolean isDescriptionDone = false;
            for (int i = 1; i < info.length; i++) {
                if (info[i].equals("/at") || info[i].equals("/by")) {
                    isDescriptionDone = true;
                    i++;
                }
                if (isDescriptionDone) {
                    dateAndTime.append(info[i]);
                    if (i != info.length - 1) {
                        dateAndTime.append(" ");
                    }
                } else {
                    description.append(info[i]);
                    if (i != info.length - 1) {
                        description.append(" ");
                    }
                }
            }
            if (info[0].equals("event")) {
                String[] details = dateAndTime.toString().split(" ");
                Date date = new Date(details[0]);
                task = new Event(description.toString(), date, details[1], "E");
            } else if (info[0].equals("deadline")) {
                Date date = new Date(dateAndTime.toString());
                task = new Deadline(description.toString(), date, "D");
            }
        }
        list.add(task);
        listLength++;
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + task);
        System.out.println("Now you have " + (listLength) + " tasks in the list.");
    }
    void manage(String[] info) throws DukeException {
        if (info[0].equals("done")) {
            done(info);
        }  else if (info[0].equals("list")) {
            printList();
        } else if (info[0].equals("delete")) {
            delete(info);
        }else if (info[0].equals("event")) {
            add(info);
        } else if (info[0].equals("todo")) {
            add(info);
        } else if (info[0].equals("deadline")) {
            add(info);
        } else {
            throw new DukeException("Sorry but I don't understand what that means! :-(");
        }
    }
}
