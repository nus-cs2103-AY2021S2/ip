package main.java;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Duckie is an interactive chatbot that helps with user's tasks.
 *
 * It supports todos, events, and deadlines.
 *      todo: adds task to list
 *      event: adds task to list and specifies a time for the event with "/at"
 *      deadline: adds task to list and specifies a time for the deadline with "/by"
 *
 * It checks off a task as done with "done" followed by task number.
 *
 * It also supports deletion of tasks with "delete" followed by task number.
 *
 * Exceptions are thrown for invalid inputs.
 *
 * @author Wei Yutong
 * @version CS2103T AY20/21 Semester 2, Individual Project
 * 
 */

public class Duckie {
    private static void SaveTasks(ArrayList<Task> AL, File f) throws IOException {
        FileWriter fw = new FileWriter(f);
        for (Task temp : AL) {
            if (temp instanceof Deadline) {
                fw.write(String.format("D | %s | %s | %s",
                        temp.getIsDone(), temp.getDescription(), ((Deadline) temp).getBy()) + "\n");
            } else if (temp instanceof Event) {
                fw.write(String.format("E | %s | %s | %s",
                        temp.getIsDone(), temp.getDescription(), ((Event) temp).getAt()) + "\n");
            } else {
                fw.write(String.format("T | %s | %s",
                        temp.getIsDone(), temp.getDescription()) + "\n");
            }
        }
        fw.close();
    }

    private static void LoadTasks(ArrayList<Task> AL, File f) throws IOException {
        Scanner sc = new Scanner(f);
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            s = s.trim();

            String[] sArr = s.split("\\|", 4);

            if (sArr[0].trim().equals("D")) {
                Deadline tempD = new Deadline(sArr[2].trim(), sArr[3].trim());
                if (sArr[1].trim().equals("done")) {
                    tempD.isDone = true;
                } else if (sArr[1].trim().equals("not done")){
                    tempD.isDone = false;
                }
                AL.add(tempD);
            } else if (sArr[0].trim().equals("E")) {
                Event tempE = new Event(sArr[2].trim(), sArr[3].trim());
                if (sArr[1].trim().equals("done")) {
                    tempE.isDone = true;
                } else if (sArr[1].trim().equals("not done")) {
                    tempE.isDone = false;
                }
                AL.add(tempE);
            } else {
                ToDo tempT = new ToDo(sArr[2].trim());
                if (sArr[1].trim().equals("done")) {
                    tempT.isDone = true;
                } else if (sArr[1].trim().equals("not done")) {
                    tempT.isDone = false;
                }
                AL.add(tempT);
            }
        }
    }
    public static void main(String[] args) {
        File file = new File("src/main/data/DuckieData.txt");
        ArrayList<Task> arr = new ArrayList<>();

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            LoadTasks(arr, file);
        } catch (Exception e) {
            e.getStackTrace();
        }

        System.out.println("yo im Duckie! quack quack");
        System.out.println("what can i do for ya ;)");

        String line = ":) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :)";
        System.out.println(line);

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            try {
                String input = sc.nextLine();
                input = input.trim();
                String[] str = input.split(" ", 2);

                if (input.equalsIgnoreCase("todo") ||
                        input.equalsIgnoreCase("event") ||
                        input.equalsIgnoreCase("deadline")) {
                    throw new DuckieException("there is nothing to do!");
                }

                if (input.equalsIgnoreCase("bye")) {
                    System.out.println(line);
                    System.out.println("goodbye! c ya soon ;)");
                    System.out.println(line);
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    System.out.println(line);
                    if (arr.size() == 0) {
                        System.out.println("there are no tasks in your list!");
                    } else if (arr.size() == 1) {
                        System.out.println("get to work! this is the task in your list: ");
                    } else {
                        System.out.println("get to work! these are the tasks in your list: ");
                    }
                    for (int i = 0; i < arr.size(); i++) {
                        System.out.println(i + 1 + "." + arr.get(i).toString());
                    }
                    System.out.println(line);

                } else if (str[0].equalsIgnoreCase("done")) {
                    System.out.println(line);
                    int taskNum = Integer.parseInt(str[1]);
                    Task temp = arr.get(taskNum - 1);
                    temp.markDone();
                    System.out.println("cool! this task is marked as done:");
                    System.out.println(temp.toString());
                    System.out.println(line);

                } else if (str[0].equalsIgnoreCase("todo")) {
                    System.out.println(line);
                    ToDo tempT = new ToDo(str[1]);
                    arr.add(tempT);
                    System.out.println("ok! i've added this task:");
                    System.out.println(tempT.toString());

                    if (arr.size() == 0) {
                        System.out.println("there are no tasks in your list!");
                    } else if (arr.size() == 1) {
                        System.out.println("you have " + arr.size() + " task in your list! keep working!");
                    } else {
                        System.out.println("you have " + arr.size() + " tasks in your list! keep working!");
                    }
                    System.out.println(line);

                } else if (str[0].equalsIgnoreCase("event")) {
                    if (!str[1].contains("/at")) {
                        throw new DuckieException("oops! please specify the time of your event using '/at'");
                    }

                    System.out.println(line);
                    String[] strE = str[1].split("/at", 2);
                    Event tempE = new Event(strE[0], strE[1]);
                    arr.add(tempE);
                    System.out.println("ok! i've added this task:");
                    System.out.println(tempE.toString());

                    if (arr.size() == 0) {
                        System.out.println("there are no tasks in your list!");
                    } else if (arr.size() == 1) {
                        System.out.println("you have " + arr.size() + " task in your list! keep working!");
                    } else {
                        System.out.println("you have " + arr.size() + " tasks in your list! keep working!");
                    }
                    System.out.println(line);

                } else if (str[0].equalsIgnoreCase("deadline")) {
                    if (!str[1].contains("/by")) {
                        throw new DuckieException("oops! please specify deadline using '/by'");
                    }

                    System.out.println(line);
                    String[] strD = str[1].split("/by", 2);
                    Deadline tempD = new Deadline(strD[0], strD[1]);
                    arr.add(tempD);
                    System.out.println("ok! i've added this task:");
                    System.out.println(tempD.toString());

                    if (arr.size() == 0) {
                        System.out.println("there are no tasks in your list!");
                    } else if (arr.size() == 1) {
                        System.out.println("you have " + arr.size() + " task in your list! keep working!");
                    } else {
                        System.out.println("you have " + arr.size() + " tasks in your list! keep working!");
                    }
                    System.out.println(line);

                } else if (str[0].equalsIgnoreCase("delete")) {
                    System.out.println(line);
                    System.out.println("ok! i've deleted this task: ");
                    int deleteNum = Integer.parseInt(str[1]);
                    System.out.println(arr.get(deleteNum - 1).toString());
                    arr.remove(deleteNum - 1);

                    if (arr.size() == 0) {
                        System.out.println("yay! you are done!");
                    } else if (arr.size() == 1) {
                        System.out.println("yay! you have " + arr.size() + " task left to do!");
                    } else {
                        System.out.println("yay! you have " + arr.size() + " tasks left to do!");
                    }
                } else {
                    throw new DuckieException("invalid input! please start with the word 'todo', 'event' or 'deadline',"
                            + " followed by your task.");
                }
            } catch (DuckieException e) {
                System.out.println(e.toString());
            }
        }

        try {
            SaveTasks(arr, file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

/**
 * Task encapsulates a task with String description and boolean isDone.
 * It supports getting the status of the task and marking it as done.
 */
class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Construct a Task object with a given description and isDone set to false.
     * @param description The description of this task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get the current status of the task.
     * @return Returns a cross if done, otherwise whitespace.
     */
    public String getStatus() {
        return (isDone ? "\u2718" : " ");
    }

    public String getIsDone() {
        if (this.isDone) {
            return "done";
        } else {
            return "not done";
        }
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the status of the task to true.
     */
    public void markDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatus() + "] " + description;
    }
}

/**
 * Deadline inherits from Task. It encapsulates a deadline with an additional String by.
 * Deadline is specified by [D].
 */

class Deadline extends Task {
    protected String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

/**
 * ToDo inherits from Task.
 * ToDo is specified by [T].
 */
class ToDo extends Task {
    public ToDo(String description) {

        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

/**
 * Event inherits from Task. It encapsulates an event with an additional String at.
 * Event is specified by [E].
 */
class Event extends Task {
    protected String at;
    public Event (String description, String at) {
        super(description);
        this.at = at;
    }

    public String getAt() {
        return this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

/**
 * DuckieException inherits from Exception class.
 */
class DuckieException extends Exception {
    public DuckieException(String message) {
        super(message);
    }
}