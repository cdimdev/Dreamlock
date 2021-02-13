package com.dreamlock.desktop;

import com.dreamlock.desktop.menu.EmptyChoice;
import com.dreamlock.desktop.menu.IMenuChoice;
import com.dreamlock.desktop.menu.MenuChoices;
import com.dreamlock.desktop.menu.StartLoadedGameChoice;
import com.dreamlock.desktop.menu.StartNewGameChoice;

import java.awt.Menu;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.text.StyledEditorKit.BoldAction;

public class Dreamlock {
    public static void main (String[] args) {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        boolean MainGameRunning = true;

        while (MainGameRunning) {
            createMainMenu();
            IMenuChoice menuChoice;
            try {
                input = buffer.readLine();
                MenuChoices chosenOption = MenuChoices.values()[Integer.parseInt(input)];

                if(chosenOption != null) {
                  menuChoice = chosenOption.getMenuChoice();

                  if(menuChoice.equals(new EmptyChoice())) {
                    MainGameRunning = false;
                    System.out.println("Goodbye! Farewell Traveler!!");
                  }

                  menuChoice.run();

                } else {
                  System.out.println("Invalid Option");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void createMainMenu () {
        GameUtils gameUtils = new GameUtils();
        gameUtils.GameLogo("Dreamlock");
        System.out.println("1. New Game");
        System.out.println("2. Load Game");
        System.out.println("3. Exit\n");
        System.out.print(": ");
    }
}