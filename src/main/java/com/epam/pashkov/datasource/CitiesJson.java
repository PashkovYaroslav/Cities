package com.epam.pashkov.datasource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Yaroslav on 14.05.2015.
 */
public class CitiesJson implements CitiesDAO {
    private List<String> cities;

    public CitiesJson() {
        getAllCities();
    }

    //Get all cities from database
    public List<String> getAllCities() {
        cities = new ArrayList<String>();
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(new File("cities.json")));
            JSONObject jsonBaseObj = (JSONObject) obj;
            JSONObject jsonBankObj = (JSONObject) jsonBaseObj.get("cities");

            JSONArray arrayOfCities = (JSONArray) jsonBankObj.get("city");

            for(int i = 0; i<arrayOfCities.size();i++){
                JSONObject jo = (JSONObject) arrayOfCities.get(i);
                cities.add(jo.get("name").toString());
            }
        }
        catch (Exception e) {
            e.getMessage();
        }

        return cities;
    }

    public void writeCitiesListToFile(List<String> cities){
        JSONArray citiesArray = new JSONArray();
        JSONObject objCity = new JSONObject();
        JSONObject rootObj = new JSONObject();

        for(String cityName : cities) {
            JSONObject city = new JSONObject();
            city.put("name", cityName);
            citiesArray.add(city);
        }

        objCity.put("city", citiesArray);
        rootObj.put("cities", objCity);

        File file = new File("cities.json");

        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            PrintWriter out = new PrintWriter(file.getAbsoluteFile());

            try {
                out.print(rootObj);
                System.out.println("File was created.");
            } finally {
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean getCity(String title) {
        if(cities.contains(title)){
            return true;
        }
        else{
            return false;
        }
    }
}
