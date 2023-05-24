package javaFX;

// importing javaFX related classes
import javafx.scene.control.Button;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

// importing character classes
import model.characters.Character;
import model.characters.Hero;
import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Medic;
import model.characters.Zombie;



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

}
