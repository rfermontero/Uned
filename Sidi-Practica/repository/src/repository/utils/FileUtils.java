package repository.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileUtils {

	public static OutputStream getOutputStreamFor(File file) throws IOException {
		FileOutputStream fous = null;
		if (file.createNewFile()) {
			fous = new FileOutputStream(file);
		}
		return fous;
	}

	public static File getFileFor(String folderName, String fileName) {
		return new File(new File("").getAbsoluteFile() + File.separator + folderName + File.separator + fileName);
	}
}
