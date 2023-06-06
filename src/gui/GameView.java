package gui;

// importing gui related classes
import javafx.scene.control.Button;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.characters.Direction;
// importing character classes
import model.characters.Hero;
import model.characters.Medic;
import model.characters.Zombie;
import model.collectibles.Vaccine;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;
// importing game related classes
import engine.Game;


// importing world related classes
//more imports
import java.io.IOException;
import java.util.ArrayList;



public class GameView {
    public static Hero current_hero;
    public static Zombie current_zombie;
    static String pathToHeroes = System.getProperty("user.dir") + "/gui/data/Heros.csv";
    public static StackPane[][] cells = new StackPane[15][15];

    // Setting up images
    public static ImageView heroImg = new ImageView(new Image("gui/data/Ellie.png"));
    public static ImageView zombieImg = new ImageView(new Image("gui/data/zombie.png"));
    public static ImageView CollectibleImg = new ImageView(new Image("gui/data/collectible.png"));
    public static ImageView emptyImg = new ImageView(new Image("gui/data/emptyCell.png"));
    public static ImageView vaccineImg = new ImageView(new Image("gui/data/old/vaccine.png"));
    public static ImageView supplyImg = new ImageView(new Image("gui/data/old/supply.png"));
    public static VBox vbox;
    public static ComboBox combobox;
    public static BorderPane border;
    
    public static Scene startScreen(Stage primaryStage) {
        // main pane
        StackPane stackPane = new StackPane();

        //Labels initialization
        Label gameTitle = new Label("The Last of Us");
        gameTitle.setFont(new Font("Arial", 100));
        gameTitle.setAlignment(Pos.TOP_CENTER);
        gameTitle.setMinSize(200, 200);
        gameTitle.setStyle("-fx-text-fill: white;");
        Label characterSelectedName = new Label("Character selected:    ");
        Label characterSelectedType = new Label("Type:    ");
        Label characterSelectedHealth = new Label("Health:    ");
        Label characterSelectedAttackDamage = new Label("Attack Damage:    ");
        Label characterSelectedNumberOfMoves = new Label("Max number of moves:    ");

        // Start Screen wallpaper
        Image startScreenWallpaper = new Image("gui/data/load1.jpg");
        ImageView startScreenWallpaperView = new ImageView(startScreenWallpaper);
        startScreenWallpaperView.setFitHeight(1080);
        startScreenWallpaperView.setFitWidth(1920);
        stackPane.getChildren().add(startScreenWallpaperView);

        // Add solid color text box for the text to be more visible
//        Label textBox = new Label();
//        textBox.setPrefSize(300, 150);
//        textBox.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
//        gameTitle.setTranslateY(-350);
//        stackPane.getChildren().add(textBox);


        //adding labels to pane
        stackPane.getChildren().add(gameTitle);
        stackPane.setAlignment(gameTitle, Pos.TOP_CENTER);
        gameTitle.setTranslateY(200);
        stackPane.getChildren().add(characterSelectedName);
        // Set font color to white
        characterSelectedName.setStyle("-fx-text-fill: #b9b9b9;");
        characterSelectedName.setFont(new Font("Comic Sans", 16));
        characterSelectedName.setTranslateY(-320);
        stackPane.setAlignment(characterSelectedName, Pos.BOTTOM_CENTER);

        stackPane.getChildren().add(characterSelectedType);
        // Set font color to white
        characterSelectedType.setStyle("-fx-text-fill: #b9b9b9;");
        characterSelectedType.setFont(new Font("Comic Sans", 16));
        characterSelectedType.setTranslateY(-290);
        stackPane.setAlignment(characterSelectedType, Pos.BOTTOM_CENTER);

        stackPane.getChildren().add(characterSelectedHealth);
        // Set font color to white
        characterSelectedHealth.setStyle("-fx-text-fill: #b9b9b9;");
        characterSelectedHealth.setFont(new Font("Comic Sans", 16));
        characterSelectedHealth.setTranslateY(-260);
        stackPane.setAlignment(characterSelectedHealth, Pos.BOTTOM_CENTER);

        stackPane.getChildren().add(characterSelectedAttackDamage);
        // Set font color to white
        characterSelectedAttackDamage.setStyle("-fx-text-fill: #b9b9b9;");
        characterSelectedAttackDamage.setFont(new Font("Comic Sans", 16));
        characterSelectedAttackDamage.setTranslateY(-230);
        stackPane.setAlignment(characterSelectedAttackDamage, Pos.BOTTOM_CENTER);

        stackPane.getChildren().add(characterSelectedNumberOfMoves);
        // Set font color to white
        characterSelectedNumberOfMoves.setStyle("-fx-text-fill: #b9b9b9;");
        characterSelectedNumberOfMoves.setFont(new Font("Comic Sans", 16));
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
                        primaryStage.setScene(gameScreen(primaryStage));
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

		//set ai button
		Button setAIButton = new Button("Enable AI");
		setAIButton.setOnAction(e -> {
			Game.isAi = true;
			System.out.println("AI enabled");
		});
		stackPane.getChildren().add(setAIButton);
		setAIButton.setTranslateY(-150);
		stackPane.setAlignment(setAIButton, Pos.BOTTOM_CENTER);


        // initializing scene
        Scene startScreen = new Scene(stackPane, 900, 800);
        primaryStage.setScene(startScreen);

        return startScreen;
    }

    public static Scene gameScreen(Stage primaryStage){
        border = new BorderPane();
        border.setLeft(HUD.hudHero);
        border.setCenter(map());
        border.setRight(hudBasic(primaryStage));
        Scene scene = new Scene(border, 1000, 1000);
        updatemap();

        EventHandler<KeyEvent> keyListener = event -> {
            System.out.println("Key pressed: " + event.getCode());
            if(event.getCode() == KeyCode.W || event.getCode() == KeyCode.S ||
                    event.getCode() == KeyCode.D || event.getCode() == KeyCode.A) {
                Direction d;
                if (event.getCode() == KeyCode.W) {
                    System.out.println("Up key pressed");
                    d = Direction.UP;
                } else if (event.getCode() == KeyCode.S) {
                    d = Direction.DOWN;
                } else if (event.getCode() == KeyCode.D) {
                    d = Direction.RIGHT;
                } else {
                    d = Direction.LEFT;
                }
                Buttons.moveButton(current_hero, d, primaryStage);
                updatemap();
            }
            event.consume();
        };

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, keyListener);
        scene.addEventHandler(KeyEvent.KEY_PRESSED, keyListener);
        return scene;
    }

    public static Node map(){
        GridPane grid = new GridPane();
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15 ; j++) {
                StackPane cell = new StackPane();
                grid.add(cell, i, j);
                cells[14-j][i]= cell;
                
               cell.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        StackPane temp = ((StackPane)mouseEvent.getSource());
                        int i = 14-GridPane.getRowIndex(temp);
                        int j = GridPane.getColumnIndex(temp);
                        if(Game.map[i][j] instanceof CharacterCell && ((CharacterCell) Game.map[i][j]).getCharacter() instanceof Zombie ){
                        	current_zombie = (Zombie) ((CharacterCell) Game.map[i][j]).getCharacter();
                        	System.out.print(current_zombie.getName());
                        }
                        if(Game.map[i][j] instanceof CharacterCell && ((CharacterCell) Game.map[i][j]).getCharacter() instanceof Hero ){
                        	current_hero = (Hero) ((CharacterCell) Game.map[i][j]).getCharacter();
                        	updatemap();
                        	System.out.print(current_hero.getName());
                        }
                    }
                });
            }
        }
        return grid;
    }

    public static Node hudBasic(Stage primaryStage){
        vbox = new VBox();
        vbox.setSpacing(0);
        Button endTurnButtonBox = new Button("End Turn");
        Button attackButtonBox = new Button("Attack");
        Button cureButtonBox = new Button("Cure");
        Button button5 = new Button("Special");
        Button button6 = new Button("Heroes");
        Button button7 = new Button("AI");
        endTurnButtonBox.setMinSize(100, 100);
        attackButtonBox.setMinSize(100, 100);
        cureButtonBox.setMinSize(100, 100);
        button5.setMinSize(100, 100);
        button6.setMinSize(100, 100);
		button7.setMinSize(100, 100);

        vbox.getChildren().addAll(endTurnButtonBox, attackButtonBox, cureButtonBox, button5, button6);
		if (Game.isAi == true) {
            vbox.getChildren().add(button7);
		}

        endTurnButtonBox.setOnAction(e -> {
            System.out.println("Button 1 pressed");
            Buttons.endTurnButton(primaryStage);
			updatemap();
        });
        attackButtonBox.setOnAction(e -> {
            System.out.println("Button 2 pressed");
		vbox.getChildren().clear();
		vbox.getChildren().add(HUD.hudAttack(primaryStage));
		
        });
        cureButtonBox.setOnAction(e -> {
            System.out.println("Button 3 pressed");
            vbox.getChildren().clear();
    		vbox.getChildren().add(HUD.hudCure(primaryStage));
        });
        button5.setOnAction(e -> {
            System.out.println("Button 5 pressed");
            if(current_hero instanceof Medic){
            	vbox.getChildren().clear();
    			vbox.getChildren().add(HUD.hudSpecial(current_hero, primaryStage));
            }
            else{
            	Buttons.specialButtonFE(primaryStage);
            }
        });
        button6.setOnAction(e -> {
            vbox.getChildren().clear();
            vbox.getChildren().add(heroes(primaryStage));
        });
		button7.setOnAction(e -> {
			System.out.println("Button 7 pressed");
			Buttons.aiButton(primaryStage);
		});


       
        border.setBottom(Buttons.alert);

        return vbox;
    }

    public static Node heroes(Stage primaryStage){
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
                vbox.getChildren().add(hudBasic(primaryStage));
                updatemap();
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
                // Remove all images from the cell
                cells[i][j].getChildren().clear();
                emptyImg = new ImageView(new Image("gui/data/emptyCell.png"));
                cells[i][j].getChildren().add(emptyImg);
                if(Game.map[i][j].isVisible()){
                    if(Game.map[i][j] instanceof CharacterCell && ((CharacterCell) Game.map[i][j]).getCharacter() instanceof Zombie ){
                        zombieImg = new ImageView(new Image("gui/data/zombie.png"));
                        cells[i][j].getChildren().add(zombieImg);
                    }
                    if(Game.map[i][j] instanceof CollectibleCell ){
                        if(((CollectibleCell) Game.map[i][j]).getCollectible() instanceof Vaccine){
                            vaccineImg = new ImageView(new Image("gui/data/old/vaccine.png"));
                            cells[i][j].getChildren().add(vaccineImg);
                        }
                        else{
                            supplyImg = new ImageView(new Image("gui/data/old/supply.png"));
                            cells[i][j].getChildren().add(supplyImg);
                        }
                    }
                    if(Game.map[i][j] instanceof CharacterCell && ((CharacterCell) Game.map[i][j]).getCharacter() instanceof Hero ) {
                        heroImg = new ImageView(new Image("gui/data/Ellie.png"));
                        cells[i][j].getChildren().add(heroImg);
                    }

                }
                else {
                    emptyImg = new ImageView(new Image("gui/data/invisibleCell.png"));
                    cells[i][j].getChildren().add(emptyImg);
                }
            }
        }
        border.setLeft(HUD.hudHero());
    }

}
