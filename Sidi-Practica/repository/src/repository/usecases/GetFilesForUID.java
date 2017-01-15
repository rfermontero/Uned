package repository.usecases;

import java.io.File;
import java.rmi.server.UID;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Get files with UIds use case
 */
public class GetFilesForUID {
	public List<String> execute(UID uid) {
		File baseFolderFile = new File(new File(".").getAbsoluteFile() + File.separator + uid.toString());
		if (baseFolderFile.exists()) {
			return Arrays.stream(baseFolderFile.listFiles()).map(File::getName).collect(Collectors.toList());
		}
		return Collections.emptyList();
	}
}
