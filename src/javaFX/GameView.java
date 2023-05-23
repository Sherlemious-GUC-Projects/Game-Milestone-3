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

		// character selection
		String characterSelected = "NA";
		ComboBox characterSelection = new ComboBox();
		characterSelection.getItems().addAll(
				"Explorer",
				"Fighter",
				"Medic"
		);
		characterSelection.setOnAction(e -> {
			System.out.println("Character selected: " + characterSelection.getValue());
		});
		characterSelection.valueProperty().addListener((observable, oldValue, newValue) -> {
			characterSelected = (String) newValue;
			System.out.println("Character selected: " + characterSelected);
		});
		stackPane.getChildren().add(characterSelection);
		characterSelection.setTranslateY(-100);
		stackPane.setAlignment(characterSelection, Pos.BOTTOM_CENTER);

//		 character selection description
//		if (characterSelection.getValue() == null) {
//			characterSelection.setValue("NA");
//		}
//		Label characterSelectionDescription = new Label();
//		characterSelectionDescription.textProperty().bind(characterSelection.valueProperty());
//		stackPane.getChildren().add(characterSelectionDescription);
//		characterSelectionDescription.setTranslateY(-200);
//		stackPane.setAlignment(characterSelectionDescription, Pos.BOTTOM_CENTER);




		// initializing scene
		Scene startScreen = new Scene(stackPane, 800, 600);

		return startScreen;
	}

}
