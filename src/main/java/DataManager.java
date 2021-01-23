import java.io.*;
import java.util.ArrayList;

public class DataManager {
    private String filePath;
    private static final String DELIMITER = " \\| ";

    public DataManager(String filePath) {
        this.filePath = filePath;
    }

    public void writeToFile(ArrayList<Task> tasksList) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < tasksList.size(); i++) {
            sb.append(tasksList.get(i).fileFormat()).append("\n");
        }

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.filePath));
            bw.write(sb.toString());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> readFromFile() {
        ArrayList<Task> tasksList = new ArrayList<>();
        try {
            Task task = null;
            BufferedReader br = new BufferedReader(new FileReader(this.filePath));
            String input = br.readLine();
            while(input != null) {
                String[] inputArr = input.split(DELIMITER);

                try {
                    switch (inputArr[0]) {
                        case "T":
                            task = new Todo(inputArr[2]);
                            break;
                        case "D":
                            task = new Deadline(inputArr[2], inputArr[3]);
                            break;
                        case "E":
                            task = new Event(inputArr[2], inputArr[3]);
                            break;
                    }
                    if(Integer.parseInt(inputArr[1]) == 1) {
                        task.toggleStatus();
                    }
                    tasksList.add(task);
                } catch (InvalidFormatException e) {
                    System.out.println(e.getMessage());
                }
                input = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasksList;
    }
}
