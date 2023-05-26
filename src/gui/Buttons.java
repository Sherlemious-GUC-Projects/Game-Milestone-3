package gui;

import engine.Game;
import model.characters.Hero;
import model.characters.Direction;
import model.characters.Zombie;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;
import exceptions.MovementException;

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
			GameView.exceptionLabel.setText("");
        } catch (NotEnoughActionsException e) {
			GameView.exceptionLabel.setText(e.getMessage());
        } catch (InvalidTargetException e) {
			GameView.exceptionLabel.setText(e.getMessage());
        }
    }

    public static void moveButton(Hero h,Direction d){
        System.out.println("Move Button pressed");
        try{
			GameView.exceptionLabel.setText("");
        	h.move(d);
        }catch(NotEnoughActionsException e){
        	GameView.vbox.getChildren().clear();
        	GameView.vbox.getChildren().add(GameView.moves());
			GameView.exceptionLabel.setText(e.getMessage());

        } catch (MovementException e) {
        	GameView.vbox.getChildren().clear();
        	GameView.vbox.getChildren().add(GameView.hudBasic());
			GameView.exceptionLabel.setText(e.getMessage());
		}
    }

	public static void AttackButton(Hero h , Zombie z){
    	try{
    		h.setTarget(z);
    		h.attack();
			GameView.exceptionLabel.setText("");
    	}catch(NotEnoughActionsException e){
			GameView.exceptionLabel.setText(e.getMessage());
    		GameView.vbox.getChildren().clear();
        	GameView.vbox.getChildren().add(GameView.hudBasic());
    	} catch (InvalidTargetException e) {
			GameView.exceptionLabel.setText(e.getMessage());
    		GameView.vbox.getChildren().clear();
        	GameView.vbox.getChildren().add(GameView.hudBasic());
		}
    }
}
