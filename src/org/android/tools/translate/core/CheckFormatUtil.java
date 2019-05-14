package org.android.tools.translate.core;

import org.android.tools.translate.util.Debug;
import org.android.tools.translate.util.XmlReadUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

	public static void checkStrFormat(Set<XmlReadUtil.FormatInfo> formatMap) {
		for (XmlReadUtil.FormatInfo entry : formatMap) {
			String formatStr = entry.formatStr;
//			Debug.print("check->" + formatStr);
			try {
				check(formatStr, "1st", "2nd", "3rd");
			} catch (Exception e) {
				try {
					check(formatStr, 1, 2, 3);
				} catch (Exception ee) {
					Debug.error("fatal mistake, error format->\t" + formatStr + " key->" + entry.forMatKey);
					continue;
				}
			}
			int index = formatStr.indexOf("$");
			if (index > 0) {
				if (formatStr.charAt(index - 1) == ' ' || formatStr.charAt(index + 1) == ' ') {
					Debug.error("fatal mistake, error format->\t" + formatStr + " key->" + entry.forMatKey);
				}
			}else {
				Debug.error("fatal mistake, format missed on:\t" + formatStr + " key->" + entry.forMatKey);
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
