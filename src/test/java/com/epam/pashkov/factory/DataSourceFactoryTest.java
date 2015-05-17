package com.epam.pashkov.factory;

import com.epam.pashkov.datasource.*;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by Yaroslav on 17.05.2015.
 */
public class DataSourceFactoryTest {
    public static final Logger CITIES_TEST_LOG = Logger.getLogger(DataSourceFactoryTest.class);

    @DataProvider(name="dataSourceDataProvider")
    public Object[][] createData(){
        return new Object[][]{
                new Object[]{DataSourceEnum.EXCEL,new CitiesExcel()},
                new Object[]{DataSourceEnum.JDBC,new CitiesJdbc()},
                new Object[]{DataSourceEnum.XML,new CitiesXml()},
                new Object[]{DataSourceEnum.JSON,new CitiesJson()},
                new Object[]{DataSourceEnum.TXT,new CitiesTxt()}
        };
    }

    @Test(dataProvider = "dataSourceDataProvider")
    public void testGetDataSource(DataSourceEnum dataSourceEnum, CitiesDAO citiesDAO) throws Exception {
        CITIES_TEST_LOG.info("Testing equals of " + dataSourceEnum +" and "+ citiesDAO + "get classes methods results.");
        Assert.assertEquals(DataSourceFactory.getDataSource(dataSourceEnum).getClass(), citiesDAO.getClass());
    }
}