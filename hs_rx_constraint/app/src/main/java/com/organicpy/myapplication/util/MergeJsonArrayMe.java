package com.organicpy.myapplication.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @author Mohd Hussain
 * @version 1.0
 * @since 21-01-2021
 */
public class MergeJsonArrayMe {
    private static final String MESSAGE_CONSTANT = "message";
    /*try {
            JSONArray ja = new JSONArray("[{\"id\":21,\"message\":[\"dog\",\"elephant\",\"hero\"], \"courseName\": \"majlish\"},{\"id\":21,\"message\":[\"cat\",\"parrot\",\"dragon\"], \"courseName\": \"majlish\"}," +
                    "{\"id\":31,\"message\":[\"dog\",\"elephant\",\"hero\"], \"courseName\": \"dubai creak\"}]");
            System.out.println(mergeJson(ja));
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
    /**
     * It check and merge content of two same id's of Json Object
     * @param arr - Json Array that needs to check and merge for duplicate data
     * @return non-duplicate jsonArray
     */
    JSONArray mergeJson(JSONArray arr) throws JSONException {
        JSONArray newArray = new JSONArray();
        ArrayList<Integer> list = new ArrayList<>();
        JSONObject obj = arr.getJSONObject(0);
        list.add(obj.getInt("id"));
        newArray.put(obj);
        for ( int i = 1; i < arr.length(); i++) {
            JSONObject sbj = arr.getJSONObject(i);
            if (list.contains(sbj.getInt("id"))){
                int matchedArray = list.indexOf(sbj.getInt("id"));
                JSONArray mainArray = newArray.getJSONObject(matchedArray).getJSONArray(MESSAGE_CONSTANT);
                JSONArray msgArray = sbj.getJSONArray(MESSAGE_CONSTANT);
                newArray.getJSONObject(matchedArray).put(MESSAGE_CONSTANT, concatArray(mainArray, msgArray));
            } else {
                list.add(sbj.getInt("id"));
                newArray.put(sbj);
            }
        }
        return newArray;
    }

    /**
     *  Merge two JsonArray together
     *  @param j1,j2 - json Array
     *  @return merged jsonArray
     */
    JSONArray concatArray(JSONArray j1, JSONArray j2) throws JSONException {
        JSONArray result = new JSONArray();
        for (int i = 0; i < j1.length(); i++) {
            result.put(j1.get(i));
        }
        for (int i = 0; i < j2.length(); i++) {
            result.put(j2.get(i));
        }
        return result;
    }
}
