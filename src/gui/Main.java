package gui;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import gui.GameView;
import engine.Game;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		Scene startScreen = GameView.startScreen(primaryStage);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
