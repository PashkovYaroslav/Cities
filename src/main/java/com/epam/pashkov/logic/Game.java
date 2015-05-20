package com.epam.pashkov.logic;

import com.epam.pashkov.datasource.CitiesDAO;
import com.epam.pashkov.players.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Yaroslav_Pashkov on 5/15/2015.
 */
public class Game<T extends Player> implements GameInterface<T> {
    private T people;
    private T computer;
    private List<String> historyList;
    public Scanner sc = new Scanner(System.in);

    public Player getPeople() {
        return people;
    }

    public void setPeople(T people) {
        this.people = people;
    }

    public Player getComputer() {
        return computer;
    }

    public void setComputer(T computer) {
        this.computer = computer;
    }

    public List<String> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<String> historyList) {
        this.historyList = historyList;
    }

    public Game() {
    }

    public void startGame(CitiesDAO dataSource, String playerStartWord, T people, T computer){
        setPeople(people);
        setComputer(computer);
        String city = playerStartWord;
        historyList = new ArrayList<String>();
        while(true){
            city = computer.sayCity(dataSource,historyList,"",city);
            if(city.equals("")){
                stopGame("Computer");
                break;
            }
            historyList.add(city);
            System.out.println("Computer says: "+city);
            String playerSayCity = sc.nextLine();
            try {
                city = people.sayCity(dataSource, historyList, playerSayCity, city);
            }
            catch(StringIndexOutOfBoundsException e){
                city = "";
                System.out.println("People can't say city.");
            }
            if(city.equals("")){
                stopGame("People");
                break;
            }
            historyList.add(city);
            System.out.println("People says: "+city);
        }
    }

    public void stopGame(String player){
        System.out.println(player + " was lose.");
    }
}
