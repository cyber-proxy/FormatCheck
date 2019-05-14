package org.android.tools.translate.core;

import org.android.tools.translate.util.Debug;
import org.android.tools.translate.util.XmlReadUtil;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Checker {
    private static final String COLOR_TAG = "color";

    private List<String> mFilePathList = new ArrayList<>();
    private Set<XmlReadUtil.FormatInfo> mFormatStrKeySet = Collections.EMPTY_SET;
    private String rootDirec = "";

    public Checker(String directory) {
        rootDirec = directory;
    }

    public void checkformat() {
        mFilePathList = CfgUtl.getStringXmlFilePathList(rootDirec);
        mFormatStrKeySet = XmlReadUtil.getFormatKeyList(CfgUtl.getEngXmlPath(rootDirec));
        checkFormatInternal();
    }

    private void checkFormatInternal() {
        for (String xmlPath : mFilePathList) {
            Debug.print("check format->" + xmlPath);
            Set<XmlReadUtil.FormatInfo> formatInfos = XmlReadUtil.getFormatStrList(xmlPath, mFormatStrKeySet);
            if (formatInfos != null && !formatInfos.isEmpty()) {
                CheckFormatUtil.checkStrFormat(formatInfos);
            }
        }
    }

}
