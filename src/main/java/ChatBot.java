import java.util.ArrayList;
import java.util.Arrays;
import static simulator.Design.*;

import simulator.DukeException;
import task.*;

public class ChatBot {
    private ArrayList<Task> list;
    public ChatBot() {
        list = new ArrayList<>();
    }
    public String getTally() {
        return "     Currently you have " + list.size() + " tasks in the list.";
    }

    public void process(String input) {
        try {
            String[] content = input.split("\\s+");
            String command = content[0];
            if (command.equals("list")) {
                printList(list);
            } else {
                if (command.equals("done")) {
                    int index = Integer.parseInt(content[1]);
                    Task task = list.get(index - 1);
                    task.complete();
                    printBox(markMsg + "      " + task);
                } else if (command.equals("delete")) {
                    int index = Integer.parseInt(content[1]);
                    String task = list.get(index - 1).toString();
                    list.remove(index - 1);
                    printBox(deleteMsg + task + "\n \n" + getTally());
                } else {
                    String[] description = Arrays.copyOfRange(content, 1, content.length);
                    Task newTask;
                    if (command.equals("todo")) {
                        newTask = new Todo(description);
                        list.add(newTask);
                        printBox(addMsg + newTask + "\n \n" + getTally());
                    } else if (command.equals("deadline")) {
                        newTask = new Deadline(description);
                        list.add(newTask);
                        printBox(addMsg + newTask + "\n \n" + getTally());
                    } else if (command.equals("event")) {
                        newTask = new Event(description);
                        list.add(newTask);
                        printBox(addMsg + newTask + "\n \n" + getTally());
                    } else {
                        printBox("☹ OOPS!!! Incorrect input, please check!");
                    }
                }
            }
        } catch (DukeException err) {
            printBox(err.getMessage());

        }
        catch (Exception err) {
            printBox("☹ OOPS!!! Incorrect input, please check!");
        }
    }
}
