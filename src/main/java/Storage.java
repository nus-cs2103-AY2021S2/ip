import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {

	public Storage() {
		try {
            File dir = new File("tasklist");
            dir.mkdirs();
            File f = new File(dir, "mytasks.txt");
            f.createNewFile();
        } catch (IOException err) {
            err.printStackTrace();
        }
	}
}