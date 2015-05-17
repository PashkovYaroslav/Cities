package com.epam.pashkov.datasource;

import java.util.List;

/**
 * Created by Yaroslav on 14.05.2015.
 */
public interface CitiesDAO {
    public List<String> getAllCities();
    public boolean getCity(String title);
}
