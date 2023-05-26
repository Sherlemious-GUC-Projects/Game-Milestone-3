package gui;

// importing gui related classes
import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;
import javafx.scene.control.Button;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import model.characters.Direction;
// importing character classes
import model.characters.Hero;
import model.characters.Zombie;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;
// importing game related classes
import engine.Game;
import gui.Buttons;

// importing world related classes
//more imports
import java.io.IOException;
import java.util.ArrayList;



public class GameView {
    public static Hero current_hero;
    public static Zombie current_zombie;
    // Get the current file path
    static String pathToHeroes = System.getProperty("user.dir") + "\\src\\gui\\data\\Heros.csv";

//    static String pathToHeroes = "A:\\University\\Uni Work\\Projects\\Game\\Milestone 3\\bin\\gui\\data\\Heros.csv";
    public static StackPane[][] cells = new StackPane[15][15];
    public static ImageView heroImg;
    public static ImageView zombieImg;
    public static ImageView CollectibleImg;
    public static ImageView emptyImg;
    public static VBox vbox;
    public static ComboBox combobox;

    public static Scene startScreen(Stage primaryStage) {
        // main pane
        StackPane stackPane = new StackPane();

        //Labels initialization
        Label gameTitle = new Label("The Last of Us");
        Label characterSelectedName = new Label("Character selected:    ");
        Label characterSelectedType = new Label("Type:    ");
        Label characterSelectedHealth = new Label("Health:    ");
        Label characterSelectedAttackDamage = new Label("Attack Damage:    ");
        Label characterSelectedNumberOfMoves = new Label("Max number of moves:    ");

        //adding labels to pane
        stackPane.getChildren().add(gameTitle);
        stackPane.setAlignment(gameTitle, Pos.TOP_CENTER);
        gameTitle.setTranslateY(200);

        stackPane.getChildren().add(characterSelectedName);
        characterSelectedName.setTranslateY(-300);
        stackPane.setAlignment(characterSelectedName, Pos.BOTTOM_CENTER);

        stackPane.getChildren().add(characterSelectedType);
        characterSelectedType.setTranslateY(-275);
        stackPane.setAlignment(characterSelectedType, Pos.BOTTOM_CENTER);

        stackPane.getChildren().add(characterSelectedHealth);
        characterSelectedHealth.setTranslateY(-250);
        stackPane.setAlignment(characterSelectedHealth, Pos.BOTTOM_CENTER);

        stackPane.getChildren().add(characterSelectedAttackDamage);
        characterSelectedAttackDamage.setTranslateY(-225);
        stackPane.setAlignment(characterSelectedAttackDamage, Pos.BOTTOM_CENTER);

        stackPane.getChildren().add(characterSelectedNumberOfMoves);
        characterSelectedNumberOfMoves.setTranslateY(-200);
        stackPane.setAlignment(characterSelectedNumberOfMoves, Pos.BOTTOM_CENTER);

        // character selection
        ComboBox characterSelection = new ComboBox();
        characterSelection.getItems().addAll(
                "Joel Miller",
                "Ellie Williams",
                "Tess",
                "Riley Abel",
                "Tommy Miller",
                "Bill",
                "David",
                "Henry Burell"
        );
        characterSelection.valueProperty().addListener((observable, oldValue, newValue) -> {
            characterSelectedName.setText("Character selected:    " + newValue);

            switch (newValue.toString()) {
                case "Joel Miller":
                    characterSelectedType.setText("Type:    " + "fighter");
                    characterSelectedHealth.setText("Health:    " + 140);
                    characterSelectedAttackDamage.setText("Attack Damage:    " + 30);
                    characterSelectedNumberOfMoves.setText("Max number of moves:    " + 5);
                    break;
                case "Ellie Williams":
                    characterSelectedType.setText("Type:    " + "medic");
                    characterSelectedHealth.setText("Health:    " + 110);
                    characterSelectedAttackDamage.setText("Attack Damage:    " + 15);
                    characterSelectedNumberOfMoves.setText("Max number of moves:    " + 6);
                    break;
                case "Tess":
                    characterSelectedType.setText("Type:    " + "explorer");
                    characterSelectedHealth.setText("Health:    " + 80);
                    characterSelectedAttackDamage.setText("Attack Damage:    " + 20);
                    characterSelectedNumberOfMoves.setText("Max number of moves:    " + 6);
                    break;
                case "Riley Abel":
                    characterSelectedType.setText("Type:    " + "explorer");
                    characterSelectedHealth.setText("Health:    " + 90);
                    characterSelectedAttackDamage.setText("Attack Damage:    " + 25);
                    characterSelectedNumberOfMoves.setText("Max number of moves:    " + 5);
                    break;
                case "Tommy Miller":
                    characterSelectedType.setText("Type:    " + "explorer");
                    characterSelectedHealth.setText("Health:    " + 95);
                    characterSelectedAttackDamage.setText("Attack Damage:    " + 25);
                    characterSelectedNumberOfMoves.setText("Max number of moves:    " + 5);
                    break;
                case "Bill":
                    characterSelectedType.setText("Type:    " + "medic");
                    characterSelectedHealth.setText("Health:    " + 100);
                    characterSelectedAttackDamage.setText("Attack Damage:    " + 10);
                    characterSelectedNumberOfMoves.setText("Max number of moves:    " + 7);
                    break;
                case "David":
                    characterSelectedType.setText("Type:    " + "fighter");
                    characterSelectedHealth.setText("Health:    " + 150);
                    characterSelectedAttackDamage.setText("Attack Damage:    " + 35);
                    characterSelectedNumberOfMoves.setText("Max number of moves:    " + 4);
                    break;
                case "Henry Burell":
                    characterSelectedType.setText("Type:    " + "medic");
                    characterSelectedHealth.setText("Health:    " + 105);
                    characterSelectedAttackDamage.setText("Attack Damage:    " + 15);
                    characterSelectedNumberOfMoves.setText("Max number of moves:    " + 6);
                    break;
                default:
                    characterSelectedType.setText("Type:    ");
                    characterSelectedHealth.setText("Health:    ");
                    characterSelectedAttackDamage.setText("Attack Damage:    ");
                    characterSelectedNumberOfMoves.setText("Max number of moves:    ");
                    break;
            }
        });

        //Load Heroes
        try{
            Game.loadHeroes(pathToHeroes);
        }catch(IOException e){
            System.out.print(e.getMessage());
        }

        //start button
        ArrayList<Hero> availableHeroes = Game.availableHeroes;
        Button startButton = new Button("Start");
        startButton.setOnAction(e -> {
            if(characterSelection.valueProperty().getValue() != null){
                for (int i = 0; i < availableHeroes.size(); i++) {
                    Hero hero = availableHeroes.get(i);
                    if (hero==null) {
                        // Remove the current element from the iterator and the list.
                        availableHeroes.remove(i);
                    }
                    if(hero.getName().equals(characterSelection.valueProperty().getValue())){
                        Game.startGame(hero);
                        current_hero=hero;
                        primaryStage.setScene(gameScreen());
                        primaryStage.setFullScreen(true);
                    }
                }
				System.out.println("Game started");
				System.out.println("Hero selected: " + Game.heroes.get(0).getName());
			}
        });

        //adding character selection to pane
        stackPane.getChildren().add(characterSelection);
        characterSelection.setTranslateY(-100);
        stackPane.setAlignment(characterSelection, Pos.BOTTOM_CENTER);
        stackPane.getChildren().add(startButton);
        startButton.setTranslateY(-50);
        stackPane.setAlignment(startButton, Pos.BOTTOM_CENTER);

        // initializing scene
        Scene startScreen = new Scene(stackPane, 900, 800);
        primaryStage.setScene(startScreen);

        return startScreen;
    }

    public static Scene gameScreen(){
        BorderPane border = new BorderPane();
        border.setLeft(HUD.hudHero());
        border.setCenter(map());
        border.setRight(hudBasic());
        Scene scene = new Scene(border, 1000, 1000);
        updatemap();
        return scene;
    }

    public static Node map(){
        GridPane grid = new GridPane();
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15 ; j++) {
                StackPane cell = new StackPane();
                grid.add(cell, i, j);
                cells[14-j][i]= cell;
            }
        }
        return grid;
    }

    public static Node hudBasic(){
        vbox = new VBox();
        vbox.setSpacing(0);
        Button button1 = new Button("End Turn");
        Button button2 = new Button("Attack");
        Button button3 = new Button("Cure");
        Button button4 = new Button("Move");
        Button button5 = new Button("Special");
        Button button6 = new Button("Heroes");
        button1.setMinSize(100, 100);
        button2.setMinSize(100, 100);
        button3.setMinSize(100, 100);
        button4.setMinSize(100, 100);
        button5.setMinSize(100, 100);
        button6.setMinSize(100, 100);

        vbox.getChildren().addAll(button1, button2, button3, button4, button5, button6);

        button1.setOnAction(e -> {
            System.out.println("Button 1 pressed");
            Buttons.endTurnButton();
			updatemap();
        });
        button2.setOnAction(e -> {
            System.out.println("Button 2 pressed");
        });
        button3.setOnAction(e -> {
            System.out.println("Button 3 pressed");
        });
        button4.setOnAction(e -> {
            System.out.println("Button 4 pressed");
			vbox.getChildren().clear();
			vbox.getChildren().add(moves());
        });
        button5.setOnAction(e -> {
            System.out.println("Button 5 pressed");
        });
        button6.setOnAction(e -> {
            vbox.getChildren().clear();
            vbox.getChildren().add(heroes());
        });


        return vbox;
    }

    public static Node heroes(){
        combobox = new ComboBox();
        for(Hero hero : Game.heroes) {
            combobox.getItems().add(hero.getName());
        }
        combobox.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event){

                for(Hero hero : Game.heroes) {
                    if(hero.getName().equals(combobox.valueProperty().get())) current_hero= hero;
                }
                vbox.getChildren().clear();;
                vbox.getChildren().add(hudBasic());
            }
        });


        return combobox;
    }

    public static Scene endScreen(boolean isWinner){
        Label finalText = new Label();
        if(isWinner){
            finalText.setText("You Won!");
        }else{
            finalText.setText("You Lost!");
        }
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(finalText);
        Scene endScreen = new Scene(stackPane, 900, 800);
        return endScreen;
    }
    public static void updatemap(){
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15 ; j++) {
                emptyImg = new ImageView(new Image("gui/data/emptycell.png"));
                if(Game.map[i][j].isVisible()){
                    if(Game.map[i][j] instanceof CharacterCell && ((CharacterCell) Game.map[i][j]).getCharacter() instanceof Zombie ){
                        zombieImg = new ImageView(new Image("gui/data/zombie.png"));
                        cells[i][j].getChildren().add(zombieImg);
                    }
                    if(Game.map[i][j] instanceof CollectibleCell ){
                        CollectibleImg = new ImageView(new Image("gui/data/collectible.png"));
                        cells[i][j].getChildren().add(CollectibleImg);
                    }
                    if(Game.map[i][j] instanceof TrapCell ||(Game.map[i][j] instanceof CharacterCell && ((CharacterCell) Game.map[i][j]).getCharacter()==null) ){
                        emptyImg = new ImageView(new Image("gui/data/emptycell.png"));
                        cells[i][j].getChildren().add(emptyImg);
                    }
                    if(Game.map[i][j] instanceof CharacterCell && ((CharacterCell) Game.map[i][j]).getCharacter() instanceof Hero ){
                        heroImg = new ImageView(new Image("gui/data/hero.png"));
                        cells[i][j].getChildren().add(heroImg);
                    }
                }else{
                    emptyImg = new ImageView(new Image("gui/data/emptycell.png"));
                    cells[i][j].getChildren().add(emptyImg);
                }

            }
        }
    }
    	public static Node moves(){
		VBox directions = new VBox();
		Button up = new Button("Up");
		Button down = new Button("Down");
		Button left = new Button("Left");
		Button right = new Button("Right");
		up.setMinSize(100, 100);
		down.setMinSize(100, 100);
		left.setMinSize(100, 100);
		right.setMinSize(100, 100);
		up.setOnAction(e -> {
			System.out.println("Button 2 pressed");
			Direction d = Direction.UP;
			Buttons.moveButton(current_hero, d);
			updatemap();
			vbox.getChildren().clear();
			vbox.getChildren().add(hudBasic());
		});
		down.setOnAction(e -> {
			System.out.println("Button 2 pressed");
			Direction d = Direction.DOWN;
			Buttons.moveButton(current_hero, d);
			updatemap();
			vbox.getChildren().clear();
			vbox.getChildren().add(hudBasic());
		});
		left.setOnAction(e -> {
			System.out.println("Button 2 pressed");
			Direction d = Direction.LEFT;
			Buttons.moveButton(current_hero, d);
			updatemap();
			vbox.getChildren().clear();
			vbox.getChildren().add(hudBasic());
		});
		right.setOnAction(e -> {
			System.out.println(current_hero.getName());
			Direction d = Direction.RIGHT;
			Buttons.moveButton(current_hero, d);
			updatemap();
			vbox.getChildren().clear();
			vbox.getChildren().add(hudBasic());
		});
		directions.getChildren().addAll(up,down,left,right);
		return directions;
	}

}
