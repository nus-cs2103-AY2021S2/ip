package justin;



import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This class creates a storage class that hold information of the task list
 * @author Goh Wei Kiat aka github : mrweikiat
 * @version CS2103T AY20/21 Semester 2, Individual Project 'IP'
 */


public class Storage {

    protected String filePath;
    protected File file;

    /**
     * This method takes in a String filepath and creates a new Storage class with a file that contains path to
     * the txt file storing the list information
     *
     * @param filePath The absolute filepath of justin.txt
     */

    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);

    }

    /**
     * This method calls to a storage class and return
     * the absolute file path in String
     *
     * @return String containing abosulute file path to justin.txt
     */

    public String getFilePath() {
        return this.filePath;
    }

    /**
     * This method taks in the filepath containing list of tasks in .txt and
     * returns a TaskList contain all the different classes of Tasks
     *
     * @param filePath The absolute filepath of justin.txt
     * @return TaskList This returns a loaded Tasklist
     */

    public TaskList loadFile(String filePath)  {

        TaskList holder = new TaskList();
        File file = new File(filePath);

        if (file.exists()) {
            try {
                for (String line : Files.readAllLines(Paths.get(filePath))) {

                    //System.out.println(line); // for debugging

                    // create an linkedlist to store the split lines
                    String[] splits = line.split("\\|", 5);

                    // splits[0] is the holder
                    // T is t0do
                    // E is event
                    // D is deadline
                    // else just a vanilla task

                    if (splits[0].equals("T")) {
                        Todo td = new Todo(splits[2]);
                        if (splits[1].equals("1")) {
                            td.markAsDone();
                        }
                        holder.getList().add(td);
                    } else if (splits[0].equals("D")) {
                        Deadline dl = new Deadline(splits[2], splits[3]);
                        if (splits[1].equals("1")) {
                            dl.markAsDone();
                        }
                        holder.getList().add(dl);
                    } else if (splits[0].equals("E")) {
                        Event e = new Event(splits[2], splits[3]);
                        if (splits[1].equals("1")) {
                            e.markAsDone();
                        }
                        holder.getList().add(e);
                    }
                    else {
                        Task t = new Task(splits[1]);
                        holder.getList().add(t);
                    }


                }
            } catch (IOException e) {
                System.out.println("file not found");
            }
        } else {

            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
                System.out.println("New file created");
            } catch (IOException e) {
                System.out.println("Error creating file");
            }

        }

        return holder;

    }

    /**
     * This method takes in the absolute file path of justin.txt and the current tasklist
     * and attempts to save the information of each tasks into the .txt file
     *
     * @param tasks The TaskList class containing arraylist of Tasks
     * @param filePath The absolute filepath of justin.txt
     */


    // method to save contents of list onto justin.txt
    public static void saveFile(TaskList tasks, String filePath) {

        try {

            StringBuffer sb = new StringBuffer(""); // create empty sb to store content of list

            for (int i = 0; i < tasks.getList().size(); i++) {

                String holder = ""; // to hold content of current line to be stored into sb

                if (tasks.getList().get(i) instanceof Todo) { // is a t0do class
                    if (tasks.getList().get(i).isDone) {
                        holder = "T" + "|" + "1" + "|" + tasks.getList().get(i).description;
                    } else {
                        holder = "T" + "|" + "0" + "|" + tasks.getList().get(i).description;
                    }
                } else if (tasks.getList().get(i) instanceof Deadline) { // is a deadline class
                    if (tasks.getList().get(i).isDone) {
                        holder = "D" + "|" + "1" + "|" + tasks.getList().get(i).description +
                                "|" + ((Deadline) tasks.getList().get(i)).by;
                    } else {
                        holder = "D" + "|" + "0" + "|" + tasks.getList().get(i).description +
                                "|" + ((Deadline) tasks.getList().get(i)).by;
                    }
                } else if (tasks.getList().get(i) instanceof Event) {
                    if (tasks.getList().get(i).isDone) {
                        holder = "E" + "|" + "1" + "|" + tasks.getList().get(i).description +
                                "|" + ((Event) tasks.getList().get(i)).dateTime;
                    } else {
                        holder = "E" + "|" + "1" + "|" + tasks.getList().get(i).description +
                                "|" + ((Event) tasks.getList().get(i)).dateTime;
                    }
                } else {
                    // vanilla event
                    if (tasks.getList().get(i).isDone) {
                        holder = "1" + "|" + tasks.getList().get(i).description;
                    } else {
                        holder = "1" + "|" + tasks.getList().get(i).description;
                    }

                }

                if (i < tasks.getList().size()) {
                    sb.append((holder + "\n"));
                } else {
                    sb.append(holder);
                }

            }

            // write content of sb into file

            FileWriter myWriter = new FileWriter(new File(filePath));
            myWriter.write(sb.toString());
            myWriter.close();

        } catch(IOException e) {
            System.out.println("Unable to write to file justin.txt");
        }
    }



}