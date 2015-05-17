package com.epam.pashkov.players;

import com.epam.pashkov.datasource.CitiesDAO;

import java.util.List;

/**
 * Created by Yaroslav_Pashkov on 5/15/2015.
 */
public class People implements Player {
    public String sayCity(CitiesDAO citiesData, List<String> history, String city, String prewCity) throws StringIndexOutOfBoundsException {
        if (prewCity.charAt(prewCity.length() - 1) == 'ы' || prewCity.charAt(prewCity.length() - 1) == 'ъ' || prewCity.charAt(prewCity.length() - 1) == 'ь') {
            if (city.charAt(0) == Character.toUpperCase(prewCity.charAt(prewCity.length() - 2))) {
                if (citiesData.getCity(city)) {
                    if (!history.contains(city)) {
                        return city;
                    }
                }
            }
        } else {
            if (city.charAt(0) == Character.toUpperCase(prewCity.charAt(prewCity.length() - 1))) {
                if (citiesData.getCity(city)) {
                    if (!history.contains(city)) {
                        return city;
                    }
                }
            }
        }
        return "";
    }
}
