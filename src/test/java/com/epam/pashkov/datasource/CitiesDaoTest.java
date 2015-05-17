package com.epam.pashkov.datasource;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import static org.testng.Assert.*;

/**
 * Created by Yaroslav on 17.05.2015.
 */
public class CitiesDaoTest {
    public static final Logger CITIES_TEST_LOG = Logger.getLogger(CitiesDaoTest.class);

    @DataProvider(name="daoDataProvider")
    public Object[][] createSomeDao(){
        return new Object[][]{new Object[]{new CitiesExcel()},
                            new Object[]{new CitiesJdbc()},
                            new Object[]{new CitiesJson()},
                            new Object[]{new CitiesTxt()},
                            new Object[]{new CitiesXml()}};
    }

    @Test(dataProvider = "daoDataProvider")
    public void testGetAllCities(CitiesDAO citiesDAO) throws Exception {
        CITIES_TEST_LOG.info("Testing " + citiesDAO + " size.");
        Assert.assertEquals(citiesDAO.getAllCities().size(), 10987);
    }

    @Test(dataProvider = "daoDataProvider")
    public void testGetCity(CitiesDAO citiesDAO) throws Exception {
        CITIES_TEST_LOG.info("Testing " + citiesDAO + " get city.");
        Assert.assertTrue(citiesDAO.getCity("Киев"));
    }

    @Test(dataProvider = "daoDataProvider")
    public void testGetCityFail(CitiesDAO citiesDAO) throws Exception {
        CITIES_TEST_LOG.info("Testing " + citiesDAO + " get fail city.");
        Assert.assertFalse(citiesDAO.getCity("123"));
    }
}