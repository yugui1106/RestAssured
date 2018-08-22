package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by chengzw on 2018/8/4.
 */
public class PropertiesUtils {
    private static String propertiesName = "url-{env}.properties";
    private static Properties prop = new Properties();
    static {
        load();
    }

    protected static void load() {
        String env = System.getenv(EnvironmentUtil.ENV_KEY);
        String loadProperty = propertiesName;
        loadProperty = loadProperty.replace("{env}", env);
        InputStream is = null;
        try{
            is = PropertiesUtils.class.getClassLoader().getResourceAsStream(loadProperty);
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getProperty(String key){
        return prop.getProperty(key);
    }
}
