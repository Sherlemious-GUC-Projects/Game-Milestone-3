package gui;

import javafx.stage.Stage;
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
	public static Boolean Flag;
    public static void endTurnButton(Stage primaryStage){
        System.out.println("End Turn Button Pressed");
        try {
            Game.endTurn();
            if(Game.checkWin()){
				primaryStage.setScene(GameView.endScreen(true));
            }
            else if(Game.checkGameOver()){
				primaryStage.setScene(GameView.endScreen(false));
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
    public static void moveButton(Hero h,Direction d, Stage primaryStage){
        System.out.println("Move Button pressed");
		Flag= false;
        try{
        	h.move(d);
		if(Flag)alert = new Label("You Walked Into a Trap!!!");
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
        	GameView.vbox.getChildren().add(GameView.hudBasic(primaryStage));
        	GameView.updatemap();
		}
        
        

    }
	public static void AttackButton(Hero h , Zombie z, Stage primaryStage){
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
        	GameView.vbox.getChildren().add(GameView.hudBasic(primaryStage));
        	GameView.updatemap();
		}
    }
	public static void cureButton(Hero h , Zombie z, Stage primaryStage){
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
        	GameView.vbox.getChildren().add(GameView.hudBasic(primaryStage));
        	GameView.updatemap();
		}
	}
	public static void specialButtonFE(Stage primaryStage){
		try{
			GameView.current_hero.useSpecial();
		}catch (NoAvailableResourcesException e) {
			Label message=new Label(e.getMessage());
			message.setFont(new Font("Arial", 35));
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
        	GameView.vbox.getChildren().add(GameView.hudBasic(primaryStage));
        	GameView.updatemap();
		}
	}
	public static void specialButtonM(Hero h, Hero h1, Stage primaryStage){
		try{
			h.setTarget(h1);
			GameView.current_hero.useSpecial();
		}catch (NoAvailableResourcesException e) {
			Label message=new Label(e.getMessage());
			message.setFont(new Font("Arial", 35));
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
        	GameView.vbox.getChildren().add(GameView.hudBasic(primaryStage));
        	GameView.updatemap();
		}
	}
}
