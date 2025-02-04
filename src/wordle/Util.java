package wordle;

public class Util {

	static public void debug(Object value, boolean shouldPrint) {
		if (shouldPrint) {
			System.out.print(value);
		}
	}
	static public void debugf(String format, boolean shouldPrint, Object... args) {
		if (shouldPrint) {
			System.out.printf(format, args);
		}
	}
	static public void debugln(Object value, boolean shouldPrint) {
		if (shouldPrint) {
			System.out.println(value);
		}
	}

	public static String strip(String str) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < str.length(); i++) {
			char letter = str.charAt(i);
			if (isAlpha(letter)) {
				sb.append(letter);
			}
		}

		String cleaned = removeAccents(sb.toString());

		return cleaned.toUpperCase();
	}

	private static boolean isAlpha(char c) {
		return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') ||
				(c >= 'á' && c <= 'ú') || (c >= 'Á' && c <= 'Ú');
	}

	private static String removeAccents(String str) {
		str = str.toLowerCase();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < str.length(); i++) {
			char letter = str.charAt(i);

			switch (letter) {
				case 'á': case 'à': case 'ã': case 'â': case 'ä': case 'å':
					sb.append('a');
					break;
				case 'é': case 'è': case 'ê': case 'ë':
					sb.append('e');
					break;
				case 'í': case 'ì': case 'î': case 'ï':
					sb.append('i');
					break;
				case 'ó': case 'ò': case 'ô': case 'õ': case 'ö':
					sb.append('o');
					break;
				case 'ú': case 'ù': case 'û': case 'ü':
					sb.append('u');
					break;
				default:
					sb.append(letter);
					break;
			}
		}
		return sb.toString();
	}

}
