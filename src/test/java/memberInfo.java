import io.restassured.response.*;
import login.LoginApp;
import org.testng.annotations.*;
import utils.PropertiesUtils;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

/**
 * Created by chengzw on 2018/8/22.
 */
public class memberInfo extends LoginApp {
    private static String class_url = PropertiesUtils.getProperty("class_url");
    @BeforeClass
    public static void setUpClass(){
        useRelaxedHTTPSValidation();
    }
    @Test
    public void member(){
        Response response =
                given()
                        .spec(requestSpec)
                .when()
                        .log().all()
                        .get(class_url+"/japi/platform/122120016")
                        .prettyPeek()
                ;
        response.then().assertThat().body(matchesJsonSchemaInClasspath("member.json"));
    }
}
