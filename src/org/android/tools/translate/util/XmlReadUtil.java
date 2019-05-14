package org.android.tools.translate.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.*;

public class XmlReadUtil {
    private static final String FORMAT_TAG = "$";

    public static Set<FormatInfo> getFormatKeyList(String file) {
        Set<FormatInfo> keySet = new HashSet<>();
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(new File(file));
        } catch (DocumentException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        Element root = document.getRootElement();
        List<Element> elements = root.elements();
        for (Element element : elements) {
            String key = element.attributeValue("name");
            String value = element.getText();
            if (value.contains(FORMAT_TAG))
                keySet.add(new FormatInfo(getCharCount(value, FORMAT_TAG), key, value));
        }
        return keySet;
    }

    public static Set<FormatInfo> getFormatStrList(String fileName, Set<FormatInfo> keySet) {
        Set<FormatInfo> formatInfos = new HashSet<>();
        SAXReader reader = new SAXReader();
        Document document = null;
        boolean fileExists = false;
        try {
            document = reader.read(new File(fileName));
            fileExists = true;
        } catch (Exception e){
            Debug.error(fileName + " don't exists.");
        }
        if (fileExists) {
            Element root = document.getRootElement();
            List<Element> elements = root.elements();
            for (Element element : elements) {
                String key = element.attributeValue("name");
                String value = element.getText();
                for (FormatInfo formatInfo : keySet){
                    if (formatInfo.forMatKey.equals(key)){
                        int count = getCharCount(value, FORMAT_TAG);
                        if (count != formatInfo.formatCount){
                            Debug.error("format miss: " + value + " key->" + key + " english:\t" + formatInfo.formatStr);
                        }else {
                            formatInfos.add(new FormatInfo(count, key, value));
                        }
                        break;
                    }
                }
            }
        }
        return formatInfos;
    }

    public static class FormatInfo{
        public int formatCount;
        public String forMatKey;
        public String formatStr;

        public FormatInfo(int formatCount, String forMatKey, String formatStr) {
            this.formatCount = formatCount;
            this.forMatKey = forMatKey;
            this.formatStr = formatStr;
        }
    }

    private static int getCharCount(String str, String tag){
        int index = 0;
        int count = 0;
        while ((index = str.indexOf(tag)) != -1){
            str = str.substring(index + tag.length());
            count++;
        }
        return count;
    }
}
