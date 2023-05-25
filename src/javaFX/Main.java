package javaFX;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javaFX.GameView;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		Scene startScreen = GameView.startScreen();
		Scene gameScreen = GameView.game();
		Scene endScreen = GameView.endScreen(true);
		primaryStage.setScene(endScreen);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
