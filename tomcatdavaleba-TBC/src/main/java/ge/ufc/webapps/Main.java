package ge.ufc.webapps;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main
{
    public static void main(String[] args) {
        String son = "{\"triangles\":[{\"a\":10, \"b\":20, \"c\":30}, {\"a\":10, \"b\":20, \"c\":30}]}";
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        var k = gson.fromJson(son,Triangles.class);
        System.out.println(k.getTriangles().get(0));
    }
}
