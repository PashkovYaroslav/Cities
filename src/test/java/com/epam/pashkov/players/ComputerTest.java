package com.epam.pashkov.players;

import com.epam.pashkov.datasource.CitiesDAO;
import com.epam.pashkov.datasource.CitiesExcel;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by Yaroslav on 17.05.2015.
 */
public class ComputerTest {
    public static final Logger CITIES_TEST_LOG = Logger.getLogger(ComputerTest.class);

    @DataProvider(name="sayCityDataProvider")
    public Object[][] createData(){
        return new Object[][]{
                new Object[]{new CitiesExcel(),new ArrayList<String>(),"Киев", 'В'},
                new Object[]{new CitiesExcel(),new ArrayList<String>(),"Пермь", 'М'}
        };
    }

    @Test(dataProvider = "sayCityDataProvider")
    public void testSayCity(CitiesDAO citiesDAO,List<String> history,String prewCity,char firstChar) throws Exception {
        history.add(prewCity);
        CITIES_TEST_LOG.info("CitiesDAO: "+citiesDAO +", history: "+ history+", prewCity: "+ prewCity+", firstChar: "+ firstChar);
        Assert.assertEquals(new Computer().sayCity(citiesDAO,history,"",prewCity).charAt(0),firstChar);
    }
}