package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage{
    private String fileUrl;

    Storage(String fileUrl) {
        this.fileUrl = fileUrl;
    }


    public ArrayList<String> load() throws FileNotFoundException {
        File storageFile = new File(fileUrl);
        Scanner sc = new Scanner(storageFile);
        ArrayList<String> data = new ArrayList<>();
        while (sc.hasNextLine()) {
            data.add(sc.nextLine());
        }
        return data;
    }


    public void store(ArrayList<String> taskList) throws IOException {
        FileWriter fw = new FileWriter(fileUrl);
        String data = taskList.size() == 0 ? "" : taskList.get(0);
        for (int i = 1; i < taskList.size(); i++) {
            data += System.lineSeparator() + taskList.get(i);
        }
        fw.write(data);
        fw.close();
    }

}
/*
todo
Handling file/folder not found exception
Handling IOException
*/