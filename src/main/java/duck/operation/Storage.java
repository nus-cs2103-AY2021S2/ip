package duck.operation;

import duck.task.TaskList;

import java.io.*;

public class Storage {
    private File fileOfData;
    public Storage(String filePath) throws IOException {
        this.fileOfData = new File(filePath);
        if (!fileOfData.isFile()) {
            fileOfData.createNewFile();
        }
    }

    public String[] load() throws IOException {
        FileInputStream inStream = new FileInputStream(fileOfData);
        InputStreamReader reader = new InputStreamReader(inStream);
        BufferedReader br = new BufferedReader(reader);
        String[] data = new String[1000];
        int lines = 0;
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            data[lines] = line;
            lines++;
        }
        br.close();
        return data;
    }
    public void updateFile(TaskList tasks) throws IOException {
        FileOutputStream outStream = new FileOutputStream(fileOfData);
        OutputStreamWriter writer = new OutputStreamWriter(outStream, "UTF-8");
        writer.write("");
        writer.flush();
        for (int i = 0; i < tasks.getSizeOfTasks(); i++) {
            writer.write(tasks.getTask(i).getTaskInfoOfFile() + "\n");
        }
        writer.flush();
    }

}
