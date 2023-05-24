package javaFX;

// importing javaFX related classes


import engine.Game;
import javafx.scene.control.Button;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
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

// importing character classes
import model.characters.Character;
import model.characters.Hero;
import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Medic;
import model.characters.Zombie;

//more imports 


public class GameView {
	public static Scene startScreen() {
		// main pane
		StackPane stackPane = new StackPane();

		//start button
		Button startButton = new Button("Start");
		startButton.setOnAction(e -> {
			System.out.println("Start button pressed");
			
		});
		stackPane.getChildren().add(startButton);
		startButton.setTranslateY(-50);
		stackPane.setAlignment(startButton, Pos.BOTTOM_CENTER);

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
	
		//Image initialization??

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

		//adding character selection to pane
		stackPane.getChildren().add(characterSelection);
		characterSelection.setTranslateY(-100);
		stackPane.setAlignment(characterSelection, Pos.BOTTOM_CENTER);

		// initializing scene
		Scene startScreen = new Scene(stackPane, 800, 600);

		return startScreen;
	}
	public static Scene game(){
		BorderPane border = new BorderPane();
        border.setCenter(map());
        border.setRight(hud_basic());
        Scene scene = new Scene(border, 900, 800);
        
		return scene;
		
	}
	public static Node map(){
		GridPane grid = new GridPane();
		for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15 ; j++) {
                Rectangle rect = new Rectangle(50, 50);
                rect.setFill(Color.WHITE);
                rect.setStroke(Color.BLACK);
                grid.add(rect, i, j);
            }
        }
		return grid;
	}
	public static Node hud_basic(){
		VBox vbox = new VBox();
		vbox.setSpacing(0);
        //image
        Image img = new Image("Sprite-0002.png");
        ImageView imgView = new ImageView(img);
        imgView.setFitHeight(100);
        imgView.setFitWidth(100);
        Button button1 = new Button("");
        button1.setGraphic(imgView);
        Button button2 = new Button("Attack");
        Button button3 = new Button("Cure");
        Button button4 = new Button("Move");
        Button button5 = new Button("Special");
        button1.setMinSize(100, 100);
        button2.setMinSize(100, 100);
        button3.setMinSize(100, 100);
        button4.setMinSize(100, 100);
        button5.setMinSize(100, 100);
        
        ComboBox comboBox = new ComboBox();
        for(Hero hero : Game.heroes) {
            comboBox.getItems().add(hero.getName());
        }

        vbox.getChildren().addAll(button1, button2, button3, button4, button5);
        
        button1.setOnAction(e -> {
            System.out.println("Button 1 pressed");
        });
        button2.setOnAction(e -> {
        	
        });
        button3.setOnAction(e -> {
            System.out.println("Button 3 pressed");
        });
        button4.setOnAction(e -> {
            System.out.println("Button 4 pressed");
        });
        button5.setOnAction(e -> {
            System.out.println("Button 5 pressed");
        });
        return vbox;
		
	}
	
}
