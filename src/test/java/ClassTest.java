import io.restassured.response.Response;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
/**
 * Created by chengzw on 2018/8/4.
 */
public class ClassTest {
    public static String token;

    @BeforeClass
    public static void setUpClass(){
        useRelaxedHTTPSValidation();
    }
    @Test
    public void testLogin(){
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("u", "");
        jsonMap.put("p", "");
        jsonMap.put("ac", "4124c5f1-1031-4fda-b06f-a88ac5ad8d9f");
        jsonMap.put("mc", "8d9576f998bfae4ea6f34d236fec7596ec41e286");
        Response res =
                given()
                        .params(jsonMap)
                .when()
                        .post("https://auth.dxy.net/api/login/v2");
        token = res.then().extract().path("token");
        System.out.println(token);
        Response response =
                given()
                        .header("DXY-AUTH-TOKEN",token)
                        .header("Host","class.dxy.net")
                        .header("Referfer","https://class.dxy.net")
                        .header("app-ac", "4124c5f1-1031-4fda-b06f-a88ac5ad8d9f")
                        .header("app-mc", "8d9576f998bfae4ea6f34d236fec7596ec41e286")
                        .param("showStatus",2)
                .when()
                        .log().all()
                        .get("https://class.dxy.net/japi/platform/122120023")
                ;
        response.prettyPeek();
    }

}
