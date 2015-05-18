package com.epam.pashkov.players;

import com.epam.pashkov.datasource.CitiesDAO;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yaroslav_Pashkov on 5/15/2015.
 */
public class Computer implements Player {
    public static final Logger CITIES_TEST_LOG = Logger.getLogger(Computer.class);

    public String sayCity(CitiesDAO citiesData, List<String> history, String city, String prewCity) {
        if (prewCity.charAt(prewCity.length() - 1) == 'ы' || prewCity.charAt(prewCity.length() - 1) == 'ъ' || prewCity.charAt(prewCity.length() - 1) == 'ь') {
            CITIES_TEST_LOG.info("The last char is 'ы', 'ъ', 'ь'");
            char prewChar = Character.toUpperCase(prewCity.charAt(prewCity.length() - 2));
            return findCityInDataSource(citiesData, history, prewChar);
        }

        else {
            CITIES_TEST_LOG.info("The last char isn't 'ы', 'ъ', 'ь'");
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
                    CITIES_TEST_LOG.info("History not contains "+city);
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

