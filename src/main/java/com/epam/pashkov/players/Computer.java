package com.epam.pashkov.players;

import com.epam.pashkov.datasource.CitiesDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yaroslav_Pashkov on 5/15/2015.
 */
public class Computer implements Player {
    public String sayCity(CitiesDAO citiesData, List<String> history, String city, String prewCity) {
        if (prewCity.charAt(prewCity.length() - 1) == 'ы' || prewCity.charAt(prewCity.length() - 1) == 'ъ' || prewCity.charAt(prewCity.length() - 1) == 'ь') {
            char prewChar = Character.toUpperCase(prewCity.charAt(prewCity.length() - 2));
            return findCityInDataSource(citiesData, history, prewChar);
        }

        else {
            char prewChar = Character.toUpperCase(prewCity.charAt(prewCity.length() - 1));
            return findCityInDataSource(citiesData, history, prewChar);
        }
    }

    private String findCityInDataSource(CitiesDAO citiesData, List<String> history, char prewChar) {
        List<String> citiesList = citiesData.getAllCities();
        String newCity = "";

        for (String city : citiesList) {
            if (city.charAt(0) == prewChar) {
                if (!history.contains(city)) {
                    newCity = city;
                    break;
                }
                else{
                    continue;
                }
            }
        }

        return newCity;
    }
}

