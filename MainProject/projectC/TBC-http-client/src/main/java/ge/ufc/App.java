package ge.ufc;
import ge.ufc.config.ClientProperties;
import ge.ufc.models.BalanceRequest;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.HashMap;
import static ge.ufc.servers.TBCServiceB.httpFillBalanceRequest;
import static ge.ufc.servers.TBCServiceB.httpGetUserRequest;

public class App
{
    public static void main( String[] args )
    {
        int timeout = ClientProperties.getInstance().getTimeout();
        HashMap<String,String> urls = ClientProperties.getInstance().getUrl();
        try {
            //1
            printResponse(httpGetUserRequest(urls.get("task1url"),timeout),null);

            //2
            printResponse(httpGetUserRequest(urls.get("task2url"),timeout),null);

            BalanceRequest balanceRequest;
            //3
            balanceRequest = new BalanceRequest("99",4,20);
            printResponse(httpFillBalanceRequest(balanceRequest,timeout),balanceRequest);

            //4
            balanceRequest = new BalanceRequest("100",10,20);
            printResponse(httpFillBalanceRequest(balanceRequest,timeout),balanceRequest);

            //5
            balanceRequest = new BalanceRequest("101",4,-100);
            printResponse(httpFillBalanceRequest(balanceRequest,timeout),balanceRequest);

            //6
            balanceRequest = new BalanceRequest("99",4,20);
            printResponse(httpFillBalanceRequest(balanceRequest,timeout),balanceRequest);

            //7
            // manamde xelit vayenebt status = 1 ze;
            balanceRequest = new BalanceRequest("99",4,20);
            printResponse(httpFillBalanceRequest(balanceRequest,timeout),balanceRequest);

            //8
            balanceRequest = new BalanceRequest("101",4,-100);
            printResponse(httpFillBalanceRequest(balanceRequest,timeout),balanceRequest);

            //9
            balanceRequest = new BalanceRequest("99",5,200);
            printResponse(httpFillBalanceRequest(balanceRequest,timeout),balanceRequest);

            //10
            balanceRequest = new BalanceRequest("99",4,200);
            printResponse(httpFillBalanceRequest(balanceRequest,timeout),balanceRequest);

        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static int counter = 0;
    public static void printResponse(HttpResponse<String> response,BalanceRequest request)
    {

        System.out.println("TASK " + ++counter + " ");
        if(request != null)
            System.out.println("Request: " + request.toJson());
        System.out.println("Response: " + response);
        System.out.println("ResponseBody: " + response.body());
        System.out.println("------------------------");
    }
}
