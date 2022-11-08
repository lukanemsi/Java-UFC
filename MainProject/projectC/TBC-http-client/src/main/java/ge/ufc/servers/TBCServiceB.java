package ge.ufc.servers;

import ge.ufc.config.ClientProperties;
import ge.ufc.models.BalanceRequest;

import java.io.IOException;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class TBCServiceB
{
    public static HttpResponse<String> httpGetUserRequest(String url,int timeout) throws URISyntaxException, IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().version(HttpClient.Version.HTTP_2)
                .uri(URI.create(url)).timeout(Duration.ofSeconds(timeout))
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static HttpResponse<String> httpFillBalanceRequest(BalanceRequest balanceRequest,int timeout) throws URISyntaxException, IOException, InterruptedException {
        ClientProperties clientProperties = ClientProperties.getInstance();

        final String url = clientProperties.getUrl().get("url");
        final String requestBody = balanceRequest.toJson();
        HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
        HttpRequest request = HttpRequest.newBuilder(new URI(url)).timeout(Duration.ofSeconds(timeout))
                .version(HttpClient.Version.HTTP_2).headers("Content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
