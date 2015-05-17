package com.epam.pashkov;

import com.epam.pashkov.datasource.*;
import com.epam.pashkov.factory.DataSourceEnum;
import com.epam.pashkov.factory.DataSourceFactory;
import com.epam.pashkov.logic.Game;
import com.epam.pashkov.players.Computer;
import com.epam.pashkov.players.People;

import java.util.Collection;
import java.util.Collections;

/**
 * Created by Yaroslav on 14.05.2015.
 */
public class Main {
    public static void main(String[] args) {
        /*CitiesJson citiesJson = new CitiesJson();
        citiesJson.getAllCities();*/

        /*CitiesXml citiesXml = new CitiesXml();
        citiesXml.getAllCities();

        CitiesTxt citiesTxt = new CitiesTxt();
        citiesTxt.writeCitiesListToFile(new CitiesJdbc().getAllCities());*/

        /*CitiesTxt citiesTxt = new CitiesTxt();
        citiesTxt.getAllCities();*/

        /*CitiesExcel citiesExcel = new CitiesExcel();
        citiesExcel.writeCitiesListToFile(new CitiesJdbc().getAllCities());*/

        Game game = new Game(new People(), new Computer());
        CitiesDAO citiesData = DataSourceFactory.getDataSource(DataSourceEnum.JDBC);

        // People starts first
        if(args.length!=0) {
            game.startGame(citiesData, args[0]);
        }
        else{
            game.startGame(citiesData, "Киев");
        }
    }
}
