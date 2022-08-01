package Json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONObject;

public class Main
{
    public static void main(String[] args)
    {
        FacialEmotionRecognized emotion = new FacialEmotionRecognized(Mouth.NEUTRAL,Eyebrows.UPWARD);
        String json = emotionToJson(emotion);
        System.out.println(json);
        FacialEmotionRecognized copy = fromJsonString(json);
        System.out.println(copy.getEmotion());
    }
    public static FacialEmotionRecognized fromJsonString(String jsonString)
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.fromJson(jsonString,FacialEmotionRecognized.class);
    }
    public static String emotionToJson(FacialEmotionRecognized emotionRecognized)
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(emotionRecognized);
    }
    public static String faceEmotionAsJson(FaceEmotion faceEmotion)
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("eyebrows",faceEmotion.getEyebrows());
        jsonObject.put("mouth",faceEmotion.getMouth());
        return jsonObject.toJSONString();
    }
}
