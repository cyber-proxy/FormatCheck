package org.android.tools.translate.dlg;

import org.android.tools.translate.core.Checker;
import org.android.tools.translate.util.Debug;

public class DetectorDlg {
	public static void show() {
		FileChooseDlg.showDlg(new FileChooseDlg.DetectAction() {
			
			@Override
			public void detector(String resPath, String cfgPath) {
				Debug.print("\tresPath->" +resPath + "\n\tcfg Path->" + cfgPath);
				Checker parseXml = new Checker("E:\\Eclipse\\workspace\\FormatCheck\\src\\org\\mobile\\tsotumu\\cdf\\language.json", resPath);
				parseXml.checkformat();
			}
		});
	}

}
