package ge.ufc.webapps.models;

import com.google.gson.Gson;

public class JsonResponse
{
    private static final Gson gson = new Gson();
    private final short code;
    private final long system_transaction_id;

    public JsonResponse(long system_transaction_id, short code) {
        this.system_transaction_id = system_transaction_id;
        this.code = code;
    }
    public String toJson()
    {
        return gson.toJson(this);
    }
}
