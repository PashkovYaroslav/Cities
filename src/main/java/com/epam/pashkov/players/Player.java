package com.epam.pashkov.players;

import com.epam.pashkov.datasource.CitiesDAO;

import java.util.List;

/**
 * Created by Yaroslav_Pashkov on 5/15/2015.
 */
public interface Player {
    public String sayCity(CitiesDAO citiesData, List<String> history, String city, String prewCity);
}
