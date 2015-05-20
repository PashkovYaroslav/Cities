package com.epam.pashkov.datasource;

/**
 * Created by Yaroslav on 19.05.2015.
 */
import static org.easymock.EasyMock.*;

import com.epam.pashkov.players.People;
import org.junit.*;

import java.util.ArrayList;

public class MockDataSourceTest {
    public static final String CITY = "Киев";

    @Test
    public void testDataSource(){
        CitiesDAO mock = createNiceMock(CitiesDAO.class);
        People people = new People();
        people.sayCity(mock,new ArrayList<String>(),CITY,"Львов");

        expect(mock.getCity(CITY));
        replay();

        people.sayCity(mock,new ArrayList<String>(),CITY,"Львов");
        verify();
    }
}
