package com.epam.pashkov.players;

import com.epam.pashkov.datasource.CitiesDAO;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Yaroslav_Pashkov on 5/15/2015.
 */
public class People implements Player {
    public static final Logger CITIES_TEST_LOG = Logger.getLogger(Computer.class);

    public String sayCity(CitiesDAO citiesData, List<String> history, String city, String prewCity) throws StringIndexOutOfBoundsException {
        if (prewCity.charAt(prewCity.length() - 1) == 'ы' || prewCity.charAt(prewCity.length() - 1) == 'ъ' || prewCity.charAt(prewCity.length() - 1) == 'ь') {
            CITIES_TEST_LOG.info("The last char is 'ы', 'ъ', 'ь'");
            if (city.charAt(0) == Character.toUpperCase(prewCity.charAt(prewCity.length() - 2))) {
                if (citiesData.getCity(city)) {
                    if (!history.contains(city)) {
                        CITIES_TEST_LOG.info("History not contains "+city);
                        return city;
                    }
                }
            }
        } else {
            CITIES_TEST_LOG.info("The last char isn't 'ы', 'ъ', 'ь'");
            if (city.charAt(0) == Character.toUpperCase(prewCity.charAt(prewCity.length() - 1))) {
                if (citiesData.getCity(city)) {
                    if (!history.contains(city)) {
                        CITIES_TEST_LOG.info("History not contains "+city);
                        return city;
                    }
                }
            }
        }
        return "";
    }
}
