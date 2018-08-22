package utils;

import org.testng.annotations.DataProvider;
import utils.EnvironmentUtil;

import java.io.File;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by chengzw on 2018/6/10.
 */
public class DataProviderUtil {
    private static ExcelUtil ExcelData = new ExcelUtil();

    @DataProvider
    public static Object[][] TestData(Method method) throws Exception {
        String pathFile = "/Users/chengzw/dxy/idxy_interface/src/test/resources/"+(method.getDeclaringClass().getName().toLowerCase() + "." + method.getName()).replace(".", File.separator) + "-{env}.xlsx";
        String env = System.getenv(EnvironmentUtil.ENV_KEY);
        String loadProperty = pathFile;
        loadProperty = loadProperty.replace("{env}", env);
        List<List<String>> result = ExcelData.readXlsx(loadProperty);
        String[][] res=new String[result.size()][];
        try {
            for(int i=0;i<result.size();i++){
                res[i]=result.get(i).toArray(new String[0]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
