import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

public class Storage {

	public static final String OS = System.getProperty("os.name").toLowerCase(Locale.ROOT);

	public static boolean saveBytes(String pathString, byte[] data) {
		Path path = Paths.get(pathString);
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path.getParent());
				Files.createFile(path);
			} catch (IOException e) {
				return false;
			}
		}
		try (FileOutputStream fileOutputStream = new FileOutputStream(pathString)) {
			fileOutputStream.write(data);
		} catch (IOException ioException) {
			return false;
		}
		return true;
	}

	public static byte[] loadBytes(String path) {
		try (FileInputStream fileInputStream = new FileInputStream(path)) {
			return fileInputStream.readAllBytes();
		} catch (IOException fileNotFoundException) {
			return null;
		}
	}

	public static <T> T deserialize(byte[] data, Class<T> type) {
		Object object;
		try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data)) {
			try (ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {
				object = objectInputStream.readObject();
			} catch (ClassNotFoundException | InvalidClassException classException) {
				return null;
			}
		} catch (IOException ioException) {
			return null;
		}
		return type.cast(object);
	}

	public static <T> byte[] serialize(T object) {
		try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
			try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
				objectOutputStream.writeObject(object);
				objectOutputStream.flush();
				return byteArrayOutputStream.toByteArray();
			}
		} catch (IOException ioException) {
			return null;
		}
	}
}
