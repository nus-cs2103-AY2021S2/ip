package duke;

import duke.tasks.Task;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;

import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.IOException;

public class Storage {
    protected String filePath;

    public Storage(String filePath)  {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadAllTasks() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        File f = new File(filePath);

        if(f.exists() && !f.isDirectory()) {
            try {
                for(String line :  Files.readAllLines(Paths.get(filePath))) {
                    String[] dataArr = line.split(" \\| ");
                    String typeTask = dataArr[0];

                    switch (typeTask) {
                    case "T":
                        ToDo td = new ToDo(dataArr[2]);
                        if (dataArr[1].equals("1")) {
                            td.markAsDone();
                        }

                        taskList.add(td);
                        break;

                    case "D":
                        Deadline d = new Deadline(dataArr[2], dataArr[3]);

                        if (dataArr[1].equals("1")) {
                            d.markAsDone();
                        }

                        taskList.add(d);
                        break;

                    case "E":
                        Event e = new Event(dataArr[2], dataArr[3]);

                        if (dataArr[1].equals("1")) {
                            e.markAsDone();
                        }
                        taskList.add(e);
                        break;
                    default:
                        throw new DukeException("Line cannot be read");
                    }
                }
                System.out.println("Successfully loaded all tasks");
                return taskList;
            } catch (IOException e) {
                System.out.println("Error reading file" + e);
                throw new DukeException("Error reading file");
            }
        } else {
            try {
                f.getParentFile().mkdirs();
                f.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file" + e);
                throw new DukeException("Error reading file");
            }
        }
        return new ArrayList<>();
    }

    public void addNewDataToFile(String taskType, String done, String description, String deadline) {
        try {
            FileWriter mw = new FileWriter(filePath, true);
            switch (taskType) {
            case "T":
                mw.write(taskType + " | " + done + " | " + description + "\n");
                mw.close();
                break;
            case "D":
            case "E":
                mw.write(taskType + " | " + done + " | " + description + " | " + deadline + "\n");
                mw.close();
            }
        } catch (IOException e) {
            System.out.println("Encountered problem writing to file" + e);
        }
    }

    public void editDataInFile(int taskNumber, String taskType, String done,
                                      String description, String deadline, int total) {
        try {

            BufferedReader br = new BufferedReader(new FileReader(filePath));

            //String buffer to store contents of the file
            StringBuffer sb = new StringBuffer("");

            //Keep track of the line number
            int lineNumber = 1;
            String line;
            System.out.println("total: " + total);
            System.out.println("taskNumber: " + taskNumber);

            while ((line = br.readLine()) != null) {
                //Store each valid line in the string buffer
                if (lineNumber != taskNumber && lineNumber != total) {
                    System.out.println("linenumber: " + lineNumber + " line: " + line);
                    sb.append(line + "\n");
                } else if (lineNumber != taskNumber) {
                    System.out.println("linenumber: " + lineNumber + " line: " + line);
                    sb.append(line);
                } else {
                    String data;
                    if (taskType.equals("T")) {
                        data = taskType + " | " + done + " | " + description;
                    } else {
                        data = taskType + " | " + done + " | " + description + " | " + deadline;
                    }
                    System.out.println("lineNumber: " + lineNumber + " line: " + line);

                    if (lineNumber == total) {
                        sb.append(data);
                    } else {
                        sb.append(data + "\n");
                    }
                }
                lineNumber++;
            }
            br.close();

            FileWriter fw = new FileWriter(new File(filePath));
            //Write entire string buffer into the file
            fw.write(sb.toString());
            fw.close();
        }
        catch (IOException e) {
            System.out.println("Error editing file: " + e.getMessage());
        }
    }

    public void removeDataInFile(int taskNumber, int total) {
        try {
            String currentDir = System.getProperty("user.dir");
            String filePath = currentDir + File.separator + "data" + File.separator + "duke.txt";
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            //String buffer to store contents of the file
            StringBuffer sb = new StringBuffer("");

            //Keep track of the line number
            int lineNumber = 1;
            String line;

            while ((line = br.readLine()) != null) {
                //Store each valid line in the string buffer
                if (lineNumber != taskNumber && lineNumber == total + 1) {
                    sb.append(line);
                } else if (lineNumber != taskNumber) {
                    sb.append(line + "\n");
                }
                lineNumber++;
            }
            br.close();

            FileWriter fw = new FileWriter(new File(filePath));
            //Write entire string buffer into the file
            fw.write(sb.toString());
            fw.close();
        }
        catch (Exception ex) {
            System.out.println("Error deleting line: " + ex.getMessage());
        }
    }
}
