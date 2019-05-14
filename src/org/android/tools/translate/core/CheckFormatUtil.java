package org.android.tools.translate.core;

import org.android.tools.translate.util.Debug;

import java.util.ArrayList;
import java.util.List;

/**
 * <font color="#4A90E2">%1$s</font> is safe
 * @author LC
 *
 */
public class CheckFormatUtil {
	private static void check(String formatStr, String... args) {
		String.format(formatStr, args);
	}

	private static void check(String formatStr, Integer... args) {
		String.format(formatStr, args);
	}

	public static void checkStrFormat(List<String> formatList) {
		for (String formatStr :  formatList) {
			try {
				check(formatStr, "1st", "2nd", "3rd");
			} catch (Exception e) {
				try {
					check(formatStr, 1, 2, 3);
				} catch (Exception ee) {
					Debug.error("Error: \t" + formatStr);
					continue;
				}
			}
			int index = formatStr.indexOf("$");
			if (formatStr.charAt(index - 1) ==  ' ' || formatStr.charAt(index + 1) == ' ') {
				Debug.error("error format->\t" + formatStr);
			}
		}
	}

	public static void checkColorFormat(ArrayList<String> formatList) {
		String colorString = "#F00000000";
		if (colorString.length() > 6) {
			Debug.print("Error: " + colorString);
		}
	}
}
