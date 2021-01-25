package duke;

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

	private File data;

	Storage() {
		try {
			Path location = Path.of(Parser.class.getProtectionDomain().getCodeSource().getLocation().toURI());
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

	TaskList load() {
		ArrayList<Task> list = new ArrayList<Task>();
		try {
			BufferedReader br = Files.newBufferedReader(this.data.toPath());
			String line;

			while ((line = br.readLine()) != null) {
				String[] info = line.split("1!1");

				if (info[0].equals("todo")) {
					list.add(new ToDo(info[1], Boolean.parseBoolean(info[2])));
				} else if (info[0].equals("deadline")) {
					list.add(new Deadline(info[1], info[2], Boolean.parseBoolean(info[3])));
				} else if (info[0].equals("event")) {
					list.add(new Event(info[1], info[2], Boolean.parseBoolean(info[3])));
				}
			}
		} catch (IOException e) {

		}
		return new TaskList(list);
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
