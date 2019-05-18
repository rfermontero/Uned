package compiler.code;

public class Label {
	private static int i = 0;
	
	public static String getLabel() {
		String label = "txt" + i;
		i++;
		return label;
	}
}