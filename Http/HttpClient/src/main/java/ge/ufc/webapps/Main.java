package ge.ufc.webapps;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class Main
{
    public static void main(String[] args) {
        HttpClientClass httpClientClass = new HttpClientClass();
        httpClientClass.setUrls();
        List<String> servletUrls = HttpClientClass.getServletUrls();
        List<String> old_servletUrls= HttpClientClass.getOld_servletUrls();

        System.err.println("Post");
        httpClientClass.httpPost(servletUrls.get(1), Authorization.NOT_REQUIRED);
        httpClientClass.httpPost(old_servletUrls.get(1), Authorization.REQUIRED);
        System.err.println("Get");
        httpClientClass.httpGet(servletUrls.get(0),Authorization.NOT_REQUIRED);
        httpClientClass.httpGet(old_servletUrls.get(0),Authorization.REQUIRED);
        System.err.println("GetAsync");
        try{
            httpClientClass.httpGetAsync(List.of(new URI(old_servletUrls.get(0)),new URI(servletUrls.get(0))),Authorization.REQUIRED);
        } catch (URISyntaxException ignored){}
        System.err.println("Auth");
        httpClientClass.httpAuth("http://localhost:8080/servlet-with-auth/basicAuth");
        System.err.println("URLCon");
        httpClientClass.httpGetOld(old_servletUrls.get(0),Authorization.REQUIRED);
        httpClientClass.httpGetOld(servletUrls.get(0),Authorization.NOT_REQUIRED);

        //Errors
//        httpClientClass.httpGet(old_servletUrls.get(1), Authorization.NOT_REQUIRED);
//        httpClientClass.httpGet("http://localhost:8080/old-servlet/web?p=50", Authorization.REQUIRED);
    }
}
