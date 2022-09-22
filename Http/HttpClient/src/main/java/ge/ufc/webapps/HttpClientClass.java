package ge.ufc.webapps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.SECONDS;


public class HttpClientClass {

    private static final Logger logger = LogManager.getLogger();
    private static ServerURL url = ServerURL.getServerURl();
    private static List<String> servletUrls = new ArrayList<>();
    private static List<String> old_servletUrls = new ArrayList<>();


    public void httpGet(String url,Authorization authorization)
    {
        logger.debug("Get started");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = null;
        if(authorization.equals(Authorization.NOT_REQUIRED)) {
            try {
                request = HttpRequest.newBuilder(new URI(url)).timeout(Duration.of(HttpClientClass.url.getTimeOut(), SECONDS)).version(HttpClient.Version.HTTP_2).GET().build();
            } catch (URISyntaxException e) {
                logger.error(e.getMessage());
                throw new RuntimeException(e);
            }
        }else if(authorization.equals(Authorization.REQUIRED))
        {
            try {
                request = HttpRequest.newBuilder(new URI(url)).timeout(Duration.of(HttpClientClass.url.getTimeOut(), SECONDS))
                        .version(HttpClient.Version.HTTP_2)
                        .header("username","admin")
                        .header("password","password").GET().build();
            } catch (URISyntaxException e) {
                logger.error(e.getMessage());
                throw new RuntimeException(e);
            }
        }
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        logger.debug("Get_URL: {}", url);
        logger.trace("Get_Response_Body: {}", () -> response.body());
        logger.debug("Get_Response_Status_Code: {}", () -> response.statusCode());
        logger.trace("Get ended!");

    }
    public void httpGetAsync(List<URI> uris,Authorization authorization)
    {
        logger.debug("GetAsync started");
        HttpClient client = HttpClient.newHttpClient();
        List<CompletableFuture<HttpResponse<String>>> futures = null;
        if(authorization.equals(Authorization.NOT_REQUIRED)) {
            futures = uris.stream().map(uri -> client
                            .sendAsync(HttpRequest.newBuilder(uri).GET().build(), HttpResponse.BodyHandlers.ofString()))
                    .collect(Collectors.toList());
        }else
        {
            futures = uris.stream().map(uri -> client
                            .sendAsync(
                                    HttpRequest.newBuilder(uri)
                                            .header("username","admin")
                                            .header("password","password")
                                            .GET().build(), HttpResponse.BodyHandlers.ofString()))
                    .collect(Collectors.toList());
        }
        CompletableFuture.allOf(futures.toArray(CompletableFuture<?>[]::new)).join();
        futures.forEach(f -> {
            HttpResponse<String> response;
            try {
                response = f.get();
            } catch (InterruptedException | ExecutionException e) {
                logger.error(e.getMessage());
                throw new RuntimeException(e);
            }
            logger.debug("GetAsync_Response_URL: {}", () -> response.uri());
            logger.trace("http_Get_Response_Body: {}", () -> response.body());
            logger.debug("http_Get_Response_Status_Code: {}", () -> response.statusCode());

        });

        logger.trace("GetAsync ended");
    }
    public void httpPost(String url, Authorization authorization)
    {
        logger.debug("Post started");
        HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
        HttpRequest request = null;
        String requestBody = "";
        if (authorization.equals(Authorization.REQUIRED)) {
            try {
                requestBody = "{\"a\":3,\"b\":4,\"c\":5}";
                request = HttpRequest.newBuilder(new URI(url)).timeout(Duration.of(HttpClientClass.url.getTimeOut(), SECONDS))
                        .version(HttpClient.Version.HTTP_2).headers("username", "admin", "password", "password").POST(HttpRequest.BodyPublishers.ofString(requestBody)).build();

            } catch (URISyntaxException e) {
                logger.error(e.getMessage());
                throw new RuntimeException(e);
            }
        } else if (authorization.equals(Authorization.NOT_REQUIRED)) {

            requestBody =
                    "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
                            "<rectangle> " +
                            "<width>200.0</width>" +
                            "<length>120.0</length>" +
                            "</rectangle>";
            try {
                request = HttpRequest.newBuilder(new URI(url))
                        .version(HttpClient.Version.HTTP_2).POST(HttpRequest.BodyPublishers.ofString(requestBody)).build();
            } catch (URISyntaxException e) {
                logger.error(e.getMessage());
                throw new RuntimeException(e);
            }
        }

        logger.debug("Post_URL: {}", url);
        logger.debug("Post_Body: {}", requestBody);
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        logger.trace("Post_Response_Body: {}", () -> response.body());
        logger.debug("Post_Response_Status_Code: {}", () -> response.statusCode());
        logger.trace("Post ended!");
    }
    public void httpAuth(String url)
    {

        logger.debug("Auth started");
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }


        Authenticator auth = new Authenticator(){
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("tomcat", "password".toCharArray());
            }
        };
        Authenticator.setDefault(auth);
        try {
            System.out.println("Response -> " + FullResponseBuilder.getFullResponse(connection));
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        connection.setRequestProperty("Authorization", "Basic " + Base64.getEncoder().encodeToString(("tomcat:password").getBytes()));
        try {
            System.out.println("Response -> " + FullResponseBuilder.getFullResponse(connection));
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        logger.debug("Auth ended");
    }
    public  void httpGetOld(String url,Authorization authorization)
    {
        logger.trace("Get started!");
        HttpURLConnection con;
        try {
            con = (HttpURLConnection) new URL(url).openConnection();
            con.setRequestMethod("GET");
        }
        catch (IOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        if(authorization.equals(Authorization.REQUIRED))
        {
            con.setRequestProperty("username", "admin");
            con.setRequestProperty("password", "password");
        }
        logger.debug("Get_URL: {}", () -> con.getURL());
        logger.debug("Get_Response_Status_Code: {}", () -> {
            try {
                return con.getResponseCode();
            } catch (IOException e) {
                logger.error(e.getMessage());
                throw new RuntimeException(e);
            }
        });
        BufferedReader br ;
        try {
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        logger.debug("Get_Response_Body: {}", () -> br.lines().reduce("\n",(a,b) -> a+b));
        logger.trace("Get ended!");

    }
    public void setUrls()
    {
        String[] urls = getUrl().getUrls();
        for (int i = 0; i < urls.length; i++) {
            if(urls[i].contains("/servlet/")) {

                if(urls[i].contains("?s="))
                    servletUrls.add(0,urls[i]);
                else
                    servletUrls.add(urls[i]);
            }
            if(urls[i].contains("/old-servlet/")) {
                if(urls[i].contains("?p="))
                    old_servletUrls.add(0,urls[i]);
                else
                    old_servletUrls.add(urls[i]);
            }
        }
    }
    public static ServerURL getUrl() {
        return url;
    }
    public static List<String> getServletUrls() {
        return servletUrls;
    }

    public static List<String> getOld_servletUrls() {
        return old_servletUrls;
    }
}
