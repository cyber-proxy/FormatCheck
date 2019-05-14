package org.android.tools.translate;

import org.android.tools.translate.util.Debug;
import org.android.tools.translate.core.Checker;

public class DetectFormat {
	// <font color="#4A90E2">%1$s</font> is safe
	// CheckFormatUtil.check("<font color=\"#4A 90E2\">%1$s</font>", new
	public static void main(String[] args) {
		if (args != null && args.length > 1 && args[0].equals("-path") && args[1].length() > 0) {
			Checker check = new Checker( args[1]);
			check.checkformat();
		}else {
			showHelp();
		}

	}

	private static void showHelp() {
		Debug.print("-path the path of \"res\" directory");
	}
}
