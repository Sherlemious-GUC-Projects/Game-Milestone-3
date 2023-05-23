package javaFX;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javaFX.GameView;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		Scene startScreen = GameView.startScreen();
		primaryStage.setScene(startScreen);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
