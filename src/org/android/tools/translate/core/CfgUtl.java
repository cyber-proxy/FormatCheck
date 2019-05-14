package org.android.tools.translate.core;

import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CfgUtl {
    private static final String PATH_FORMATTER = "\\values-%s\\strings.xml";
    private final static String PATH_REGREX = "\\values\\strings.xml";

    /**
     * 返回所有的values下strings.xml完整路径列表
     * @param cfgPath 形如：E:\Eclipse\workspace\FormatCheck\src\org\android\tools\translate\cdf\language.json
     * @param rootDirec 形如:"E:\xxx\myPrj\app\src\main\res"
     * @return
     */
    public static List<String> getCfgLanguagePath(String cfgPath, String rootDirec)  {
        List<String> languagePathList = new ArrayList<>();
        StringBuilder languageCfg = new StringBuilder();
        File langaugeConfig = new File(cfgPath);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(langaugeConfig));
            String line = null;
            while ((line = br.readLine()) != null) {
                languageCfg.append(line);
            }
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        JSONObject json = JSONObject.fromObject(languageCfg.toString());

        Iterator<String> iterator = json.keys();
        while (iterator.hasNext()) {
            String key = iterator.next().trim()/* .toLowerCase() */;
            String filePath;
            if ("en".equals(key)) {
                filePath = rootDirec + PATH_REGREX;
            } else {
                filePath = rootDirec + String.format(PATH_FORMATTER, key);
            }
            languagePathList.add(filePath);
        }
        return languagePathList;
    }
}
