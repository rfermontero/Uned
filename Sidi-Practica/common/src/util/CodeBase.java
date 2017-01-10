package util;

public class CodeBase {
	private static final String CODEBASE = "java.rmi.server.codebase";

	public static void set(Class<?> clazz) {
		String root = clazz.getProtectionDomain().getCodeSource().getLocation().toString();
		String path = System.getProperty(CODEBASE);
		if (path != null && !path.isEmpty()) {
			root = path + " " + root;
		}
		System.setProperty(CODEBASE, root);
	}
}
