package search.Utils;

import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import search.Models.RbTree;

public class JsonParsing {

    public static String loadJsonData(RbTree tree, String resourcePath) {
        StringBuilder jsonDetails = new StringBuilder();
        
        try (InputStream filStream = JsonParsing.class.getResourceAsStream(resourcePath)) {
            if (filStream != null) {
                JSONArray jsonArray = new JSONArray(new JSONTokener(filStream));
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String key = jsonObject.keys().next();
                    String value = jsonObject.getString(key);
                    tree.insert(key, value);

                    jsonDetails.append(jsonObject.toString(4)).append("\n");
                }
            } else {
                System.err.println("Failed to load data.json. The resource is not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonDetails.toString();
    }
}

