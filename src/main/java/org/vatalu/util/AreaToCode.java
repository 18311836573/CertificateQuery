package org.vatalu.util;


import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class AreaToCode {
    private static Map<String, String> citycode;

    static {
        Properties props = new Properties();
        citycode = new HashMap<>();
        InputStream in = null;
        try {
            in = AreaToCode.class.getClassLoader().getResourceAsStream("citycode.properties");
            props.load(in);
            Enumeration<?> en = props.propertyNames();
            while (en.hasMoreElements()) {
                String key = (String) en.nextElement();
                String property = props.getProperty(key);
                citycode.put(key, property);
                System.out.println(key + " " + property);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getCode(String area){
        return citycode.get(area);
    }

}
