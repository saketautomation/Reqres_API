package utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class JsonUtil {

    private JSONObject jsonObject;
    private  static JsonUtil jsonUtil;

    private JsonUtil(){}


    public JSONObject loadJsonFile(String fileName) {
        JSONParser parser = new JSONParser();
        try {
            try {
                jsonObject = (JSONObject) parser.parse(new FileReader(System.getProperty("user.dir") + "\\src\\main\\resources\\jsonFiles\\" + fileName + ".json"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }


    public JSONObject getUpdatedJsonObject(JSONObject jsonObject, String fieldName, Object fieldValue) {
        Set<String> jsonKeys = jsonObject.keySet();
        Iterator<String> jsonIterator = jsonKeys.iterator();
        String key;
        while (jsonIterator.hasNext()) {
            key = jsonIterator.next();
            if (key.equals(fieldName)) {
                if (fieldValue.equals("null")) {
                    jsonObject.put(fieldName, null);
                } else {
                    jsonObject.put(fieldName, fieldValue);
                }
                break;
            } else if (key != null && jsonObject.get(key) instanceof JSONObject) {
                getUpdatedJsonObject((JSONObject) jsonObject.get(key), fieldName, fieldValue);
            } else if (key != null && jsonObject.get(key) instanceof JSONArray) {
                for (Object object : (JSONArray) jsonObject.get(key)) {
                    getUpdatedJsonObject((JSONObject) object, fieldName, fieldValue);
                }
            }

        }
        return jsonObject;
    }


    public JSONObject getUpdatedJsonObject(JSONObject jsonObject, Map<String, Object> fieldsToBeUpdated) {
        JSONObject updatedJson = jsonObject;
        for (Map.Entry<String, Object> entry : fieldsToBeUpdated.entrySet()) {
            updatedJson = getUpdatedJsonObject(updatedJson, entry.getKey(), entry.getValue());
        }
        return updatedJson;
    }

    public static JsonUtil getJsonUtil(){
        if (jsonUtil==null){
            jsonUtil=new JsonUtil();
        }
        return jsonUtil;
    }

}
