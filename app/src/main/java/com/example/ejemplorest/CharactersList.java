package com.example.ejemplorest;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CharactersList {
    private Context context;
    private List<Character> characters;
    private RequestQueue requestQueue;

    public CharactersList(Context context) {
        this.context = context;
        getInfoFromRest();
        characters = new ArrayList<Character>();
    }
    public void getInfoFromRest(){
        this.requestQueue = Volley.newRequestQueue(context);
        String url = "https://g6990ff2aa40aea-db202112132032.adb.us-ashburn-1.oraclecloudapps.com/ords/admin/characters/";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("items");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonCharacter = jsonArray.getJSONObject(i);
                        int id = 0;
                        String name = "";
                        String description = "";
                        String power = "";
                        int strength = 0;
                        int agility = 0;
                        if (!jsonCharacter.getString("name").equals("null")) {
                            name = jsonCharacter.getString("name");
                        }
                        if (!jsonCharacter.getString("description").equals("null")) {
                            description = jsonCharacter.getString("description");
                        }
                        if (!jsonCharacter.getString("power").equals("null")) {
                            power = jsonCharacter.getString("power");
                        }
                        if (!jsonCharacter.getString("strength").equals("null")) {
                            try {
                                strength = Integer.valueOf(jsonCharacter.getString("strength"));
                            }catch (Exception e){
                                strength = 0;
                                Log.i(null, e.toString());
                            }
                        }
                        if (!jsonCharacter.getString("agility").equals("null")) {
                            try {
                                agility = Integer.valueOf(jsonCharacter.getString("agility"));
                            }catch (Exception e){
                                agility = 0;
                                Log.i(null, e.toString());
                            }

                        }
                        JSONArray jsonLinksArray = jsonCharacter.getJSONArray("links");
                        JSONObject jsonLinkObject = jsonLinksArray.getJSONObject(0);
                        String link = jsonLinkObject.getString("href");
                        Character character = new Character(id, name, description, power, agility, strength, link);
                        characters.add(character);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(null, error.toString());
            }
        });
        this.requestQueue.add(stringRequest);
    }
    public List<Character> getAll(){
        while (characters.size()==0);
        return characters;
    }
}
