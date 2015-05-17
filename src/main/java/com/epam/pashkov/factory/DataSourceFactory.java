package com.epam.pashkov.factory;

import com.epam.pashkov.datasource.*;

/**
 * Created by Yaroslav on 17.05.2015.
 */
public class DataSourceFactory {
    public static CitiesDAO getDataSource(DataSourceEnum dataSourceType) {
        switch (dataSourceType) {
            case EXCEL: {
                return new CitiesExcel();
            }
            case JDBC: {
                return new CitiesJdbc();
            }
            case TXT: {
                return new CitiesTxt();
            }
            case JSON: {
                return new CitiesJson();
            }
            case XML: {
                return new CitiesXml();
            }
            default:{
                return null;
            }
        }
    }
}
