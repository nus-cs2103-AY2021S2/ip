import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import java.io.BufferedWriter;
import java.io.FileWriter;

import java.io.File;
import java.util.Scanner;

import static simulator.Design.printBox;
import static simulator.Design.printList;
import static simulator.Design.ADD_MSG;
import static simulator.Design.MARK_MSG;
import static simulator.Design.DELETE_MSG;
import static simulator.Design.EXIT_MSG;


import simulator.DukeException;
import task.*;

public class ChatBot {
    private final ArrayList<Task> list;
    private File data;

    public ChatBot() {
        list = new ArrayList<>();
    }

    public void startUp() {
        try {
            data = new File("./data.txt");
            if (data.createNewFile()) {
                printBox("No Save Record Detected... \n"
                        + "     Creating New List! :)");
            } else {
                printBox("Saved Record Detected... \n"
                        + "     Retrieving List! :)");
                Scanner sc = new Scanner(data);
                while (sc.hasNext()) {
                    String[] content = sc.nextLine().split("\\|");
                    String command = content[0];
                    String status = content[1];
                    Task task;
                    if (command.equals("T")) {
                        task = new Todo(status, content[2]);
                    } else {
                        String[] description = content[2].split("@");
                        if (command.equals("D")) {
                            task = new Deadline(status, description);
                        } else {
                            task = new Event(status, description);
                        }
                    }
                    list.add(task);
                }
            }
        } catch (DukeException | IOException ex) {
            printBox(ex.getMessage());
        }
    }

    public void save() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(data));
        for (Task task : list) {
            String status = task.getStatus();
            String type = task.getType();
            String description = task.getDetails();
            if (type.equals("T")) {
                writer.write(type + "|" + status + "|" + description);
            } else {
                String date = type.equals("D") ? ((Deadline) task).getDate() : ((Event) task).getDate();
                if ((type.equals("D")
                        ? ((Deadline) task).getTime() == null
                        : ((Event) task).getTime() == null)) {
                    writer.write(type + "|" + status + "|" + description + "@" + date);
                } else {
                    String time = type.equals("D") ? ((Deadline) task).getTime() : ((Event) task).getTime();
                    writer.write(type + "|" + status + "|" + description + "@" + date + "@" + time);
                }
            }
            writer.newLine();
        }
        printBox("List Saved Successfully!\n"
                + "     " + EXIT_MSG);
        writer.close();
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
                    printBox(MARK_MSG + "      " + task);
                } else if (command.equals("delete")) {
                    int index = Integer.parseInt(content[1]);
                    String task = list.get(index - 1).toString();
                    list.remove(index - 1);
                    printBox(DELETE_MSG + task + "\n \n" + getTally());
                } else {
                    String[] description = Arrays.copyOfRange(content, 1, content.length);
                    Task newTask;
                    if (command.equals("todo")) {
                        newTask = new Todo(description);
                        list.add(newTask);
                        printBox(ADD_MSG + newTask + "\n \n" + getTally());
                    } else if (command.equals("deadline")) {
                        newTask = new Deadline(description);
                        list.add(newTask);
                        printBox(ADD_MSG + newTask + "\n \n" + getTally());
                    } else if (command.equals("event")) {
                        newTask = new Event(description);
                        list.add(newTask);
                        printBox(ADD_MSG + newTask + "\n \n" + getTally());
                    } else {
                        printBox("☹ OOPS!!! Incorrect input, please check!");
                    }
                }
            }
        } catch (DukeException err) {
            printBox(err.getMessage());
        } catch (Exception err) {
            printBox("☹ OOPS!!! Incorrect input, please check!");
        }
    }
}
