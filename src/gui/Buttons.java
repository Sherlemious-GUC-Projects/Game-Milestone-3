package gui;

import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import engine.Game;

import model.characters.Hero;
import model.characters.Direction;
import model.characters.Zombie;
import model.characters.Direction;

import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;

import java.awt.Point;
import java.util.ArrayList;


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

	public static void aiButton(Stage primaryStage){
		GameView.updatemap();
		Hero h = GameView.current_hero;
		Point curLoc = h.getLocation();

		// get valid locations
		ArrayList<Point> validLocs = new ArrayList<Point>();
		for (int i = 0; i < 9; i++){
			Point p = convertFromIdxToPoint(i);
			p = new Point(h.getLocation().x + p.x, h.getLocation().y + p.y);
			validLocs.add(p);
		}
		
		// try to cure
		if (h.getVaccineInventory().size() > 0){
			for (Zombie z : Game.zombies){
				Point zLoc = z.getLocation();
				if (!validLocs.contains(zLoc))	continue;
				h.setTarget(z);
				try{
					h.cure();
					GameView.vbox.getChildren().clear();
					GameView.vbox.getChildren().add(GameView.hudBasic(primaryStage));
					GameView.updatemap();
					return;
				}catch(Exception e){
				}
			}
		}
		// try to attack
		if (h.getActionsAvailable() > 0){
			for (Zombie z : Game.zombies){
				Point zLoc = z.getLocation();
				if (!validLocs.contains(zLoc))	continue;

				h.setTarget(z);
				try{
					h.attack();
					GameView.vbox.getChildren().clear();
					GameView.vbox.getChildren().add(GameView.hudBasic(primaryStage));
					GameView.updatemap();
					continue;
				}catch(Exception e){
					System.out.println(e.getMessage());
					continue;
				}
			}
		}

		// try to move
		if (h.getActionsAvailable() > 0){
			// get all directions
			ArrayList<Direction> allDirs = new ArrayList<Direction>();
			allDirs.add(Direction.UP);
			allDirs.add(Direction.DOWN);
			allDirs.add(Direction.LEFT);
			allDirs.add(Direction.RIGHT);

			for (Direction d : allDirs){
				Point newP = convertFromDirectionToPoint(d);
				Cell c = Game.map[newP.x][newP.y];
				if (c instanceof CollectibleCell){
					// if it is a collectible cell then move to it
					try{
						h.move(d);
						GameView.updatemap();
						return;
					}catch(Exception e){
						System.out.println(e.getMessage());
						continue;
					}
				}else if (c instanceof TrapCell){
					// if it is a trap cell then remove it from the list of directions to not go to it
					allDirs.remove(d);
					continue;
				}
			}
			// failed to move then go to any direction that is not a trap
			if (allDirs.size() != 0){
				for (Direction d : allDirs){
					try{
						h.move(d);
						GameView.updatemap();
						return;
					}catch(Exception e){
						System.out.println(e.getMessage());
						continue;
					}
				}
			}else{
				try{
					h.move(Direction.UP);
					GameView.updatemap();
					return;
				}catch(Exception e){
					System.out.println(e.getMessage());
					return;
				}
			}
		}

	}	

	private static Point convertFromIdxToPoint(int idx){
		int N = 3;
		int Width = (N - 1) / 2;
		int x = idx % N - Width;
		int y = idx / N - Width;

		return new Point(x,y);
	}

	private static Point convertFromDirectionToPoint(Direction d){
		switch(d){
			case UP:
				return new Point(0, 1);
			case DOWN:
				return new Point(0, -1);
			case LEFT:
				return new Point(-1, 0);
			case RIGHT:
				return new Point(1, 0);
			default:
				return new Point(0, 0);
		}
	}
}
