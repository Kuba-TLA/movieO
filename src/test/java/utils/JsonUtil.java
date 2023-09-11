package utils;

import base.BaseTest;
import org.apache.commons.logging.Log;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

public class JsonUtil {

    public Object[] dataProvider1(){
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;


        try {
            Object obj = parser.parse(new FileReader("src/test/resources/testData.json"));
            jsonObject = (JSONObject) obj;
        } catch (IOException | ParseException exception) {
            exception.printStackTrace();
        }

        Object[] data = new Object[1];


        HashMap<String, String> hashMap = new LinkedHashMap<>();
        if(jsonObject != null){
            Set<String> jsonObjKeys = jsonObject.keySet();
            for (String jsonObjKey : jsonObjKeys){
                hashMap.put(jsonObjKey, (String) jsonObject.get(jsonObjKey));
            }
        } else {
            // needs to extend baseTest
            // log.error("Error retrieving JSON data");
            throw new RuntimeException();
        }

        data[0] = hashMap;
        return data;
    }
}
