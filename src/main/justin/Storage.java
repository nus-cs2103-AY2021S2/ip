import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Storage {

    protected String filePath;
    protected File file;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);

    }

    public String getFilePath() {
        return this.filePath;
    }

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

    // method to save contents of list onto justin.txt
    public static void saveFile(TaskList task, String filePath) {

        try {

            StringBuffer sb = new StringBuffer(""); // create empty sb to store content of list

            for (int i = 0; i < task.getList().size(); i++) {

                String holder = ""; // to hold content of current line to be stored into sb

                if (task.getList().get(i) instanceof Todo) { // is a t0do class
                    if (task.getList().get(i).isDone) {
                        holder = "T" + "|" + "1" + "|" + task.getList().get(i).description;
                    } else {
                        holder = "T" + "|" + "0" + "|" + task.getList().get(i).description;
                    }
                } else if (task.getList().get(i) instanceof Deadline) { // is a deadline class
                    if (task.getList().get(i).isDone) {
                        holder = "D" + "|" + "1" + "|" + task.getList().get(i).description + "|" + ((Deadline) task.getList().get(i)).by;
                    } else {
                        holder = "D" + "|" + "0" + "|" + task.getList().get(i).description + "|" + ((Deadline) task.getList().get(i)).by;
                    }
                } else if (task.getList().get(i) instanceof Event) {
                    if (task.getList().get(i).isDone) {
                        holder = "E" + "|" + "1" + "|" + task.getList().get(i).description + "|" + ((Event) task.getList().get(i)).dateTime;
                    } else {
                        holder = "E" + "|" + "1" + "|" + task.getList().get(i).description + "|" + ((Event) task.getList().get(i)).dateTime;
                    }
                } else {
                    // vanilla event
                    if (task.getList().get(i).isDone) {
                        holder = "1" + "|" + task.getList().get(i).description;
                    } else {
                        holder = "1" + "|" + task.getList().get(i).description;
                    }

                }

                if (i < task.getList().size()) {
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