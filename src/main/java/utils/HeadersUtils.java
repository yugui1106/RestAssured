package utils;

import io.restassured.http.Header;
import io.restassured.http.Headers;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by chengzw on 2018/8/4.
 */
public class HeadersUtils {
    protected static String AC="4124c5f1-1031-4fda-b06f-a88ac5ad8d9f";
    protected static String MC="8d9576f998bfae4ea6f34d236fec7596ec41e286";
    protected static Headers APP_HEADER;

    private String url;
    private String token="";

    public HeadersUtils() {

    }

    public HeadersUtils(String url, String token) {
        this.url = url;
        this.token = token;
    }

    public String getUrl() {
        return url;
    }

    public String getToken() {
        return token;
    }

    public Headers build() {
        System.setProperty(EnvironmentUtil.ENV_KEY, EnvironmentUtil.PROFILE_DEV);
        try {
            URL url = new URL(getUrl());
            return new Headers(
                    new Header("app-ac",AC),
                    new Header("app-mc",MC),
                    new Header("Referer", getUrl()),
                    new Header("Host",url.getHost()),
                    new Header("DXY-AUTH-TOKEN",getToken())
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
