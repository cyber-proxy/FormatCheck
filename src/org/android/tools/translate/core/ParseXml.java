package org.android.tools.translate.core;

import org.android.tools.translate.util.Debug;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class ParseXml {
    private static final String FORMAT_TAG = "%";
    private static final String COLOR_TAG = "color";

    private List<String> mFilePathList = new ArrayList<>();
    private List<String> mFormatStrList = new ArrayList<>();
    private List<String> mFormatStrKeyList = new ArrayList<>();
    private String cfgPath = "";
    private String rootDirec = "";

    public ParseXml(String languageFile, String directory) {
        cfgPath = languageFile;
        rootDirec = directory;
    }

    public void checkformat() {
        mFilePathList = CfgUtl.getCfgLanguagePath(cfgPath, rootDirec);
        checkFormatInternal();
    }

    private void checkFormatInternal() {
        for (String xmlPath : mFilePathList) {
            Debug.print("check format->" + xmlPath);
            parseFile(xmlPath);
        }
        CheckFormatUtil.checkStrFormat(mFormatStrList);
        CheckFormatUtil.checkColorFormat(null);
    }

    private void parseFile(String xmlFile) {
        boolean found = false;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(xmlFile), "UTF-8"));
            found = true;
        } catch (Exception e1) {
            Debug.error(xmlFile + " not found.");
        }
        if (found) {
            try {
                String line = null;
                while ((line = br.readLine()) != null) {
                    if (line.contains(FORMAT_TAG)) {
                        mFormatStrList.add(line);
                    }
                    if (line.contains(COLOR_TAG)) {
                        // TODO: 2019/5/14 检测Color
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
