package gui;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;

public class Buttons {
    public static void endTurnButton(){
        System.out.println("End Turn Button Pressed");
        try {
            Game.endTurn();
            if(Game.checkWin()){
                GameView.endScreen(true);
            }
            else if(Game.checkGameOver()){
                GameView.endScreen(false);
            }
        } catch (NotEnoughActionsException ex) {
            ex.printStackTrace();
        } catch (InvalidTargetException ex) {
            ex.printStackTrace();
        }
    }
    public static void moveButton(){
        System.out.println("Move Button pressed");

    }
}
