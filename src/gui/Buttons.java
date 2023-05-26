package gui;

import engine.Game;
import model.characters.Hero;
import model.characters.Direction;
import model.characters.Zombie;
import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
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
        } catch (NotEnoughActionsException ex) {
            ex.printStackTrace();
        } catch (InvalidTargetException ex) {
            ex.printStackTrace();
        }
    }
    public static void moveButton(Hero h,Direction d){
        System.out.println("Move Button pressed");
        try{
        	h.move(d);
        }catch(NotEnoughActionsException e){
        	GameView.vbox.getChildren().clear();
        	GameView.vbox.getChildren().add(GameView.moves());
        	e.printStackTrace();
        } catch (MovementException e) {
        	GameView.vbox.getChildren().clear();
        	GameView.vbox.getChildren().add(GameView.hudBasic());
			e.printStackTrace();
		}
        
        

    }
	public static void AttackButton(Hero h , Zombie z){
    	try{
    		h.setTarget(z);
    		h.attack();
    	}catch(NotEnoughActionsException e){
    		e.printStackTrace();
    	} catch (InvalidTargetException e) {
			e.printStackTrace();
		}finally{
			GameView.vbox.getChildren().clear();
        	GameView.vbox.getChildren().add(GameView.hudBasic());
        	GameView.updatemap();
		}
    }
	public static void cureButton(Hero h , Zombie z){
		try{
			h.setTarget(z);
			h.cure();
		}catch(NotEnoughActionsException e){
			e.printStackTrace();
		} catch (NoAvailableResourcesException e) {
			e.printStackTrace();
		} catch (InvalidTargetException e) {
			e.printStackTrace();
		}finally{
			GameView.vbox.getChildren().clear();
        	GameView.vbox.getChildren().add(GameView.hudBasic());
        	GameView.updatemap();
		}
	}
}
