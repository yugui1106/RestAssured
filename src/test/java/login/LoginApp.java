package login;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.*;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import utils.PropertiesUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

/**
 * Created by chengzw on 2018/8/4.
 */
public class LoginApp {
    protected static String AC="4124c5f1-1031-4fda-b06f-a88ac5ad8d9f";
    protected static String MC="8d9576f998bfae4ea6f34d236fec7596ec41e286";
    public static String token;
    public static RequestSpecification requestSpec;
    private static String authUrl = PropertiesUtils.getProperty("auth_url");

    @BeforeClass
    public static void login(){
        useRelaxedHTTPSValidation();
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("u", "");
        jsonMap.put("p", "");
        jsonMap.put("ac", AC);
        jsonMap.put("mc", MC);
        Response response = given().params(jsonMap).when().post(authUrl+"/api/login/v2");
        token = response.then().extract().path("token");
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.addHeader("app-ac",AC);
        builder.addHeader("app-mc",MC);
        builder.addHeader("dxy-auth-token",token);
        builder.addHeader("Referer", "https://class.dxy.net");
        builder.addHeader("host","class.dxy.net");
        builder.addParam("ac",AC);
        requestSpec= builder.build();
    }
}
