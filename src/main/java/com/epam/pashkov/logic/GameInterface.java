package com.epam.pashkov.logic;

import com.epam.pashkov.datasource.CitiesDAO;
import com.epam.pashkov.players.Player;

/**
 * Created by Yaroslav_Pashkov on 5/20/2015.
 */
public interface GameInterface<T extends Player> {
    public void startGame(CitiesDAO dataSource, String playerStartWord, T people, T computer);
    public void stopGame(String player);
}
