import java.util.ArrayList;
import java.util.Scanner;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.net.URISyntaxException;
import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;

class Storage {

	//private final Path path;
	private File data;

	Storage() {
		try {
			Path location = Path.of(Duke.class.getProtectionDomain().getCodeSource().getLocation().toURI());
			Path dir = location.resolve("data");
			Path file = dir.resolve("list.txt");

			Files.createDirectories(dir);

			boolean fileExists = java.nio.file.Files.exists(file);
			if (!fileExists) {
				Files.createFile(file);
			}

			this.data = new File(file.toUri());

		} catch (URISyntaxException e) {

		} catch (IOException e) {

		}
	}

	File load() {
		return this.data;
	}

	void save(ArrayList<Task> mem) {
		
		ArrayList<String> list = new ArrayList<String>();

		for (Task t : mem) {
			list.add(t.saveName());
		}
		
		try {
			Files.write(this.data.toPath(), list);
		} catch (IOException e) {
			
		}
	}
}
