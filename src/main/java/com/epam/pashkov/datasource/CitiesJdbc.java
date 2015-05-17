package com.epam.pashkov.datasource;

import com.epam.pashkov.datasource.ConnectorDB;
import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yaroslav on 14.05.2015.
 */
public class CitiesJdbc implements CitiesDAO {
    private List<String> cities;

    public CitiesJdbc() {
        getAllCities();
    }

    public List<String> getAllCities() {
        List<String> cities = new ArrayList<String>();

        Connection connection = null;
        Statement statement = null;

        try {
            connection = ConnectorDB.getConnection();
            statement = (Statement) connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT Name FROM city");
                while(rs.next()){
                    cities.add(rs.getString(1));
                }
            this.cities = cities;

        } catch (SQLException e) {
            System.err.println("SQL Exeption:"+e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return cities;
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
