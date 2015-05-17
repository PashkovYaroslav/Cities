package com.epam.pashkov.datasource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yaroslav on 14.05.2015.
 */
public class CitiesTxt implements CitiesDAO {
    private List<String> cities;

    public CitiesTxt() {
        getAllCities();
    }

    //Get all cities from database
    public List<String> getAllCities() {
        cities = new ArrayList<String>();
        try {
            BufferedReader in = new BufferedReader(new FileReader("cities.txt"));
            try {
                String s;
                while ((s = in.readLine()) != null) {
                    cities.add(s);
                }
            } finally {
                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        return cities;
    }

    public void writeCitiesListToFile(List<String> cities){
        File file = new File("cities.txt");
        String text = "";

        for(String city : cities){
            text+=city+"\n";
        }

        try {
            if(!file.exists()){
                file.createNewFile();
            }

            PrintWriter out = new PrintWriter(file.getAbsoluteFile());

            try {
                out.print(text);
            } finally {
                out.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
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
