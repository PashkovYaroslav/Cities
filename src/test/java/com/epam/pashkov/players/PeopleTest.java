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
public class PeopleTest {
    public static final Logger CITIES_TEST_LOG = Logger.getLogger(PeopleTest.class);

    @DataProvider(name="sayCityDataProvider")
    public Object[][] createData(){
        return new Object[][]{
                new Object[]{new CitiesExcel(),new ArrayList<String>(),"Волгоград","Киев","Волгоград"},
                new Object[]{new CitiesExcel(),new ArrayList<String>(),"Мариуполь","Пермь","Мариуполь"},
                new Object[]{new CitiesExcel(),new ArrayList<String>(),"Минск","Пермь",""}
        };
    }

    @Test(dataProvider = "sayCityDataProvider")
    public void testSayCity(CitiesDAO citiesDAO,List<String> history,String city,String prewCity,String expectedCity) throws Exception {
        history.add(prewCity);
        CITIES_TEST_LOG.info("CitiesDAO: " + citiesDAO + ", history: " + history + ", prewCity: " + prewCity + ", expectedCity: " + expectedCity);
        Assert.assertEquals(new People().sayCity(citiesDAO, history, city, prewCity),expectedCity);
    }

    @Test(expectedExceptions=StringIndexOutOfBoundsException.class)
    public void testSayCityException() throws Exception {
        CITIES_TEST_LOG.warn("Test exception.");
        Assert.assertEquals(new People().sayCity(new CitiesExcel(), new ArrayList<String>(), "", "Киев"),"");
    }
}