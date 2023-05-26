package gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import engine.Game;
import javafx.scene.text.Font;
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
        } catch (NotEnoughActionsException e) {
			Label message=new Label(e.getMessage());
			message.setFont(new Font("Arial", 30));
			message.setAlignment(Pos.TOP_CENTER);
			message.setMinSize(200, 200);
			alert=message;
        } catch (InvalidTargetException e) {
			Label message=new Label(e.getMessage());
			message.setFont(new Font("Arial", 30));
			message.setAlignment(Pos.TOP_CENTER);
			message.setMinSize(200, 200);
			alert=message;
        }
    }
    public static void moveButton(Hero h,Direction d){
        System.out.println("Move Button pressed");
        try{
        	h.move(d);
        }catch(NotEnoughActionsException e){
			Label message=new Label(e.getMessage());
			message.setFont(new Font("Arial", 30));
			message.setAlignment(Pos.TOP_CENTER);
			message.setMinSize(200, 200);
			alert=message;
        } catch (MovementException e) {
			Label message=new Label(e.getMessage());
			message.setFont(new Font("Arial", 30));
			message.setAlignment(Pos.TOP_CENTER);
			message.setMinSize(200, 200);
			alert=message;
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
			Label message=new Label(e.getMessage());
			message.setFont(new Font("Arial", 30));
			message.setAlignment(Pos.TOP_CENTER);
			message.setMinSize(200, 200);
			alert=message;
    	} catch (InvalidTargetException e) {
			Label message=new Label(e.getMessage());
			message.setFont(new Font("Arial", 30));
			message.setAlignment(Pos.TOP_CENTER);
			message.setMinSize(200, 200);
			alert=message;
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
			Label message=new Label(e.getMessage());
			message.setFont(new Font("Arial", 30));
			message.setAlignment(Pos.TOP_CENTER);
			message.setMinSize(200, 200);
			alert=message;
		} catch (NoAvailableResourcesException e) {
			Label message=new Label(e.getMessage());
			message.setFont(new Font("Arial", 30));
			message.setAlignment(Pos.TOP_CENTER);
			message.setMinSize(200, 200);
			alert=message;
		} catch (InvalidTargetException e) {
			Label message=new Label(e.getMessage());
			message.setFont(new Font("Arial", 35));
			message.setAlignment(Pos.TOP_CENTER);
			message.setMinSize(200, 200);
			alert=message;
		}finally{
			GameView.vbox.getChildren().clear();
        	GameView.vbox.getChildren().add(GameView.hudBasic());
        	GameView.updatemap();
		}
	}
}
