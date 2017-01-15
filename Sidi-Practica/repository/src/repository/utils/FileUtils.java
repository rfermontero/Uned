package repository.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * File utils class.
 * Provides utility method for files.
 */
public class FileUtils {

	/**
	 * Creates a output stream for a specified file.
	 *
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static OutputStream getOutputStreamFor(File file) throws IOException {
		FileOutputStream fous = null;
		if (file.createNewFile()) {
			fous = new FileOutputStream(file);
		}
		return fous;
	}

	/**
	 * Return a file with folderName as parent folder tree and fileName as file name.
	 *
	 * @param folderName
	 * @param fileName
	 * @return
	 */
	public static File getFileFor(String folderName, String fileName) {
		return new File(new File("").getAbsoluteFile() + File.separator + folderName + File.separator + fileName);
	}
}
