import java.io.FileWriter;
import java.io.IOException;
/*
Taken from 2103T file access demo
 */
public class FileWriting {

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static void writeToFile( String string) {
        String file2 = "data/duke.txt";
        try {
            writeToFile(file2,string );
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

}