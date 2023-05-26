package gui;

import javafx.scene.control.Label;
import engine.Game;
import model.characters.Hero;
import model.characters.Direction;
import model.characters.Zombie;
import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import exceptions.MovementException;

public class Buttons {
	public static Label alert;
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
            alert=new Label(ex.getMessage());
        } catch (InvalidTargetException ex) {
        	alert=new Label(ex.getMessage());
        }
    }
    public static void moveButton(Hero h,Direction d){
        System.out.println("Move Button pressed");
        try{
        	h.move(d);
        }catch(NotEnoughActionsException e){
        	
        	alert=new Label(e.getMessage());
        } catch (MovementException e) {
        	
        	alert=new Label(e.getMessage());
		} finally{
			GameView.vbox.getChildren().clear();
        	GameView.vbox.getChildren().add(GameView.hudBasic());
        	GameView.updatemap();
		}
        
        

    }
	public static void AttackButton(Hero h , Zombie z){
    	try{
    		h.setTarget(z);
    		h.attack();
    	}catch(NotEnoughActionsException e){
    		alert=new Label(e.getMessage());
    	} catch (InvalidTargetException e) {
    		alert=new Label(e.getMessage());
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
			alert=new Label(e.getMessage());
		} catch (NoAvailableResourcesException e) {
			alert=new Label(e.getMessage());
		} catch (InvalidTargetException e) {
			alert=new Label(e.getMessage());
		}finally{
			GameView.vbox.getChildren().clear();
        	GameView.vbox.getChildren().add(GameView.hudBasic());
        	GameView.updatemap();
		}
	}
}
